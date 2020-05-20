package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StationSalary;
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

public interface StationSalaryMapper {
    @Delete({
        "delete from SAM_STATION_SALARY",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_STATION_SALARY (id, station_amount, ",
        "station_stage, station_grad, ",
        "debit_account_id, credit_account_id, ",
        "type)",
        "values (#{id,jdbcType=VARCHAR}, #{stationAmount,jdbcType=DECIMAL}, ",
        "#{stationStage,jdbcType=DECIMAL}, #{stationGrad,jdbcType=DECIMAL}, ",
        "#{debitAccountId,jdbcType=VARCHAR}, #{creditAccountId,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR})"
    })
    int insert(StationSalary record);

    @InsertProvider(type=StationSalarySqlProvider.class, method="insertSelective")
    int insertSelective(StationSalary record);

    @Select({
        "select",
        "id, station_amount, station_stage, station_grad, debit_account_id, credit_account_id, ",
        "type",
        "from SAM_STATION_SALARY",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="station_amount", property="stationAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="station_stage", property="stationStage", jdbcType=JdbcType.DECIMAL),
        @Result(column="station_grad", property="stationGrad", jdbcType=JdbcType.DECIMAL),
        @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    StationSalary selectByPrimaryKey(String id);

    @UpdateProvider(type=StationSalarySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StationSalary record);

    @Update({
        "update SAM_STATION_SALARY",
        "set station_amount = #{stationAmount,jdbcType=DECIMAL},",
          "station_stage = #{stationStage,jdbcType=DECIMAL},",
          "station_grad = #{stationGrad,jdbcType=DECIMAL},",
          "debit_account_id = #{debitAccountId,jdbcType=VARCHAR},",
          "credit_account_id = #{creditAccountId,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(StationSalary record);

    @Select({
            "select * from SAM_STATION_SALARY",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="station_amount", property="stationAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="station_stage", property="stationStage", jdbcType=JdbcType.DECIMAL),
            @Result(column="station_grad", property="stationGrad", jdbcType=JdbcType.DECIMAL),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    List<StationSalary> findAll();

    @Select({
            "select",
            "id, station_amount, station_stage, station_grad, debit_account_id, credit_account_id, ",
            "type",
            "from SAM_STATION_SALARY",
            "where type = #{type,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="station_amount", property="stationAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="station_stage", property="stationStage", jdbcType=JdbcType.DECIMAL),
            @Result(column="station_grad", property="stationGrad", jdbcType=JdbcType.DECIMAL),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    StationSalary findByType(String type);
}