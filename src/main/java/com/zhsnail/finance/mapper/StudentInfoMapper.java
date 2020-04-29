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
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_STUDENT_INFO (id, name, ",
        "stu_no, stu_class, ",
        "dorm_id, profession_id)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{stuNo,jdbcType=VARCHAR}, #{stuClass,jdbcType=VARCHAR}, ",
        "#{dormId,jdbcType=VARCHAR}, #{professionId,jdbcType=VARCHAR})"
    })
    int insert(StudentInfo record);

    @InsertProvider(type=StudentInfoSqlProvider.class, method="insertSelective")
    int insertSelective(StudentInfo record);

    @Select({
        "select",
        "id, name, stu_no, stu_class, dorm_id, profession_id",
        "from CAM_STUDENT_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
        @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    StudentInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=StudentInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StudentInfo record);

    @Update({
        "update CAM_STUDENT_INFO",
        "set name = #{name,jdbcType=VARCHAR},",
          "stu_no = #{stuNo,jdbcType=VARCHAR},",
          "stu_class = #{stuClass,jdbcType=VARCHAR},",
          "dorm_id = #{dormId,jdbcType=VARCHAR},",
          "profession_id = #{professionId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(StudentInfo record);
}