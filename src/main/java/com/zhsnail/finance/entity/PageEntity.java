package com.zhsnail.finance.entity;

import com.alibaba.excel.annotation.ExcelIgnore;

public class PageEntity {
    @ExcelIgnore
    private int pageNum=1;//当前页
    @ExcelIgnore
    private int pageSize=10;//页面大小

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
