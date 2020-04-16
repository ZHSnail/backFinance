package com.zhsnail.finance.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @program: wcp
 * @description: 配置类实现AsyncConfigurer接口并重写AsyncConfigurer方法，并返回一个ThreadPoolTaskExecutor
 * @author: Cay.jiang
 * @create: 2018-03-12 17:27
 **/

//声明这是一个配置类
@Configuration
//开启注解：开启异步支持
@EnableAsync
public class TaskExecutorConfigurer implements AsyncConfigurer {
    private static final Logger log = LoggerFactory.getLogger(TaskExecutorConfigurer.class);
    @Bean
    //配置类实现AsyncConfigurer接口并重写AsyncConfigurer方法，并返回一个ThreadPoolTaskExecutor
    //这样我们就得到了一个基于线程池的TaskExecutor
    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //如果池中的实际线程数小于corePoolSize,无论是否其中有空闲的线程，都会给新的任务产生新的线程
        taskExecutor.setCorePoolSize(5);
        //连接池中保留的最大连接数。Default: 15 maxPoolSize
        taskExecutor.setMaxPoolSize(10);
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(25);
        //等待所有线程执行完
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.initialize();
        return taskExecutor;
    }
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new WcpAsyncExceptionHandler();
    }
    /**
     * 自定义异常处理类
     * @author hry
     *
     */
    class WcpAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        //手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            System.out.println("-------------》》》捕获到线程异常信息");
            log.info("Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : obj) {
                log.info("Parameter value - " + param);
            }
        }

    }
}