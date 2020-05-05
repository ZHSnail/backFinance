package com.zhsnail.finance.service;

import com.zhsnail.finance.mapper.PayNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayNoticeServiceImpl implements PayNoticeService {
    @Autowired
    private PayNoticeMapper payNoticeMapper;
}
