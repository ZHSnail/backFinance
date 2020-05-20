package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.OrgInfo;
import com.zhsnail.finance.vo.OrgInfoVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface OrgInfoMapper {
    @Delete({
        "delete from SAM_ORG_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_ORG_INFO (id, code, ",
        "name, state)",
        "values (#{id,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR})"
    })
    int insert(OrgInfo record);

    @InsertProvider(type=OrgInfoSqlProvider.class, method="insertSelective")
    int insertSelective(OrgInfo record);

    @Select({
        "select",
        "id, code, name, state",
        "from SAM_ORG_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR)
    })
    OrgInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=OrgInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrgInfo record);

    @Update({
        "update SAM_ORG_INFO",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(OrgInfo record);

    @SelectProvider(type=OrgInfoSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR)
    })
    List<OrgInfo> findAllByCondition(OrgInfoVo orgInfoVo);
}