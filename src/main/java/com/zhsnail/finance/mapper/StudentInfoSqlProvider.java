package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StudentInfo;
import org.apache.ibatis.jdbc.SQL;

public class StudentInfoSqlProvider {

    public String insertSelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_STUDENT_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStuNo() != null) {
            sql.VALUES("stu_no", "#{stuNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStuClass() != null) {
            sql.VALUES("stu_class", "#{stuClass,jdbcType=VARCHAR}");
        }
        
        if (record.getDormId() != null) {
            sql.VALUES("dorm_id", "#{dormId,jdbcType=VARCHAR}");
        }
        
        if (record.getProfessionId() != null) {
            sql.VALUES("profession_id", "#{professionId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StudentInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_STUDENT_INFO");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getStuNo() != null) {
            sql.SET("stu_no = #{stuNo,jdbcType=VARCHAR}");
        }
        
        if (record.getStuClass() != null) {
            sql.SET("stu_class = #{stuClass,jdbcType=VARCHAR}");
        }
        
        if (record.getDormId() != null) {
            sql.SET("dorm_id = #{dormId,jdbcType=VARCHAR}");
        }
        
        if (record.getProfessionId() != null) {
            sql.SET("profession_id = #{professionId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}