package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from SYM_USER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SYM_USER (id, user_name, ",
        "psw, student_id, ",
        "staff_id)",
        "values (#{id,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{psw,jdbcType=VARCHAR}, #{studentId,jdbcType=VARCHAR}, ",
        "#{staffId,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, user_name, psw, student_id, staff_id",
        "from SYM_USER",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="psw", property="psw", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update SYM_USER",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "psw = #{psw,jdbcType=VARCHAR},",
          "student_id = #{studentId,jdbcType=VARCHAR},",
          "staff_id = #{staffId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(User record);
    @Select({
            "select * from SYM_USER where staff_id = #{staffId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType=JdbcType.VARCHAR),
            @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR)
    })
    User findByStaId(String staffId);

    @Select({
            "select * from SYM_USER where student_id = #{studentId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType=JdbcType.VARCHAR),
            @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR)
    })
    User findUserByStuId(String studentId);

    @Select({
            "select * from SYM_USER where user_name = #{userName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType=JdbcType.VARCHAR),
            @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR)
    })
    User findUserByUserName(String userName);
    @Select({
            "select * from SYM_USER where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType=JdbcType.VARCHAR),
            @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR),
            @Result(property = "roles",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.RoleMapper.findByUserId",fetchType = FetchType.LAZY))
    })
    User findUserRoleById(String id);

    @Select({
            "select * from SYM_USER where user_name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="psw", property="psw", jdbcType=JdbcType.VARCHAR),
            @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR),
            @Result(property = "roles",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.RoleMapper.findByUserId",fetchType = FetchType.LAZY))
    })
    User findUserInfo(String name);
}