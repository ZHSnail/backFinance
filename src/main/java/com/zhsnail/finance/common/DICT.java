package com.zhsnail.finance.common;

/**
 * 字典
 */
public interface DICT {
    String SYS_ROLE_NAME = "老师";
    /**
     * ture
     */
    String BOOLEAN_STATE_TRUE = "TRUE";
    /**
     * false
     */
    String BOOLEAN_STATE_FALSE = "FALSE";
    /**
     * 申请单状态 -- 退回
     */
    String STATUS_BACK = "BACK";
    /**
     * 申请单状态 -- 非正常中止（标识草稿软删状态）
     */
    String STATUS_DEL = "DEL";
    /**
     * 草稿
     */
    String STATUS_DRAFT = "DRAFT";
    /**
     * 审核中
     */
    String STATUS_CMT = "CMT";
    /**
     * 完成或待付款
     */
    String STATUS_EXE = "EXE";
    /**
     * 已完成
     */
    String STATUS_FINSH = "FINSH";
}
