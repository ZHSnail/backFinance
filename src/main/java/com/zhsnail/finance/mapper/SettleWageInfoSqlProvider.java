package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SettleWageInfo;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class SettleWageInfoSqlProvider {

    public String insertSelective(SettleWageInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_SETTLE_WAGE_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getExeDate() != null) {
            sql.VALUES("exe_date", "#{exeDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getSalaryPeriod() != null) {
            sql.VALUES("salary_period", "#{salaryPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalAmount() != null) {
            sql.VALUES("total_amount", "#{totalAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getRelatedNumber() != null) {
            sql.VALUES("related_number", "#{relatedNumber,jdbcType=DECIMAL}");
        }
        
        if (record.getSuccessNumber() != null) {
            sql.VALUES("success_number", "#{successNumber,jdbcType=DECIMAL}");
        }
        
        if (record.getPayStubInfoId() != null) {
            sql.VALUES("pay_stub_info_id", "#{payStubInfoId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SettleWageInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_SETTLE_WAGE_INFO");
        
        if (record.getExeDate() != null) {
            sql.SET("exe_date = #{exeDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getSalaryPeriod() != null) {
            sql.SET("salary_period = #{salaryPeriod,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalAmount() != null) {
            sql.SET("total_amount = #{totalAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getRelatedNumber() != null) {
            sql.SET("related_number = #{relatedNumber,jdbcType=DECIMAL}");
        }
        
        if (record.getSuccessNumber() != null) {
            sql.SET("success_number = #{successNumber,jdbcType=DECIMAL}");
        }
        
        if (record.getPayStubInfoId() != null) {
            sql.SET("pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<SettleWageInfo>> map ){
        List<SettleWageInfo> accounts = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO SAM_SETTLE_WAGE_INFO ");
        sb.append("(id, exe_date,name,salary_period,total_amount," +
                "related_number,success_number,pay_stub_info_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].exeDate}, #'{'list[{0}].name}" +
                ",#'{'list[{0}].salaryPeriod},#'{'list[{0}].totalAmount}," +
                "#'{'list[{0}].relatedNumber},#'{'list[{0}].successNumber},#'{'list[{0}].payStubInfoId})");
        for (int i = 0; i < accounts.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < accounts.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}