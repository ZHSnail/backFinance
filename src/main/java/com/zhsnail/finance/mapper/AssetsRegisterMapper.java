package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsRegister;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.awt.*;
import java.util.List;

public interface AssetsRegisterMapper {
    @Delete({
        "delete from ASM_ASSETS_REGISTER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS_REGISTER (id, create_time, ",
        "creater, code, status, ",
        "update_time, updater, ",
        "assets_id, memo)",
        "values (#{id,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{updater,jdbcType=VARCHAR}, ",
        "#{assetsId,jdbcType=VARCHAR}, #{memo,jdbcType=VARCHAR})"
    })
    int insert(AssetsRegister record);

    @InsertProvider(type=AssetsRegisterSqlProvider.class, method="insertSelective")
    int insertSelective(AssetsRegister record);

    @Select({
        "select",
        "id, create_time, creater, code, status, update_time, updater, assets_id, memo",
        "from ASM_ASSETS_REGISTER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
        @Result(column="assets_id", property="assetsId", jdbcType=JdbcType.VARCHAR),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="assets_id",property="assets",one=@One(select="com.zhsnail.finance.mapper.AssetsMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    AssetsRegister selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsRegisterSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssetsRegister record);

    @Update({
        "update ASM_ASSETS_REGISTER",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "updater = #{updater,jdbcType=VARCHAR},",
          "assets_id = #{assetsId,jdbcType=VARCHAR},",
          "memo = #{memo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AssetsRegister record);

    @Select({
            "select",
            "id, create_time, creater, code, status, update_time, updater, assets_id, memo",
            "from ASM_ASSETS_REGISTER",
            "where assets_id = #{assetsId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id", property="assetsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id",property="assets",one=@One(select="com.zhsnail.finance.mapper.AssetsMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    AssetsRegister findByAssetsId(String assetsId);

    @SelectProvider(type=AssetsRegisterSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id", property="assetsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id",property="assets",one=@One(select="com.zhsnail.finance.mapper.AssetsMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<AssetsRegister> findAllByCondition(AssetsRegisterVo assetsRegisterVo);

    @SelectProvider(type=AssetsRegisterSqlProvider.class, method="selectAllTaskConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id", property="assetsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id",property="assets",one=@One(select="com.zhsnail.finance.mapper.AssetsMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<AssetsRegister> findAllCurrentUserTask(AssetsRegisterVo assetsRegisterVo);

    @SelectProvider(type=AssetsRegisterSqlProvider.class, method="findTaskListSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id", property="assetsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id",property="assets",one=@One(select="com.zhsnail.finance.mapper.AssetsMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<AssetsRegister> findTaskList(AssetsRegisterVo assetsRegisterVo);

    @Select({
            "<script>",
            "select * ",
            "from ASM_ASSETS_REGISTER",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id", property="assetsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_id",property="assets",one=@One(select="com.zhsnail.finance.mapper.AssetsMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<AssetsRegister> findByIds(@Param("ids") List<String> ids);
}