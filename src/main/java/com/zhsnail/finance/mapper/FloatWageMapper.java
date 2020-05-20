package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.vo.FloatWageVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FloatWageMapper {
    @Delete({
        "delete from SAM_FLOAT_WAGE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_FLOAT_WAGE (id, code, ",
        "name, amount, tax_type, ",
        "sign_type, state, ",
        "debit_account_id, credit_account_id)",
        "values (#{id,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, #{taxType,jdbcType=VARCHAR}, ",
        "#{signType,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, ",
        "#{debitAccountId,jdbcType=VARCHAR}, #{creditAccountId,jdbcType=VARCHAR})"
    })
    int insert(FloatWage record);

    @InsertProvider(type=FloatWageSqlProvider.class, method="insertSelective")
    int insertSelective(FloatWage record);

    @Select({
        "select",
        "id, code, name, amount, tax_type, sign_type, state, debit_account_id, credit_account_id",
        "from SAM_FLOAT_WAGE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="tax_type", property="taxType", jdbcType=JdbcType.VARCHAR),
        @Result(column="sign_type", property="signType", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="debit_account_id",property="debitAccount",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="credit_account_id",property="creditAccount",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    FloatWage selectByPrimaryKey(String id);

    @UpdateProvider(type=FloatWageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FloatWage record);

    @Update({
        "update SAM_FLOAT_WAGE",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "tax_type = #{taxType,jdbcType=VARCHAR},",
          "sign_type = #{signType,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "debit_account_id = #{debitAccountId,jdbcType=VARCHAR},",
          "credit_account_id = #{creditAccountId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FloatWage record);

    @SelectProvider(type=FloatWageSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="tax_type", property="taxType", jdbcType=JdbcType.VARCHAR),
            @Result(column="sign_type", property="signType", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR)
    })
    List<FloatWage> findAllByCondition(FloatWageVo floatWageVo);

    @Select({
            "select",
            "id, code, name, amount, tax_type, sign_type, state, debit_account_id, credit_account_id",
            "from SAM_FLOAT_WAGE",
            "where sign_type = #{signType,jdbcType=VARCHAR} and state = 'TRUE'"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="tax_type", property="taxType", jdbcType=JdbcType.VARCHAR),
            @Result(column="sign_type", property="signType", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR)
    })
    List<FloatWage> findBySignType(String signType);

    @Select({
            "select * from SAM_FLOAT_WAGE",
            "where id in (" ,
            "select float_wage_id from SAM_FLOAT_STUB " ,
            "where pay_stub_info_id = #{payStubId,jdbcType=VARCHAR})",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="tax_type", property="taxType", jdbcType=JdbcType.VARCHAR),
            @Result(column="sign_type", property="signType", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="debit_account_id", property="debitAccountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="credit_account_id", property="creditAccountId", jdbcType=JdbcType.VARCHAR)
    })
    List<FloatWage> findByPayStubId(String payStubId);
}