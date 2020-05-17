package com.zhsnail.finance.vo;

import com.zhsnail.finance.entity.PageEntity;

public class AssetsKindVo extends PageEntity {
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
    //借方折旧科目名称
    private String debitDepreAccName;
    //贷方折旧科目名称
    private String creditDepreAccName;
    //借方固定资产会计科目名称
    private String debitAssetsAccName;
    //贷方固定资产会计科目名称
    private String creditAssetsAccName;
    //折旧方法名称
    private String depreMethodName;

    public String getDepreMethodName() {
        return depreMethodName;
    }

    public void setDepreMethodName(String depreMethodName) {
        this.depreMethodName = depreMethodName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDebitDepreAccId() {
        return debitDepreAccId;
    }

    public void setDebitDepreAccId(String debitDepreAccId) {
        this.debitDepreAccId = debitDepreAccId;
    }

    public String getCreditDepreAccId() {
        return creditDepreAccId;
    }

    public void setCreditDepreAccId(String creditDepreAccId) {
        this.creditDepreAccId = creditDepreAccId;
    }

    public String getDepreMethod() {
        return depreMethod;
    }

    public void setDepreMethod(String depreMethod) {
        this.depreMethod = depreMethod;
    }

    public String getDebitAssetsAccId() {
        return debitAssetsAccId;
    }

    public void setDebitAssetsAccId(String debitAssetsAccId) {
        this.debitAssetsAccId = debitAssetsAccId;
    }

    public String getCreditAssetsAccId() {
        return creditAssetsAccId;
    }

    public void setCreditAssetsAccId(String creditAssetsAccId) {
        this.creditAssetsAccId = creditAssetsAccId;
    }

    public String getDebitDepreAccName() {
        return debitDepreAccName;
    }

    public void setDebitDepreAccName(String debitDepreAccName) {
        this.debitDepreAccName = debitDepreAccName;
    }

    public String getCreditDepreAccName() {
        return creditDepreAccName;
    }

    public void setCreditDepreAccName(String creditDepreAccName) {
        this.creditDepreAccName = creditDepreAccName;
    }

    public String getDebitAssetsAccName() {
        return debitAssetsAccName;
    }

    public void setDebitAssetsAccName(String debitAssetsAccName) {
        this.debitAssetsAccName = debitAssetsAccName;
    }

    public String getCreditAssetsAccName() {
        return creditAssetsAccName;
    }

    public void setCreditAssetsAccName(String creditAssetsAccName) {
        this.creditAssetsAccName = creditAssetsAccName;
    }
}
