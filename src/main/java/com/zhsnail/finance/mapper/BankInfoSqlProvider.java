package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.BankInfo;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class BankInfoSqlProvider {

    public String insertSelective(BankInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_BANK_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getAccountNumber() != null) {
            sql.VALUES("account_number", "#{accountNumber,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getSubBranch() != null) {
            sql.VALUES("sub_branch", "#{subBranch,jdbcType=VARCHAR}");
        }

        if (record.getAccountname() != null) {
            sql.VALUES("accountName", "#{accountname,jdbcType=VARCHAR}");
        }

        if (record.getAccounttype() != null) {
            sql.VALUES("accountType", "#{accounttype,jdbcType=VARCHAR}");
        }

        if (record.getStaffId() != null) {
            sql.VALUES("staff_id", "#{staffId,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(BankInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_BANK_INFO");

        if (record.getAccountNumber() != null) {
            sql.SET("account_number = #{accountNumber,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getSubBranch() != null) {
            sql.SET("sub_branch = #{subBranch,jdbcType=VARCHAR}");
        }

        if (record.getAccountname() != null) {
            sql.SET("accountName = #{accountname,jdbcType=VARCHAR}");
        }

        if (record.getAccounttype() != null) {
            sql.SET("accountType = #{accounttype,jdbcType=VARCHAR}");
        }

        if (record.getStaffId() != null) {
            sql.SET("staff_id = #{staffId,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=VARCHAR}");

        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<BankInfo>> map ){
        List<BankInfo> accounts = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO SAM_BANK_INFO ");
        sb.append("(id, account_number,name,sub_branch,accountName,accountType,staff_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].accountNumber}, #'{'list[{0}].name},#'{'list[{0}].subBranch},#'{'list[{0}].accountname},#'{'list[{0}].accounttype},#'{'list[{0}].staffId})");
        for (int i = 0; i < accounts.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < accounts.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}