package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsKind;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AssetsKindMapper {
    @Delete({
        "delete from ASM_ASSETS_KIND",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into ASM_ASSETS_KIND (id, code, ",
        "name, debit_depre_acc_id, ",
        "credit_depre_acc_id, depre_method, ",
        "debit_assets_acc_id, credit_assets_acc_id)",
        "values (#{id,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{debitDepreAccId,jdbcType=VARCHAR}, ",
        "#{creditDepreAccId,jdbcType=VARCHAR}, #{depreMethod,jdbcType=VARCHAR}, ",
        "#{debitAssetsAccId,jdbcType=VARCHAR}, #{creditAssetsAccId,jdbcType=VARCHAR})"
    })
    int insert(AssetsKind record);

    @InsertProvider(type=AssetsKindSqlProvider.class, method="insertSelective")
    int insertSelective(AssetsKind record);

    @Select({
        "select",
        "id, code, name, debit_depre_acc_id, credit_depre_acc_id, depre_method, debit_assets_acc_id, ",
        "credit_assets_acc_id",
        "from ASM_ASSETS_KIND",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_depre_acc_id", property="debitDepreAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_depre_acc_id", property="creditDepreAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="depre_method", property="depreMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_assets_acc_id", property="debitAssetsAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_assets_acc_id", property="creditAssetsAccId", jdbcType=JdbcType.VARCHAR)
    })
    AssetsKind selectByPrimaryKey(String id);

    @UpdateProvider(type=AssetsKindSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AssetsKind record);

    @Update({
        "update ASM_ASSETS_KIND",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "debit_depre_acc_id = #{debitDepreAccId,jdbcType=VARCHAR},",
          "credit_depre_acc_id = #{creditDepreAccId,jdbcType=VARCHAR},",
          "depre_method = #{depreMethod,jdbcType=VARCHAR},",
          "debit_assets_acc_id = #{debitAssetsAccId,jdbcType=VARCHAR},",
          "credit_assets_acc_id = #{creditAssetsAccId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AssetsKind record);
    @Select({
            "select * from ASM_ASSETS_KIND"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_depre_acc_id", property="debitDepreAccId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_depre_acc_id", property="creditDepreAccId", jdbcType=JdbcType.VARCHAR),
            @Result(column="depre_method", property="depreMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_assets_acc_id", property="debitAssetsAccId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_assets_acc_id", property="creditAssetsAccId", jdbcType=JdbcType.VARCHAR)
    })
    List<AssetsKind> findAll();
}