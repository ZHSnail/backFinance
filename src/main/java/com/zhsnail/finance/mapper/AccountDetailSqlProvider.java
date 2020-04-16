package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountDetail;
import org.apache.ibatis.jdbc.SQL;

public class AccountDetailSqlProvider {

    public String insertSelective(AccountDetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("LEM_ACCOUNT_DETAIL");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAmount() != null) {
            sql.VALUES("debit_amount", "#{debitAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditAmount() != null) {
            sql.VALUES("credit_amount", "#{creditAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountPeriod() != null) {
            sql.VALUES("account_period", "#{accountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getVoucherId() != null) {
            sql.VALUES("voucher_id", "#{voucherId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("account_id", "#{accountId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AccountDetail record) {
        SQL sql = new SQL();
        sql.UPDATE("LEM_ACCOUNT_DETAIL");
        
        if (record.getDebitAmount() != null) {
            sql.SET("debit_amount = #{debitAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditAmount() != null) {
            sql.SET("credit_amount = #{creditAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountPeriod() != null) {
            sql.SET("account_period = #{accountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getVoucherId() != null) {
            sql.SET("voucher_id = #{voucherId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{accountId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}