package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.vo.AccountBalanceVo;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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

    public String updateByAccIdSelective(AccountBalanceVo record) {
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

        sql.WHERE("account_id = #{accountId,jdbcType=VARCHAR}");

        return sql.toString();
    }

    public String selectAllConditionSql(AccountBalanceVo accountBalanceVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("LEM_ACCOUNT_BALANCE");
        if (accountBalanceVo!=null){
            if (StringUtils.isNotBlank(accountBalanceVo.getAccountId())){
                sql.WHERE("account_id = #{accountId,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(accountBalanceVo.getAccountPeriod())){
                sql.WHERE("account_period = #{accountPeriod,jdbcType=VARCHAR}");
            }
            if (CollectionUtils.isNotEmpty(accountBalanceVo.getAccountIds())){
                StringBuilder sb = new StringBuilder();
                sb.append("account_id in (");
                for (int i = 0;i<accountBalanceVo.getAccountIds().size();i++){
                    sb.append("'");
                    sb.append(accountBalanceVo.getAccountIds().get(i));
                    sb.append("'");
                    if (i != accountBalanceVo.getAccountIds().size()-1){
                        sb.append(",");
                    }
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }
        }
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<AccountBalance>> map ){
        List<AccountBalance> accountBalances = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO LEM_ACCOUNT_BALANCE ");
        sb.append("(id, debit_stayear_amt,credit_stayear_amt,credit_staperiod_amt,debit_staperiod_amt," +
                "credit_endperiod_amt,debit_endperiod_amt,credit_currperiod_amt,debit_currperiod_amt," +
                "credit_accumyear_amt,debit_accumyear_amt,account_period,account_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].id},#'{'list[{0}].debitStayearAmt}, #'{'list[{0}].creditStayearAmt}," +
                "#'{'list[{0}].creditStaperiodAmt},#'{'list[{0}].debitStaperiodAmt}," +
                "#'{'list[{0}].creditEndperiodAmt},#'{'list[{0}].debitEndperiodAmt}," +
                "#'{'list[{0}].creditCurrperiodAmt},#'{'list[{0}].debitCurrperiodAmt}," +
                        "#'{'list[{0}].creditAccumyearAmt},#'{'list[{0}].debitAccumyearAmt},#'{'list[{0}].accountPeriod}," +
                        "#'{'list[{0}].accountId})");
        for (int i = 0; i < accountBalances.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < accountBalances.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String batchUpdateSql(Map<String, List<AccountBalanceVo>> map ){
        List<AccountBalanceVo> accountBalanceVos = map.get("list");
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<accountBalanceVos.size();i++){
            AccountBalanceVo accountBalanceVo = accountBalanceVos.get(i);
            sb.append("UPDATE LEM_ACCOUNT_BALANCE SET ");
            if (accountBalanceVo.getDebitStayearAmt()!=null){
                sb.append("debit_stayear_amt = "+accountBalanceVo.getDebitStayearAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getCreditStayearAmt() != null) {
                sb.append("credit_stayear_amt = "+accountBalanceVo.getCreditStayearAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getCreditStaperiodAmt() != null) {
                sb.append("credit_staperiod_amt = "+accountBalanceVo.getCreditStaperiodAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getDebitStaperiodAmt() != null) {
                sb.append("debit_staperiod_amt = "+accountBalanceVo.getDebitStaperiodAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getCreditEndperiodAmt() != null) {
                sb.append("credit_endperiod_amt = "+accountBalanceVo.getCreditEndperiodAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getDebitEndperiodAmt() != null) {
                sb.append("debit_endperiod_amt = "+accountBalanceVo.getDebitEndperiodAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getCreditCurrperiodAmt() != null) {
                sb.append("credit_currperiod_amt = "+accountBalanceVo.getCreditCurrperiodAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getDebitCurrperiodAmt() != null) {
                sb.append("debit_currperiod_amt = "+accountBalanceVo.getDebitCurrperiodAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getCreditAccumyearAmt() != null) {
                sb.append("credit_accumyear_amt = "+accountBalanceVo.getCreditAccumyearAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getDebitAccumyearAmt() != null) {
                sb.append("debit_accumyear_amt = "+accountBalanceVo.getDebitAccumyearAmt());
                sb.append(",");
            }
            if (accountBalanceVo.getAccountPeriod() != null) {
                sb.append("account_period = '"+accountBalanceVo.getAccountPeriod()+"'");
                sb.append(",");
            }
            sb.replace(sb.lastIndexOf(","),sb.length(),"");
            sb.append(" WHERE account_id = '"+accountBalanceVo.getAccountId()+"'");
            sb.append(";");
        }
        return sb.toString();
    }
}