package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.DormInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DormInfoMapper {
    @Delete({
        "delete from CAM_DORM_INFO",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_DORM_INFO (ID, DORM_NUMBER, ",
        "BUILD_NUMBER)",
        "values (#{id,jdbcType=VARCHAR}, #{dormNumber,jdbcType=VARCHAR}, ",
        "#{buildNumber,jdbcType=VARCHAR})"
    })
    int insert(DormInfo record);

    @InsertProvider(type=DormInfoSqlProvider.class, method="insertSelective")
    int insertSelective(DormInfo record);

    @Select({
        "select",
        "ID, DORM_NUMBER, BUILD_NUMBER",
        "from CAM_DORM_INFO",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DORM_NUMBER", property="dormNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUILD_NUMBER", property="buildNumber", jdbcType=JdbcType.VARCHAR)
    })
    DormInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=DormInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DormInfo record);

    @Update({
        "update CAM_DORM_INFO",
        "set DORM_NUMBER = #{dormNumber,jdbcType=VARCHAR},",
          "BUILD_NUMBER = #{buildNumber,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DormInfo record);
}