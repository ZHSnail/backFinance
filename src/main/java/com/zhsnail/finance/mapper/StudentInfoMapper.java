package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StudentInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface StudentInfoMapper {
    @Delete({
        "delete from CAM_STUDENT_INFO",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_STUDENT_INFO (ID, NAME, ",
        "STU_NO, STU_CLASS, ",
        "DORM_ID, PROFESSION_ID)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{stuNo,jdbcType=VARCHAR}, #{stuClass,jdbcType=VARCHAR}, ",
        "#{dormId,jdbcType=VARCHAR}, #{professionId,jdbcType=VARCHAR})"
    })
    int insert(StudentInfo record);

    @InsertProvider(type=StudentInfoSqlProvider.class, method="insertSelective")
    int insertSelective(StudentInfo record);

    @Select({
        "select",
        "ID, NAME, STU_NO, STU_CLASS, DORM_ID, PROFESSION_ID",
        "from CAM_STUDENT_INFO",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="STU_NO", property="stuNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="STU_CLASS", property="stuClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="DORM_ID", property="dormId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROFESSION_ID", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    StudentInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=StudentInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentInfo record);

    @Update({
        "update CAM_STUDENT_INFO",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "STU_NO = #{stuNo,jdbcType=VARCHAR},",
          "STU_CLASS = #{stuClass,jdbcType=VARCHAR},",
          "DORM_ID = #{dormId,jdbcType=VARCHAR},",
          "PROFESSION_ID = #{professionId,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(StudentInfo record);
}