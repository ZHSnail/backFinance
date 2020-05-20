package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ScaleSalary;
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

public interface ScaleSalaryMapper {
    @Delete({
        "delete from SAM_SCALE_SALARY",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_SCALE_SALARY (id, scale_amount, ",
        "scale_stage, scale_grad, ",
        "debit_account_id, credit_account_id, ",
        "type)",
        "values (#{id,jdbcType=VARCHAR}, #{scaleAmount,jdbcType=DECIMAL}, ",
        "#{scaleStage,jdbcType=DECIMAL}, #{scaleGrad,jdbcType=DECIMAL}, ",
        "#{debitAccountId,jdbcType=VARCHAR}, #{creditAccountId,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR})"
    })
    int insert(ScaleSalary record);

    @InsertProvider(type=ScaleSalarySqlProvider.class, method="insertSelective")
    int insertSelective(ScaleSalary record);

    @Select({
        "select",
        "id, scale_amount, scale_stage, scale_grad, debit_account_id, credit_account_id, ",
        "type",
        "from SAM_SCALE_SALARY",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="scale_amount", property="scaleAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="scale_stage", property="scaleStage", jdbcType=JdbcType.DECIMAL),
        @Result(column="scale_grad", property="scaleGrad", jdbcType=JdbcType.DECIMAL),
        @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    ScaleSalary selectByPrimaryKey(String id);

    @UpdateProvider(type=ScaleSalarySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ScaleSalary record);

    @Update({
        "update SAM_SCALE_SALARY",
        "set scale_amount = #{scaleAmount,jdbcType=DECIMAL},",
          "scale_stage = #{scaleStage,jdbcType=DECIMAL},",
          "scale_grad = #{scaleGrad,jdbcType=DECIMAL},",
          "debit_account_id = #{debitAccountId,jdbcType=VARCHAR},",
          "credit_account_id = #{creditAccountId,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(ScaleSalary record);

    @Select({
            "select * from SAM_SCALE_SALARY",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="scale_amount", property="scaleAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="scale_stage", property="scaleStage", jdbcType=JdbcType.DECIMAL),
            @Result(column="scale_grad", property="scaleGrad", jdbcType=JdbcType.DECIMAL),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<ScaleSalary> findAll();

    @Select({
            "select",
            "id, scale_amount, scale_stage, scale_grad, debit_account_id, credit_account_id, ",
            "type",
            "from SAM_SCALE_SALARY",
            "where type = #{type,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="scale_amount", property="scaleAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="scale_stage", property="scaleStage", jdbcType=JdbcType.DECIMAL),
            @Result(column="scale_grad", property="scaleGrad", jdbcType=JdbcType.DECIMAL),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    ScaleSalary findByType(String type);
}