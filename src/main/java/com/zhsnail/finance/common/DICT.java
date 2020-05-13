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
     * 借贷方向--借
     */
    String LENDER_ACCOUNT_DIRECTION_DEBIT = "DEBIT";
    /**
     * 借贷方向--贷
     */
    String LENDER_ACCOUNT_DIRECTION_CREDIT = "CREDIT";
    /**
     * 凭证过账状态--未过账
     */
    String VOUCHER_POST_STATUS_UNPOST = "UNPOST";
    /**
     * 凭证交易类型--已过账
     */
    String VOUCHER_POST_STATUS_POSTED = "POSTED";
    /**
     * 凭证交易类型--其他
     */
    String VOUCHER_DEAL_TYPE_OTHER = "OTHER";
    /**
     * 凭证交易类型--现金
     */
    String VOUCHER_DEAL_TYPE_CASH = "CASH";
    /**
     * 凭证交易类型--银行
     */
    String VOUCHER_DEAL_TYPE_BANK = "BANK";
    /**
     * 凭证业务类型--收费付款
     */
    String VOUCHER_BIZ_TYPE_CHARGE_PAY = "CHARGE_PAY";
    /**
     * 凭证业务类型--工资付款
     */
    String VOUCHER_BIZ_TYPE_SALARY_PAY = "SALARY_PAY";
    /**
     * 凭证业务类型--固定资产登记
     */
    String VOUCHER_BIZ_TYPE_ASSETS_REG = "ASSETS_REG";
    /**
     * 凭证业务类型--固定资产折旧
     */
    String VOUCHER_BIZ_TYPE_ASSETS_DEPRECIATED = "ASSETS_DEPRECIATED";
    /**
     * 凭证业务类型--固定资产采购
     */
    String VOUCHER_BIZ_TYPE_ASSETS_PURCHASE = "ASSETS_PURCHASE";
    /**
     * 凭证业务类型--手工凭证
     */
    String VOUCHER_BIZ_TYPE_MANUAL_VOUCHER = "MANUAL_VOUCHER";

     /**
     * 资产类会计科目
     */
    String ACCOUNT_TYPE_ASSETS="ASSETS";
    /**
     * 成本类会计科目
     */
    String ACCOUNT_TYPE_COST="COST";
    /**
     * 费用类会计科目
     */
    String ACCOUNT_TYPE_EXPENSES="EXPENSES";
    /**
     * 负债类会计科目
     */
    String ACCOUNT_TYPE_LIABILITIES="LIABILITIES";
    /**
     * 所有者权益类会计科目
     */
    String ACCOUNT_TYPE_OWNER="OWNER";
    /**
     * 收入类会计科目
     */
    String ACCOUNT_TYPE_INCOME="INCOME";
    /**
     * 未付款
     */
    String PAY_DETAIL_STATUS_UNPAID = "UNPAID";
    /**
     * 已付款
     */
    String PAY_DETAIL_STATUS_PAID = "PAID";
    /**
     * 缴费通知工作流申请
     */
    String PAY_NOTICE_WORK_KEY="payNoticeReq";
    /**
     * 手工凭证工作流申请
     */
    String VOUCHER_MANUAL_WORK_KEY="voucherReq";
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
     * 当前登录的key
     */
    String LOGIN = "LOGIN";
    /**
     * 当前登录是学生
     */
    String LOGIN_STUDENT = "STUDENT";
    /**
     * 当前登录是员工
     */
    String LOGIN_STAFF = "STAFF";
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
