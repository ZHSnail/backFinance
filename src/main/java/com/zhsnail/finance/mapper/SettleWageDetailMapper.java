package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SettleWageDetail;
import com.zhsnail.finance.entity.SettleWageInfo;
import com.zhsnail.finance.entity.SettleWageDetail;
import com.zhsnail.finance.vo.SettleWageDetailVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SettleWageDetailMapper {
    @Delete({
        "delete from SAM_SETTLE_WAGE_DETAIL",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_SETTLE_WAGE_DETAIL (id, settle_wage_id, ",
        "staff_id, payable_amount, ",
        "taxable_amount, paid_amount, ",
        "deduction_amount)",
        "values (#{id,jdbcType=VARCHAR}, #{settleWageId,jdbcType=VARCHAR}, ",
        "#{staffId,jdbcType=VARCHAR}, #{payableAmount,jdbcType=DECIMAL}, ",
        "#{taxableAmount,jdbcType=DECIMAL}, #{paidAmount,jdbcType=DECIMAL}, ",
        "#{deductionAmount,jdbcType=DECIMAL})"
    })
    int insert(SettleWageDetail record);

    @InsertProvider(type=SettleWageDetailSqlProvider.class, method="insertSelective")
    int insertSelective(SettleWageDetail record);

    @Select({
        "select",
        "id, settle_wage_id, staff_id, payable_amount, taxable_amount, paid_amount, deduction_amount",
        "from SAM_SETTLE_WAGE_DETAIL",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="settle_wage_id", property="settleWageId", jdbcType=JdbcType.VARCHAR),
        @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR),
        @Result(column="payable_amount", property="payableAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="taxable_amount", property="taxableAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="paid_amount", property="paidAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="deduction_amount", property="deductionAmount", jdbcType=JdbcType.DECIMAL)
    })
    SettleWageDetail selectByPrimaryKey(String id);

    @UpdateProvider(type=SettleWageDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SettleWageDetail record);

    @Update({
        "update SAM_SETTLE_WAGE_DETAIL",
        "set settle_wage_id = #{settleWageId,jdbcType=VARCHAR},",
          "staff_id = #{staffId,jdbcType=VARCHAR},",
          "payable_amount = #{payableAmount,jdbcType=DECIMAL},",
          "taxable_amount = #{taxableAmount,jdbcType=DECIMAL},",
          "paid_amount = #{paidAmount,jdbcType=DECIMAL},",
          "deduction_amount = #{deductionAmount,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SettleWageDetail record);

    @InsertProvider(type=SettleWageDetailSqlProvider.class, method="batchinsertSql")
    void batchInsert(List<SettleWageDetail> settleWageDetailList);
    
    @SelectProvider(type=SettleWageDetailSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="settle_wage_id", property="settleWageId", jdbcType=JdbcType.VARCHAR),
            @Result(column="staff_id", property="staffId", jdbcType=JdbcType.VARCHAR),
            @Result(column="payable_amount", property="payableAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="taxable_amount", property="taxableAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="paid_amount", property="paidAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="deduction_amount", property="deductionAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="staff_id",property="staffInfo",one=@One(select="com.zhsnail.finance.mapper.StaffInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<SettleWageDetail> findAllByCondition(SettleWageDetailVo settleWageDetailVo);
}