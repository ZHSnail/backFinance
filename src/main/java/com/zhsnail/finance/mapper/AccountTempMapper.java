package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountTemp;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AccountTempMapper {
    @Delete({
        "delete from VCM_ACCOUNT_TEMP",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into VCM_ACCOUNT_TEMP (id, voucher_id, ",
        "account_id, debit_amt, ",
        "credit_amt, direction)",
        "values (#{id,jdbcType=VARCHAR}, #{voucherId,jdbcType=VARCHAR}, ",
        "#{accountId,jdbcType=VARCHAR}, #{debitAmt,jdbcType=DECIMAL}, ",
        "#{creditAmt,jdbcType=DECIMAL}, #{direction,jdbcType=VARCHAR})"
    })
    int insert(AccountTemp record);

    @InsertProvider(type=AccountTempSqlProvider.class, method="insertSelective")
    int insertSelective(AccountTemp record);

    @Select({
        "select",
        "id, voucher_id, account_id, debit_amt, credit_amt, direction",
        "from VCM_ACCOUNT_TEMP",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="voucher_id", property="voucherId", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_amt", property="debitAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_amt", property="creditAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="direction", property="direction", jdbcType=JdbcType.VARCHAR)
    })
    AccountTemp selectByPrimaryKey(String id);

    @UpdateProvider(type=AccountTempSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountTemp record);

    @Update({
        "update VCM_ACCOUNT_TEMP",
        "set voucher_id = #{voucherId,jdbcType=VARCHAR},",
          "account_id = #{accountId,jdbcType=VARCHAR},",
          "debit_amt = #{debitAmt,jdbcType=DECIMAL},",
          "credit_amt = #{creditAmt,jdbcType=DECIMAL},",
          "direction = #{direction,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AccountTemp record);

    @InsertProvider(type=AccountTempSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<AccountTemp> accountTemps);

    @Select({
            "select * ",
            "from VCM_ACCOUNT_TEMP",
            "where voucher_id = #{voucherId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="voucher_id", property="voucherId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_amt", property="debitAmt", jdbcType=JdbcType.DECIMAL),
            @Result(column="credit_amt", property="creditAmt", jdbcType=JdbcType.DECIMAL),
            @Result(column="direction", property="direction", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id",property="account",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<AccountTemp> findByVoucherId(String voucherId);
}