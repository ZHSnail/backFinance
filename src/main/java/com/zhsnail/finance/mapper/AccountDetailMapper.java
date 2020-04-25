package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AccountDetail;
import com.zhsnail.finance.vo.AccountDetailVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AccountDetailMapper {
    @Delete({
        "delete from LEM_ACCOUNT_DETAIL",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into LEM_ACCOUNT_DETAIL (id, debit_amount, ",
        "credit_amount, account_period, ",
        "voucher_id, account_id)",
        "values (#{id,jdbcType=VARCHAR}, #{debitAmount,jdbcType=DECIMAL}, ",
        "#{creditAmount,jdbcType=DECIMAL}, #{accountPeriod,jdbcType=VARCHAR}, ",
        "#{voucherId,jdbcType=VARCHAR}, #{accountId,jdbcType=VARCHAR})"
    })
    int insert(AccountDetail record);

    @InsertProvider(type=AccountDetailSqlProvider.class, method="insertSelective")
    int insertSelective(AccountDetail record);

    @Select({
        "select",
        "id, debit_amount, credit_amount, account_period, voucher_id, account_id",
        "from LEM_ACCOUNT_DETAIL",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="debit_amount", property="debitAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="credit_amount", property="creditAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="account_period", property="accountPeriod", jdbcType=JdbcType.VARCHAR),
        @Result(column="voucher_id", property="voucherId", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR)
    })
    AccountDetail selectByPrimaryKey(String id);

    @UpdateProvider(type=AccountDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountDetail record);

    @Update({
        "update LEM_ACCOUNT_DETAIL",
        "set debit_amount = #{debitAmount,jdbcType=DECIMAL},",
          "credit_amount = #{creditAmount,jdbcType=DECIMAL},",
          "account_period = #{accountPeriod,jdbcType=VARCHAR},",
          "voucher_id = #{voucherId,jdbcType=VARCHAR},",
          "account_id = #{accountId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AccountDetail record);

    @SelectProvider(type=AccountDetailSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="debit_amount", property="debitAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="credit_amount", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="account_period", property="accountPeriod", jdbcType=JdbcType.VARCHAR),
            @Result(column="voucher_id", property="voucherId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id",property="accountBalance",one=@One(select="com.zhsnail.finance.mapper.AccountBalanceMapper.findByAccId",fetchType= FetchType.EAGER)),
            @Result(column="voucher_id",property="voucher",one=@One(select="com.zhsnail.finance.mapper.VoucherMapper.selectByPrimaryKey",fetchType= FetchType.EAGER))
    })
    List<AccountDetail> findByCondition(AccountDetailVo accountDetailVo);
}