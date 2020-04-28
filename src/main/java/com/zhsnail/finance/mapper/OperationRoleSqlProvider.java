package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.OperationRole;
import org.apache.ibatis.jdbc.SQL;

public class OperationRoleSqlProvider {

    public String insertSelective(OperationRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_OPERATION_ROLE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getOperationId() != null) {
            sql.VALUES("operation_id", "#{operationId,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(OperationRole record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_OPERATION_ROLE");
        
        if (record.getOperationId() != null) {
            sql.SET("operation_id = #{operationId,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}