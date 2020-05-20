package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StationInfo;
import com.zhsnail.finance.vo.StationInfoVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface StationInfoMapper {
    @Delete({
        "delete from SAM_STATION_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_STATION_INFO (id, type, ",
        "name, state, station_salary_id, ",
        "scale_salary_id, debit_scale_acc_id, ",
        "credit_scale_acc_id, debit_station_acc_id, ",
        "credit_station_acc_id)",
        "values (#{id,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, #{stationSalaryId,jdbcType=VARCHAR}, ",
        "#{scaleSalaryId,jdbcType=VARCHAR}, #{debitScaleAccId,jdbcType=VARCHAR}, ",
        "#{creditScaleAccId,jdbcType=VARCHAR}, #{debitStationAccId,jdbcType=VARCHAR}, ",
        "#{creditStationAccId,jdbcType=VARCHAR})"
    })
    int insert(StationInfo record);

    @InsertProvider(type=StationInfoSqlProvider.class, method="insertSelective")
    int insertSelective(StationInfo record);

    @Select({
        "select",
        "id, type, name, state, station_salary_id, scale_salary_id, debit_scale_acc_id, ",
        "credit_scale_acc_id, debit_station_acc_id, credit_station_acc_id",
        "from SAM_STATION_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="station_salary_id", property="stationSalaryId", jdbcType=JdbcType.VARCHAR),
        @Result(column="scale_salary_id", property="scaleSalaryId", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_scale_acc_id", property="debitScaleAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_scale_acc_id", property="creditScaleAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_station_acc_id", property="debitStationAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_station_acc_id", property="creditStationAccId", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_scale_acc_id",property="debitScaleAccount",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="credit_scale_acc_id",property="creditScaleAccount",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="debit_station_acc_id",property="debitStationAccount",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="credit_station_acc_id",property="creditStationAccount",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="station_salary_id",property="stationSalary",one=@One(select="com.zhsnail.finance.mapper.StationSalaryMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="scale_salary_id",property="scaleSalary",one=@One(select="com.zhsnail.finance.mapper.ScaleSalaryMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    StationInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=StationInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StationInfo record);

    @Update({
        "update SAM_STATION_INFO",
        "set type = #{type,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "station_salary_id = #{stationSalaryId,jdbcType=VARCHAR},",
          "scale_salary_id = #{scaleSalaryId,jdbcType=VARCHAR},",
          "debit_scale_acc_id = #{debitScaleAccId,jdbcType=VARCHAR},",
          "credit_scale_acc_id = #{creditScaleAccId,jdbcType=VARCHAR},",
          "debit_station_acc_id = #{debitStationAccId,jdbcType=VARCHAR},",
          "credit_station_acc_id = #{creditStationAccId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(StationInfo record);

    @SelectProvider(type=StationInfoSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_salary_id", property="stationSalaryId", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale_salary_id", property="scaleSalaryId", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_scale_acc_id", property="debitScaleAccId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_scale_acc_id", property="creditScaleAccId", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_station_acc_id", property="debitStationAccId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_station_acc_id", property="creditStationAccId", jdbcType=JdbcType.VARCHAR)
    })
    List<StationInfo> findAllByCondition(StationInfoVo stationInfoVo);
}