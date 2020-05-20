package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.vo.FloatWageVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class FloatWageSqlProvider {

    public String insertSelective(FloatWage record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_FLOAT_WAGE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getTaxType() != null) {
            sql.VALUES("tax_type", "#{taxType,jdbcType=VARCHAR}");
        }
        
        if (record.getSignType() != null) {
            sql.VALUES("sign_type", "#{signType,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAccountId() != null) {
            sql.VALUES("debit_account_id", "#{debitAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAccountId() != null) {
            sql.VALUES("credit_account_id", "#{creditAccountId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FloatWage record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_FLOAT_WAGE");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getTaxType() != null) {
            sql.SET("tax_type = #{taxType,jdbcType=VARCHAR}");
        }
        
        if (record.getSignType() != null) {
            sql.SET("sign_type = #{signType,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAccountId() != null) {
            sql.SET("debit_account_id = #{debitAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAccountId() != null) {
            sql.SET("credit_account_id = #{creditAccountId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(FloatWageVo floatWageVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("SAM_FLOAT_WAGE");
        if (floatWageVo!=null){
            if (StringUtils.isNotBlank(floatWageVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
            if (StringUtils.isNotBlank(floatWageVo.getState())){
                sql.WHERE("state = #{state,jdbcType=VARCHAR}");
            }
        }
        sql.ORDER_BY("id");
        return sql.toString();
    }
}