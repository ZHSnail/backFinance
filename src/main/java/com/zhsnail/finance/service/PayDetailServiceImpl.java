package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.mapper.PayDetailMapper;
import com.zhsnail.finance.mapper.StudentInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.PayDetailVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayDetailServiceImpl implements  PayDetailService {
    @Autowired
    private PayDetailMapper payDetailMapper;
    @Autowired
    private PayNoticeService payNoticeService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private VoucherService voucherService;

    @Override
    public PageInfo<PayDetailVo> findByPayNoticeId(PayDetailVo payDetailVo) {
        List<String> userIdList = new ArrayList<>();
        if (StringUtils.isNotBlank(payDetailVo.getUserName())){
            List<StudentInfo> studentInfo = studentInfoMapper.findByName(payDetailVo.getUserName());
            List<String> list = studentInfo.stream().map(item -> item.getId()).collect(Collectors.toList());
            userIdList.addAll(list);
        }
        payDetailVo.setUserIdList(userIdList);
        CommonUtil.startPage(payDetailVo);
        List<PayDetail> payDetailList = payDetailMapper.findAllByCondition(payDetailVo);
        List<PayDetailVo> payDetailVoList = new ArrayList<>();
        for (PayDetail payDetail : payDetailList){
            PayDetailVo temp = new PayDetailVo();
            BeanUtil.copyProperties(temp,payDetail);
            Map userInfo = CommonUtil.findUserInfo(payDetail.getUserId());
            temp.setUserName((String) userInfo.get("name"));
            payDetailVoList.add(temp);
        }
        PageInfo<PayDetailVo> payDetailVoPageInfo = new PageInfo<>(payDetailVoList);
        return payDetailVoPageInfo;
    }

    @Override
    public PayDetailVo findById(String id) {
        PayDetail payDetail = payDetailMapper.selectByPrimaryKey(id);
        PayDetailVo payDetailVo = new PayDetailVo();
        BeanUtil.copyProperties(payDetailVo,payDetail);
        Map userInfo = CommonUtil.findUserInfo(payDetail.getUserId());
        payDetailVo.setUserName((String) userInfo.get("name"));
        return payDetailVo;
    }

    @Override
    public void execPay(String id) {
        PayDetail payDetail = payDetailMapper.selectByPrimaryKey(id);
        payDetail.setPayDate(new Date());
        payDetail.setUpdater((String) CommonUtil.getCurrentUser().get("id"));
        payDetail.setUpdateTime(new Date());
        payDetail.setStatus(DICT.PAY_DETAIL_STATUS_PAID);
        payDetailMapper.updateByPrimaryKeySelective(payDetail);
        String payNoticeId = payDetail.getPayNoticeId();
        Map payNoticeMap = payNoticeService.findById(payNoticeId);
        PayNotice payNotice = new PayNotice();
        BeanUtil.mapToBean(payNoticeMap,payNotice);
        FeeKind feeKind = payNotice.getFeeKind();
        //贷方会计科目id
        String creditAccountId = feeKind.getCreditAccountId();
        //借方会计科目id
        String debitAccountId = feeKind.getDebitAccountId();
        //生成凭证
        voucherService.generateVoucher(initVoucher(payDetail,feeKind.getName(),payNotice.getCreater()),debitAccountId,creditAccountId);
        List<PayDetail> payDetailList = payDetailMapper.findByPayNoticeId(payNoticeId);
        //如果已经没有了要付款的单据，就把缴费通知单完成。
        List<PayDetail> collect = payDetailList.stream().filter(item -> DICT.PAY_DETAIL_STATUS_UNPAID.equals(item.getStatus())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            payNoticeService.updateStatusById(payNoticeId,DICT.STATUS_FINSH);
        }
    }

    private Voucher initVoucher(PayDetail payDetail,String memo,String originator){
        Voucher voucher = CommonUtil.initVoucher(originator);
        //业务日期
        voucher.setBizDate(payDetail.getPayDate());
        //业务id
        voucher.setBizId(payDetail.getId());
        //业务类型
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_CHARGE_PAY);
        //源单号
        voucher.setBizCode(payDetail.getCode());
        //交易类型
        voucher.setDealType(DICT.VOUCHER_DEAL_TYPE_BANK);
        //借方金额
        voucher.setDebitTotal(payDetail.getAmount());
        //贷方金额
        voucher.setCreditTotal(payDetail.getAmount());
        //审核人
        voucher.setAuditer(originator);
        //备注
        voucher.setMemo(memo);
        return voucher;
    }
    @Override
    public PageInfo<PayDetail> findByUserId(PageEntity pageEntity) {
        String id = (String) CommonUtil.getCurrentUser().get("id");
        CommonUtil.startPage(pageEntity);
        List<PayDetail> payDetailList = payDetailMapper.findByUserId(id);
        PageInfo<PayDetail> payDetailPageInfo = new PageInfo<>(payDetailList);
        return payDetailPageInfo;
    }
}
