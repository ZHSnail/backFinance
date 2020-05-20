package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsChange;
import com.zhsnail.finance.entity.AssetsChange;
import com.zhsnail.finance.vo.AssetsChangeVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AssetsChangeMapper {
    @Delete({
        "delete from ASM_ASSETS_CHANGE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS_CHANGE (id, create_time, ",
        "creater, code, status, ",
        "update_time, updater, ",
        "assets_id, memo)",
        "values (#{id,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{updater,jdbcType=VARCHAR}, ",
        "#{assetsId,jdbcType=VARCHAR}, #{memo,jdbcType=VARCHAR})"
    })
    int insert(AssetsChange record);

    @InsertProvider(type=AssetsChangeSqlProvider.class, method="insertSelective")
    int insertSelective(AssetsChange record);

    @Select({
        "select",
        "id, create_time, creater, code, status, update_time, updater, assets_id, memo",
        "from ASM_ASSETS_CHANGE",
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
        @Result(property = "assetsList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.AssetsMapper.findByChangeId",fetchType = FetchType.LAZY)),
        @Result(property = "assetsTempList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.AssetsTempMapper.findByChangeId",fetchType = FetchType.LAZY)),

    })
    AssetsChange selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsChangeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssetsChange record);

    @Update({
        "update ASM_ASSETS_CHANGE",
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
    int updateByPrimaryKey(AssetsChange record);

    @SelectProvider(type=AssetsChangeSqlProvider.class, method="findTaskListSql")
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
    })
    List<AssetsChange> findTaskList(AssetsChangeVo assetsChangeVo);
    @Select({
            "<script>",
            "select * ",
            "from ASM_ASSETS_CHANGE",
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
    })
    List<AssetsChange> findByIds(@Param("ids") List<String> ids);
    
    @SelectProvider(type=AssetsChangeSqlProvider.class, method="selectAllConditionSql")
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
    })
    List<AssetsChange> findAllByCondition(AssetsChangeVo assetsChangeVo);

    @SelectProvider(type=AssetsChangeSqlProvider.class, method="selectAllTaskConditionSql")
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
    })
    List<AssetsChange> findAllCurrentUserTask(AssetsChangeVo assetsChangeVo);
}