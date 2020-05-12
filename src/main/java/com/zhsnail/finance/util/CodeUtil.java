package com.zhsnail.finance.util;

import com.zhsnail.finance.service.SysSequenceService;

import java.util.UUID;

public class CodeUtil {
    private static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);

    private static SysSequenceService sysSequenceService = SpringUtil.getBean(SysSequenceService.class);
    /**
     * 获取唯一单号
     * @return
     */
    public static String getCode(String prefixName){
        return sysSequenceService.getSeqDateNo(prefixName);
    }

    /**
     * 获取唯一id
     * @return
     */
    public static String getId(){
        return Long.toString(snowflakeIdWorker.nextId());
    }

    /**
     * 获取凭证号
     */
    public static String getVoucherCode(){
        return sysSequenceService.getVoucherCode();
    }
}
