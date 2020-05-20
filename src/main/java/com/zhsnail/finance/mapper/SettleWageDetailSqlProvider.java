package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.SettleWageDetail;
import com.zhsnail.finance.vo.SettleWageDetailVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class SettleWageDetailSqlProvider {

    public String insertSelective(SettleWageDetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_SETTLE_WAGE_DETAIL");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getSettleWageId() != null) {
            sql.VALUES("settle_wage_id", "#{settleWageId,jdbcType=VARCHAR}");
        }
        
        if (record.getStaffId() != null) {
            sql.VALUES("staff_id", "#{staffId,jdbcType=VARCHAR}");
        }
        
        if (record.getPayableAmount() != null) {
            sql.VALUES("payable_amount", "#{payableAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getTaxableAmount() != null) {
            sql.VALUES("taxable_amount", "#{taxableAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getPaidAmount() != null) {
            sql.VALUES("paid_amount", "#{paidAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getDeductionAmount() != null) {
            sql.VALUES("deduction_amount", "#{deductionAmount,jdbcType=DECIMAL}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SettleWageDetail record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_SETTLE_WAGE_DETAIL");
        
        if (record.getSettleWageId() != null) {
            sql.SET("settle_wage_id = #{settleWageId,jdbcType=VARCHAR}");
        }
        
        if (record.getStaffId() != null) {
            sql.SET("staff_id = #{staffId,jdbcType=VARCHAR}");
        }
        
        if (record.getPayableAmount() != null) {
            sql.SET("payable_amount = #{payableAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getTaxableAmount() != null) {
            sql.SET("taxable_amount = #{taxableAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getPaidAmount() != null) {
            sql.SET("paid_amount = #{paidAmount,jdbcType=DECIMAL}");
        }
        
        if (record.getDeductionAmount() != null) {
            sql.SET("deduction_amount = #{deductionAmount,jdbcType=DECIMAL}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<SettleWageDetail>> map ){
        List<SettleWageDetail> accounts = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO SAM_SETTLE_WAGE_DETAIL ");
        sb.append("(id, settle_wage_id,staff_id,payable_amount,taxable_amount," +
                "paid_amount,deduction_amount) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].settleWageId}, #'{'list[{0}].staffId}" +
                ",#'{'list[{0}].payableAmount},#'{'list[{0}].taxableAmount}," +
                "#'{'list[{0}].paidAmount},#'{'list[{0}].deductionAmount})");
        for (int i = 0; i < accounts.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < accounts.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String selectAllConditionSql(SettleWageDetailVo settleWageDetailVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("SAM_SETTLE_WAGE_DETAIL");
        if (settleWageDetailVo!=null){
            if (StringUtils.isNotBlank(settleWageDetailVo.getSettleWageId())){
                sql.WHERE("settle_wage_id = #{settleWageId,jdbcType=VARCHAR}");
            }
        }
        sql.ORDER_BY("id desc");
        return sql.toString();
    }
}