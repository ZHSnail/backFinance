package com.zhsnail.finance.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ThreadLocalVariables {

    private static ThreadLocal<ConcurrentHashMap> contextThreadLocal = new ThreadLocal<ConcurrentHashMap>(){
        @Override
        protected ConcurrentHashMap initialValue() {
            return new ConcurrentHashMap();
        }
    };

    public static ConcurrentMap getContextThreadLocal() {
        if(contextThreadLocal.get()==null){
            contextThreadLocal.set(new ConcurrentHashMap());
        }
        return contextThreadLocal.get();
    }

    public static void remove(){
        contextThreadLocal.remove();
    }

}

