package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SystemParam;
import org.apache.ibatis.jdbc.SQL;

public class SystemParamSqlProvider {

    public String insertSelective(SystemParam record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_SYSTEM_PARAM");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getNowAccountPeriod() != null) {
            sql.VALUES("now_account_period", "#{nowAccountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseCurrency() != null) {
            sql.VALUES("base_currency", "#{baseCurrency,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.VALUES("start_time", "#{startTime,jdbcType=DATE}");
        }
        
        if (record.getUnit() != null) {
            sql.VALUES("unit", "#{unit,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemParam record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_SYSTEM_PARAM");
        
        if (record.getNowAccountPeriod() != null) {
            sql.SET("now_account_period = #{nowAccountPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseCurrency() != null) {
            sql.SET("base_currency = #{baseCurrency,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{startTime,jdbcType=DATE}");
        }
        
        if (record.getUnit() != null) {
            sql.SET("unit = #{unit,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}