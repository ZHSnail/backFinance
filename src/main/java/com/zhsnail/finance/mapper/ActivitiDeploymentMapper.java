package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ActivitiDeployment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ActivitiDeploymentMapper {
    @Delete({
        "delete from ACT_RE_DEPLOYMENT",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ACT_RE_DEPLOYMENT (ID_, NAME_, ",
        "CATEGORY_, TENANT_ID_, ",
        "DEPLOY_TIME_)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{tenantId,jdbcType=VARCHAR}, ",
        "#{deployTime,jdbcType=TIMESTAMP})"
    })
    int insert(ActivitiDeployment record);

    @InsertProvider(type=ActivitiDeploymentSqlProvider.class, method="insertSelective")
    int insertSelective(ActivitiDeployment record);

    @Select({
        "select",
        "ID_, NAME_, CATEGORY_, TENANT_ID_, DEPLOY_TIME_",
        "from ACT_RE_DEPLOYMENT",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPLOY_TIME_", property="deployTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ActivitiDeployment selectByPrimaryKey(String id);

    @UpdateProvider(type=ActivitiDeploymentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ActivitiDeployment record);

    @Update({
        "update ACT_RE_DEPLOYMENT",
        "set NAME_ = #{name,jdbcType=VARCHAR},",
          "CATEGORY_ = #{category,jdbcType=VARCHAR},",
          "TENANT_ID_ = #{tenantId,jdbcType=VARCHAR},",
          "DEPLOY_TIME_ = #{deployTime,jdbcType=TIMESTAMP}",
        "where ID_ = #{id,jdbcType=VARCHAR}"
    })

    int updateByPrimaryKey(ActivitiDeployment record);
    @Select({
            "select * from ACT_RE_DEPLOYMENT",
    })
    @Results({
            @Result(column="ID_", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="NAME_", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_", property="category", jdbcType=JdbcType.VARCHAR),
            @Result(column="TENANT_ID_", property="tenantId", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEPLOY_TIME_", property="deployTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ID_",property="activitiProcess",one=@One(select="com.zhsnail.finance.mapper.ActivitiProcessMapper.findByDeploymentId",fetchType= FetchType.EAGER))
    })
    List<ActivitiDeployment> findAll();
}