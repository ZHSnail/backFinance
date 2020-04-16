package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PermissionMapper {
    @Delete({
        "delete from SYM_PERMISSION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_PERMISSION (id, perm_name, ",
        "parent_id, perm_level, ",
        "url, perm_order, ",
        "icon, status)",
        "values (#{id,jdbcType=VARCHAR}, #{permName,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=VARCHAR}, #{permLevel,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{permOrder,jdbcType=VARCHAR}, ",
        "#{icon,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR})"
    })
    int insert(Permission record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    int insertSelective(Permission record);

    @Select({
        "select",
        "id, perm_name, parent_id, perm_level, url, perm_order, icon, status",
        "from SYM_PERMISSION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="perm_name", property="permName", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="perm_level", property="permLevel", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="perm_order", property="permOrder", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    Permission selectByPrimaryKey(String id);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update SYM_PERMISSION",
        "set perm_name = #{permName,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=VARCHAR},",
          "perm_level = #{permLevel,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "perm_order = #{permOrder,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Permission record);
    @Select({
            "select * from SYM_PERMISSION " ,
            "where id in (" ,
            "select perm_id from SYM_PERMISSION_ROLE " ,
            "where role_id = #{roleId,jdbcType=VARCHAR})",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="perm_name", property="permName", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="perm_level", property="permLevel", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="perm_order", property="permOrder", jdbcType=JdbcType.VARCHAR),
            @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR)
    })
    List<Permission> findByRoleId(String roleId);
}