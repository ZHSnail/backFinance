package com.zhsnail.finance.service;

import com.zhsnail.finance.entity.SysSequence;
import com.zhsnail.finance.mapper.SysSequenceMapper;
import com.zhsnail.finance.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@EnableAsync
@EnableScheduling
public class SysSequenceServiceImpl implements SysSequenceService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SysSequenceServiceImpl.class);
    @Autowired
    private SysSequenceMapper sysSequenceMapper;
    @Override
    public String getSeqDateNo(String prefixName) {
        SysSequence sysSequence = new SysSequence();
        sysSequence.setName(prefixName);
        int seqNo = sysSequenceMapper.getSeq(sysSequence);
        String suffixCode =  String.format("%03d", seqNo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(new Date()).replaceAll("-","");
        return prefixName+dateString+suffixCode;
    }

    @Async
    @Scheduled(cron = "0 0 0 */1 * ?")
    @Override
    public void runInit() {
        LOGGER.info("初始化序列号开始。。。。");
        List<SysSequence> sysSequenceList = sysSequenceMapper.findAll();
        sysSequenceList.forEach(sysSequence->{
            sysSequence.setValue(0);
            sysSequenceMapper.updateByPrimaryKeySelective(sysSequence);
        });
        LOGGER.info("初始化序列号结束。。。。");
    }

    @Override
    public void saveSysSequence(SysSequence sysSequence) {
        sysSequenceMapper.insert(sysSequence);
    }
}