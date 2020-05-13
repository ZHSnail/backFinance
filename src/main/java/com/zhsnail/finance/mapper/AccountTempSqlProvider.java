package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountTemp;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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
        
        if (record.getDirection() != null) {
            sql.VALUES("direction", "#{direction,jdbcType=VARCHAR}");
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
        
        if (record.getDirection() != null) {
            sql.SET("direction = #{direction,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<AccountTemp>> map ){
        List<AccountTemp> payDetails = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO VCM_ACCOUNT_TEMP ");
        sb.append("(id, voucher_id,account_id,debit_amt,credit_amt,direction) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].voucherId}, " +
                "#'{'list[{0}].accountId},#'{'list[{0}].debitAmt},#'{'list[{0}].creditAmt},#'{'list[{0}].direction})");
        for (int i = 0; i < payDetails.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < payDetails.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}