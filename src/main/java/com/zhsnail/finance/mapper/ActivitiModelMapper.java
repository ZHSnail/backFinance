package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ActivitiModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ActivitiModelMapper {
    @Delete({
        "delete from ACT_RE_MODEL",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ACT_RE_MODEL (ID_, REV_, ",
        "NAME_, KEY_, CATEGORY_, ",
        "CREATE_TIME_, LAST_UPDATE_TIME_, ",
        "VERSION_, META_INFO_, ",
        "DEPLOYMENT_ID_, EDITOR_SOURCE_VALUE_ID_, ",
        "EDITOR_SOURCE_EXTRA_VALUE_ID_, TENANT_ID_)",
        "values (#{id,jdbcType=VARCHAR}, #{rev,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{key,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateTime,jdbcType=TIMESTAMP}, ",
        "#{version,jdbcType=INTEGER}, #{metaInfo,jdbcType=VARCHAR}, ",
        "#{deploymentId,jdbcType=VARCHAR}, #{editorSourceValueId,jdbcType=VARCHAR}, ",
        "#{editorSourceExtraValueId,jdbcType=VARCHAR}, #{tenantId,jdbcType=VARCHAR})"
    })
    int insert(ActivitiModel record);

    @InsertProvider(type=ActivitiModelSqlProvider.class, method="insertSelective")
    int insertSelective(ActivitiModel record);

    @Select({
        "select",
        "ID_, REV_, NAME_, KEY_, CATEGORY_, CREATE_TIME_, LAST_UPDATE_TIME_, VERSION_, ",
        "META_INFO_, DEPLOYMENT_ID_, EDITOR_SOURCE_VALUE_ID_, EDITOR_SOURCE_EXTRA_VALUE_ID_, ",
        "TENANT_ID_",
        "from ACT_RE_MODEL",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REV_", property="rev", jdbcType=JdbcType.INTEGER),
        @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME_", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LAST_UPDATE_TIME_", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="VERSION_", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="META_INFO_", property="metaInfo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPLOYMENT_ID_", property="deploymentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="EDITOR_SOURCE_VALUE_ID_", property="editorSourceValueId", jdbcType=JdbcType.VARCHAR),
        @Result(column="EDITOR_SOURCE_EXTRA_VALUE_ID_", property="editorSourceExtraValueId", jdbcType=JdbcType.VARCHAR),
        @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR)
    })
    ActivitiModel selectByPrimaryKey(String id);

    @UpdateProvider(type=ActivitiModelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ActivitiModel record);

    @Update({
        "update ACT_RE_MODEL",
        "set REV_ = #{rev,jdbcType=INTEGER},",
          "NAME_ = #{name,jdbcType=VARCHAR},",
          "KEY_ = #{key,jdbcType=VARCHAR},",
          "CATEGORY_ = #{category,jdbcType=VARCHAR},",
          "CREATE_TIME_ = #{createTime,jdbcType=TIMESTAMP},",
          "LAST_UPDATE_TIME_ = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "VERSION_ = #{version,jdbcType=INTEGER},",
          "META_INFO_ = #{metaInfo,jdbcType=VARCHAR},",
          "DEPLOYMENT_ID_ = #{deploymentId,jdbcType=VARCHAR},",
          "EDITOR_SOURCE_VALUE_ID_ = #{editorSourceValueId,jdbcType=VARCHAR},",
          "EDITOR_SOURCE_EXTRA_VALUE_ID_ = #{editorSourceExtraValueId,jdbcType=VARCHAR},",
          "TENANT_ID_ = #{tenantId,jdbcType=VARCHAR}",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ActivitiModel record);

    @Select({
            "select * from ACT_RE_MODEL"
    })
    @Results({
            @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="REV_", property="rev", jdbcType=JdbcType.INTEGER),
            @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="KEY_", property="key", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME_", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="LAST_UPDATE_TIME_", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="VERSION_", property="version", jdbcType=JdbcType.INTEGER),
            @Result(column="META_INFO_", property="metaInfo", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEPLOYMENT_ID_", property="deploymentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="EDITOR_SOURCE_VALUE_ID_", property="editorSourceValueId", jdbcType=JdbcType.VARCHAR),
            @Result(column="EDITOR_SOURCE_EXTRA_VALUE_ID_", property="editorSourceExtraValueId", jdbcType=JdbcType.VARCHAR),
            @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR),
    })
    List<ActivitiModel> findAll();
    @Select({
            "select * from ACT_RE_MODEL where DEPLOYMENT_ID_ = #{deploymentId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="REV_", property="rev", jdbcType=JdbcType.INTEGER),
            @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="KEY_", property="key", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME_", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="LAST_UPDATE_TIME_", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="VERSION_", property="version", jdbcType=JdbcType.INTEGER),
            @Result(column="META_INFO_", property="metaInfo", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEPLOYMENT_ID_", property="deploymentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="EDITOR_SOURCE_VALUE_ID_", property="editorSourceValueId", jdbcType=JdbcType.VARCHAR),
            @Result(column="EDITOR_SOURCE_EXTRA_VALUE_ID_", property="editorSourceExtraValueId", jdbcType=JdbcType.VARCHAR),
            @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR),
    })
    ActivitiModel findByDeploymentId(String deploymentId);
}