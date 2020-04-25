package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountTemp;
import org.apache.ibatis.jdbc.SQL;

public class AccountTempSqlProvider {

    public String insertSelective(AccountTemp record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("VCM_ACCOUNT_TEMP");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getVoucherId() != null) {
            sql.VALUES("voucher_id", "#{voucherId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("account_id", "#{accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAmt() != null) {
            sql.VALUES("debit_amt", "#{debitAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditAmt() != null) {
            sql.VALUES("credit_amt", "#{creditAmt,jdbcType=DECIMAL}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AccountTemp record) {
        SQL sql = new SQL();
        sql.UPDATE("VCM_ACCOUNT_TEMP");
        
        if (record.getVoucherId() != null) {
            sql.SET("voucher_id = #{voucherId,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAmt() != null) {
            sql.SET("debit_amt = #{debitAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditAmt() != null) {
            sql.SET("credit_amt = #{creditAmt,jdbcType=DECIMAL}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}