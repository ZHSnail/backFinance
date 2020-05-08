package com.zhsnail.finance.mapper;

import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.PayDetail;
import com.zhsnail.finance.vo.PayDetailVo;
import com.zhsnail.finance.vo.PayNoticeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class PayDetailSqlProvider {

    public String insertSelective(PayDetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_PAY_DETAIL");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getMemo() != null) {
            sql.VALUES("memo", "#{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getPayDate() != null) {
            sql.VALUES("pay_date", "#{payDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeMethod() != null) {
            sql.VALUES("fee_method", "#{feeMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
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
        
        if (record.getPayNoticeId() != null) {
            sql.VALUES("pay_notice_id", "#{payNoticeId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PayDetail record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_PAY_DETAIL");
        
        if (record.getMemo() != null) {
            sql.SET("memo = #{memo,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=DECIMAL}");
        }
        
        if (record.getPayDate() != null) {
            sql.SET("pay_date = #{payDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeMethod() != null) {
            sql.SET("fee_method = #{feeMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=VARCHAR}");
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
        
        if (record.getPayNoticeId() != null) {
            sql.SET("pay_notice_id = #{payNoticeId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<PayDetail>> map ){
        List<PayDetail> payDetails = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO CAM_PAY_DETAIL ");
        sb.append("(id, memo,amount,pay_date,status,fee_method,code,user_id,create_time,creater,updater,update_time,pay_notice_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].memo}, " +
                "#'{'list[{0}].amount},#'{'list[{0}].payDate},#'{'list[{0}].status}," +
                "#'{'list[{0}].feeMethod},#'{'list[{0}].code},#'{'list[{0}].userId}," +
                "#'{'list[{0}].createTime},#'{'list[{0}].creater},#'{'list[{0}].updater}," +
                "#'{'list[{0}].updateTime},#'{'list[{0}].payNoticeId})");
        for (int i = 0; i < payDetails.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < payDetails.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String selectAllConditionSql(PayDetailVo payDetailVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("CAM_PAY_DETAIL");
        if (payDetailVo!=null){
            if (StringUtils.isNotBlank(payDetailVo.getCode())){
                sql.WHERE("code like concat('%', #{code,jdbcType=VARCHAR},'%')");
            }
            if (StringUtils.isNotBlank(payDetailVo.getStatus())){
                sql.WHERE("status = #{status,jdbcType=VARCHAR}");
            }
            if (CollectionUtils.isNotEmpty(payDetailVo.getUserIdList())){
                StringBuilder sb = new StringBuilder();
                sb.append("user_id in (");
                for (int i = 0;i<payDetailVo.getUserIdList().size();i++){
                    sb.append("'");
                    sb.append(payDetailVo.getUserIdList().get(i));
                    sb.append("'");
                    if (i != payDetailVo.getUserIdList().size()-1){
                        sb.append("',");
                    }
                }
                sb.append(")");
                sql.WHERE(sb.toString());
            }
        }
        sql.WHERE("pay_notice_id = #{payNoticeId,jdbcType=VARCHAR}");
        sql.ORDER_BY("create_time desc");
        return sql.toString();
    }
}