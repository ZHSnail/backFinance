package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.AccountTemp;
import com.zhsnail.finance.entity.PageEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VoucherVo extends PageEntity {
    //凭证号
    private String code;

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
    //制单人名称
    private String originatorName;
    //审核人
    private String auditer;
    //审核人名称
    private String auditerName;
    //过账人
    private String keeper;
    //过账人名称
    private String keeperName;
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
    //源单号
    private String bizCode;
    //会计科目分录
    private List<AccountTemp> accountTempList;
    //业务类型名称
    private String bizName;
    //交易类型名称
    private String dealName;
    //勾对状态
    private String tickState;
    //勾对日期
    private Date tickDate;

    private List<String> ids;

    //会计科目分录
    private List<AccountTempVo> accountTempVoList;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<AccountTempVo> getAccountTempVoList() {
        return accountTempVoList;
    }

    public void setAccountTempVoList(List<AccountTempVo> accountTempVoList) {
        this.accountTempVoList = accountTempVoList;
    }

    public String getTickState() {
        return tickState;
    }

    public void setTickState(String tickState) {
        this.tickState = tickState;
    }

    public Date getTickDate() {
        return tickDate;
    }

    public void setTickDate(Date tickDate) {
        this.tickDate = tickDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public List<AccountTemp> getAccountTempList() {
        return accountTempList;
    }

    public void setAccountTempList(List<AccountTemp> accountTempList) {
        this.accountTempList = accountTempList;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }

    public String getAuditerName() {
        return auditerName;
    }

    public void setAuditerName(String auditerName) {
        this.auditerName = auditerName;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPostingStatus() {
        return postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        this.postingStatus = postingStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public BigDecimal getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(BigDecimal debitTotal) {
        this.debitTotal = debitTotal;
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public BigDecimal getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(BigDecimal creditTotal) {
        this.creditTotal = creditTotal;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
