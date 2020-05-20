package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ScaleSalary;
import org.apache.ibatis.jdbc.SQL;

public class ScaleSalarySqlProvider {

    public String insertSelective(ScaleSalary record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_SCALE_SALARY");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getScaleAmount() != null) {
            sql.VALUES("scale_amount", "#{scaleAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getScaleStage() != null) {
            sql.VALUES("scale_stage", "#{scaleStage,jdbcType=DECIMAL}");
        }
        
        if (record.getScaleGrad() != null) {
            sql.VALUES("scale_grad", "#{scaleGrad,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitAccountId() != null) {
            sql.VALUES("debit_account_id", "#{debitAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAccountId() != null) {
            sql.VALUES("credit_account_id", "#{creditAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ScaleSalary record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_SCALE_SALARY");
        
        if (record.getScaleAmount() != null) {
            sql.SET("scale_amount = #{scaleAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getScaleStage() != null) {
            sql.SET("scale_stage = #{scaleStage,jdbcType=DECIMAL}");
        }
        
        if (record.getScaleGrad() != null) {
            sql.SET("scale_grad = #{scaleGrad,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitAccountId() != null) {
            sql.SET("debit_account_id = #{debitAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAccountId() != null) {
            sql.SET("credit_account_id = #{creditAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}