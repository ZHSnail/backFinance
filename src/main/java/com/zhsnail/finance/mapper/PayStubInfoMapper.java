package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.PayStubInfo;
import com.zhsnail.finance.vo.PayStubInfoVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PayStubInfoMapper {
    @Delete({
        "delete from SAM_PAY_STUB_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_PAY_STUB_INFO (id, name, ",
        "scope, last_exe_date, ",
        "related_number)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{scope,jdbcType=VARCHAR}, #{lastExeDate,jdbcType=TIMESTAMP}, ",
        "#{relatedNumber,jdbcType=DECIMAL})"
    })
    int insert(PayStubInfo record);

    @InsertProvider(type=PayStubInfoSqlProvider.class, method="insertSelective")
    int insertSelective(PayStubInfo record);

    @Select({
        "select",
        "id, name, scope, last_exe_date, related_number",
        "from SAM_PAY_STUB_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="scope", property="scope", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_exe_date", property="lastExeDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="related_number", property="relatedNumber", jdbcType=JdbcType.DECIMAL),
        @Result(property = "floatWageList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.FloatWageMapper.findByPayStubId",fetchType = FetchType.EAGER))
    })
    PayStubInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=PayStubInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PayStubInfo record);

    @Update({
        "update SAM_PAY_STUB_INFO",
        "set name = #{name,jdbcType=VARCHAR},",
          "scope = #{scope,jdbcType=VARCHAR},",
          "last_exe_date = #{lastExeDate,jdbcType=TIMESTAMP},",
          "related_number = #{relatedNumber,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PayStubInfo record);

    @SelectProvider(type=PayStubInfoSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="scope", property="scope", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_exe_date", property="lastExeDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="related_number", property="relatedNumber", jdbcType=JdbcType.DECIMAL),
            @Result(property = "floatWageList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.FloatWageMapper.findByPayStubId",fetchType = FetchType.EAGER))
    })
    List<PayStubInfo> findAllByCondition(PayStubInfoVo payStubInfoVo);
}