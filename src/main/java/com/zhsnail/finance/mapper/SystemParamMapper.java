package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SystemParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SystemParamMapper {
    @Delete({
        "delete from SYM_SYSTEM_PARAM",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_SYSTEM_PARAM (id, now_account_period, ",
        "base_currency, state, ",
        "start_time, unit)",
        "values (#{id,jdbcType=VARCHAR}, #{nowAccountPeriod,jdbcType=VARCHAR}, ",
        "#{baseCurrency,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, ",
        "#{startTime,jdbcType=DATE}, #{unit,jdbcType=VARCHAR})"
    })
    int insert(SystemParam record);

    @InsertProvider(type=SystemParamSqlProvider.class, method="insertSelective")
    int insertSelective(SystemParam record);

    @Select({
        "select",
        "id, now_account_period, base_currency, state, start_time, unit",
        "from SYM_SYSTEM_PARAM",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="now_account_period", property="nowAccountPeriod", jdbcType=JdbcType.VARCHAR),
        @Result(column="base_currency", property="baseCurrency", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.DATE),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR)
    })
    SystemParam selectByPrimaryKey(String id);

    @UpdateProvider(type=SystemParamSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemParam record);

    @Update({
        "update SYM_SYSTEM_PARAM",
        "set now_account_period = #{nowAccountPeriod,jdbcType=VARCHAR},",
          "base_currency = #{baseCurrency,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "start_time = #{startTime,jdbcType=DATE},",
          "unit = #{unit,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SystemParam record);

    @Select({
            "select",
            "id, now_account_period, base_currency, state, start_time, unit",
            "from SYM_SYSTEM_PARAM",
            "where state = 'TRUE'"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="now_account_period", property="nowAccountPeriod", jdbcType=JdbcType.VARCHAR),
            @Result(column="base_currency", property="baseCurrency", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_time", property="startTime", jdbcType=JdbcType.DATE),
            @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR)
    })
    SystemParam findCurrentSysParam();
}