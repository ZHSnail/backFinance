package com.zhsnail.finance.main;

import com.zhsnail.finance.service.AccountService;
import com.zhsnail.finance.service.StudentInfoService;
import com.zhsnail.finance.service.SystemService;
import com.zhsnail.finance.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("执行初始化缓存开始。。。。。");
        SpringUtil.getBean(StudentInfoService.class).findAll();
        SpringUtil.getBean(SystemService.class).getCurrentSysParam();
        SpringUtil.getBean(AccountService.class).findAllAccount();
        SpringUtil.getBean(AccountService.class).findDetailAccount();
        logger.info("执行初始化缓存结束。。。。。");
    }
}
