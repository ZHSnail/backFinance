package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_USER");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPsw() != null) {
            sql.VALUES("psw", "#{psw,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentId() != null) {
            sql.VALUES("student_id", "#{studentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStaffId() != null) {
            sql.VALUES("staff_id", "#{staffId,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_USER");
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPsw() != null) {
            sql.SET("psw = #{psw,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{studentId,jdbcType=VARCHAR}");
        }
        
        if (record.getStaffId() != null) {
            sql.SET("staff_id = #{staffId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}