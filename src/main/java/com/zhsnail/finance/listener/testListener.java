package com.zhsnail.finance.listener;

public class testListener extends BaseActivitiTaskEventListener {

    @Override
    public void approve(String businessKey) {
        System.out.println("审核通过-------------------------------");
    }

    @Override
    public void lastApprove(String businessKey) {
        System.out.println("最后一步审核通过-------------------------------");
    }

    @Override
    public void refuse(String businessKey) {
        System.out.println("拒绝，并返回申请-------------------------------");
    }

    @Override
    public void apply(String businessKey) {
        System.out.println("提交申请-------------------------------");
    }
}
