package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountDetail;
import com.zhsnail.finance.entity.AccountDetail;
import com.zhsnail.finance.vo.AccountDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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

    public String selectAllConditionSql(AccountDetailVo accountDetailVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("LEM_ACCOUNT_DETAIL");
        if (accountDetailVo!=null){
            if (StringUtils.isNotBlank(accountDetailVo.getAccountId())){
                sql.WHERE("account_id = #{accountId,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(accountDetailVo.getAccountPeriod())){
                sql.WHERE("account_period = #{accountPeriod,jdbcType=VARCHAR}");
            }
        }
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<AccountDetail>> map ){
        List<AccountDetail> accountDetailList = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO LEM_ACCOUNT_DETAIL ");
        sb.append("(id, debit_amount,credit_amount,account_period,voucher_id," +
                "account_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].debitAmount}, #'{'list[{0}].creditAmount}" +
                ",#'{'list[{0}].accountPeriod},#'{'list[{0}].voucherId}," +
                "#'{'list[{0}].accountId})");
        for (int i = 0; i < accountDetailList.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < accountDetailList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}