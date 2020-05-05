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
    /**
     * 收费方式----面向宿舍
     */
    String FEE_METHOD_DORM = "DORM";
    /**
     * 收费方式----面向专业
     */
    String FEE_METHOD_MAJOR = "MAJOR";
    /**
     * 收费方式----面向角色
     */
    String FEE_METHOD_ROLE = "ROLE";
    /**
     * 审批意见
     */
    public static final String COMMENT = "comment";
    /**
     * 流程操作
     */
    public static final String ACTION = "action";
    /**
     * 申请
     */
    public static final String APPLY = "apply";
    /**
     * 删除
     */
    public static final String DELETE = "delete";
    /**
     * 退回上一步
     */
    public static final String BACK_UP = "back_up";
    /**
     * 撤销
     */
    public static final String REVOKE = "revoke";
    /**
     * 退回
     */
    public static final String BACK = "back";
    /**
     * 同意
     */
    public static final String APPROVE ="approve";
    /**
     * 同意
     */
    public static final String LAST_APPROVE ="last_approve";
    /**
     * 业务主键
     */
    public static final String BUSINESSKEY = "businessKey";
    /**
     * 工作流主键
     */
    public static final String WORKKEY = "workKey";
    /**
     * 流程节点ID
     */
    public static final String TASK_DEF_KEY = "TASK_DEF_KEY";
    /**
     * 拒绝
     */
    public static final String REFUSE = "refuse";

}
