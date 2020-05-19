package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsPurchase;
import com.zhsnail.finance.vo.AssetsPurchaseVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

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
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(property = "assetsList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.AssetsMapper.findByPurchaseId",fetchType = FetchType.LAZY))
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

    @SelectProvider(type=AssetsPurchaseSqlProvider.class, method="findTaskListSql")
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
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
    })
    List<AssetsPurchase> findTaskList(AssetsPurchaseVo assetsPurchaseVo);

    @Select({
            "<script>",
            "select * ",
            "from ASM_ASSETS_PURCHASE",
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
            @Result(column="req_time", property="reqTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="purchase_method", property="purchaseMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
    })
    List<AssetsPurchase> findByIds(@Param("ids") List<String> ids);

    @SelectProvider(type=AssetsPurchaseSqlProvider.class, method="selectAllConditionSql")
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
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
    })
    List<AssetsPurchase> findAllByCondition(AssetsPurchaseVo assetsPurchaseVo);

    @SelectProvider(type=AssetsPurchaseSqlProvider.class, method="selectAllTaskConditionSql")
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
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
    })
    List<AssetsPurchase> findAllCurrentUserTask(AssetsPurchaseVo assetsPurchaseVo);
}