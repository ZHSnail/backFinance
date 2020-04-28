package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StudentInfo;
import org.apache.ibatis.jdbc.SQL;

public class StudentInfoSqlProvider {

    public String insertSelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_STUDENT_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStuNo() != null) {
            sql.VALUES("STU_NO", "#{stuNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStuClass() != null) {
            sql.VALUES("STU_CLASS", "#{stuClass,jdbcType=VARCHAR}");
        }
        
        if (record.getDormId() != null) {
            sql.VALUES("DORM_ID", "#{dormId,jdbcType=VARCHAR}");
        }
        
        if (record.getProfessionId() != null) {
            sql.VALUES("PROFESSION_ID", "#{professionId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_STUDENT_INFO");
        
        if (record.getName() != null) {
            sql.SET("NAME = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStuNo() != null) {
            sql.SET("STU_NO = #{stuNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStuClass() != null) {
            sql.SET("STU_CLASS = #{stuClass,jdbcType=VARCHAR}");
        }
        
        if (record.getDormId() != null) {
            sql.SET("DORM_ID = #{dormId,jdbcType=VARCHAR}");
        }
        
        if (record.getProfessionId() != null) {
            sql.SET("PROFESSION_ID = #{professionId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}