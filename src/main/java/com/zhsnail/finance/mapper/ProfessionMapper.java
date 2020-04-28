package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Profession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ProfessionMapper {
    @Delete({
        "delete from CAM_PROFESSION",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_PROFESSION (ID, NAME, ",
        "IS_LEAF, PARENT_ID, ",
        "GRADE)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{isLeaf,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{grade,jdbcType=VARCHAR})"
    })
    int insert(Profession record);

    @InsertProvider(type=ProfessionSqlProvider.class, method="insertSelective")
    int insertSelective(Profession record);

    @Select({
        "select",
        "ID, NAME, IS_LEAF, PARENT_ID, GRADE",
        "from CAM_PROFESSION",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_LEAF", property="isLeaf", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_ID", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="GRADE", property="grade", jdbcType=JdbcType.VARCHAR)
    })
    Profession selectByPrimaryKey(String id);

    @UpdateProvider(type=ProfessionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Profession record);

    @Update({
        "update CAM_PROFESSION",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "IS_LEAF = #{isLeaf,jdbcType=VARCHAR},",
          "PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "GRADE = #{grade,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Profession record);
}