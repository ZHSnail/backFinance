package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.ibatis.jdbc.SQL;

public class AccountSqlProvider {

    public String insertSelective(Account record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("LEM_ACCOUNT");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountName() != null) {
            sql.VALUES("account_name", "#{accountName,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsCash() != null) {
            sql.VALUES("is_cash", "#{isCash,jdbcType=VARCHAR}");
        }
        
        if (record.getIsBank() != null) {
            sql.VALUES("is_bank", "#{isBank,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDetail() != null) {
            sql.VALUES("is_detail", "#{isDetail,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Account record) {
        SQL sql = new SQL();
        sql.UPDATE("LEM_ACCOUNT");
        
        if (record.getAccountName() != null) {
            sql.SET("account_name = #{accountName,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsCash() != null) {
            sql.SET("is_cash = #{isCash,jdbcType=VARCHAR}");
        }
        
        if (record.getIsBank() != null) {
            sql.SET("is_bank = #{isBank,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDetail() != null) {
            sql.SET("is_detail = #{isDetail,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(AccountVo accountVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("LEM_ACCOUNT");
        if (accountVo.getCode() != null){
            sql.WHERE("code = #{code,jdbcType=VARCHAR}");
        }
        if (accountVo.getAccountName() != null){
            sql.WHERE("accountName like concat('%',#{accountName},'%')");
        }
        return sql.toString();
    }
}