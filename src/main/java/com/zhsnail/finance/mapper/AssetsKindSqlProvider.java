package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsKind;
import org.apache.ibatis.jdbc.SQL;

public class AssetsKindSqlProvider {

    public String insertSelective(AssetsKind record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ASM_ASSETS_KIND");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitDepreAccId() != null) {
            sql.VALUES("debit_depre_acc_id", "#{debitDepreAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditDepreAccId() != null) {
            sql.VALUES("credit_depre_acc_id", "#{creditDepreAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getDepreMethod() != null) {
            sql.VALUES("depre_method", "#{depreMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAssetsAccId() != null) {
            sql.VALUES("debit_assets_acc_id", "#{debitAssetsAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAssetsAccId() != null) {
            sql.VALUES("credit_assets_acc_id", "#{creditAssetsAccId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AssetsKind record) {
        SQL sql = new SQL();
        sql.UPDATE("ASM_ASSETS_KIND");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitDepreAccId() != null) {
            sql.SET("debit_depre_acc_id = #{debitDepreAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditDepreAccId() != null) {
            sql.SET("credit_depre_acc_id = #{creditDepreAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getDepreMethod() != null) {
            sql.SET("depre_method = #{depreMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getDebitAssetsAccId() != null) {
            sql.SET("debit_assets_acc_id = #{debitAssetsAccId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAssetsAccId() != null) {
            sql.SET("credit_assets_acc_id = #{creditAssetsAccId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}