package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Assets;
import org.apache.ibatis.jdbc.SQL;

public class AssetsSqlProvider {

    public String insertSelective(Assets record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ASM_ASSETS");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getAssetsKindId() != null) {
            sql.VALUES("assets_kind_id", "#{assetsKindId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getStorageTime() != null) {
            sql.VALUES("storage_time", "#{storageTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDepreMethod() != null) {
            sql.VALUES("depre_method", "#{depreMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLossReport() != null) {
            sql.VALUES("loss_report", "#{lossReport,jdbcType=VARCHAR}");
        }
        
        if (record.getUsefulLife() != null) {
            sql.VALUES("useful_life", "#{usefulLife,jdbcType=VARCHAR}");
        }
        
        if (record.getStoragePlace() != null) {
            sql.VALUES("storage_place", "#{storagePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getNorms() != null) {
            sql.VALUES("norms", "#{norms,jdbcType=VARCHAR}");
        }
        
        if (record.getOrival() != null) {
            sql.VALUES("orival", "#{orival,jdbcType=DECIMAL}");
        }
        
        if (record.getSalvage() != null) {
            sql.VALUES("salvage", "#{salvage,jdbcType=DECIMAL}");
        }
        
        if (record.getLossReportTime() != null) {
            sql.VALUES("loss_report_time", "#{lossReportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCleanCost() != null) {
            sql.VALUES("clean_cost", "#{cleanCost,jdbcType=DECIMAL}");
        }
        
        if (record.getNum() != null) {
            sql.VALUES("num", "#{num,jdbcType=VARCHAR}");
        }
        
        if (record.getObtainMethod() != null) {
            sql.VALUES("obtain_method", "#{obtainMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseId() != null) {
            sql.VALUES("purchase_id", "#{purchaseId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Assets record) {
        SQL sql = new SQL();
        sql.UPDATE("ASM_ASSETS");
        
        if (record.getAssetsKindId() != null) {
            sql.SET("assets_kind_id = #{assetsKindId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getStorageTime() != null) {
            sql.SET("storage_time = #{storageTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDepreMethod() != null) {
            sql.SET("depre_method = #{depreMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLossReport() != null) {
            sql.SET("loss_report = #{lossReport,jdbcType=VARCHAR}");
        }
        
        if (record.getUsefulLife() != null) {
            sql.SET("useful_life = #{usefulLife,jdbcType=VARCHAR}");
        }
        
        if (record.getStoragePlace() != null) {
            sql.SET("storage_place = #{storagePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getNorms() != null) {
            sql.SET("norms = #{norms,jdbcType=VARCHAR}");
        }
        
        if (record.getOrival() != null) {
            sql.SET("orival = #{orival,jdbcType=DECIMAL}");
        }
        
        if (record.getSalvage() != null) {
            sql.SET("salvage = #{salvage,jdbcType=DECIMAL}");
        }
        
        if (record.getLossReportTime() != null) {
            sql.SET("loss_report_time = #{lossReportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCleanCost() != null) {
            sql.SET("clean_cost = #{cleanCost,jdbcType=DECIMAL}");
        }
        
        if (record.getNum() != null) {
            sql.SET("num = #{num,jdbcType=VARCHAR}");
        }
        
        if (record.getObtainMethod() != null) {
            sql.SET("obtain_method = #{obtainMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseId() != null) {
            sql.SET("purchase_id = #{purchaseId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}