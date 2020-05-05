package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.vo.ProfessionVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProfessionMapper {
    @Delete({
        "delete from CAM_PROFESSION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_PROFESSION (id, name, ",
        "is_leaf, parent_id, ",
        "grade)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{isLeaf,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{grade,jdbcType=VARCHAR})"
    })
    int insert(Profession record);

    @InsertProvider(type=ProfessionSqlProvider.class, method="insertSelective")
    int insertSelective(Profession record);

    @Select({
        "select",
        "id, name, is_leaf, parent_id, grade",
        "from CAM_PROFESSION",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_leaf", property="isLeaf", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.VARCHAR)
    })
    Profession selectByPrimaryKey(String id);

    @UpdateProvider(type=ProfessionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Profession record);

    @Update({
        "update CAM_PROFESSION",
        "set name = #{name,jdbcType=VARCHAR},",
          "is_leaf = #{isLeaf,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=VARCHAR},",
          "grade = #{grade,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Profession record);

    @Select({
            "select * from CAM_PROFESSION",
            "where is_leaf = 'FALSE'"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_leaf", property="isLeaf", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.VARCHAR)
    })
    List<Profession> findParentProession();

    @Select({
            "select",
            "id, name, is_leaf, parent_id, grade",
            "from CAM_PROFESSION",
            "where parent_id = #{parentId,jdbcType=VARCHAR} order by id "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_leaf", property="isLeaf", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.VARCHAR)
    })
    List<Profession> findByParentId(String parentId);

    @SelectProvider(type=ProfessionSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_leaf", property="isLeaf", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType=JdbcType.VARCHAR),
            @Result(column="id",property="total",one=@One(select="com.zhsnail.finance.mapper.StudentInfoMapper.countByProfessionId",fetchType= FetchType.EAGER)),
    })
    List<Profession> findAllByCondition(ProfessionVo professionVo);
}