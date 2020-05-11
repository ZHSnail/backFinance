package com.zhsnail.finance.common;

import com.zhsnail.finance.service.StudentInfoService;
import com.zhsnail.finance.util.SpringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Aspect
public class UpdateCacheAspect {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCacheAspect.class);
    //缓存管理器
    @Autowired
    private CacheManager cacheManager;
    /**
     * 拦截注解标志得方法
     */
    @Pointcut("@annotation(com.zhsnail.finance.common.UpdateCache)")
    public void handleListCache(){

    }

    /**
     * 方法执行后进行处理
     * @param joinPoint
     */
    @After("handleListCache()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        //拿到执行得方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //拿到注解
        UpdateCache updateCache = method.getAnnotation(UpdateCache.class);
        //缓存名称
        String name = updateCache.name();
        String beanName = updateCache.beanName();
        String methodName = updateCache.methodName();
        Object bean = SpringUtil.getBean(beanName);
        Method findMethod = ReflectionUtils.findMethod(bean.getClass(), methodName);
        Object o = ReflectionUtils.invokeMethod(findMethod, bean);
        //清空缓存map中对应的值
        Cache cache = cacheManager.getCache(name);
        SimpleKey simpleKey = (SimpleKey) SimpleKeyGenerator.generateKey(new Object[]{});
        cache.evict(cache);
        logger.info("删除name为{}的缓存数据",name);
        ConcurrentMap<Object, Object> concurrentMap = new ConcurrentHashMap<>();
        if (o != null){
            cache.put(name, concurrentMap.put(simpleKey, o));
        }
        logger.info("保存缓存数据{}",o);
    }
}
