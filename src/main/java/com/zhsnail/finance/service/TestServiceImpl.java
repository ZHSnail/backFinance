package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.Test;
import com.zhsnail.finance.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl {
    @Autowired
    private TestMapper testMapper;
    public void save(){
        Test test = new Test();
        test.setId("54646");
        test.setAccountId("54554");
        test.setName("454");
        testMapper.insert(test);
    }
}
