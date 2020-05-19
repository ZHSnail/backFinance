package com.zhsnail.finance.mapper;

import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.vo.AccountBalanceVo;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class AssetsSqlProvider {

    public String insertSelective(Assets record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ASM_ASSETS");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getAssetsKindId() != null) {
            sql.VALUES("assets_kind_id", "#{assetsKindId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getStorageTime() != null) {
            sql.VALUES("storage_time", "#{storageTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDepreMethod() != null) {
            sql.VALUES("depre_method", "#{depreMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLossReport() != null) {
            sql.VALUES("loss_report", "#{lossReport,jdbcType=VARCHAR}");
        }
        
        if (record.getUsefulLife() != null) {
            sql.VALUES("useful_life", "#{usefulLife,jdbcType=VARCHAR}");
        }
        
        if (record.getStoragePlace() != null) {
            sql.VALUES("storage_place", "#{storagePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getNorms() != null) {
            sql.VALUES("norms", "#{norms,jdbcType=VARCHAR}");
        }
        
        if (record.getOrival() != null) {
            sql.VALUES("orival", "#{orival,jdbcType=DECIMAL}");
        }
        
        if (record.getSalvage() != null) {
            sql.VALUES("salvage", "#{salvage,jdbcType=DECIMAL}");
        }
        
        if (record.getLossReportTime() != null) {
            sql.VALUES("loss_report_time", "#{lossReportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCleanCost() != null) {
            sql.VALUES("clean_cost", "#{cleanCost,jdbcType=DECIMAL}");
        }
        
        if (record.getNum() != null) {
            sql.VALUES("num", "#{num,jdbcType=VARCHAR}");
        }
        
        if (record.getObtainMethod() != null) {
            sql.VALUES("obtain_method", "#{obtainMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseId() != null) {
            sql.VALUES("purchase_id", "#{purchaseId,jdbcType=VARCHAR}");
        }
        
        if (record.getChangeId() != null) {
            sql.VALUES("change_id", "#{changeId,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Assets record) {
        SQL sql = new SQL();
        sql.UPDATE("ASM_ASSETS");
        
        if (record.getAssetsKindId() != null) {
            sql.SET("assets_kind_id = #{assetsKindId,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }
        
        if (record.getStorageTime() != null) {
            sql.SET("storage_time = #{storageTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDepreMethod() != null) {
            sql.SET("depre_method = #{depreMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getLossReport() != null) {
            sql.SET("loss_report = #{lossReport,jdbcType=VARCHAR}");
        }
        
        if (record.getUsefulLife() != null) {
            sql.SET("useful_life = #{usefulLife,jdbcType=VARCHAR}");
        }
        
        if (record.getStoragePlace() != null) {
            sql.SET("storage_place = #{storagePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getNorms() != null) {
            sql.SET("norms = #{norms,jdbcType=VARCHAR}");
        }
        
        if (record.getOrival() != null) {
            sql.SET("orival = #{orival,jdbcType=DECIMAL}");
        }
        
        if (record.getSalvage() != null) {
            sql.SET("salvage = #{salvage,jdbcType=DECIMAL}");
        }
        
        if (record.getLossReportTime() != null) {
            sql.SET("loss_report_time = #{lossReportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCleanCost() != null) {
            sql.SET("clean_cost = #{cleanCost,jdbcType=DECIMAL}");
        }
        
        if (record.getNum() != null) {
            sql.SET("num = #{num,jdbcType=VARCHAR}");
        }
        
        if (record.getObtainMethod() != null) {
            sql.SET("obtain_method = #{obtainMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getPurchaseId() != null) {
            sql.SET("purchase_id = #{purchaseId,jdbcType=VARCHAR}");
        }
        
        if (record.getChangeId() != null) {
            sql.SET("change_id = #{changeId,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String batchinsertSql(Map<String, List<Assets>> map ){
        List<Assets> assetss = map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ASM_ASSETS ");
        sb.append("(id,assets_kind_id,name,code,storage_time,depre_method," +
                "loss_report,useful_life,storage_place,norms,orival,salvage," +
                "loss_report_time,clean_cost,num,obtain_method,state,purchase_id,change_id) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].assetsKindId}, " +
                "#'{'list[{0}].name},#'{'list[{0}].code},#'{'list[{0}].storageTime},#'{'list[{0}].depreMethod}," +
                "#'{'list[{0}].lossReport},#'{'list[{0}].usefulLife},#'{'list[{0}].storagePlace}," +
                "#'{'list[{0}].norms},#'{'list[{0}].orival},#'{'list[{0}].salvage},#'{'list[{0}].lossReportTime}," +
                "#'{'list[{0}].cleanCost},#'{'list[{0}].num},#'{'list[{0}].obtainMethod},#'{'list[{0}].state}," +
                "#'{'list[{0}].purchaseId},#'{'list[{0}].changeId})");
        for (int i = 0; i < assetss.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < assetss.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String batchUpdateSql(Map<String, List<Assets>> map ){
        List<Assets> assetsList = map.get("list");
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<assetsList.size();i++){
            Assets assets = assetsList.get(i);
            sb.append("UPDATE ASM_ASSETS SET ");
            if (assets.getAssetsKindId() != null) {
                sb.append("assets_kind_id = '"+assets.getAssetsKindId()+"'");
                sb.append(",");
            }

            if (assets.getName() != null) {
                sb.append("name = '"+assets.getName()+"'");
                sb.append(",");
            }

            if (assets.getCode() != null) {
                sb.append("code = '"+assets.getCode()+"'");
                sb.append(",");
            }

            if (assets.getStorageTime() != null) {
                sb.append("storage_time = "+assets.getStorageTime());
                sb.append(",");
            }

            if (assets.getDepreMethod() != null) {
                sb.append("depre_method = '"+assets.getDepreMethod()+"'");
                sb.append(",");
            }

            if (assets.getLossReport() != null) {
                sb.append("loss_report = '"+assets.getLossReport()+"'");
                sb.append(",");
            }

            if (assets.getUsefulLife() != null) {
                sb.append("useful_life = '"+assets.getUsefulLife()+"'");
                sb.append(",");
            }

            if (assets.getStoragePlace() != null) {
                sb.append("storage_place = '"+assets.getStoragePlace()+"'");
                sb.append(",");
            }

            if (assets.getNorms() != null) {
                sb.append("norms = '"+assets.getNorms()+"'");
                sb.append(",");
            }

            if (assets.getOrival() != null) {
                sb.append("orival = "+assets.getOrival() );
                sb.append(",");
            }

            if (assets.getSalvage() != null) {
                sb.append("salvage = "+assets.getSalvage());
                sb.append(",");
            }

            if (assets.getLossReportTime() != null) {
                sb.append("loss_report_time = "+assets.getLossReportTime());
                sb.append(",");
            }

            if (assets.getCleanCost() != null) {
                sb.append("clean_cost = "+assets.getCleanCost());
                sb.append(",");
            }

            if (assets.getNum() != null) {
                sb.append("num = '"+assets.getNum()+"'");
                sb.append(",");
            }

            if (assets.getObtainMethod() != null) {
                sb.append("obtain_method = '"+assets.getObtainMethod()+"'");
                sb.append(",");
            }

            if (assets.getState() != null) {
                sb.append("state = '"+assets.getState()+"'");
                sb.append(",");
            }

            if (assets.getChangeId() != null) {
                sb.append("change_id = '"+assets.getChangeId()+"'");
                sb.append(",");
            }
            ;
            sb.replace(sb.lastIndexOf(","),sb.length(),"");
            sb.append(" WHERE id = '"+assets.getId()+"'");
            sb.append(";");
        }
        return sb.toString();
    }
}