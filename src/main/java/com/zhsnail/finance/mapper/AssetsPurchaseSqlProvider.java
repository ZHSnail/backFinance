package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.AssetsPurchase;
import org.apache.ibatis.jdbc.SQL;

public class AssetsPurchaseSqlProvider {

    public String insertSelective(AssetsPurchase record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ASM_ASSETS_PURCHASE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.VALUES("creater", "#{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdater() != null) {
            sql.VALUES("updater", "#{updater,jdbcType=VARCHAR}");
        }
        
        if (record.getReqTime() != null) {
            sql.VALUES("req_time", "#{reqTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPurchaseMethod() != null) {
            sql.VALUES("purchase_method", "#{purchaseMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AssetsPurchase record) {
        SQL sql = new SQL();
        sql.UPDATE("ASM_ASSETS_PURCHASE");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("creater = #{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdater() != null) {
            sql.SET("updater = #{updater,jdbcType=VARCHAR}");
        }
        
        if (record.getReqTime() != null) {
            sql.SET("req_time = #{reqTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPurchaseMethod() != null) {
            sql.SET("purchase_method = #{purchaseMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}