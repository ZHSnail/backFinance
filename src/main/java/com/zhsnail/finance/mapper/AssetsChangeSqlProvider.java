package com.zhsnail.finance.mapper;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.AssetsChange;
import com.zhsnail.finance.vo.AssetsChangeVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class AssetsChangeSqlProvider {

    public String insertSelective(AssetsChange record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ASM_ASSETS_CHANGE");
        
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
        
        if (record.getAssetsId() != null) {
            sql.VALUES("assets_id", "#{assetsId,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AssetsChange record) {
        SQL sql = new SQL();
        sql.UPDATE("ASM_ASSETS_CHANGE");
        
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
        
        if (record.getAssetsId() != null) {
            sql.SET("assets_id = #{assetsId,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String findTaskListSql(AssetsChangeVo assetsChangeVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("ASM_ASSETS_CHANGE");
        if (assetsChangeVo!=null){
            if (StringUtils.isNotBlank(assetsChangeVo.getCreater())){
                sql.WHERE("creater = #{creater,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(assetsChangeVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '"+ DICT.STATUS_DRAFT+"' , '"
                +DICT.STATUS_BACK+"' , '" +
                DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE
                +"' )");
        return sql.toString();
    }

    public String selectAllConditionSql(AssetsChangeVo assetsChangeVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("ASM_ASSETS_CHANGE");
        if (assetsChangeVo!=null){
            if (StringUtils.isNotBlank(assetsChangeVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '" + DICT.STATUS_FINSH+"' , '" +
                DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE
                +"' )");
        sql.ORDER_BY("create_time desc");
        return sql.toString();
    }

    public String selectAllTaskConditionSql(AssetsChangeVo assetsChangeVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("ASM_ASSETS_CHANGE");
        if (assetsChangeVo!=null){
            if (StringUtils.isNotBlank(assetsChangeVo.getCode())){
                sql.WHERE("code = #{code,jdbcType=VARCHAR}");
            }
            if (StringUtils.isNotBlank(assetsChangeVo.getStatus())){
                sql.WHERE("status = #{status,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ( '" + DICT.STATUS_CMT+"' , '"+DICT.STATUS_EXE +"' )");
        sql.WHERE("creater = #{creater,jdbcType=VARCHAR}");
        sql.ORDER_BY("create_time desc");
        return sql.toString();
    }
}