package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsDepreciation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AssetsDepreciationMapper {
    @Delete({
        "delete from ASM_ASSETS_DEPRECIATION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS_DEPRECIATION (id, create_time, ",
        "creater, code, status, ",
        "update_time, updater, ",
        "assets_id, depre_amount, ",
        "depre_time, memo)",
        "values (#{id,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{updater,jdbcType=VARCHAR}, ",
        "#{assetsId,jdbcType=VARCHAR}, #{depreAmount,jdbcType=DECIMAL}, ",
        "#{depreTime,jdbcType=TIMESTAMP}, #{memo,jdbcType=VARCHAR})"
    })
    int insert(AssetsDepreciation record);

    @InsertProvider(type=AssetsDepreciationSqlProvider.class, method="insertSelective")
    int insertSelective(AssetsDepreciation record);

    @Select({
        "select",
        "id, create_time, creater, code, status, update_time, updater, assets_id, depre_amount, ",
        "depre_time, memo",
        "from ASM_ASSETS_DEPRECIATION",
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
        @Result(column="depre_amount", property="depreAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="depre_time", property="depreTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    AssetsDepreciation selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsDepreciationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssetsDepreciation record);

    @Update({
        "update ASM_ASSETS_DEPRECIATION",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "updater = #{updater,jdbcType=VARCHAR},",
          "assets_id = #{assetsId,jdbcType=VARCHAR},",
          "depre_amount = #{depreAmount,jdbcType=DECIMAL},",
          "depre_time = #{depreTime,jdbcType=TIMESTAMP},",
          "memo = #{memo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AssetsDepreciation record);
}