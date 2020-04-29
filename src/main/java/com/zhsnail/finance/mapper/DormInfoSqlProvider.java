package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.DormInfo;
import org.apache.ibatis.jdbc.SQL;

public class DormInfoSqlProvider {

    public String insertSelective(DormInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_DORM_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getDormNumber() != null) {
            sql.VALUES("dorm_number", "#{dormNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getBuildNumber() != null) {
            sql.VALUES("build_number", "#{buildNumber,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(DormInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_DORM_INFO");
        
        if (record.getDormNumber() != null) {
            sql.SET("dorm_number = #{dormNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getBuildNumber() != null) {
            sql.SET("build_number = #{buildNumber,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}