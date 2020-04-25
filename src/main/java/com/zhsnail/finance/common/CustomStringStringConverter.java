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
        return new CellData(s);
    }
}
