package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountBalance;
import com.zhsnail.finance.vo.AccountBalanceVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

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


    @UpdateProvider(type=AccountBalanceSqlProvider.class, method="updateByAccIdSelective")
    void updateByAccId(AccountBalanceVo accountBalanceVo);

    @SelectProvider(type=AccountBalanceSqlProvider.class, method="selectAllConditionSql")
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
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id",property="account",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER))
    })
    List<AccountBalance> findByCondition(AccountBalanceVo accountBalanceVo);

    @Select({
            "select * from LEM_ACCOUNT_BALANCE",
            "where account_id = #{accountId,jdbcType=VARCHAR}"
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
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id",property="account",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER))
    })
    AccountBalance findByAccId(String accountId);

    @Delete({
            "delete from LEM_ACCOUNT_BALANCE",
            "where account_id = #{accountId,jdbcType=VARCHAR}"
    })
    void deleteByaAcountId(String accountId);

    @InsertProvider(type=AccountBalanceSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<AccountBalance> accountBalances);
    
    /*@Update({"<script>" +
            "<foreach item='accountBalanceVo' collection='accountBalanceVos' index='index' open='' close='' separator=';'>" +
            " UPDATE LEM_ACCOUNT_BALANCE " +
            " SET " +
            "<if test='#{accountBalanceVo.debitStayearAmt} != null'>debit_stayear_amt = #{accountBalanceVo.debitStayearAmt},</if>" +
            "<if test='#{accountBalanceVo.creditStayearAmt} != null'>credit_stayear_amt = #{accountBalanceVo.creditStayearAmt},</if>" +
            "<if test='#{accountBalanceVo.creditStaperiodAmt} != null'>credit_staperiod_amt = #{accountBalanceVo.creditStaperiodAmt},</if>" +
            "<if test='#{accountBalanceVo.debitStaperiodAmt} != null'>debit_staperiod_amt = #{accountBalanceVo.debitStaperiodAmt},</if>" +
            "<if test='#{accountBalanceVo.creditEndperiodAmt} != null'>credit_endperiod_amt = #{accountBalanceVo.creditEndperiodAmt},</if>" +
            "<if test='#{accountBalanceVo.debitEndperiodAmt} != null'>debit_endperiod_amt = #{accountBalanceVo.debitEndperiodAmt},</if>" +
            "<if test='#{accountBalanceVo.creditCurrperiodAmt} != null'>credit_currperiod_amt = #{accountBalanceVo.creditCurrperiodAmt},</if>" +
            "<if test='#{accountBalanceVo.debitCurrperiodAmt} != null'>debit_currperiod_amt = #{accountBalanceVo.debitCurrperiodAmt},</if>" +
            "<if test='#{accountBalanceVo.creditAccumyearAmt} != null'>credit_accumyear_amt = #{accountBalanceVo.creditAccumyearAmt},</if>" +
            "<if test='#{accountBalanceVo.debitAccumyearAmt} != null'>debit_accumyear_amt = #{accountBalanceVo.debitAccumyearAmt},</if>" +
            "<if test='#{accountBalanceVo.accountPeriod} != null'>account_period = #{accountBalanceVo.accountPeriod}</if>" +
            " WHERE account_id = #{accountBalanceVo.accountId} " +
            "</foreach>" +
            "</script>"})*/
    @UpdateProvider(type=AccountBalanceSqlProvider.class, method="batchUpdateSql")
    void batchUpdate(List<AccountBalanceVo> accountBalanceVos);
}