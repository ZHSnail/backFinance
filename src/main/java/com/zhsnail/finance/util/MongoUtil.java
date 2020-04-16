package com.zhsnail.finance.util;

import com.zhsnail.finance.common.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

public class MongoUtil {

    private static MongoTemplate mongoTemplate =  SpringUtil.getBean(MongoTemplate.class);

    /**
     * 插入系统日志
     * @param systemLog 系统日志实体
     */
    public static void insertLog(SystemLog systemLog){
        mongoTemplate.insert(systemLog);
    }

    /**
     * 查询所有系统日志
     * @return
     */
    public static List<SystemLog> findAll(){
        return mongoTemplate.findAll(SystemLog.class);
    }

    /**
     * 根据用户id查询系统日志
     * @param userId 用户id
     * @return
     */
    public static SystemLog findByUserId(String userId){
        Query query = new Query(Criteria.where("userId").is(userId));
        SystemLog systemLog = mongoTemplate.findOne(query, SystemLog.class);
        return systemLog;
    }

    public static void main(String[] args) {
        SystemLog log = new SystemLog();
        log.setDesc("455456");
        mongoTemplate.insert(log);
    }
}
