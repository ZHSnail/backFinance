package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AccountMapper {
    @Delete({
        "delete from LEM_ACCOUNT",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into LEM_ACCOUNT (id, account_name, ",
        "code, level, parent_id, ",
        "is_cash, is_bank, ",
        "is_detail)",
        "values (#{id,jdbcType=VARCHAR}, #{accountName,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{isCash,jdbcType=VARCHAR}, #{isBank,jdbcType=VARCHAR}, ",
        "#{isDetail,jdbcType=VARCHAR})"
    })
    int insert(Account record);

    @InsertProvider(type=AccountSqlProvider.class, method="insertSelective")
    int insertSelective(Account record);

    @Select({
        "select",
        "id, account_name, code, level, parent_id, is_cash, is_bank, is_detail",
        "from LEM_ACCOUNT",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_cash", property="isCash", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_bank", property="isBank", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_detail", property="isDetail", jdbcType=JdbcType.VARCHAR)
    })
    Account selectByPrimaryKey(String id);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Account record);

    @Update({
        "update LEM_ACCOUNT",
        "set account_name = #{accountName,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=VARCHAR},",
          "is_cash = #{isCash,jdbcType=VARCHAR},",
          "is_bank = #{isBank,jdbcType=VARCHAR},",
          "is_detail = #{isDetail,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Account record);

    @SelectProvider(type=AccountSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_cash", property="isCash", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_bank", property="isBank", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_detail", property="isDetail", jdbcType=JdbcType.VARCHAR)
    })
    List<Account> findAllByCondition(AccountVo accountVo);

    @Select("select * from LEM_ACCOUNT where level < #{level,jdbcType=VARCHAR} and is_detail = FALSE")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_cash", property="isCash", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_bank", property="isBank", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_detail", property="isDetail", jdbcType=JdbcType.VARCHAR)
    })
    List<Account> findUpperAccount(String level);

    @Select("select * from LEM_ACCOUNT where parent_id = #{parentId,jdbcType=VARCHAR}")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_cash", property="isCash", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_bank", property="isBank", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_detail", property="isDetail", jdbcType=JdbcType.VARCHAR)
    })
    List<Account> findByParentId(String parentId);

    @InsertProvider(type=AccountSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<Account> accounts);

    @Select("select * from LEM_ACCOUNT where code = #{code,jdbcType=VARCHAR} and is_detail = TRUE")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_cash", property="isCash", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_bank", property="isBank", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_detail", property="isDetail", jdbcType=JdbcType.VARCHAR)
    })
    Account findByCode(String code);

    @Select("select * from LEM_ACCOUNT where is_detail = 'TRUE' ")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="account_name", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_cash", property="isCash", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_bank", property="isBank", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_detail", property="isDetail", jdbcType=JdbcType.VARCHAR)
    })
    List<Account> findAllDetailAccount();
}