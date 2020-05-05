package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.DormInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface DormInfoMapper {
    @Delete({
        "delete from CAM_DORM_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_DORM_INFO (id, dorm_number, ",
        "build_number)",
        "values (#{id,jdbcType=VARCHAR}, #{dormNumber,jdbcType=VARCHAR}, ",
        "#{buildNumber,jdbcType=VARCHAR})"
    })
    int insert(DormInfo record);

    @InsertProvider(type=DormInfoSqlProvider.class, method="insertSelective")
    int insertSelective(DormInfo record);

    @Select({
        "select",
        "id, dorm_number, build_number",
        "from CAM_DORM_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="dorm_number", property="dormNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="build_number", property="buildNumber", jdbcType=JdbcType.VARCHAR)
    })
    DormInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=DormInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DormInfo record);

    @Update({
        "update CAM_DORM_INFO",
        "set dorm_number = #{dormNumber,jdbcType=VARCHAR},",
          "build_number = #{buildNumber,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DormInfo record);

    @Select({
            "select * from CAM_DORM_INFO",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="dorm_number", property="dormNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="build_number", property="buildNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="id",property="total",one=@One(select="com.zhsnail.finance.mapper.StudentInfoMapper.countByDormId",fetchType= FetchType.EAGER)),
    })
    List<DormInfo> findAll();
}