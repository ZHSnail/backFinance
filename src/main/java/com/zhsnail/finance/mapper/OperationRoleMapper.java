package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.OperationRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OperationRoleMapper {
    @Delete({
        "delete from SYM_OPERATION_ROLE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_OPERATION_ROLE (id, operation_id, ",
        "role_id)",
        "values (#{id,jdbcType=VARCHAR}, #{operationId,jdbcType=VARCHAR}, ",
        "#{roleId,jdbcType=VARCHAR})"
    })
    int insert(OperationRole record);

    @InsertProvider(type=OperationRoleSqlProvider.class, method="insertSelective")
    int insertSelective(OperationRole record);

    @Select({
        "select",
        "id, operation_id, role_id",
        "from SYM_OPERATION_ROLE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="operation_id", property="operationId", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.VARCHAR)
    })
    OperationRole selectByPrimaryKey(String id);

    @UpdateProvider(type=OperationRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OperationRole record);

    @Update({
        "update SYM_OPERATION_ROLE",
        "set operation_id = #{operationId,jdbcType=VARCHAR},",
          "role_id = #{roleId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(OperationRole record);
}