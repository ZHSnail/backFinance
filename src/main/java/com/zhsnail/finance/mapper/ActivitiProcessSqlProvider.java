package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ActivitiProcess;
import org.apache.ibatis.jdbc.SQL;

public class ActivitiProcessSqlProvider {

    public String insertSelective(ActivitiProcess record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ACT_RE_PROCDEF");
        
        if (record.getId() != null) {
            sql.VALUES("ID_", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getRev() != null) {
            sql.VALUES("REV_", "#{rev,jdbcType=INTEGER}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("CATEGORY_", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("NAME_", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getKey() != null) {
            sql.VALUES("KEY_", "#{key,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.VALUES("VERSION_", "#{version,jdbcType=INTEGER}");
        }
        
        if (record.getDeploymentId() != null) {
            sql.VALUES("DEPLOYMENT_ID_", "#{deploymentId,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceName() != null) {
            sql.VALUES("RESOURCE_NAME_", "#{resourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getDgrmResourceName() != null) {
            sql.VALUES("DGRM_RESOURCE_NAME_", "#{dgrmResourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("DESCRIPTION_", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getHasStartFormKey() != null) {
            sql.VALUES("HAS_START_FORM_KEY_", "#{hasStartFormKey,jdbcType=TINYINT}");
        }
        
        if (record.getHasGraphicalNotation() != null) {
            sql.VALUES("HAS_GRAPHICAL_NOTATION_", "#{hasGraphicalNotation,jdbcType=TINYINT}");
        }
        
        if (record.getSuspensionState() != null) {
            sql.VALUES("SUSPENSION_STATE_", "#{suspensionState,jdbcType=INTEGER}");
        }
        
        if (record.getTenantId() != null) {
            sql.VALUES("TENANT_ID_", "#{tenantId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ActivitiProcess record) {
        SQL sql = new SQL();
        sql.UPDATE("ACT_RE_PROCDEF");
        
        if (record.getRev() != null) {
            sql.SET("REV_ = #{rev,jdbcType=INTEGER}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("CATEGORY_ = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("NAME_ = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getKey() != null) {
            sql.SET("KEY_ = #{key,jdbcType=VARCHAR}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("VERSION_ = #{version,jdbcType=INTEGER}");
        }
        
        if (record.getDeploymentId() != null) {
            sql.SET("DEPLOYMENT_ID_ = #{deploymentId,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceName() != null) {
            sql.SET("RESOURCE_NAME_ = #{resourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getDgrmResourceName() != null) {
            sql.SET("DGRM_RESOURCE_NAME_ = #{dgrmResourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("DESCRIPTION_ = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getHasStartFormKey() != null) {
            sql.SET("HAS_START_FORM_KEY_ = #{hasStartFormKey,jdbcType=TINYINT}");
        }
        
        if (record.getHasGraphicalNotation() != null) {
            sql.SET("HAS_GRAPHICAL_NOTATION_ = #{hasGraphicalNotation,jdbcType=TINYINT}");
        }
        
        if (record.getSuspensionState() != null) {
            sql.SET("SUSPENSION_STATE_ = #{suspensionState,jdbcType=INTEGER}");
        }
        
        if (record.getTenantId() != null) {
            sql.SET("TENANT_ID_ = #{tenantId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID_ = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}