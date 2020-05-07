package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SysSequence;
import org.apache.ibatis.jdbc.SQL;

public class SysSequenceSqlProvider {

    public String insertSelective(SysSequence record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYS_SEQUENCE");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("value", "#{value,jdbcType=INTEGER}");
        }
        
        if (record.getStep() != null) {
            sql.VALUES("step", "#{step,jdbcType=SMALLINT}");
        }
        
        if (record.getStart() != null) {
            sql.VALUES("start", "#{start,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SysSequence record) {
        SQL sql = new SQL();
        sql.UPDATE("SYS_SEQUENCE");
        
        if (record.getValue() != null) {
            sql.SET("value = #{value,jdbcType=INTEGER}");
        }
        
        if (record.getStep() != null) {
            sql.SET("step = #{step,jdbcType=SMALLINT}");
        }
        
        if (record.getStart() != null) {
            sql.SET("start = #{start,jdbcType=INTEGER}");
        }
        
        sql.WHERE("name = #{name,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}