package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ActivitiDeployment;
import org.apache.ibatis.jdbc.SQL;

public class ActivitiDeploymentSqlProvider {

    public String insertSelective(ActivitiDeployment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ACT_RE_DEPLOYMENT");
        
        if (record.getId() != null) {
            sql.VALUES("ID_", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("NAME_", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("CATEGORY_", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getTenantId() != null) {
            sql.VALUES("TENANT_ID_", "#{tenantId,jdbcType=VARCHAR}");
        }
        
        if (record.getDeployTime() != null) {
            sql.VALUES("DEPLOY_TIME_", "#{deployTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ActivitiDeployment record) {
        SQL sql = new SQL();
        sql.UPDATE("ACT_RE_DEPLOYMENT");
        
        if (record.getName() != null) {
            sql.SET("NAME_ = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("CATEGORY_ = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getTenantId() != null) {
            sql.SET("TENANT_ID_ = #{tenantId,jdbcType=VARCHAR}");
        }
        
        if (record.getDeployTime() != null) {
            sql.SET("DEPLOY_TIME_ = #{deployTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("ID_ = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}