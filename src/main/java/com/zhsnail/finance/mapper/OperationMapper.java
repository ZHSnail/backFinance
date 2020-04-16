package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Operation;
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

public interface OperationMapper {
    @Delete({
        "delete from SYM_OPERATION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_OPERATION (id, module, ",
        "operation_name, operation_url, ",
        "memo)",
        "values (#{id,jdbcType=VARCHAR}, #{module,jdbcType=VARCHAR}, ",
        "#{operationName,jdbcType=VARCHAR}, #{operationUrl,jdbcType=VARCHAR}, ",
        "#{memo,jdbcType=VARCHAR})"
    })
    int insert(Operation record);

    @InsertProvider(type=OperationSqlProvider.class, method="insertSelective")
    int insertSelective(Operation record);

    @Select({
        "select",
        "id, module, operation_name, operation_url, memo",
        "from SYM_OPERATION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="module", property="module", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation_name", property="operationName", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation_url", property="operationUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    Operation selectByPrimaryKey(String id);

    @UpdateProvider(type=OperationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Operation record);

    @Update({
        "update SYM_OPERATION",
        "set module = #{module,jdbcType=VARCHAR},",
          "operation_name = #{operationName,jdbcType=VARCHAR},",
          "operation_url = #{operationUrl,jdbcType=VARCHAR},",
          "memo = #{memo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Operation record);

    @Select({
            "select * from SYM_OPERATION " ,
            "where id in (" ,
            "select operation_id from SYM_OPERATION_ROLE " ,
            "where role_id = #{roleId,jdbcType=VARCHAR})",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="module", property="module", jdbcType=JdbcType.VARCHAR),
            @Result(column="operation_name", property="operationName", jdbcType=JdbcType.VARCHAR),
            @Result(column="operation_url", property="operationUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR)
    })
    List<Operation> findByRoleId(String roleId);
}