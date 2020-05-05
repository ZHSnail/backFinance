package com.zhsnail.finance.service;

import com.zhsnail.finance.mapper.PayDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayDetailServiceImpl implements  PayDetailService {
    @Autowired
    private PayDetailMapper payDetailMapper;
}
