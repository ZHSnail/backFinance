package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ActivitiModel;
import org.apache.ibatis.jdbc.SQL;

public class ActivitiModelSqlProvider {

    public String insertSelective(ActivitiModel record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ACT_RE_MODEL");
        
        if (record.getId() != null) {
            sql.VALUES("ID_", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getRev() != null) {
            sql.VALUES("REV_", "#{rev,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("NAME_", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getKey() != null) {
            sql.VALUES("KEY_", "#{key,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("CATEGORY_", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME_", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastUpdateTime() != null) {
            sql.VALUES("LAST_UPDATE_TIME_", "#{lastUpdateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersion() != null) {
            sql.VALUES("VERSION_", "#{version,jdbcType=INTEGER}");
        }
        
        if (record.getMetaInfo() != null) {
            sql.VALUES("META_INFO_", "#{metaInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getDeploymentId() != null) {
            sql.VALUES("DEPLOYMENT_ID_", "#{deploymentId,jdbcType=VARCHAR}");
        }
        
        if (record.getEditorSourceValueId() != null) {
            sql.VALUES("EDITOR_SOURCE_VALUE_ID_", "#{editorSourceValueId,jdbcType=VARCHAR}");
        }
        
        if (record.getEditorSourceExtraValueId() != null) {
            sql.VALUES("EDITOR_SOURCE_EXTRA_VALUE_ID_", "#{editorSourceExtraValueId,jdbcType=VARCHAR}");
        }
        
        if (record.getTenantId() != null) {
            sql.VALUES("TENANT_ID_", "#{tenantId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ActivitiModel record) {
        SQL sql = new SQL();
        sql.UPDATE("ACT_RE_MODEL");
        
        if (record.getRev() != null) {
            sql.SET("REV_ = #{rev,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("NAME_ = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getKey() != null) {
            sql.SET("KEY_ = #{key,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("CATEGORY_ = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME_ = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastUpdateTime() != null) {
            sql.SET("LAST_UPDATE_TIME_ = #{lastUpdateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("VERSION_ = #{version,jdbcType=INTEGER}");
        }
        
        if (record.getMetaInfo() != null) {
            sql.SET("META_INFO_ = #{metaInfo,jdbcType=VARCHAR}");
        }
        
        if (record.getDeploymentId() != null) {
            sql.SET("DEPLOYMENT_ID_ = #{deploymentId,jdbcType=VARCHAR}");
        }
        
        if (record.getEditorSourceValueId() != null) {
            sql.SET("EDITOR_SOURCE_VALUE_ID_ = #{editorSourceValueId,jdbcType=VARCHAR}");
        }
        
        if (record.getEditorSourceExtraValueId() != null) {
            sql.SET("EDITOR_SOURCE_EXTRA_VALUE_ID_ = #{editorSourceExtraValueId,jdbcType=VARCHAR}");
        }
        
        if (record.getTenantId() != null) {
            sql.SET("TENANT_ID_ = #{tenantId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ID_ = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}