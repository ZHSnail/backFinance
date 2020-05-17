package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsPurchase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AssetsPurchaseMapper {
    @Delete({
        "delete from ASM_ASSETS_PURCHASE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS_PURCHASE (id, create_time, ",
        "creater, code, status, ",
        "update_time, updater, ",
        "req_time, purchase_method, ",
        "memo)",
        "values (#{id,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{updater,jdbcType=VARCHAR}, ",
        "#{reqTime,jdbcType=TIMESTAMP}, #{purchaseMethod,jdbcType=VARCHAR}, ",
        "#{memo,jdbcType=VARCHAR})"
    })
    int insert(AssetsPurchase record);

    @InsertProvider(type=AssetsPurchaseSqlProvider.class, method="insertSelective")
    int insertSelective(AssetsPurchase record);

    @Select({
        "select",
        "id, create_time, creater, code, status, update_time, updater, req_time, purchase_method, ",
        "memo",
        "from ASM_ASSETS_PURCHASE",
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
        @Result(column="req_time", property="reqTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="purchase_method", property="purchaseMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    AssetsPurchase selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsPurchaseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssetsPurchase record);

    @Update({
        "update ASM_ASSETS_PURCHASE",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "updater = #{updater,jdbcType=VARCHAR},",
          "req_time = #{reqTime,jdbcType=TIMESTAMP},",
          "purchase_method = #{purchaseMethod,jdbcType=VARCHAR},",
          "memo = #{memo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AssetsPurchase record);
}