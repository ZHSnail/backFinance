package com.zhsnail.finance.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

@Configuration
public class MongoConfig {
    //获取配置文件中数据库信息
    @Value("${spring.data.mongodb.database}")
    String db;
    @Value("${spring.data.mongodb.host}")
    String host;
    @Value("${spring.data.mongodb.username}")
    String username;
    @Value("${spring.data.mongodb.password}")
    String password;
    @Value("${spring.data.mongodb.authentication-database}")
    String authentication;

    ////GridFSBucket用于打开下载流
    @Bean
    public GridFSBucket getGridFSBucket(MongoDbFactory mongoDbFactory){

//        MongoClient mongoClient = new MongoClient();
//        MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
        GridFSBucket bucket = GridFSBuckets.create(mongoDbFactory.getDb());
        return bucket;
    }
}
