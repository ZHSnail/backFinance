package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StaffInfo;
import com.zhsnail.finance.vo.StaffInfoVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface StaffInfoMapper {
    @Delete({
        "delete from SAM_STAFF_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into SAM_STAFF_INFO (id, staff_number, ",
        "name, degree, card_number, ",
        "entry_date, org_id, ",
        "mobile, mail, station_info_id, ",
        "post_title, user_name, ",
        "pay_stub_info_id, station_lv, ",
        "scale_lv, post_wage_amount, ",
        "settle_wage_id)",
        "values (#{id,jdbcType=VARCHAR}, #{staffNumber,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{degree,jdbcType=VARCHAR}, #{cardNumber,jdbcType=VARCHAR}, ",
        "#{entryDate,jdbcType=TIMESTAMP}, #{orgId,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, #{stationInfoId,jdbcType=VARCHAR}, ",
        "#{postTitle,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{payStubInfoId,jdbcType=VARCHAR}, #{stationLv,jdbcType=VARCHAR}, ",
        "#{scaleLv,jdbcType=VARCHAR}, #{postWageAmount,jdbcType=DECIMAL}, ",
        "#{settleWageId,jdbcType=VARCHAR})"
    })
    int insert(StaffInfo record);

    @InsertProvider(type=StaffInfoSqlProvider.class, method="insertSelective")
    int insertSelective(StaffInfo record);

    @Select({
        "select",
        "id, staff_number, name, degree, card_number, entry_date, org_id, mobile, mail, ",
        "station_info_id, post_title, user_name, pay_stub_info_id, station_lv, scale_lv, ",
        "post_wage_amount, settle_wage_id",
        "from SAM_STAFF_INFO",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="staff_number", property="staffNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="degree", property="degree", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_number", property="cardNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="entry_date", property="entryDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
        @Result(column="station_info_id", property="stationInfoId", jdbcType=JdbcType.VARCHAR),
        @Result(column="post_title", property="postTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR),
        @Result(column="station_lv", property="stationLv", jdbcType=JdbcType.VARCHAR),
        @Result(column="scale_lv", property="scaleLv", jdbcType=JdbcType.VARCHAR),
        @Result(column="post_wage_amount", property="postWageAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="settle_wage_id", property="settleWageId", jdbcType=JdbcType.VARCHAR),
        @Result(column="station_info_id",property="stationInfo",one=@One(select="com.zhsnail.finance.mapper.StationInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(column="pay_stub_info_id",property="payStubInfo",one=@One(select="com.zhsnail.finance.mapper.PayStubInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
        @Result(property="bankInfoList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.BankInfoMapper.findByStaffId",fetchType = FetchType.EAGER)),
        @Result(property = "roles",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.RoleMapper.findByBizId",fetchType = FetchType.EAGER)),
        @Result(column="org_id",property="orgInfo",one=@One(select="com.zhsnail.finance.mapper.OrgInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    StaffInfo selectByPrimaryKey(String id);

    @UpdateProvider(type=StaffInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StaffInfo record);

    @Update({
        "update SAM_STAFF_INFO",
        "set staff_number = #{staffNumber,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "degree = #{degree,jdbcType=VARCHAR},",
          "card_number = #{cardNumber,jdbcType=VARCHAR},",
          "entry_date = #{entryDate,jdbcType=TIMESTAMP},",
          "org_id = #{orgId,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "mail = #{mail,jdbcType=VARCHAR},",
          "station_info_id = #{stationInfoId,jdbcType=VARCHAR},",
          "post_title = #{postTitle,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR},",
          "station_lv = #{stationLv,jdbcType=VARCHAR},",
          "scale_lv = #{scaleLv,jdbcType=VARCHAR},",
          "post_wage_amount = #{postWageAmount,jdbcType=DECIMAL},",
          "settle_wage_id = #{settleWageId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(StaffInfo record);
    @SelectProvider(type=StaffInfoSqlProvider.class, method="selectAllConditionSql")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="staff_number", property="staffNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="degree", property="degree", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_number", property="cardNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="entry_date", property="entryDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_info_id", property="stationInfoId", jdbcType=JdbcType.VARCHAR),
            @Result(column="post_title", property="postTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_lv", property="stationLv", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale_lv", property="scaleLv", jdbcType=JdbcType.VARCHAR),
            @Result(column="post_wage_amount", property="postWageAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="settle_wage_id", property="settleWageId", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_info_id",property="stationInfo",one=@One(select="com.zhsnail.finance.mapper.StationInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
            @Result(column="pay_stub_info_id",property="payStubInfo",one=@One(select="com.zhsnail.finance.mapper.PayStubInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
            @Result(property="bankInfoList",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.BankInfoMapper.findByStaffId",fetchType = FetchType.EAGER)),
            @Result(property = "roles",column = "id",many = @Many(select = "com.zhsnail.finance.mapper.RoleMapper.findByBizId",fetchType = FetchType.EAGER)),
            @Result(column="org_id",property="orgInfo",one=@One(select="com.zhsnail.finance.mapper.OrgInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<StaffInfo> findAllByCondition(StaffInfoVo staffInfoVo);

    @Select({
            "select",
            "id, staff_number, name, degree, card_number, entry_date, org_id, mobile, mail, ",
            "station_info_id, post_title, user_name, pay_stub_info_id, station_lv, scale_lv, ",
            "post_wage_amount, settle_wage_id",
            "from SAM_STAFF_INFO",
            "where pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="staff_number", property="staffNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="degree", property="degree", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_number", property="cardNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="entry_date", property="entryDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_info_id", property="stationInfoId", jdbcType=JdbcType.VARCHAR),
            @Result(column="post_title", property="postTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_lv", property="stationLv", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale_lv", property="scaleLv", jdbcType=JdbcType.VARCHAR),
            @Result(column="post_wage_amount", property="postWageAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="settle_wage_id", property="settleWageId", jdbcType=JdbcType.VARCHAR),
    })
    List<StaffInfo> findByPayStubInfoId(String payStubInfoId);

    @Select({
            "select",
            "id, staff_number, name, degree, card_number, entry_date, org_id, mobile, mail, ",
            "station_info_id, post_title, user_name, pay_stub_info_id, station_lv, scale_lv, ",
            "post_wage_amount, settle_wage_id",
            "from SAM_STAFF_INFO",
            "where settle_wage_id = #{settleWageId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="staff_number", property="staffNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="degree", property="degree", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_number", property="cardNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="entry_date", property="entryDate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_info_id", property="stationInfoId", jdbcType=JdbcType.VARCHAR),
            @Result(column="post_title", property="postTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pay_stub_info_id", property="payStubInfoId", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_lv", property="stationLv", jdbcType=JdbcType.VARCHAR),
            @Result(column="scale_lv", property="scaleLv", jdbcType=JdbcType.VARCHAR),
            @Result(column="post_wage_amount", property="postWageAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="settle_wage_id", property="settleWageId", jdbcType=JdbcType.VARCHAR),
            @Result(column="station_info_id",property="stationInfo",one=@One(select="com.zhsnail.finance.mapper.StationInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
            @Result(column="pay_stub_info_id",property="payStubInfo",one=@One(select="com.zhsnail.finance.mapper.PayStubInfoMapper.selectByPrimaryKey",fetchType= FetchType.EAGER)),
    })
    List<StaffInfo> findBySettleWageId(String settleWageId);

}