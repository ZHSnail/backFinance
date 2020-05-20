package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StaffInfo;
import com.zhsnail.finance.vo.StaffInfoVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class StaffInfoSqlProvider {

    public String insertSelective(StaffInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_STAFF_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getStaffNumber() != null) {
            sql.VALUES("staff_number", "#{staffNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDegree() != null) {
            sql.VALUES("degree", "#{degree,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNumber() != null) {
            sql.VALUES("card_number", "#{cardNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getEntryDate() != null) {
            sql.VALUES("entry_date", "#{entryDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrgId() != null) {
            sql.VALUES("org_id", "#{orgId,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getMail() != null) {
            sql.VALUES("mail", "#{mail,jdbcType=VARCHAR}");
        }
        
        if (record.getStationInfoId() != null) {
            sql.VALUES("station_info_id", "#{stationInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getPostTitle() != null) {
            sql.VALUES("post_title", "#{postTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPayStubInfoId() != null) {
            sql.VALUES("pay_stub_info_id", "#{payStubInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getStationLv() != null) {
            sql.VALUES("station_lv", "#{stationLv,jdbcType=VARCHAR}");
        }
        
        if (record.getScaleLv() != null) {
            sql.VALUES("scale_lv", "#{scaleLv,jdbcType=VARCHAR}");
        }
        
        if (record.getPostWageAmount() != null) {
            sql.VALUES("post_wage_amount", "#{postWageAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getSettleWageId() != null) {
            sql.VALUES("settle_wage_id", "#{settleWageId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StaffInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_STAFF_INFO");
        
        if (record.getStaffNumber() != null) {
            sql.SET("staff_number = #{staffNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDegree() != null) {
            sql.SET("degree = #{degree,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNumber() != null) {
            sql.SET("card_number = #{cardNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getEntryDate() != null) {
            sql.SET("entry_date = #{entryDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrgId() != null) {
            sql.SET("org_id = #{orgId,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getMail() != null) {
            sql.SET("mail = #{mail,jdbcType=VARCHAR}");
        }
        
        if (record.getStationInfoId() != null) {
            sql.SET("station_info_id = #{stationInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getPostTitle() != null) {
            sql.SET("post_title = #{postTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPayStubInfoId() != null) {
            sql.SET("pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getStationLv() != null) {
            sql.SET("station_lv = #{stationLv,jdbcType=VARCHAR}");
        }
        
        if (record.getScaleLv() != null) {
            sql.SET("scale_lv = #{scaleLv,jdbcType=VARCHAR}");
        }
        
        if (record.getPostWageAmount() != null) {
            sql.SET("post_wage_amount = #{postWageAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getSettleWageId() != null) {
            sql.SET("settle_wage_id = #{settleWageId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(StaffInfoVo staffInfoVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("SAM_STAFF_INFO");
        if (staffInfoVo!=null){
            if (StringUtils.isNotBlank(staffInfoVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
            if (CollectionUtils.isNotEmpty(staffInfoVo.getEntryDateList())){
                List<String> entryDateList = staffInfoVo.getEntryDateList();
                sql.WHERE("entry_date > '"+entryDateList.get(0)+"' and entry_date < '"+entryDateList.get(1)+"'");
            }
        }
        sql.ORDER_BY("entry_date desc");
        return sql.toString();
    }

}