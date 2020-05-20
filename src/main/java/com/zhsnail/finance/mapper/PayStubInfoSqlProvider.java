package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.entity.PayStubInfo;
import com.zhsnail.finance.vo.PayStubInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public class PayStubInfoSqlProvider {

    public String insertSelective(PayStubInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_PAY_STUB_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.VALUES("scope", "#{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getLastExeDate() != null) {
            sql.VALUES("last_exe_date", "#{lastExeDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRelatedNumber() != null) {
            sql.VALUES("related_number", "#{relatedNumber,jdbcType=DECIMAL}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PayStubInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_PAY_STUB_INFO");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.SET("scope = #{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getLastExeDate() != null) {
            sql.SET("last_exe_date = #{lastExeDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRelatedNumber() != null) {
            sql.SET("related_number = #{relatedNumber,jdbcType=DECIMAL}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(PayStubInfoVo payStubInfoVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("SAM_PAY_STUB_INFO");
        if (payStubInfoVo!=null){
            if (StringUtils.isNotBlank(payStubInfoVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
        }
        sql.ORDER_BY("id");
        return sql.toString();
    }

}