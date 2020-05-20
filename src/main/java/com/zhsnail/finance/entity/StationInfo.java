package com.zhsnail.finance.entity;

public class StationInfo {

    private String id;
    //岗位类型
    private String type;
    //岗位名称
    private String name;
    //状态
    private String state;

    private String stationSalaryId;

    private String scaleSalaryId;
    //岗位工资
    private StationSalary stationSalary;
    //薪级工资
    private ScaleSalary scaleSalary;

    private String debitScaleAccId;

    private String creditScaleAccId;

    private String debitStationAccId;

    private String creditStationAccId;

    private Account debitScaleAccount;

    private Account creditScaleAccount;

    private Account debitStationAccount;

    private Account creditStationAccount;

    public StationSalary getStationSalary() {
        return stationSalary;
    }

    public void setStationSalary(StationSalary stationSalary) {
        this.stationSalary = stationSalary;
    }

    public ScaleSalary getScaleSalary() {
        return scaleSalary;
    }

    public void setScaleSalary(ScaleSalary scaleSalary) {
        this.scaleSalary = scaleSalary;
    }

    public Account getDebitScaleAccount() {
        return debitScaleAccount;
    }

    public void setDebitScaleAccount(Account debitScaleAccount) {
        this.debitScaleAccount = debitScaleAccount;
    }

    public Account getCreditScaleAccount() {
        return creditScaleAccount;
    }

    public void setCreditScaleAccount(Account creditScaleAccount) {
        this.creditScaleAccount = creditScaleAccount;
    }

    public Account getDebitStationAccount() {
        return debitStationAccount;
    }

    public void setDebitStationAccount(Account debitStationAccount) {
        this.debitStationAccount = debitStationAccount;
    }

    public Account getCreditStationAccount() {
        return creditStationAccount;
    }

    public void setCreditStationAccount(Account creditStationAccount) {
        this.creditStationAccount = creditStationAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getStationSalaryId() {
        return stationSalaryId;
    }

    public void setStationSalaryId(String stationSalaryId) {
        this.stationSalaryId = stationSalaryId == null ? null : stationSalaryId.trim();
    }

    public String getScaleSalaryId() {
        return scaleSalaryId;
    }

    public void setScaleSalaryId(String scaleSalaryId) {
        this.scaleSalaryId = scaleSalaryId == null ? null : scaleSalaryId.trim();
    }

    public String getDebitScaleAccId() {
        return debitScaleAccId;
    }

    public void setDebitScaleAccId(String debitScaleAccId) {
        this.debitScaleAccId = debitScaleAccId == null ? null : debitScaleAccId.trim();
    }

    public String getCreditScaleAccId() {
        return creditScaleAccId;
    }

    public void setCreditScaleAccId(String creditScaleAccId) {
        this.creditScaleAccId = creditScaleAccId == null ? null : creditScaleAccId.trim();
    }

    public String getDebitStationAccId() {
        return debitStationAccId;
    }

    public void setDebitStationAccId(String debitStationAccId) {
        this.debitStationAccId = debitStationAccId == null ? null : debitStationAccId.trim();
    }

    public String getCreditStationAccId() {
        return creditStationAccId;
    }

    public void setCreditStationAccId(String creditStationAccId) {
        this.creditStationAccId = creditStationAccId == null ? null : creditStationAccId.trim();
    }
}