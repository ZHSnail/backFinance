package com.zhsnail.finance.mapper;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.AssetsPurchase;
import com.zhsnail.finance.vo.AssetsPurchaseVo;
import org.apache.commons.lang3.StringUtils;
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
        
        if (record.getTotalAmt() != null) {
            sql.VALUES("total_amt", "#{totalAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitAccountId() != null) {
            sql.VALUES("debit_account_id", "#{debitAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAccountId() != null) {
            sql.VALUES("credit_account_id", "#{creditAccountId,jdbcType=VARCHAR}");
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
        
        if (record.getTotalAmt() != null) {
            sql.SET("total_amt = #{totalAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDebitAccountId() != null) {
            sql.SET("debit_account_id = #{debitAccountId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreditAccountId() != null) {
            sql.SET("credit_account_id = #{creditAccountId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
    public String findTaskListSql(AssetsPurchaseVo assetsPurchaseVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("ASM_ASSETS_PURCHASE");
        if (assetsPurchaseVo!=null){
            if (StringUtils.isNotBlank(assetsPurchaseVo.getCreater())){
                sql.WHERE("creater = #{creater,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(assetsPurchaseVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '"+ DICT.STATUS_DRAFT+"' , '"
                +DICT.STATUS_BACK+"' , '" +
                DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE
                +"' )");
        return sql.toString();
    }

    public String selectAllConditionSql(AssetsPurchaseVo assetsPurchaseVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("ASM_ASSETS_PURCHASE");
        if (assetsPurchaseVo!=null){
            if (StringUtils.isNotBlank(assetsPurchaseVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '" + DICT.STATUS_FINSH+"' , '" +
                DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE
                +"' )");
        sql.ORDER_BY("create_time desc");
        return sql.toString();
    }

    public String selectAllTaskConditionSql(AssetsPurchaseVo assetsPurchaseVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("ASM_ASSETS_PURCHASE");
        if (assetsPurchaseVo!=null){
            if (StringUtils.isNotBlank(assetsPurchaseVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(assetsPurchaseVo.getStatus())){
                sql.WHERE("status = #{status,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '" + DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE +"' )");
        sql.WHERE("creater = #{creater,jdbcType=VARCHAR}");
        sql.ORDER_BY("create_time desc");
        return sql.toString();
    }
}