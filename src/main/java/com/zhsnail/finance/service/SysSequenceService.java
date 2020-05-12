package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.SysSequence;

public interface SysSequenceService {
    /**
     * 获取增长的序列号
     * @param prefixName 前缀
     * @return
     */
    String getSeqDateNo(String prefixName);

    /**
     * 每天初始化序列号的方法
     */
    void runInit();
    /**
     * 每月初始化凭证
     */
    void runInitVoucherCode();
    /**
     * 保存序列号
     * @param sysSequence 序列
     */
    void saveSysSequence(SysSequence sysSequence);

    /**
     * 获取凭证号
     */
    String getVoucherCode();
}
