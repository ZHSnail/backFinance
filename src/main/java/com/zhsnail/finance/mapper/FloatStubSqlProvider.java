package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.FloatStub;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class FloatStubSqlProvider {

    public String insertSelective(FloatStub record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_FLOAT_STUB");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getPayStubInfoId() != null) {
            sql.VALUES("pay_stub_info_id", "#{payStubInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getFloatWageId() != null) {
            sql.VALUES("float_wage_id", "#{floatWageId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FloatStub record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_FLOAT_STUB");
        
        if (record.getPayStubInfoId() != null) {
            sql.SET("pay_stub_info_id = #{payStubInfoId,jdbcType=VARCHAR}");
        }
        
        if (record.getFloatWageId() != null) {
            sql.SET("float_wage_id = #{floatWageId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<FloatStub>> map ){
        List<FloatStub> floatStubs = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO SAM_FLOAT_STUB ");
        sb.append("(id, pay_stub_info_id,float_wage_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].payStubInfoId}, #'{'list[{0}].floatWageId})");
        for (int i = 0; i < floatStubs.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < floatStubs.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}