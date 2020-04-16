package com.zhsnail.finance.mapper;

import com.zhsnail.finance.main.FinanceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FinanceApplication.class})
public class TestMapperTest {
    @Autowired
    private TestMapper testMapper;

    @Test
    public void testInsert() throws Exception {
        com.zhsnail.finance.entity.Test test = testMapper.selectByPrimaryKey("1");
        System.out.println(test);
    }
}