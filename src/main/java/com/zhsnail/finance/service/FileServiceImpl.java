package com.zhsnail.finance.service;

import cn.hutool.core.io.IoUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zhsnail.finance.common.Appendix;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.util.CodeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Override
    public String uploadFileToGridFS(InputStream in, String contentType) {
        String gridfsId = CodeUtil.getId();
        gridFsTemplate.store(in, gridfsId , contentType);
        return gridfsId;
    }

    @Override
    public Appendix saveFile(Appendix appendix) {
        return mongoTemplate.save(appendix);
    }

    @Override
    public Appendix queryFileById(String id) {
        Appendix appendix = mongoTemplate.findById(id, Appendix.class);
        if (appendix != null){
            Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(appendix.getGridfsId()));
            try {
                GridFSFile gridFSFile = gridFsTemplate.findOne(gridQuery);
                GridFSDownloadStream in = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
                if (in.getGridFSFile().getLength()>0){
                    //获取流对象
                    GridFsResource resource = new GridFsResource(gridFSFile, in);
                    //获取数据
                    appendix.setContent(IoUtil.readBytes(resource.getInputStream()));
                    return appendix;
                }else {
                    appendix = null;
                    return null;
                }
            }catch (Exception e){
                throw new BaseRuningTimeException(e);
            }
        }
        return null;
    }

    public List<Appendix> queryByBizId(String bizId,boolean needContext){
        Query query = new Query().addCriteria(Criteria.where("bizId").is(bizId));
        List<Appendix> appendixList = mongoTemplate.find(query, Appendix.class);
        if (needContext){
            arrangeContext(appendixList);
        }
        return appendixList;
    }

    @Override
    public void deleteFile(String id) {
        Appendix appendix = mongoTemplate.findById(id, Appendix.class);
        if (appendix !=null){
            Query deleteFileQuery = new Query().addCriteria(Criteria.where("filename").is(appendix.getGridfsId()));
            gridFsTemplate.delete(deleteFileQuery);
            //删除集合fileDocment中的数据
            Query deleteQuery=new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(deleteQuery,Appendix.class);
        }else {
            throw new BaseRuningTimeException("文件不存在");
        }
    }

    /**
     * 设置二进制数据
     * @param appendixList 附件list
     */
    private void arrangeContext(List<Appendix> appendixList){
        if (CollectionUtils.isNotEmpty(appendixList)){
            try {
                for (Appendix appendix: appendixList) {
                    Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(appendix.getGridfsId()));
                    GridFSFile gridFSFile = gridFsTemplate.findOne(gridQuery);
                    GridFSDownloadStream in = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
                    if (in.getGridFSFile().getLength()>0){
                        //获取流对象
                        GridFsResource resource = new GridFsResource(gridFSFile, in);
                        //获取数据
                        appendix.setContent(IoUtil.readBytes(resource.getInputStream()));
                    }else {
                        appendix = null;
                    }
                }
            }catch (Exception e){
                throw new BaseRuningTimeException("文件不存在");
            }
        }
    }

    @Override
    public List<Appendix> queryByIds(String... ids) {
        Query query = new Query().addCriteria(Criteria.where("id").in(ids));
        List<Appendix> appendixList = mongoTemplate.find(query, Appendix.class);
        arrangeContext(appendixList);
        return appendixList;
    }

    @Override
    public List<Map> onViewFile(String... ids) {
        Query query = new Query().addCriteria(Criteria.where("id").in(ids));
        List<Appendix> appendixList = mongoTemplate.find(query, Appendix.class);
        List<Map> list = new ArrayList<>();
        appendixList.stream().forEach(appendix -> {
            Map<String, String> map = new HashMap<>();
            map.put("name",appendix.getName());
            map.put("url","/system/download/"+appendix.getId());
            map.put("id",appendix.getId());
            list.add(map);
        });
        return list;
    }

    @Override
    public void updateRelation(String module, String bizId, String... ids) {
        Query query = new Query().addCriteria(Criteria.where("module").is(module).where("id").in(ids));
        Update update = Update.update("bizId", bizId);
        mongoTemplate.updateMulti(query,update,Appendix.class);
    }
}
