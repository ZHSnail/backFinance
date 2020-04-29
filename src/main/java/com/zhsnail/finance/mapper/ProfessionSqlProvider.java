package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.vo.AccountVo;
import com.zhsnail.finance.vo.ProfessionVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class ProfessionSqlProvider {

    public String insertSelective(Profession record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("CAM_PROFESSION");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.VALUES("is_leaf", "#{isLeaf,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("grade", "#{grade,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Profession record) {
        SQL sql = new SQL();
        sql.UPDATE("CAM_PROFESSION");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.SET("is_leaf = #{isLeaf,jdbcType=VARCHAR}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("grade = #{grade,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String selectAllConditionSql(ProfessionVo professionVo){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("CAM_PROFESSION");
        if (professionVo!=null){
            if (StringUtils.isNotBlank(professionVo.getName())){
                sql.WHERE("name like concat('%', #{name,jdbcType=VARCHAR},'%')");
            }
        }
        return sql.toString();
    }
}