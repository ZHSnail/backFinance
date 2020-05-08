package com.zhsnail.finance.common;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class CustomStringStringConverter implements Converter<String> {
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String val;
        if ("是".equals(cellData.getStringValue())){
            return DICT.BOOLEAN_STATE_TRUE;
        }
        if ("否".equals(cellData.getStringValue())){
            return DICT.BOOLEAN_STATE_FALSE;
        }
        if ("资产类".equals(cellData.getStringValue())){
            return DICT.ACCOUNT_TYPE_ASSETS;
        }
        if ("成本类".equals(cellData.getStringValue())){
            return DICT.ACCOUNT_TYPE_COST;
        }
        if ("费用类".equals(cellData.getStringValue())){
            return DICT.ACCOUNT_TYPE_EXPENSES;
        }
        if ("负债类".equals(cellData.getStringValue())){
            return DICT.ACCOUNT_TYPE_LIABILITIES;
        }
        if ("所有者权益类".equals(cellData.getStringValue())){
            return DICT.ACCOUNT_TYPE_OWNER;
        }
        if ("收入类".equals(cellData.getStringValue())){
            return DICT.ACCOUNT_TYPE_INCOME;
        }
        return cellData.getStringValue();
    }

    @Override
    public CellData convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (DICT.BOOLEAN_STATE_TRUE.equals(s)){
            return new CellData("是");
        }
        if (DICT.BOOLEAN_STATE_FALSE.equals(s)){
            return new CellData("否");
        }
        if (DICT.ACCOUNT_TYPE_ASSETS.equals(s)){
            return new CellData("资产类");
        }
        if (DICT.ACCOUNT_TYPE_COST.equals(s)){
            return new CellData("成本类");
        }
        if (DICT.ACCOUNT_TYPE_EXPENSES.equals(s)){
            return new CellData("费用类");
        }
        if (DICT.ACCOUNT_TYPE_LIABILITIES.equals(s)){
            return new CellData("负债类");
        }
        if (DICT.ACCOUNT_TYPE_OWNER.equals(s)){
            return new CellData("所有者权益类");
        }
        if (DICT.ACCOUNT_TYPE_INCOME.equals(s)){
            return new CellData("收入类");
        }
        return new CellData(s);
    }
}
