package com.zhsnail.finance.entity;

public class ImportResult {
    private String id;

    private String errResult;

    private String succResult;

    private String fileId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getErrResult() {
        return errResult;
    }

    public void setErrResult(String errResult) {
        this.errResult = errResult == null ? null : errResult.trim();
    }

    public String getSuccResult() {
        return succResult;
    }

    public void setSuccResult(String succResult) {
        this.succResult = succResult == null ? null : succResult.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }
}