package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.RoleUser;
import org.apache.ibatis.jdbc.SQL;

public class RoleUserSqlProvider {

    public String insertSelective(RoleUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_ROLE_USER");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getBizId() != null) {
            sql.VALUES("biz_id", "#{bizId,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RoleUser record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_ROLE_USER");
        
        if (record.getBizId() != null) {
            sql.SET("biz_id = #{bizId,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}