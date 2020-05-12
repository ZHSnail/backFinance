package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface VoucherMapper {
    @Delete({
        "delete from VCM_VOUCHER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into VCM_VOUCHER (id, biz_id, ",
        "module, posting_status, ",
        "STATUS, originator, ",
        "auditer, keeper, ",
        "posting_date, biz_type, ",
        "debit_total, account_period, ",
        "code, biz_date, ",
        "deal_type, credit_total, ",
        "memo, biz_code)",
        "values (#{id,jdbcType=VARCHAR}, #{bizId,jdbcType=VARCHAR}, ",
        "#{module,jdbcType=VARCHAR}, #{postingStatus,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{originator,jdbcType=VARCHAR}, ",
        "#{auditer,jdbcType=VARCHAR}, #{keeper,jdbcType=VARCHAR}, ",
        "#{postingDate,jdbcType=TIMESTAMP}, #{bizType,jdbcType=VARCHAR}, ",
        "#{debitTotal,jdbcType=DECIMAL}, #{accountPeriod,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, #{bizDate,jdbcType=TIMESTAMP}, ",
        "#{dealType,jdbcType=VARCHAR}, #{creditTotal,jdbcType=DECIMAL}, ",
        "#{memo,jdbcType=VARCHAR}, #{bizCode,jdbcType=VARCHAR})"
    })
    int insert(Voucher record);

    @InsertProvider(type=VoucherSqlProvider.class, method="insertSelective")
    int insertSelective(Voucher record);

    @Select({
        "select",
        "id, biz_id, module, posting_status, STATUS, originator, auditer, keeper, posting_date, ",
        "biz_type, debit_total, account_period, code, biz_date, deal_type, credit_total, ",
        "memo, biz_code",
        "from VCM_VOUCHER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
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
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_date", property="bizDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deal_type", property="dealType", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_total", property="creditTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_code", property="bizCode", jdbcType=JdbcType.VARCHAR)
    })
    Voucher selectByPrimaryKey(String id);

    @UpdateProvider(type=VoucherSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Voucher record);

    @Update({
        "update VCM_VOUCHER",
        "set biz_id = #{bizId,jdbcType=VARCHAR},",
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
          "code = #{code,jdbcType=VARCHAR},",
          "biz_date = #{bizDate,jdbcType=TIMESTAMP},",
          "deal_type = #{dealType,jdbcType=VARCHAR},",
          "credit_total = #{creditTotal,jdbcType=DECIMAL},",
          "memo = #{memo,jdbcType=VARCHAR},",
          "biz_code = #{bizCode,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Voucher record);

    @SelectProvider(type=VoucherSqlProvider.class, method="selectAllConditionSql")
    List<Voucher> findAllByCondition(VoucherVo voucherVo);
}