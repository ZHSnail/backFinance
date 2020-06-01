package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.vo.StudentInfoVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

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
        @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR),
        @Result(property = "roles",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.RoleMapper.findByBizId",fetchType = FetchType.EAGER))
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

    @SelectProvider(type=StudentInfoSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
            @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="profession",one = @One(select = "com.zhsnail.finance.mapper.ProfessionMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
            @Result(column="dorm_id", property="dormInfo",one = @One(select = "com.zhsnail.finance.mapper.DormInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
            @Result(property = "roles",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.RoleMapper.findByBizId",fetchType = FetchType.EAGER))
    })
    List<StudentInfo> findAllByCondition(StudentInfoVo studentInfoVo);

    @Results({
            @Result(column = "total", javaType = Integer.class)
    })
    @Select({"SELECT COUNT(*) AS total FROM CAM_STUDENT_INFO where profession_id = #{professionId,jdbcType=VARCHAR}"})
    Integer countByProfessionId(String professionId);

    @Results(value = {
            @Result(column = "total", javaType = Integer.class)
    })
    @Select({"SELECT COUNT(*) AS total FROM CAM_STUDENT_INFO where dorm_id = #{dormId,jdbcType=VARCHAR}"})
    Integer countByDormId(String dormId);
    @Select({
            "select",
            "id, name, stu_no, stu_class, dorm_id, profession_id",
            "from CAM_STUDENT_INFO",
            "where profession_id = #{professionId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
            @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    List<StudentInfo> findByProfessionId(String professionId);
    @Select({
            "select",
            "id, name, stu_no, stu_class, dorm_id, profession_id",
            "from CAM_STUDENT_INFO",
            "where dorm_id = #{dormId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
            @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    List<StudentInfo> findByDormId(String dormId);

    @Select({
            "<script>",
            "select * ",
            "from CAM_STUDENT_INFO",
            "where dorm_id in",
            "<foreach collection='dormIds' item='dormId' open='(' separator=',' close=')'>",
            "#{dormId}",
            "</foreach>",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
            @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    List<StudentInfo> findByDormIds(@Param("dormIds") List<String> dormIds);

    @Select({
            "<script>",
            "select * ",
            "from CAM_STUDENT_INFO",
            "where profession_id in",
            "<foreach collection='professionIds' item='professionId' open='(' separator=',' close=')'>",
            "#{professionId}",
            "</foreach>",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
            @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    List<StudentInfo> findByProfessionIds(@Param("professionIds") List<String> professionIds);

    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_no", property="stuNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="stu_class", property="stuClass", jdbcType=JdbcType.VARCHAR),
            @Result(column="dorm_id", property="dormId", jdbcType=JdbcType.VARCHAR),
            @Result(column="profession_id", property="professionId", jdbcType=JdbcType.VARCHAR)
    })
    @Select({
            "select",
            "id, name, stu_no, stu_class, dorm_id, profession_id",
            "from CAM_STUDENT_INFO",
            "where name like concat('%', #{name,jdbcType=VARCHAR},'%')"
    })
    List<StudentInfo> findByName(String name);
}