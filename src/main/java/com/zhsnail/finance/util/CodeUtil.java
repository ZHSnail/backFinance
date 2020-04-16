package com.zhsnail.finance.util;

import java.util.UUID;

public class CodeUtil {
    private static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);

    /**
     * 获取唯一单号
     * @return
     */
    public static String getCode(String biz){
        return biz+Long.toString(snowflakeIdWorker.nextId());
    }

    /**
     * 获取唯一id
     * @return
     */
    public static String getId(){
        String id = UUID.randomUUID().toString();
        String uId = id.replaceAll("-", "");
        return uId.replaceAll("[a-zA-Z]","" );
    }
}
