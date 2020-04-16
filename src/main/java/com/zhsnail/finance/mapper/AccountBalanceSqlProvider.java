package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountBalance;
import org.apache.ibatis.jdbc.SQL;

public class AccountBalanceSqlProvider {

    public String insertSelective(AccountBalance record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("LEM_ACCOUNT_BALANCE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitStayearAmt() != null) {
            sql.VALUES("debit_stayear_amt", "#{debitStayearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditStayearAmt() != null) {
            sql.VALUES("credit_stayear_amt", "#{creditStayearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditStaperiodAmt() != null) {
            sql.VALUES("credit_staperiod_amt", "#{creditStaperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitStaperiodAmt() != null) {
            sql.VALUES("debit_staperiod_amt", "#{debitStaperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditEndperiodAmt() != null) {
            sql.VALUES("credit_endperiod_amt", "#{creditEndperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitEndperiodAmt() != null) {
            sql.VALUES("debit_endperiod_amt", "#{debitEndperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditCurrperiodAmt() != null) {
            sql.VALUES("credit_currperiod_amt", "#{creditCurrperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitCurrperiodAmt() != null) {
            sql.VALUES("debit_currperiod_amt", "#{debitCurrperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditAccumyearAmt() != null) {
            sql.VALUES("credit_accumyear_amt", "#{creditAccumyearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitAccumyearAmt() != null) {
            sql.VALUES("debit_accumyear_amt", "#{debitAccumyearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountPeriod() != null) {
            sql.VALUES("account_period", "#{accountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("account_id", "#{accountId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AccountBalance record) {
        SQL sql = new SQL();
        sql.UPDATE("LEM_ACCOUNT_BALANCE");
        
        if (record.getDebitStayearAmt() != null) {
            sql.SET("debit_stayear_amt = #{debitStayearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditStayearAmt() != null) {
            sql.SET("credit_stayear_amt = #{creditStayearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditStaperiodAmt() != null) {
            sql.SET("credit_staperiod_amt = #{creditStaperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitStaperiodAmt() != null) {
            sql.SET("debit_staperiod_amt = #{debitStaperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditEndperiodAmt() != null) {
            sql.SET("credit_endperiod_amt = #{creditEndperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitEndperiodAmt() != null) {
            sql.SET("debit_endperiod_amt = #{debitEndperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditCurrperiodAmt() != null) {
            sql.SET("credit_currperiod_amt = #{creditCurrperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitCurrperiodAmt() != null) {
            sql.SET("debit_currperiod_amt = #{debitCurrperiodAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCreditAccumyearAmt() != null) {
            sql.SET("credit_accumyear_amt = #{creditAccumyearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitAccumyearAmt() != null) {
            sql.SET("debit_accumyear_amt = #{debitAccumyearAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountPeriod() != null) {
            sql.SET("account_period = #{accountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{accountId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}