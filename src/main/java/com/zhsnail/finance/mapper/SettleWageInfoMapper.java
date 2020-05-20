package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SettleWageInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SettleWageInfoMapper {
    @Delete({
        "delete from SAM_SETTLE_WAGE_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_SETTLE_WAGE_INFO (id, exe_date, ",
        "name, salary_period, ",
        "total_amount, related_number, ",
        "success_number, pay_stub_info_id)",
        "values (#{id,jdbcType=VARCHAR}, #{exeDate,jdbcType=TIMESTAMP}, ",
        "#{name,jdbcType=VARCHAR}, #{salaryPeriod,jdbcType=VARCHAR}, ",
        "#{totalAmount,jdbcType=DECIMAL}, #{relatedNumber,jdbcType=DECIMAL}, ",
        "#{successNumber,jdbcType=DECIMAL}, #{payStubInfoId,jdbcType=VARCHAR})"
    })
    int insert(SettleWageInfo record);

    @InsertProvider(type=SettleWageInfoSqlProvider.class, method="insertSelective")
    int insertSelective(SettleWageInfo record);

    @Select({
        "select",
        "id, exe_date, name, salary_period, total_amount, related_number, success_number, ",
        "pay_stub_info_id",
        "from SAM_SETTLE_WAGE_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="exe_date", property="exeDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="salary_period", property="salaryPeriod", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="related_number", property="relatedNumber", jdbcType=JdbcType.DECIMAL),
        @Result(column="success_number", property="successNumber", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR),
        @Result(property = "staffInfoList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.StaffInfoMapper.findBySettleWageId",fetchType = FetchType.LAZY)),
        @Result(column="pay_stub_info_id",property="payStubInfo",one=@One(select="com.zhsnail.finance.mapper.PayStubInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    SettleWageInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=SettleWageInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SettleWageInfo record);

    @Update({
        "update SAM_SETTLE_WAGE_INFO",
        "set exe_date = #{exeDate,jdbcType=TIMESTAMP},",
          "name = #{name,jdbcType=VARCHAR},",
          "salary_period = #{salaryPeriod,jdbcType=VARCHAR},",
          "total_amount = #{totalAmount,jdbcType=DECIMAL},",
          "related_number = #{relatedNumber,jdbcType=DECIMAL},",
          "success_number = #{successNumber,jdbcType=DECIMAL},",
          "pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SettleWageInfo record);

    @InsertProvider(type=SettleWageInfoSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<SettleWageInfo> settleWageInfoList);

    @Select({
            "select",
            "id, exe_date, name, salary_period, total_amount, related_number, success_number, ",
            "pay_stub_info_id",
            "from SAM_SETTLE_WAGE_INFO",
            "where salary_period = #{salaryPeriod,jdbcType=VARCHAR} and pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="exe_date", property="exeDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="salary_period", property="salaryPeriod", jdbcType=JdbcType.VARCHAR),
            @Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="related_number", property="relatedNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="success_number", property="successNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR)
    })
    SettleWageInfo findBySalPeriodAndPayStuId(String salaryPeriod,String payStubInfoId);

    @Select({
            "select",
            "id, exe_date, name, salary_period, total_amount, related_number, success_number, ",
            "pay_stub_info_id",
            "from SAM_SETTLE_WAGE_INFO",
            "where pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="exe_date", property="exeDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="salary_period", property="salaryPeriod", jdbcType=JdbcType.VARCHAR),
            @Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="related_number", property="relatedNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="success_number", property="successNumber", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR)
    })
    List<SettleWageInfo> findByPayStuId(String payStubInfoId);
}