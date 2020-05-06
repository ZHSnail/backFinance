package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FeeKind;
import com.zhsnail.finance.vo.FeeKindVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FeeKindMapper {
    @Delete({
        "delete from CAM_FEE_KIND",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_FEE_KIND (id, name, ",
        "time_mold, fee_method, ",
        "state, account_id)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{timeMold,jdbcType=VARCHAR}, #{feeMethod,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=VARCHAR}, #{accountId,jdbcType=VARCHAR})"
    })
    int insert(FeeKind record);

    @InsertProvider(type=FeeKindSqlProvider.class, method="insertSelective")
    int insertSelective(FeeKind record);

    @Select({
        "select",
        "id, name, time_mold, fee_method, state, account_id",
        "from CAM_FEE_KIND",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_mold", property="timeMold", jdbcType=JdbcType.VARCHAR),
        @Result(column="fee_method", property="feeMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR)
    })
    FeeKind selectByPrimaryKey(String id);

    @UpdateProvider(type=FeeKindSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeeKind record);

    @Update({
        "update CAM_FEE_KIND",
        "set name = #{name,jdbcType=VARCHAR},",
          "time_mold = #{timeMold,jdbcType=VARCHAR},",
          "fee_method = #{feeMethod,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "account_id = #{accountId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FeeKind record);

    @Select({
            "select",
            "id, name, time_mold, fee_method, state, account_id",
            "from CAM_FEE_KIND",
            "where state = 'TRUE' "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="time_mold", property="timeMold", jdbcType=JdbcType.VARCHAR),
            @Result(column="fee_method", property="feeMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id",property="account",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<FeeKind> findAll();

    @SelectProvider(type=FeeKindSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="time_mold", property="timeMold", jdbcType=JdbcType.VARCHAR),
            @Result(column="fee_method", property="feeMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="account_id",property="account",one=@One(select="com.zhsnail.finance.mapper.AccountMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<FeeKind> findByCondition(FeeKindVo feeKindVo);
}