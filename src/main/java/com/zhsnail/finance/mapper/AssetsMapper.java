package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Assets;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AssetsMapper {
    @Delete({
        "delete from ASM_ASSETS",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS (id, assets_kind_id, ",
        "name, code, storage_time, ",
        "depre_method, loss_report, ",
        "useful_life, storage_place, ",
        "norms, orival, salvage, ",
        "loss_report_time, clean_cost, ",
        "num, obtain_method, ",
        "state, purchase_id)",
        "values (#{id,jdbcType=VARCHAR}, #{assetsKindId,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{storageTime,jdbcType=TIMESTAMP}, ",
        "#{depreMethod,jdbcType=VARCHAR}, #{lossReport,jdbcType=VARCHAR}, ",
        "#{usefulLife,jdbcType=VARCHAR}, #{storagePlace,jdbcType=VARCHAR}, ",
        "#{norms,jdbcType=VARCHAR}, #{orival,jdbcType=DECIMAL}, #{salvage,jdbcType=DECIMAL}, ",
        "#{lossReportTime,jdbcType=TIMESTAMP}, #{cleanCost,jdbcType=DECIMAL}, ",
        "#{num,jdbcType=VARCHAR}, #{obtainMethod,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=VARCHAR}, #{purchaseId,jdbcType=VARCHAR})"
    })
    int insert(Assets record);

    @InsertProvider(type=AssetsSqlProvider.class, method="insertSelective")
    int insertSelective(Assets record);

    @Select({
        "select",
        "id, assets_kind_id, name, code, storage_time, depre_method, loss_report, useful_life, ",
        "storage_place, norms, orival, salvage, loss_report_time, clean_cost, num, obtain_method, ",
        "state, purchase_id",
        "from ASM_ASSETS",
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
        @Result(column="purchase_id", property="purchaseId", jdbcType=JdbcType.VARCHAR)
    })
    Assets selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Assets record);

    @Update({
        "update ASM_ASSETS",
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
          "purchase_id = #{purchaseId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Assets record);
}