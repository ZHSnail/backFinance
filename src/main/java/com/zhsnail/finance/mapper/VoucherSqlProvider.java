package com.zhsnail.finance.mapper;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class VoucherSqlProvider {

    public String insertSelective(Voucher record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("VCM_VOUCHER");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getBizId() != null) {
            sql.VALUES("biz_id", "#{bizId,jdbcType=VARCHAR}");
        }
        
        if (record.getModule() != null) {
            sql.VALUES("module", "#{module,jdbcType=VARCHAR}");
        }
        
        if (record.getPostingStatus() != null) {
            sql.VALUES("posting_status", "#{postingStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("STATUS", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getOriginator() != null) {
            sql.VALUES("originator", "#{originator,jdbcType=VARCHAR}");
        }
        
        if (record.getAuditer() != null) {
            sql.VALUES("auditer", "#{auditer,jdbcType=VARCHAR}");
        }
        
        if (record.getKeeper() != null) {
            sql.VALUES("keeper", "#{keeper,jdbcType=VARCHAR}");
        }
        
        if (record.getPostingDate() != null) {
            sql.VALUES("posting_date", "#{postingDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBizType() != null) {
            sql.VALUES("biz_type", "#{bizType,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitTotal() != null) {
            sql.VALUES("debit_total", "#{debitTotal,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountPeriod() != null) {
            sql.VALUES("account_period", "#{accountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getBizDate() != null) {
            sql.VALUES("biz_date", "#{bizDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDealType() != null) {
            sql.VALUES("deal_type", "#{dealType,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditTotal() != null) {
            sql.VALUES("credit_total", "#{creditTotal,jdbcType=DECIMAL}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getBizCode() != null) {
            sql.VALUES("biz_code", "#{bizCode,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Voucher record) {
        SQL sql = new SQL();
        sql.UPDATE("VCM_VOUCHER");
        
        if (record.getBizId() != null) {
            sql.SET("biz_id = #{bizId,jdbcType=VARCHAR}");
        }
        
        if (record.getModule() != null) {
            sql.SET("module = #{module,jdbcType=VARCHAR}");
        }
        
        if (record.getPostingStatus() != null) {
            sql.SET("posting_status = #{postingStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("STATUS = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getOriginator() != null) {
            sql.SET("originator = #{originator,jdbcType=VARCHAR}");
        }
        
        if (record.getAuditer() != null) {
            sql.SET("auditer = #{auditer,jdbcType=VARCHAR}");
        }
        
        if (record.getKeeper() != null) {
            sql.SET("keeper = #{keeper,jdbcType=VARCHAR}");
        }
        
        if (record.getPostingDate() != null) {
            sql.SET("posting_date = #{postingDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBizType() != null) {
            sql.SET("biz_type = #{bizType,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitTotal() != null) {
            sql.SET("debit_total = #{debitTotal,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountPeriod() != null) {
            sql.SET("account_period = #{accountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getBizDate() != null) {
            sql.SET("biz_date = #{bizDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDealType() != null) {
            sql.SET("deal_type = #{dealType,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditTotal() != null) {
            sql.SET("credit_total = #{creditTotal,jdbcType=DECIMAL}");
        }
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getBizCode() != null) {
            sql.SET("biz_code = #{bizCode,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(VoucherVo voucherVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("VCM_VOUCHER");
        if (voucherVo!=null){
            if (StringUtils.isNotBlank(voucherVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(voucherVo.getAccountPeriod())){
                sql.WHERE("account_period = #{accountPeriod,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '" + DICT.STATUS_FINSH+"' , '" +
                DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE
                +"' )");
        sql.ORDER_BY("code desc");
        return sql.toString();
    }

    public String selectPostVoucherConditionSql(VoucherVo voucherVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("VCM_VOUCHER");
        if (voucherVo!=null){
            if (StringUtils.isNotBlank(voucherVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(voucherVo.getAccountPeriod())){
                sql.WHERE("account_period = #{accountPeriod,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("posting_status = '"+DICT.VOUCHER_POST_STATUS_UNPOST+"'");
        sql.WHERE("status in ('"+DICT.STATUS_EXE+"','"+DICT.STATUS_FINSH+"')");
        sql.ORDER_BY("code desc");
        return sql.toString();
    }
}