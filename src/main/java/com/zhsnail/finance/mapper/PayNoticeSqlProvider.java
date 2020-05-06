package com.zhsnail.finance.mapper;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.PayNotice;
import com.zhsnail.finance.vo.AccountDetailVo;
import com.zhsnail.finance.vo.PayNoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class PayNoticeSqlProvider {

    public String insertSelective(PayNotice record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_PAY_NOTICE");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalAmount() != null) {
            sql.VALUES("total_amount", "#{totalAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("account_id", "#{accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getOrg() != null) {
            sql.VALUES("org", "#{org,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalUser() != null) {
            sql.VALUES("total_user", "#{totalUser,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getFeeScope() != null) {
            sql.VALUES("fee_scope", "#{feeScope,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeKindId() != null) {
            sql.VALUES("fee_kind_id", "#{feeKindId,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getPeriod() != null) {
            sql.VALUES("period", "#{period,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLineMax() != null) {
            sql.VALUES("dead_line_max", "#{deadLineMax,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeadLineMin() != null) {
            sql.VALUES("dead_line_min", "#{deadLineMin,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPayDetailId() != null) {
            sql.VALUES("pay_detail_id", "#{payDetailId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.VALUES("creater", "#{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdater() != null) {
            sql.VALUES("updater", "#{updater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PayNotice record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_PAY_NOTICE");
        
        if (record.getTotalAmount() != null) {
            sql.SET("total_amount = #{totalAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{accountId,jdbcType=VARCHAR}");
        }
        
        if (record.getOrg() != null) {
            sql.SET("org = #{org,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getTotalUser() != null) {
            sql.SET("total_user = #{totalUser,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getFeeScope() != null) {
            sql.SET("fee_scope = #{feeScope,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeKindId() != null) {
            sql.SET("fee_kind_id = #{feeKindId,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getPeriod() != null) {
            sql.SET("period = #{period,jdbcType=VARCHAR}");
        }
        
        if (record.getDeadLineMax() != null) {
            sql.SET("dead_line_max = #{deadLineMax,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDeadLineMin() != null) {
            sql.SET("dead_line_min = #{deadLineMin,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPayDetailId() != null) {
            sql.SET("pay_detail_id = #{payDetailId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("creater = #{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdater() != null) {
            sql.SET("updater = #{updater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String findTaskListSql(PayNoticeVo payNoticeVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("CAM_PAY_NOTICE");
        if (payNoticeVo!=null){
            if (StringUtils.isNotBlank(payNoticeVo.getCreater())){
                sql.WHERE("creater = #{creater,jdbcType=VARCHAR}");
            }
        }
        sql.WHERE("status in ("+ DICT.STATUS_DRAFT+","
                +DICT.STATUS_BACK+"," +
                ""+DICT.STATUS_CMT+","
                +")");
        return sql.toString();
    }
}