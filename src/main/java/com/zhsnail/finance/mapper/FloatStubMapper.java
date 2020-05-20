package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FloatStub;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FloatStubMapper {
    @Delete({
        "delete from SAM_FLOAT_STUB",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_FLOAT_STUB (id, pay_stub_info_id, ",
        "float_wage_id)",
        "values (#{id,jdbcType=VARCHAR}, #{payStubInfoId,jdbcType=VARCHAR}, ",
        "#{floatWageId,jdbcType=VARCHAR})"
    })
    int insert(FloatStub record);

    @InsertProvider(type=FloatStubSqlProvider.class, method="insertSelective")
    int insertSelective(FloatStub record);

    @Select({
        "select",
        "id, pay_stub_info_id, float_wage_id",
        "from SAM_FLOAT_STUB",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR),
        @Result(column="float_wage_id", property="floatWageId", jdbcType=JdbcType.VARCHAR)
    })
    FloatStub selectByPrimaryKey(String id);

    @UpdateProvider(type=FloatStubSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FloatStub record);

    @Update({
        "update SAM_FLOAT_STUB",
        "set pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR},",
          "float_wage_id = #{floatWageId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FloatStub record);

    @Delete({
            "delete from SAM_FLOAT_STUB",
            "where pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}"
    })
    void deleteByPayStubInfoId(String payStubInfoId);

    @InsertProvider(type=FloatStubSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<FloatStub> floatStubList);
}