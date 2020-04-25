package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;

public class VoucherVo extends PageEntity {
    //凭证号
    private Integer code;

    private String id;
    //业务单id
    private String bizId;
    //模块名
    private String module;
    //过账状态
    private String postingStatus;
    //状态
    private String status;
    //制单人
    private String originator;
    //审核人
    private String auditer;
    //过账人
    private String keeper;
    //过账日期
    private Date postingDate;
    //业务类型
    private String bizType;
    //借方总金额
    private BigDecimal debitTotal;
    //会计期间
    private String accountPeriod;
    //记账日期、业务日期
    private Date bizDate;
    //交易类型
    private String dealType;
    //贷方总金额
    private BigDecimal creditTotal;
    //备注
    private String memo;

}
