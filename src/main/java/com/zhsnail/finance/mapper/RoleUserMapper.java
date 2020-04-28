package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.RoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoleUserMapper {
    @Delete({
        "delete from SYM_ROLE_USER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_ROLE_USER (id, role_id, ",
        "user_id)",
        "values (#{id,jdbcType=VARCHAR}, #{roleId,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=VARCHAR})"
    })
    int insert(RoleUser record);

    @InsertProvider(type=RoleUserSqlProvider.class, method="insertSelective")
    int insertSelective(RoleUser record);

    @Select({
        "select",
        "id, role_id, user_id",
        "from SYM_ROLE_USER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR)
    })
    RoleUser selectByPrimaryKey(String id);

    @UpdateProvider(type=RoleUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleUser record);

    @Update({
        "update SYM_ROLE_USER",
        "set role_id = #{roleId,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(RoleUser record);
}