package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StationSalary;
import org.apache.ibatis.jdbc.SQL;

public class StationSalarySqlProvider {

    public String insertSelective(StationSalary record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_STATION_SALARY");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getStationAmount() != null) {
            sql.VALUES("station_amount", "#{stationAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getStationStage() != null) {
            sql.VALUES("station_stage", "#{stationStage,jdbcType=DECIMAL}");
        }
        
        if (record.getStationGrad() != null) {
            sql.VALUES("station_grad", "#{stationGrad,jdbcType=DECIMAL}");
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

    public String updateByPrimaryKeySelective(StationSalary record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_STATION_SALARY");
        
        if (record.getStationAmount() != null) {
            sql.SET("station_amount = #{stationAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getStationStage() != null) {
            sql.SET("station_stage = #{stationStage,jdbcType=DECIMAL}");
        }
        
        if (record.getStationGrad() != null) {
            sql.SET("station_grad = #{stationGrad,jdbcType=DECIMAL}");
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