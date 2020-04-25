package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.ImportResult;
import org.apache.ibatis.jdbc.SQL;

public class ImportResultSqlProvider {

    public String insertSelective(ImportResult record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SYM_IMPORT_RESULT");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getErrResult() != null) {
            sql.VALUES("err_result", "#{errResult,jdbcType=VARCHAR}");
        }
        
        if (record.getSuccResult() != null) {
            sql.VALUES("succ_result", "#{succResult,jdbcType=VARCHAR}");
        }
        
        if (record.getFileId() != null) {
            sql.VALUES("file_id", "#{fileId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImportResult record) {
        SQL sql = new SQL();
        sql.UPDATE("SYM_IMPORT_RESULT");
        
        if (record.getErrResult() != null) {
            sql.SET("err_result = #{errResult,jdbcType=VARCHAR}");
        }
        
        if (record.getSuccResult() != null) {
            sql.SET("succ_result = #{succResult,jdbcType=VARCHAR}");
        }
        
        if (record.getFileId() != null) {
            sql.SET("file_id = #{fileId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}