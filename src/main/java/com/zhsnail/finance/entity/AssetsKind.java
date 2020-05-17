package com.zhsnail.finance.entity;

public class AssetsKind {
    private String id;
    //类别编码
    private String code;
    //类别名称
    private String name;
    //借方折旧科目id
    private String debitDepreAccId;
    //贷方折旧科目id
    private String creditDepreAccId;
    //折旧方法
    private String depreMethod;
    //借方固定资产会计科目id
    private String debitAssetsAccId;
    //贷方固定资产会计科目id
    private String creditAssetsAccId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDebitDepreAccId() {
        return debitDepreAccId;
    }

    public void setDebitDepreAccId(String debitDepreAccId) {
        this.debitDepreAccId = debitDepreAccId == null ? null : debitDepreAccId.trim();
    }

    public String getCreditDepreAccId() {
        return creditDepreAccId;
    }

    public void setCreditDepreAccId(String creditDepreAccId) {
        this.creditDepreAccId = creditDepreAccId == null ? null : creditDepreAccId.trim();
    }

    public String getDepreMethod() {
        return depreMethod;
    }

    public void setDepreMethod(String depreMethod) {
        this.depreMethod = depreMethod == null ? null : depreMethod.trim();
    }

    public String getDebitAssetsAccId() {
        return debitAssetsAccId;
    }

    public void setDebitAssetsAccId(String debitAssetsAccId) {
        this.debitAssetsAccId = debitAssetsAccId == null ? null : debitAssetsAccId.trim();
    }

    public String getCreditAssetsAccId() {
        return creditAssetsAccId;
    }

    public void setCreditAssetsAccId(String creditAssetsAccId) {
        this.creditAssetsAccId = creditAssetsAccId == null ? null : creditAssetsAccId.trim();
    }
}