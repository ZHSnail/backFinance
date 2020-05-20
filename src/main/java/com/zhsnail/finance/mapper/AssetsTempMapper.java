package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.entity.AssetsTemp;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AssetsTempMapper {
    @Delete({
        "delete from ASM_ASSETS_TEMP",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS_TEMP (id, assets_kind_id, ",
        "name, code, storage_time, ",
        "depre_method, loss_report, ",
        "useful_life, storage_place, ",
        "norms, orival, salvage, ",
        "loss_report_time, clean_cost, ",
        "num, obtain_method, ",
        "state, purchase_id, ",
        "change_id)",
        "values (#{id,jdbcType=VARCHAR}, #{assetsKindId,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{storageTime,jdbcType=TIMESTAMP}, ",
        "#{depreMethod,jdbcType=VARCHAR}, #{lossReport,jdbcType=VARCHAR}, ",
        "#{usefulLife,jdbcType=VARCHAR}, #{storagePlace,jdbcType=VARCHAR}, ",
        "#{norms,jdbcType=VARCHAR}, #{orival,jdbcType=DECIMAL}, #{salvage,jdbcType=DECIMAL}, ",
        "#{lossReportTime,jdbcType=TIMESTAMP}, #{cleanCost,jdbcType=DECIMAL}, ",
        "#{num,jdbcType=VARCHAR}, #{obtainMethod,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=VARCHAR}, #{purchaseId,jdbcType=VARCHAR}, ",
        "#{changeId,jdbcType=VARCHAR})"
    })
    int insert(AssetsTemp record);

    @InsertProvider(type=AssetsTempSqlProvider.class, method="insertSelective")
    int insertSelective(AssetsTemp record);

    @Select({
        "select",
        "id, assets_kind_id, name, code, storage_time, depre_method, loss_report, useful_life, ",
        "storage_place, norms, orival, salvage, loss_report_time, clean_cost, num, obtain_method, ",
        "state, purchase_id, change_id",
        "from ASM_ASSETS_TEMP",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="assets_kind_id", property="assetsKindId", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="storage_time", property="storageTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="depre_method", property="depreMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="loss_report", property="lossReport", jdbcType=JdbcType.VARCHAR),
        @Result(column="useful_life", property="usefulLife", jdbcType=JdbcType.VARCHAR),
        @Result(column="storage_place", property="storagePlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="norms", property="norms", jdbcType=JdbcType.VARCHAR),
        @Result(column="orival", property="orival", jdbcType=JdbcType.DECIMAL),
        @Result(column="salvage", property="salvage", jdbcType=JdbcType.DECIMAL),
        @Result(column="loss_report_time", property="lossReportTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="clean_cost", property="cleanCost", jdbcType=JdbcType.DECIMAL),
        @Result(column="num", property="num", jdbcType=JdbcType.VARCHAR),
        @Result(column="obtain_method", property="obtainMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="change_id", property="changeId", jdbcType=JdbcType.VARCHAR), @Result(column="assets_kind_id",property="assetsKind",one=@One(select="com.zhsnail.finance.mapper.AssetsKindMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    AssetsTemp selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsTempSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssetsTemp record);

    @Update({
        "update ASM_ASSETS_TEMP",
        "set assets_kind_id = #{assetsKindId,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "storage_time = #{storageTime,jdbcType=TIMESTAMP},",
          "depre_method = #{depreMethod,jdbcType=VARCHAR},",
          "loss_report = #{lossReport,jdbcType=VARCHAR},",
          "useful_life = #{usefulLife,jdbcType=VARCHAR},",
          "storage_place = #{storagePlace,jdbcType=VARCHAR},",
          "norms = #{norms,jdbcType=VARCHAR},",
          "orival = #{orival,jdbcType=DECIMAL},",
          "salvage = #{salvage,jdbcType=DECIMAL},",
          "loss_report_time = #{lossReportTime,jdbcType=TIMESTAMP},",
          "clean_cost = #{cleanCost,jdbcType=DECIMAL},",
          "num = #{num,jdbcType=VARCHAR},",
          "obtain_method = #{obtainMethod,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "purchase_id = #{purchaseId,jdbcType=VARCHAR},",
          "change_id = #{changeId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AssetsTemp record);

    @Select({
            "select * from ASM_ASSETS_TEMP",
            "where change_id = #{changeId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="assets_kind_id", property="assetsKindId", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="storage_time", property="storageTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="depre_method", property="depreMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="loss_report", property="lossReport", jdbcType=JdbcType.VARCHAR),
            @Result(column="useful_life", property="usefulLife", jdbcType=JdbcType.VARCHAR),
            @Result(column="storage_place", property="storagePlace", jdbcType=JdbcType.VARCHAR),
            @Result(column="norms", property="norms", jdbcType=JdbcType.VARCHAR),
            @Result(column="orival", property="orival", jdbcType=JdbcType.DECIMAL),
            @Result(column="salvage", property="salvage", jdbcType=JdbcType.DECIMAL),
            @Result(column="loss_report_time", property="lossReportTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="clean_cost", property="cleanCost", jdbcType=JdbcType.DECIMAL),
            @Result(column="num", property="num", jdbcType=JdbcType.VARCHAR),
            @Result(column="obtain_method", property="obtainMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.VARCHAR),
            @Result(column="change_id", property="changeId", jdbcType=JdbcType.VARCHAR),
            @Result(column="assets_kind_id",property="assetsKind",one=@One(select="com.zhsnail.finance.mapper.AssetsKindMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<AssetsTemp> findByChangeId(String changeId);

    @Delete({
            "delete from ASM_ASSETS_TEMP",
            "where change_id = #{changeId,jdbcType=VARCHAR}"
    })
    void deleteByChangeId(String changeId);

    @InsertProvider(type=AssetsTempSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<AssetsTemp> assetsTempList);

    @Select({
            "select id from ASM_ASSETS_TEMP",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
    })
    List<String> findAllId();
}