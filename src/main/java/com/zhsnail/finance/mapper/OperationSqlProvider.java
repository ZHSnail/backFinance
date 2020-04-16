package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Operation;
import org.apache.ibatis.jdbc.SQL;

public class OperationSqlProvider {

    public String insertSelective(Operation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_OPERATION");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getModule() != null) {
            sql.VALUES("module", "#{module,jdbcType=VARCHAR}");
        }
        
        if (record.getOperationName() != null) {
            sql.VALUES("operation_name", "#{operationName,jdbcType=VARCHAR}");
        }
        
        if (record.getOperationUrl() != null) {
            sql.VALUES("operation_url", "#{operationUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Operation record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_OPERATION");
        
        if (record.getModule() != null) {
            sql.SET("module = #{module,jdbcType=VARCHAR}");
        }
        
        if (record.getOperationName() != null) {
            sql.SET("operation_name = #{operationName,jdbcType=VARCHAR}");
        }
        
        if (record.getOperationUrl() != null) {
            sql.SET("operation_url = #{operationUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}