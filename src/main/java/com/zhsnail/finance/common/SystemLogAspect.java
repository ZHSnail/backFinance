package com.zhsnail.finance.common;

import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.util.MongoUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Aspect
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    private static final ThreadLocal<SystemLog> logThreadLocal = new NamedThreadLocal<SystemLog>("ThreadLocal log");
    private static final ThreadLocal<User> currentUserInfo = new NamedThreadLocal<User>("ThreadLocal userInfo");

    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.zhsnail.finance.common.SystemControllerLog)")
    public void controllerAspect() {
    }
    /**
     * 方法规则拦截
     */
    @Pointcut("execution(* com.zhsnail.finance.controller.*.*(..))")
    public void controllerPointerCut() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
//        Date beginTime = new Date();
//        beginTimeThreadLocal.set(beginTime);
        //debug模式下 显式打印开始时间用于调试
//        if (logger.isDebugEnabled()) {
//            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
//                    .format(beginTime), request.getRequestURI());
//        }
        //读取GuserCookie中的用户信息
//        String loginId = GuserCookieUtil.getLoginId(request);
//        UserInfoDTO userInfo = dubboService.userInfoService.getUserInfoByLoginId(loginId).getDataResult();
//        currentUserInfo.set(userInfo);
        User user = new User();
//        user.setId("123");
//        user.setName("张三");
        currentUserInfo.set(user);
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        User userInfo  = currentUserInfo.get();
        //登入login操作 前置通知时用户未校验 所以session中不存在用户信息
        /*if (userInfo == null) {
            String loginId = GuserCookieUtil.getLoginId(request);
            userInfo = dubboService.userInfoService.getUserInfoByLoginId(loginId).getDataResult();
            if (userInfo == null) {
                return;
            }
        }*/
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
        String desc = "";
        String type = "info";                       //日志类型(info:入库,error:错误)
        String remoteAddr = request.getRemoteAddr();//请求的IP
        String requestUri = request.getRequestURI();//请求的Uri
        String method = request.getMethod();        //请求的方法类型(post/get)
        Map<String, String[]> paramsMap = request.getParameterMap(); //请求提交的参数
        try {
            desc = getControllerMethodDescription(request,joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SystemLog log = new SystemLog();
        log.setDesc(desc);
        log.setType(type);
        log.setRemoteAddr(remoteAddr);
        log.setRequestUri(requestUri);
        log.setMethod(method);
        log.setMapToParams(paramsMap);
//        log.setUserName(userInfo.getName());
        log.setUserId(userInfo.getId());
        threadPoolTaskExecutor.execute(new SaveLogThread(log,mongoTemplate));
        logThreadLocal.set(log);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        SystemLog log = logThreadLocal.get();
        if (log != null) {
            log.setType("error");
            log.setException(e.toString());
            new UpdateLogThread(log).start();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     */
    private static String getControllerMethodDescription(HttpServletRequest request, JoinPoint joinPoint) throws IllegalAccessException, InstantiationException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method
                .getAnnotation(SystemControllerLog.class);
        String desc = controllerLog.description();
        List<String> list = descFormat(desc);
        for (String s : list) {
            //根据request的参数名获取到参数值，并对注解中的{}参数进行替换
            String value=request.getParameter(s);
            desc = desc.replace("{"+s+"}", value);
        }
        return desc;
    }

    /**
     * 获取日志信息中的动态参数
     * @param desc
     * @return
     */
    private static List<String> descFormat(String desc){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\{([^\\}]+)\\}");
        Matcher matcher = pattern.matcher(desc);
        while(matcher.find()){
            String t = matcher.group(1);
            list.add(t);
        }
        return list;
    }

    /**
     * 保存日志线程
     *
     */
    private static class SaveLogThread implements Runnable {
        private SystemLog log;
        private MongoTemplate mongoTemplate;

        SaveLogThread(SystemLog log, MongoTemplate mongoTemplate) {
            logger.debug("================="+log.toString());
            this.log = log;
            this.mongoTemplate = mongoTemplate;
        }

        @Override
        public void run() {
//            MongoUtil.insertLog(log);
            /*SystemLog systemLog = mongoTemplate.insert(log);
            logThreadLocal.set(systemLog);*/
        }
    }
    /**
     * 日志更新线程
     *
     * @author lin.r.x
     */
    private static class UpdateLogThread extends Thread {
        private SystemLog log;

        UpdateLogThread(SystemLog log) {
            super(UpdateLogThread.class.getSimpleName());
            this.log = log;
        }

        @Override
        public void run() {
            logger.debug("==============>更新日志");
        }
    }
}
