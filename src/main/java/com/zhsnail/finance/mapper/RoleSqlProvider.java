package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Role;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class RoleSqlProvider {

    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_ROLE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            sql.VALUES("role_name", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_ROLE");
        
        if (record.getRoleName() != null) {
            sql.SET("role_name = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectByIds(Map params){
        SQL sql = new SQL();

        return sql.toString();
    }
}