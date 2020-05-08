package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FeeKind;
import com.zhsnail.finance.vo.FeeKindVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class FeeKindSqlProvider {

    public String insertSelective(FeeKind record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_FEE_KIND");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeMold() != null) {
            sql.VALUES("time_mold", "#{timeMold,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeMethod() != null) {
            sql.VALUES("fee_method", "#{feeMethod,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(FeeKind record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_FEE_KIND");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeMold() != null) {
            sql.SET("time_mold = #{timeMold,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeMethod() != null) {
            sql.SET("fee_method = #{feeMethod,jdbcType=VARCHAR}");
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

    public String selectAllConditionSql(FeeKindVo feeKindVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("CAM_FEE_KIND");
        if (feeKindVo!=null){
            if (StringUtils.isNotBlank(feeKindVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
            if (StringUtils.isNotBlank(feeKindVo.getState())){
                sql.WHERE("state = #{state,jdbcType=VARCHAR}");
            }
        }
        sql.ORDER_BY("id");
        return sql.toString();
    }
}