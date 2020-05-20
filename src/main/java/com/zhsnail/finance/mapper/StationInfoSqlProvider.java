package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.StationInfo;
import com.zhsnail.finance.vo.StationInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class StationInfoSqlProvider {

    public String insertSelective(StationInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_STATION_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getStationSalaryId() != null) {
            sql.VALUES("station_salary_id", "#{stationSalaryId,jdbcType=VARCHAR}");
        }
        
        if (record.getScaleSalaryId() != null) {
            sql.VALUES("scale_salary_id", "#{scaleSalaryId,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitScaleAccId() != null) {
            sql.VALUES("debit_scale_acc_id", "#{debitScaleAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditScaleAccId() != null) {
            sql.VALUES("credit_scale_acc_id", "#{creditScaleAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitStationAccId() != null) {
            sql.VALUES("debit_station_acc_id", "#{debitStationAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditStationAccId() != null) {
            sql.VALUES("credit_station_acc_id", "#{creditStationAccId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(StationInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_STATION_INFO");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getStationSalaryId() != null) {
            sql.SET("station_salary_id = #{stationSalaryId,jdbcType=VARCHAR}");
        }
        
        if (record.getScaleSalaryId() != null) {
            sql.SET("scale_salary_id = #{scaleSalaryId,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitScaleAccId() != null) {
            sql.SET("debit_scale_acc_id = #{debitScaleAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditScaleAccId() != null) {
            sql.SET("credit_scale_acc_id = #{creditScaleAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitStationAccId() != null) {
            sql.SET("debit_station_acc_id = #{debitStationAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditStationAccId() != null) {
            sql.SET("credit_station_acc_id = #{creditStationAccId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(StationInfoVo stationInfoVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("SAM_STATION_INFO");
        if (stationInfoVo!=null){
            if (StringUtils.isNotBlank(stationInfoVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
            if (StringUtils.isNotBlank(stationInfoVo.getState())){
                sql.WHERE("state = #{state,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(stationInfoVo.getType())){
                sql.WHERE("type = #{type,jdbcType=VARCHAR}");
            }
        }
        sql.ORDER_BY("id");
        return sql.toString();
    }
}