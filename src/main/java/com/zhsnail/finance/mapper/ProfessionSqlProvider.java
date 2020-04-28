package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Profession;
import org.apache.ibatis.jdbc.SQL;

public class ProfessionSqlProvider {

    public String insertSelective(Profession record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_PROFESSION");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.VALUES("IS_LEAF", "#{isLeaf,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("PARENT_ID", "#{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("GRADE", "#{grade,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Profession record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_PROFESSION");
        
        if (record.getName() != null) {
            sql.SET("NAME = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.SET("IS_LEAF = #{isLeaf,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("PARENT_ID = #{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("GRADE = #{grade,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}