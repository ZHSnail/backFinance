package com.zhsnail.finance.common;

import com.zhsnail.finance.entity.AssetsRegister;

/**
 * 字典
 */
public interface DICT {
    String SYS_ROLE_NAME = "老师";

    /**
     * 扣减分类--应发项
     */
    String FLOAT_WAGE_SIGN_TYPE_SHOULD_PAID = "SHOULD_PAID";
    /**
     * 扣减分类--扣减项
     */
    String FLOAT_WAGE_SIGN_TYPE_DEDUCT = "DEDUCT";
    /**
     * 扣减分类--单位缴纳
     */
    String FLOAT_WAGE_SIGN_TYPE_UNIT_PAY = "UNIT_PAY";
    /**
     * 计税类型--应税项
     */
    String FLOAT_WAGE_TAX_TYPE_TAX = "TAX";
    /**
     * 计税类型--非税项
     */
    String FLOAT_WAGE_TAX_TYPE_NON_TAX = "NON_TAX";
    /**
     * 计税类型--税前扣减项
     */
    String FLOAT_WAGE_TAX_TYPE_PRE_TAX_DED = "PRE_TAX_DED";
    /**
     * 岗位类别--教师类
     */
    String STATION_INFO_TYPE_TCH = "TCH";
    /**
     * 岗位类别--高级管理类
     */
    String STATION_INFO_TYPE_SEN = "SEN";
    /**
     * 岗位类别--职能管理类
     */
    String STATION_INFO_TYPE_FUNC = "FUNC";
    /**
     * 岗位类别--其他类
     */
    String STATION_INFO_TYPE_OTH = "OTH";
    /**
     * 固定资产采购方式--公开招标
     */
    String ASSETS_PURCHASE_METHOD_OPEN_TENDER = "OPEN_TENDER";
    /**
     * 固定资产采购方式--定点采购
     */
    String ASSETS_PURCHASE_METHOD_FIXED_PURCHASE = "FIXED_PURCHASE";
    /**
     * 固定资产采购方式--院内招标
     */
    String ASSETS_PURCHASE_METHOD_HOSPITAL_TENDER = "HOSPITAL_TENDER";
    /**
     * 固定资产采购方式--协议供货
     */
    String ASSETS_PURCHASE_METHOD_PROTOCOL_SUPPLY = "PROTOCOL_SUPPLY";
    /**
     * 固定资产采购方式--网上竞价
     */
    String ASSETS_PURCHASE_METHOD_ONLINE_AUCTION = "ONLINE_AUCTION";
    /**
     * 固定资产采购方式--网上商城
     */
    String ASSETS_PURCHASE_METHOD_E_SHOP = "E_SHOP";
    /**
     * 固定资产取得方式--采购
     */
    String ASSETS_OBTAIN_METHOD_PURCHASE = "PURCHASE";
    /**
     * 固定资产取得方式--租赁
     */
    String ASSETS_OBTAIN_METHOD_LEASE = "LEASE";
    /**
     * 固定资产取得方式--研制转入
     */
    String ASSETS_OBTAIN_METHOD_DEVELOPMENT= "DEVELOPMENT";
    /**
     * 固定资产取得方式--基建转入
     */
    String ASSETS_OBTAIN_METHOD_INFRASTRUCTURE = "INFRASTRUCTURE";
    /**
     * 固定资产取得方式--接受捐赠
     */
    String ASSETS_OBTAIN_METHOD_DONATIONS = "DONATIONS";
    /**
     * 固定资产取得方式--外部调入
     */
    String ASSETS_OBTAIN_METHOD_EXTERNAL = "EXTERNAL";
    /**
     * 固定资产取得方式--盘盈
     */
    String ASSETS_OBTAIN_METHOD_INVENTORY = "INVENTORY";
    /**
     * 固定资产取得方式--合作建所方投入
     */
    String ASSETS_OBTAIN_METHOD_COOPERATIVE = "COOPERATIVE";
    /**
     * 固定资产取得方式--融资租入
     */
    String ASSETS_OBTAIN_METHOD_FINANCING = "FINANCING";
    /**
     * 固定资产取得方式--其他
     */
    String ASSETS_OBTAIN_METHOD_OTHER = "OTHER";
    /**
     * 固定资产折旧方法--年限平均法(直线法)
     */
    String ASSETS_DEPRE_METHOD_STRLINE = "STRLINE";
    /**
     * 固定资产折旧方法--工作量法
     */
    String ASSETS_DEPRE_METHOD_WORKLOAD = "WORKLOAD";
    /**
     * 固定资产折旧方法--双倍余额递减法
     */
    String ASSETS_DEPRE_METHOD_DOUDECBAL = "DOUDECBAL";
    /**
     * 固定资产折旧方法--年数总和法
     */
    String ASSETS_DEPRE_METHOD_SUMYEAR = "SUMYEAR";
    /**
     * 任务列表名称--草稿
     */
    String TASK_NAME_DRAFT_LIST = "draftList";
    /**
     * 任务列表名称--审核中
     */
    String TASK_NAME_CMT_LIST = "cmtList";
    /**
     * 任务列表名称--已完成
     */
    String TASK_NAME_EXE_LIST = "exeList";
    /**
     * 任务列表名称--正在收款
     */
    String TASK_NAME_FINISH = "finishList";
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
     * 凭证勾对状态--未勾对
     */
    String VOUCHER_TICK_STATE_UNTICK = "UNTICK";
    /**
     * 凭证勾对状态--已勾对
     */
    String VOUCHER_TICK_STATE_TICKED = "TICKED";
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
     * 固定资产登记申请
     */
    String ASSETS_REGISTER_WORK_KEY="assetsRegisterReq";
    /**
     * 固定资产采购申请
     */
    String ASSETS_PURCHASE_WORK_KEY="assetsPurchaseReq";
    /**
     * 固定资产变动申请
     */
    String ASSETS_CHANGE_WORK_KEY="assetsChangeReq";

    /**
     * 固定资产折旧申请
     */
    String ASSETS_DEPRECIATION_WORK_KEY="assetsDepreciationReq";
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
