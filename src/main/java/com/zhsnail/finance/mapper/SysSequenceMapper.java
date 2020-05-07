package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SysSequence;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SysSequenceMapper {
    @Delete({
        "delete from SYS_SEQUENCE",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String name);

    @Insert({
        "insert into SYS_SEQUENCE (name, value, ",
        "step, start)",
        "values (#{name,jdbcType=VARCHAR}, #{value,jdbcType=INTEGER}, ",
        "#{step,jdbcType=SMALLINT}, #{start,jdbcType=INTEGER})"
    })
    int insert(SysSequence record);

    @InsertProvider(type=SysSequenceSqlProvider.class, method="insertSelective")
    int insertSelective(SysSequence record);

    @Select({
        "select",
        "name, value, step, start",
        "from SYS_SEQUENCE",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="value", property="value", jdbcType=JdbcType.INTEGER),
        @Result(column="step", property="step", jdbcType=JdbcType.SMALLINT),
        @Result(column="start", property="start", jdbcType=JdbcType.INTEGER)
    })
    SysSequence selectByPrimaryKey(String name);

    @UpdateProvider(type=SysSequenceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysSequence record);

    @Update({
        "update SYS_SEQUENCE",
        "set value = #{value,jdbcType=INTEGER},",
          "step = #{step,jdbcType=SMALLINT},",
          "start = #{start,jdbcType=INTEGER}",
        "where name = #{name,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysSequence record);

    @Select({ "call generateSeq(#{name,mode=IN,jdbcType=VARCHAR},"
            + "#{value,jdbcType=INTEGER,mode=OUT})"})
    @Options(statementType= StatementType.CALLABLE)
    @ResultType(Integer.class)
    int getSeq(SysSequence sysSequence);

    @Select({
            "select",
            "name, value, step, start",
            "from SYS_SEQUENCE",
    })
    @Results({
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="value", property="value", jdbcType=JdbcType.INTEGER),
            @Result(column="step", property="step", jdbcType=JdbcType.SMALLINT),
            @Result(column="start", property="start", jdbcType=JdbcType.INTEGER)
    })
    List<SysSequence> findAll();
}