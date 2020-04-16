package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountBalance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AccountBalanceMapper {
    @Delete({
        "delete from LEM_ACCOUNT_BALANCE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into LEM_ACCOUNT_BALANCE (id, debit_stayear_amt, ",
        "credit_stayear_amt, credit_staperiod_amt, ",
        "debit_staperiod_amt, credit_endperiod_amt, ",
        "debit_endperiod_amt, credit_currperiod_amt, ",
        "debit_currperiod_amt, credit_accumyear_amt, ",
        "debit_accumyear_amt, account_period, ",
        "account_id)",
        "values (#{id,jdbcType=VARCHAR}, #{debitStayearAmt,jdbcType=DECIMAL}, ",
        "#{creditStayearAmt,jdbcType=DECIMAL}, #{creditStaperiodAmt,jdbcType=DECIMAL}, ",
        "#{debitStaperiodAmt,jdbcType=DECIMAL}, #{creditEndperiodAmt,jdbcType=DECIMAL}, ",
        "#{debitEndperiodAmt,jdbcType=DECIMAL}, #{creditCurrperiodAmt,jdbcType=DECIMAL}, ",
        "#{debitCurrperiodAmt,jdbcType=DECIMAL}, #{creditAccumyearAmt,jdbcType=DECIMAL}, ",
        "#{debitAccumyearAmt,jdbcType=DECIMAL}, #{accountPeriod,jdbcType=VARCHAR}, ",
        "#{accountId,jdbcType=VARCHAR})"
    })
    int insert(AccountBalance record);

    @InsertProvider(type=AccountBalanceSqlProvider.class, method="insertSelective")
    int insertSelective(AccountBalance record);

    @Select({
        "select",
        "id, debit_stayear_amt, credit_stayear_amt, credit_staperiod_amt, debit_staperiod_amt, ",
        "credit_endperiod_amt, debit_endperiod_amt, credit_currperiod_amt, debit_currperiod_amt, ",
        "credit_accumyear_amt, debit_accumyear_amt, account_period, account_id",
        "from LEM_ACCOUNT_BALANCE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="debit_stayear_amt", property="debitStayearAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_stayear_amt", property="creditStayearAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_staperiod_amt", property="creditStaperiodAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="debit_staperiod_amt", property="debitStaperiodAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_endperiod_amt", property="creditEndperiodAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="debit_endperiod_amt", property="debitEndperiodAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_currperiod_amt", property="creditCurrperiodAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="debit_currperiod_amt", property="debitCurrperiodAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_accumyear_amt", property="creditAccumyearAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="debit_accumyear_amt", property="debitAccumyearAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="account_period", property="accountPeriod", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR)
    })
    AccountBalance selectByPrimaryKey(String id);

    @UpdateProvider(type=AccountBalanceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountBalance record);

    @Update({
        "update LEM_ACCOUNT_BALANCE",
        "set debit_stayear_amt = #{debitStayearAmt,jdbcType=DECIMAL},",
          "credit_stayear_amt = #{creditStayearAmt,jdbcType=DECIMAL},",
          "credit_staperiod_amt = #{creditStaperiodAmt,jdbcType=DECIMAL},",
          "debit_staperiod_amt = #{debitStaperiodAmt,jdbcType=DECIMAL},",
          "credit_endperiod_amt = #{creditEndperiodAmt,jdbcType=DECIMAL},",
          "debit_endperiod_amt = #{debitEndperiodAmt,jdbcType=DECIMAL},",
          "credit_currperiod_amt = #{creditCurrperiodAmt,jdbcType=DECIMAL},",
          "debit_currperiod_amt = #{debitCurrperiodAmt,jdbcType=DECIMAL},",
          "credit_accumyear_amt = #{creditAccumyearAmt,jdbcType=DECIMAL},",
          "debit_accumyear_amt = #{debitAccumyearAmt,jdbcType=DECIMAL},",
          "account_period = #{accountPeriod,jdbcType=VARCHAR},",
          "account_id = #{accountId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AccountBalance record);
}