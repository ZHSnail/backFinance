package com.zhsnail.finance.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.metadata.holder.ReadHolder;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.ImportResult;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.AccountMapper;
import com.zhsnail.finance.service.LenderService;
import com.zhsnail.finance.service.SystemService;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.util.SpringUtil;
import org.apache.batik.transcoder.keys.StringKey;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class AccountImportListener extends AnalysisEventListener<Map<Integer, String>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountImportListener.class);

    private static final int BATCH_COUNT = 200;

    List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();

    private List<String> errString = new ArrayList<>();

    private String succString = "";

    private LenderService lenderService;

    private SystemService systemService;

    private String fileId;

    private int save_size = 0;

    private List<Account> accounts;

    /**
     *
     * @param fileId 文件id
     */
    public AccountImportListener(String fileId) {
        this.lenderService = SpringUtil.getBean(LenderService.class);
        this.systemService = SpringUtil.getBean(SystemService.class);
        this.accounts = lenderService.findAllAccount();
        this.fileId = fileId;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
        LOGGER.info("当前数据："+JsonUtil.obj2String(data));
        if (checkRowData(data)){
            list.add(data);
        }else {
            Integer currentRowNum = analysisContext.getCurrentRowNum();
            throw new BaseRuningTimeException("第"+currentRowNum+"行数据有误！");
        }
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    private boolean checkRowData(Map<Integer, String> data){
        if (Integer.parseInt(data.get(2))>1){
            Iterator<Integer> iterator = data.keySet().iterator();
            while (iterator.hasNext()){
                Integer key = iterator.next();
                String s = data.get(key);
                if (StringUtils.isBlank(s)){
                    return false;
                }
            }
            List<Account> collect = accounts.stream().filter(account -> account.getCode().equals(data.get(3))).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)){
                return false;
            }
            String id = collect.get(0).getId();
            data.put(3,id);
        }else {
            if (StringUtils.isBlank(data.get(0))||StringUtils.isBlank(data.get(1))
                    ||StringUtils.isBlank(data.get(2))||StringUtils.isBlank(data.get(4))
                    ||StringUtils.isBlank(data.get(5))||StringUtils.isBlank(data.get(6))){
                return false;
            }
        }
        List<String> rowList = new ArrayList<>();
        rowList.add("是");
        rowList.add("否");
        if (!rowList.contains(data.get(4))||!rowList.contains(data.get(5))||!rowList.contains(data.get(6))){
            return false;
        }
        for (int i=4;i<=6;i++){
            String s = data.get(i);
            if ("是".equals(s)){
                data.put(i, DICT.BOOLEAN_STATE_TRUE);
            }
            if ("否".equals(s)){
                data.put(i,DICT.BOOLEAN_STATE_FALSE);
            }
        }
        return true;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        succString="成功保存"+save_size+"条数据";
        ImportResult importResult = new ImportResult();
        importResult.setFileId(fileId);
        importResult.setId(CodeUtil.getId());
        importResult.setErrResult(JsonUtil.obj2String(errString));
        importResult.setSuccResult(JsonUtil.obj2String(succString));
        systemService.saveImresult(importResult);

        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 读头数据
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        checkHead(headMap);
    }

    private void checkHead(Map<Integer, String> headMap){
        List<String> headList = new ArrayList<>();
        headList.add("会计科目表(*号为必填项,当级次大于1时，父级科目编码必填)");
        headList.add("科目名称*");
        headList.add("科目编码*");
        headList.add("级次*");
        headList.add("父级科目编码");
        headList.add("是否明细*");
        headList.add("是否现金*");
        headList.add("是否银行*");

        Iterator<Integer> iterator = headMap.keySet().iterator();
        while (iterator.hasNext()){
            Integer key = iterator.next();
            String s = headMap.get(key);
            if (!headList.contains(s)){
                throw new BaseRuningTimeException("头信息有错误，请勿修改模板文件信息!!");
            }
        }
    }
    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            LOGGER.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
            errString.add("第"+excelDataConvertException.getRowIndex()+"行，第"+excelDataConvertException.getColumnIndex()+"列解析异常");
        }else {
            LOGGER.error(exception.getMessage());
            errString.add(exception.getMessage());
        }
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("共{}条数据，开始存储数据库！", list.size());
        List<Account> accountList = new ArrayList<>();
        for(int i =0;i<list.size();i++){
            Account account = new Account();
            Map<Integer, String> map = list.get(i);
            account.setId(CodeUtil.getId());
            account.setAccountName(map.get(0));
            account.setCode(map.get(1));
            account.setLevel(map.get(2));
            account.setParentId(map.get(3));
            account.setIsDetail(map.get(4));
            account.setIsCash(map.get(5));
            account.setIsBank(map.get(6));
            accountList.add(account);
        }
        save_size += list.size();
        lenderService.execBatchInsert(accountList);
        LOGGER.info("存储数据库成功！");
    }
}
