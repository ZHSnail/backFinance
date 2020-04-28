package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.PermissionRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PermissionRoleMapper {
    @Delete({
        "delete from SYM_PERMISSION_ROLE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_PERMISSION_ROLE (id, role_id, ",
        "perm_id)",
        "values (#{id,jdbcType=VARCHAR}, #{roleId,jdbcType=VARCHAR}, ",
        "#{permId,jdbcType=VARCHAR})"
    })
    int insert(PermissionRole record);

    @InsertProvider(type=PermissionRoleSqlProvider.class, method="insertSelective")
    int insertSelective(PermissionRole record);

    @Select({
        "select",
        "id, role_id, perm_id",
        "from SYM_PERMISSION_ROLE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="perm_id", property="permId", jdbcType=JdbcType.VARCHAR)
    })
    PermissionRole selectByPrimaryKey(String id);

    @UpdateProvider(type=PermissionRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PermissionRole record);

    @Update({
        "update SYM_PERMISSION_ROLE",
        "set role_id = #{roleId,jdbcType=VARCHAR},",
          "perm_id = #{permId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PermissionRole record);
}