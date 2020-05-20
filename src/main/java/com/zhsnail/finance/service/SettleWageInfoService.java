package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.SettleWageInfo;

import java.util.List;

public interface SettleWageInfoService {

    List<SettleWageInfo> findByPayStuId(String payStubInfoId);

    void execPaySalary(String id);
}
