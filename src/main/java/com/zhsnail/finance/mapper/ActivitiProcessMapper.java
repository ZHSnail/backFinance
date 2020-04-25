package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ActivitiProcess;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ActivitiProcessMapper {
    @Delete({
        "delete from ACT_RE_PROCDEF",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ACT_RE_PROCDEF (ID_, REV_, ",
        "CATEGORY_, NAME_, ",
        "KEY_, VERSION_, DEPLOYMENT_ID_, ",
        "RESOURCE_NAME_, DGRM_RESOURCE_NAME_, ",
        "DESCRIPTION_, HAS_START_FORM_KEY_, ",
        "HAS_GRAPHICAL_NOTATION_, SUSPENSION_STATE_, ",
        "TENANT_ID_)",
        "values (#{id,jdbcType=VARCHAR}, #{rev,jdbcType=INTEGER}, ",
        "#{category,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{key,jdbcType=VARCHAR}, #{version,jdbcType=INTEGER}, #{deploymentId,jdbcType=VARCHAR}, ",
        "#{resourceName,jdbcType=VARCHAR}, #{dgrmResourceName,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{hasStartFormKey,jdbcType=TINYINT}, ",
        "#{hasGraphicalNotation,jdbcType=TINYINT}, #{suspensionState,jdbcType=INTEGER}, ",
        "#{tenantId,jdbcType=VARCHAR})"
    })
    int insert(ActivitiProcess record);

    @InsertProvider(type=ActivitiProcessSqlProvider.class, method="insertSelective")
    int insertSelective(ActivitiProcess record);

    @Select({
        "select",
        "ID_, REV_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, ",
        "DGRM_RESOURCE_NAME_, DESCRIPTION_, HAS_START_FORM_KEY_, HAS_GRAPHICAL_NOTATION_, ",
        "SUSPENSION_STATE_, TENANT_ID_",
        "from ACT_RE_PROCDEF",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REV_", property="rev", jdbcType=JdbcType.INTEGER),
        @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="KEY_", property="key", jdbcType=JdbcType.VARCHAR),
        @Result(column="VERSION_", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="DEPLOYMENT_ID_", property="deploymentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="RESOURCE_NAME_", property="resourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="DGRM_RESOURCE_NAME_", property="dgrmResourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="DESCRIPTION_", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="HAS_START_FORM_KEY_", property="hasStartFormKey", jdbcType=JdbcType.TINYINT),
        @Result(column="HAS_GRAPHICAL_NOTATION_", property="hasGraphicalNotation", jdbcType=JdbcType.TINYINT),
        @Result(column="SUSPENSION_STATE_", property="suspensionState", jdbcType=JdbcType.INTEGER),
        @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR)
    })
    ActivitiProcess selectByPrimaryKey(String id);

    @UpdateProvider(type=ActivitiProcessSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ActivitiProcess record);

    @Update({
        "update ACT_RE_PROCDEF",
        "set REV_ = #{rev,jdbcType=INTEGER},",
          "CATEGORY_ = #{category,jdbcType=VARCHAR},",
          "NAME_ = #{name,jdbcType=VARCHAR},",
          "KEY_ = #{key,jdbcType=VARCHAR},",
          "VERSION_ = #{version,jdbcType=INTEGER},",
          "DEPLOYMENT_ID_ = #{deploymentId,jdbcType=VARCHAR},",
          "RESOURCE_NAME_ = #{resourceName,jdbcType=VARCHAR},",
          "DGRM_RESOURCE_NAME_ = #{dgrmResourceName,jdbcType=VARCHAR},",
          "DESCRIPTION_ = #{description,jdbcType=VARCHAR},",
          "HAS_START_FORM_KEY_ = #{hasStartFormKey,jdbcType=TINYINT},",
          "HAS_GRAPHICAL_NOTATION_ = #{hasGraphicalNotation,jdbcType=TINYINT},",
          "SUSPENSION_STATE_ = #{suspensionState,jdbcType=INTEGER},",
          "TENANT_ID_ = #{tenantId,jdbcType=VARCHAR}",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ActivitiProcess record);

    @Select({
            "select",
            "ID_, REV_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, ",
            "DGRM_RESOURCE_NAME_, DESCRIPTION_, HAS_START_FORM_KEY_, HAS_GRAPHICAL_NOTATION_, ",
            "SUSPENSION_STATE_, TENANT_ID_",
            "from ACT_RE_PROCDEF",
            "where DEPLOYMENT_ID_ = #{deploymentId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="REV_", property="rev", jdbcType=JdbcType.INTEGER),
            @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="KEY_", property="key", jdbcType=JdbcType.VARCHAR),
            @Result(column="VERSION_", property="version", jdbcType=JdbcType.INTEGER),
            @Result(column="DEPLOYMENT_ID_", property="deploymentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="RESOURCE_NAME_", property="resourceName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DGRM_RESOURCE_NAME_", property="dgrmResourceName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DESCRIPTION_", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="HAS_START_FORM_KEY_", property="hasStartFormKey", jdbcType=JdbcType.TINYINT),
            @Result(column="HAS_GRAPHICAL_NOTATION_", property="hasGraphicalNotation", jdbcType=JdbcType.TINYINT),
            @Result(column="SUSPENSION_STATE_", property="suspensionState", jdbcType=JdbcType.INTEGER),
            @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR)
    })
    ActivitiProcess findByDeploymentId(String deploymentId);
}