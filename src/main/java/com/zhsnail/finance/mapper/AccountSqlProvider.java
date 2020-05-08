package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
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
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(AccountVo accountVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("LEM_ACCOUNT");
        if (accountVo!=null){
            if (StringUtils.isNotBlank(accountVo.getCode())){
                sql.WHERE("code like concat('%', #{code,jdbcType=VARCHAR},'%')");
            }
            if (StringUtils.isNotBlank(accountVo.getAccountName())){
                sql.WHERE("account_name like concat('%',#{accountName,jdbcType=VARCHAR},'%')");
            }
        }
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<Account>> map ){
        List<Account> accounts = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO LEM_ACCOUNT ");
        sb.append("(id, account_name,code,level,parent_id,is_cash,is_bank,is_detail,type) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].accountName}, #'{'list[{0}].code},#'{'list[{0}].level},#'{'list[{0}].parentId},#'{'list[{0}].isCash},#'{'list[{0}].isBank},#'{'list[{0}].isDetail},#'{'list[{0}].type})");
        for (int i = 0; i < accounts.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < accounts.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}