package com.zhsnail.finance.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * list类型得实时更新缓存 默认不加key
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateCache {

    /**
     * 缓存名
     * @return
     */
    String name();


    /**
     * bean名
     * @return
     */
    String beanName();

    /**
     * 方法名
     * @return
     */
    String methodName();
}
