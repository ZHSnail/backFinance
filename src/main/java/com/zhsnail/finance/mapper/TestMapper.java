package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Test;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TestMapper {
    @Delete({
        "delete from test",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into test (id, name, ",
        "account_id)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{accountId,jdbcType=VARCHAR})"
    })
    int insert(Test record);

    @InsertProvider(type=TestSqlProvider.class, method="insertSelective")
    int insertSelective(Test record);

    @Select({
        "select",
        "id, name, account_id",
        "from test",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR)
    })
    Test selectByPrimaryKey(String id);

    @UpdateProvider(type=TestSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Test record);

    @Update({
        "update test",
        "set name = #{name,jdbcType=VARCHAR},",
          "account_id = #{accountId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Test record);
}