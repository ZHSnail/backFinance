package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.PayDetail;
import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.vo.PayDetailVo;
import com.zhsnail.finance.vo.PayNoticeVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PayDetailMapper {
    @Delete({
        "delete from CAM_PAY_DETAIL",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_PAY_DETAIL (id, memo, ",
        "amount, pay_date, ",
        "status, fee_method, ",
        "code, user_id, create_time, ",
        "creater, updater, ",
        "update_time, pay_notice_id)",
        "values (#{id,jdbcType=VARCHAR}, #{memo,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{payDate,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{feeMethod,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{updater,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{payNoticeId,jdbcType=VARCHAR})"
    })
    int insert(PayDetail record);

    @InsertProvider(type=PayDetailSqlProvider.class, method="insertSelective")
    int insertSelective(PayDetail record);

    @Select({
        "select",
        "id, memo, amount, pay_date, status, fee_method, code, user_id, create_time, ",
        "creater, updater, update_time, pay_notice_id",
        "from CAM_PAY_DETAIL",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="pay_date", property="payDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="fee_method", property="feeMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_notice_id", property="payNoticeId", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_notice_id",property="payNotice",one=@One(select="com.zhsnail.finance.mapper.PayNoticeMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    PayDetail selectByPrimaryKey(String id);

    @UpdateProvider(type=PayDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PayDetail record);

    @Update({
        "update CAM_PAY_DETAIL",
        "set memo = #{memo,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "pay_date = #{payDate,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=VARCHAR},",
          "fee_method = #{feeMethod,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "updater = #{updater,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "pay_notice_id = #{payNoticeId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PayDetail record);

    @InsertProvider(type=PayDetailSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<PayDetail> payDetails);
    @Select({
            "select",
            "id, memo, amount, pay_date, status, fee_method, code, user_id, create_time, ",
            "creater, updater, update_time, pay_notice_id",
            "from CAM_PAY_DETAIL",
            "where user_id = #{userId,jdbcType=VARCHAR}  ORDER BY create_time DESC "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_date", property="payDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="fee_method", property="feeMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_notice_id", property="payNoticeId", jdbcType=JdbcType.VARCHAR),
            @Result(column="pay_notice_id",property="payNotice",one=@One(select="com.zhsnail.finance.mapper.PayNoticeMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<PayDetail> findByUserId(String userId);

    @SelectProvider(type=PayDetailSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="pay_date", property="payDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="fee_method", property="feeMethod", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_notice_id", property="payNoticeId", jdbcType=JdbcType.VARCHAR),
            @Result(column="pay_notice_id",property="payNotice",one=@One(select="com.zhsnail.finance.mapper.PayNoticeMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<PayDetail> findAllByCondition(PayDetailVo payDetailVo);
}