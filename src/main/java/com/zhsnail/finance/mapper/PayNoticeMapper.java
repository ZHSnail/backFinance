package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.vo.PayNoticeVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

public interface PayNoticeMapper {
    @Delete({
        "delete from CAM_PAY_NOTICE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into CAM_PAY_NOTICE (id, total_amount, ",
        "account_id, org, ",
        "memo, total_user, ",
        "amount, fee_scope, ",
        "status, fee_kind_id, ",
        "code, period, dead_line_max, ",
        "dead_line_min, pay_detail_id, ",
        "create_time, creater, ",
        "updater, update_time)",
        "values (#{id,jdbcType=VARCHAR}, #{totalAmount,jdbcType=DECIMAL}, ",
        "#{accountId,jdbcType=VARCHAR}, #{org,jdbcType=VARCHAR}, ",
        "#{memo,jdbcType=VARCHAR}, #{totalUser,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{feeScope,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{feeKindId,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, #{period,jdbcType=VARCHAR}, #{deadLineMax,jdbcType=TIMESTAMP}, ",
        "#{deadLineMin,jdbcType=TIMESTAMP}, #{payDetailId,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{creater,jdbcType=VARCHAR}, ",
        "#{updater,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(PayNotice record);

    @InsertProvider(type=PayNoticeSqlProvider.class, method="insertSelective")
    int insertSelective(PayNotice record);

    @Select({
        "select",
        "id, total_amount, account_id, org, memo, total_user, amount, fee_scope, status, ",
        "fee_kind_id, code, period, dead_line_max, dead_line_min, pay_detail_id, create_time, ",
        "creater, updater, update_time",
        "from CAM_PAY_NOTICE",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="org", property="org", jdbcType=JdbcType.VARCHAR),
        @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_user", property="totalUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="fee_scope", property="feeScope", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="fee_kind_id", property="feeKindId", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="period", property="period", jdbcType=JdbcType.VARCHAR),
        @Result(column="dead_line_max", property="deadLineMax", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dead_line_min", property="deadLineMin", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pay_detail_id", property="payDetailId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fee_kind_id",property="feeKind",one=@One(select="com.zhsnail.finance.mapper.FeeKindMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    PayNotice selectByPrimaryKey(String id);

    @UpdateProvider(type=PayNoticeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PayNotice record);

    @Update({
        "update CAM_PAY_NOTICE",
        "set total_amount = #{totalAmount,jdbcType=DECIMAL},",
          "account_id = #{accountId,jdbcType=VARCHAR},",
          "org = #{org,jdbcType=VARCHAR},",
          "memo = #{memo,jdbcType=VARCHAR},",
          "total_user = #{totalUser,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DECIMAL},",
          "fee_scope = #{feeScope,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "fee_kind_id = #{feeKindId,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "period = #{period,jdbcType=VARCHAR},",
          "dead_line_max = #{deadLineMax,jdbcType=TIMESTAMP},",
          "dead_line_min = #{deadLineMin,jdbcType=TIMESTAMP},",
          "pay_detail_id = #{payDetailId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "creater = #{creater,jdbcType=VARCHAR},",
          "updater = #{updater,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PayNotice record);

    @SelectProvider(type=PayNoticeSqlProvider.class, method="findTaskListSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.VARCHAR),
            @Result(column="org", property="org", jdbcType=JdbcType.VARCHAR),
            @Result(column="memo", property="memo", jdbcType=JdbcType.VARCHAR),
            @Result(column="total_user", property="totalUser", jdbcType=JdbcType.VARCHAR),
            @Result(column="amount", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="fee_scope", property="feeScope", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="fee_kind_id", property="feeKindId", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="period", property="period", jdbcType=JdbcType.VARCHAR),
            @Result(column="dead_line_max", property="deadLineMax", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="dead_line_min", property="deadLineMin", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="pay_detail_id", property="payDetailId", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
            @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
    })
    PayNotice findTaskList(PayNoticeVo payNoticeVo);
}