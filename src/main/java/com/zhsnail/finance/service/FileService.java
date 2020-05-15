package com.zhsnail.finance.service;

import com.zhsnail.finance.common.Appendix;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FileService {

    /**
     * 保存文件
     * @param appendix 文件实体
     * @return 保存后的文件实体
     */
    Appendix saveFile(Appendix appendix);

    /**
     * 上传文件到GridFs中
     * @param in 输入流
     * @param contentType 文件类型
     * @return gridfsId
     */
    String uploadFileToGridFS(InputStream in , String contentType);

    /**
     * 根据id查询文件
     * @param id id
     * @return
     */
    Appendix queryFileById(String id);

    /**
     * 根据业务id查询文件列表
     * @param bizId 业务id
     * @return
     */
    List<Appendix> queryByBizId(String bizId);

    /**
     * 根据id删除文件
     * @param id id
     */
    void deleteFile(String id);

    /**
     * 根据id列表查询文件
     * @param ids ids
     * @return
     */
    List<Appendix> queryByIds(String... ids);

    /**
     * 回显数据
     * @param ids id列表
     * @return
     */
    List<Map> onViewFile(String... ids);
}
