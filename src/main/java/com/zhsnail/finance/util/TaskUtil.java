package com.zhsnail.finance.util;

import com.alibaba.excel.EasyExcel;
import com.zhsnail.finance.common.Appendix;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.listener.AccountImportListener;
import com.zhsnail.finance.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 异步工具
 */
public class TaskUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskUtil.class);
    private static ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
    private static FileService fileService = SpringUtil.getBean(FileService.class);

    /**
     * 异步导入excel
     * @param fileId 文件id
     */
    public static void importData(String fileId){
        threadPoolTaskExecutor.execute(new ImportDataThread(fileId,fileService));
    }

    /**
     * 异步导入excel
     * @param fileId 文件id
     * @param headRowNumber 跳过行头
     */
    public static void importData(String fileId,Integer headRowNumber){
        threadPoolTaskExecutor.execute(new ImportDataThread(fileId,fileService,headRowNumber));
    }

    /**
     * 导入excel数据线程内部类
     */
    private static class ImportDataThread implements Runnable {
        private String fileId;
        private FileService fileService;
        private Integer headRowNumber;

        ImportDataThread(String fileId, FileService fileService) {
            this.fileId=fileId;
            this.fileService = fileService;
        }

        ImportDataThread(String fileId, FileService fileService,Integer headRowNumber) {
            this.fileId=fileId;
            this.fileService = fileService;
            this.headRowNumber = headRowNumber;
        }
        @Override
        public void run() {
            LOGGER.info("=============>开始执行导入Excel数据");
            Appendix appendix = fileService.queryFileById(fileId);
            if (appendix !=null){
                InputStream in = new ByteArrayInputStream(appendix.getContent());
                if (headRowNumber != null){
                    EasyExcel.read(in, new AccountImportListener(appendix.getId())).headRowNumber(headRowNumber).sheet().doRead();
                }else {
                    EasyExcel.read(in, new AccountImportListener(appendix.getId())).sheet().doRead();
                }
                fileService.deleteFile(fileId);
                LOGGER.info("=============>结束执行导入Excel数据");
            }else {
                throw new BaseRuningTimeException("文件不存在");
            }
        }
    }
}
