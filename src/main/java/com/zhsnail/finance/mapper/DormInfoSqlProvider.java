package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.DormInfo;
import org.apache.ibatis.jdbc.SQL;

public class DormInfoSqlProvider {

    public String insertSelective(DormInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_DORM_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getDormNumber() != null) {
            sql.VALUES("DORM_NUMBER", "#{dormNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getBuildNumber() != null) {
            sql.VALUES("BUILD_NUMBER", "#{buildNumber,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(DormInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_DORM_INFO");
        
        if (record.getDormNumber() != null) {
            sql.SET("DORM_NUMBER = #{dormNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getBuildNumber() != null) {
            sql.SET("BUILD_NUMBER = #{buildNumber,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}