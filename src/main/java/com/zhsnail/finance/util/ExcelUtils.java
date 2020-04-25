package com.zhsnail.finance.util;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import org.apache.poi.util.IOUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtils {

    /**
     * 导出Excel(07版.xlsx)到指定路径下
     *
     * @param path      路径
     * @param excelName Excel名称
     * @param sheetName sheet页名称
     * @param clazz     Excel要转换的类型
     * @param data      要导出的数据
     */
    public static void export2File(String path, String excelName, String sheetName, Class clazz, List data) {
        String fileName = path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(fileName, clazz).sheet(sheetName).doWrite(data);
    }

    /**
     * 导出Excel(07版.xlsx)到web
     *
     * @param response  响应
     * @param excelName Excel名称
     * @param sheetName sheet页名称
     * @param clazz     Excel要转换的类型
     * @param data      要导出的数据
     * @throws Exception
     */
    public static void export2Web(HttpServletResponse response, String excelName, String sheetName, Class clazz, List data) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码
            excelName = URLEncoder.encode(excelName, "UTF-8");
            response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
            response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
            EasyExcel.write(response.getOutputStream(), clazz).autoCloseStream(Boolean.FALSE).sheet(sheetName).doWrite(data);
        }catch (Exception e){
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().print(JsonUtil.obj2String(new Result(false,"导出excel文件失败")));
            }catch (Exception a){
                throw new BaseRuningTimeException(a);
            }
        }
    }

    /**
     * 将指定位置指定名称的Excel导出到web
     *
     * @param response  响应
     * @param excelName 文件名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String export2Web4File(HttpServletResponse response,  String excelName) throws UnsupportedEncodingException, FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath() + "/static/excelTemplate/";
        File file = new File(path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue()));
        if (!file.exists()) {
            throw new BaseRuningTimeException("文件不存在");
        }
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

        try (
                FileInputStream in = new FileInputStream(file);
                ServletOutputStream out = response.getOutputStream();
        ) {
            IOUtils.copy(in, out);
            return "导出成功！";
        } catch (Exception e) {
            throw new BaseRuningTimeException("导出文件异常");
        }
    }
}
