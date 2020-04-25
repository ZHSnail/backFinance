package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Voucher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface VoucherMapper {
    @Delete({
        "delete from VCM_VOUCHER",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer code);

    @Insert({
        "insert into VCM_VOUCHER (code, id, ",
        "biz_id, module, posting_status, ",
        "STATUS, originator, ",
        "auditer, keeper, ",
        "posting_date, biz_type, ",
        "debit_total, account_period, ",
        "biz_date, deal_type, ",
        "credit_total, memo)",
        "values (#{code,jdbcType=INTEGER}, #{id,jdbcType=VARCHAR}, ",
        "#{bizId,jdbcType=VARCHAR}, #{module,jdbcType=VARCHAR}, #{postingStatus,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{originator,jdbcType=VARCHAR}, ",
        "#{auditer,jdbcType=VARCHAR}, #{keeper,jdbcType=VARCHAR}, ",
        "#{postingDate,jdbcType=TIMESTAMP}, #{bizType,jdbcType=VARCHAR}, ",
        "#{debitTotal,jdbcType=DECIMAL}, #{accountPeriod,jdbcType=VARCHAR}, ",
        "#{bizDate,jdbcType=TIMESTAMP}, #{dealType,jdbcType=VARCHAR}, ",
        "#{creditTotal,jdbcType=DECIMAL}, #{memo,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true, keyProperty="code", keyColumn="code")
    int insert(Voucher record);

    @InsertProvider(type=VoucherSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true, keyProperty="code", keyColumn="code")
    int insertSelective(Voucher record);

    @Select({
        "select",
        "code, id, biz_id, module, posting_status, STATUS, originator, auditer, keeper, ",
        "posting_date, biz_type, debit_total, account_period, biz_date, deal_type, credit_total, ",
        "memo",
        "from VCM_VOUCHER",
        "where code = #{code,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="code", property="code", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_id", property="bizId", jdbcType=JdbcType.VARCHAR),
        @Result(column="module", property="module", jdbcType=JdbcType.VARCHAR),
        @Result(column="posting_status", property="postingStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="originator", property="originator", jdbcType=JdbcType.VARCHAR),
        @Result(column="auditer", property="auditer", jdbcType=JdbcType.VARCHAR),
        @Result(column="keeper", property="keeper", jdbcType=JdbcType.VARCHAR),
        @Result(column="posting_date", property="postingDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="biz_type", property="bizType", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_total", property="debitTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="account_period", property="accountPeriod", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_date", property="bizDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deal_type", property="dealType", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_total", property="creditTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    Voucher selectByPrimaryKey(Integer code);

    @UpdateProvider(type=VoucherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Voucher record);

    @Update({
        "update VCM_VOUCHER",
        "set id = #{id,jdbcType=VARCHAR},",
          "biz_id = #{bizId,jdbcType=VARCHAR},",
          "module = #{module,jdbcType=VARCHAR},",
          "posting_status = #{postingStatus,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=VARCHAR},",
          "originator = #{originator,jdbcType=VARCHAR},",
          "auditer = #{auditer,jdbcType=VARCHAR},",
          "keeper = #{keeper,jdbcType=VARCHAR},",
          "posting_date = #{postingDate,jdbcType=TIMESTAMP},",
          "biz_type = #{bizType,jdbcType=VARCHAR},",
          "debit_total = #{debitTotal,jdbcType=DECIMAL},",
          "account_period = #{accountPeriod,jdbcType=VARCHAR},",
          "biz_date = #{bizDate,jdbcType=TIMESTAMP},",
          "deal_type = #{dealType,jdbcType=VARCHAR},",
          "credit_total = #{creditTotal,jdbcType=DECIMAL},",
          "memo = #{memo,jdbcType=VARCHAR}",
        "where code = #{code,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Voucher record);
}