package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.PermissionRole;
import org.apache.ibatis.jdbc.SQL;

public class PermissionRoleSqlProvider {

    public String insertSelective(PermissionRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_PERMISSION_ROLE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=VARCHAR}");
        }
        
        if (record.getPermId() != null) {
            sql.VALUES("perm_id", "#{permId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PermissionRole record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_PERMISSION_ROLE");
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=VARCHAR}");
        }
        
        if (record.getPermId() != null) {
            sql.SET("perm_id = #{permId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}