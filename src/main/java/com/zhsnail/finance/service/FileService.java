package com.zhsnail.finance.service;

import com.zhsnail.finance.common.Appendix;

import java.io.InputStream;
import java.util.List;
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

    Appendix queryFileById(String id);

    List<Appendix> queryByBizId(String bizId);

    void deleteFile(String id);
}
