package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.vo.RoleVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface RoleMapper {
    @Delete({
        "delete from SYM_ROLE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_ROLE (id, role_name, ",
        "memo)",
        "values (#{id,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR}, ",
        "#{memo,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "id, role_name, memo",
        "from SYM_ROLE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(String id);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update SYM_ROLE",
        "set role_name = #{roleName,jdbcType=VARCHAR},",
          "memo = #{memo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Role record);

    @Select({
            "select * from SYM_ROLE " ,
            "where id in (" ,
            "select role_id from SYM_ROLE_USER " ,
             "where biz_id = #{bizId,jdbcType=VARCHAR})",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(property = "permissions",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.PermissionMapper.findByRoleId",fetchType = FetchType.EAGER)),
            @Result(property = "operations",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.OperationMapper.findByRoleId",fetchType = FetchType.EAGER))
    })
    List<Role> findByBizId(String bizId);

    @Select({
            "select",
            "id, role_name, memo",
            "from SYM_ROLE"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> findAllRole();

    @SelectProvider(type=RoleSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> findAllByCondition(RoleVo roleVo);

    /*@Select({
            "select * from SYM_ROLE where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(property = "permissions",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.PermissionMapper.findByRoleId",fetchType = FetchType.LAZY))
    })
    Role findRolePermission(String id);*/

}