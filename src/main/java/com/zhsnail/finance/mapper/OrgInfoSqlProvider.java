package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.OrgInfo;
import com.zhsnail.finance.vo.OrgInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class OrgInfoSqlProvider {

    public String insertSelective(OrgInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("SAM_ORG_INFO");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(OrgInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("SAM_ORG_INFO");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(OrgInfoVo orgInfoVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("SAM_ORG_INFO");
        if (orgInfoVo!=null){
            if (StringUtils.isNotBlank(orgInfoVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
            if (StringUtils.isNotBlank(orgInfoVo.getState())){
                sql.WHERE("state = #{state,jdbcType=VARCHAR}");
            }
        }
        sql.ORDER_BY("id");
        return sql.toString();
    }
}