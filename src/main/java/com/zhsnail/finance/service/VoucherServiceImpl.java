package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Appendix;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.entity.Voucher;
import com.zhsnail.finance.mapper.AccountBalanceMapper;
import com.zhsnail.finance.mapper.AccountDetailMapper;
import com.zhsnail.finance.mapper.AccountTempMapper;
import com.zhsnail.finance.mapper.VoucherMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.vo.AccountBalanceVo;
import com.zhsnail.finance.vo.AccountTempVo;
import com.zhsnail.finance.vo.VoucherVo;
import com.zhsnail.finance.vo.VoucherVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.macros.VBAMacroExtractor;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherMapper voucherMapper;
    @Autowired
    private AccountTempMapper accountTempMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private AccountDetailMapper accountDetailMapper;
    @Autowired
    private AccountBalanceMapper accountBalanceMapper;

    private static Map<String,String> transMap;

    static {
        transMap = new HashMap<>();
        transMap.put(DICT.VOUCHER_BIZ_TYPE_CHARGE_PAY,"收费付款");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_ASSETS_DEPRECIATED,"固定资产折旧");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_ASSETS_PURCHASE,"固定资产采购");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_ASSETS_REG,"固定资产登记");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_MANUAL_VOUCHER,"手工凭证");
        transMap.put(DICT.VOUCHER_BIZ_TYPE_SALARY_PAY,"工资付款");
        transMap.put(DICT.VOUCHER_DEAL_TYPE_BANK,"银行");
        transMap.put(DICT.VOUCHER_DEAL_TYPE_CASH,"现金");
        transMap.put(DICT.VOUCHER_DEAL_TYPE_OTHER,"其他");
        transMap.put(DICT.VOUCHER_POST_STATUS_UNPOST,"未过账");
        transMap.put(DICT.VOUCHER_POST_STATUS_POSTED,"已过账");
        transMap.put(DICT.STATUS_EXE,"审核通过");
        transMap.put(DICT.STATUS_CMT,"审核中");
        transMap.put(DICT.STATUS_BACK,"退回");
        transMap.put(DICT.STATUS_DRAFT,"草稿");
        transMap.put(DICT.VOUCHER_TICK_STATE_UNTICK,"未勾对");
        transMap.put(DICT.VOUCHER_TICK_STATE_TICKED,"已勾对");
    }

    @Override
    public void saveOrUpdate(VoucherVo voucherVo) {
        Voucher voucher = new Voucher();
        BeanUtil.copyProperties(voucher,voucherVo);
        voucher.setStatus(DICT.STATUS_DRAFT);
        voucher.setPostingStatus(DICT.VOUCHER_POST_STATUS_UNPOST);
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_MANUAL_VOUCHER);
        if (StringUtils.isNotBlank(voucher.getId())){
            voucherMapper.updateByPrimaryKeySelective(voucher);
            accountTempMapper.deleteByVoucherId(voucher.getId());
        }else {
            voucher.setId(CodeUtil.getId());
            voucher.setCode(CodeUtil.getVoucherCode());
            voucherMapper.insert(voucher);
        }
        updateAccountTemp(voucherVo.getAccountTempList(),voucher.getId());
        updateAppendix(voucherVo.getFileIds(),voucher.getId());
    }

    private void updateAccountTemp(List<AccountTemp> accountTempList,String voucherId){
        if (CollectionUtils.isNotEmpty(accountTempList)){
            for (AccountTemp accountTemp:accountTempList){
                accountTemp.setId(CodeUtil.getId());
                accountTemp.setVoucherId(voucherId);
            }
            accountTempMapper.batchInsert(accountTempList);
        }
    }

    private void updateAppendix(List<String> fileIds,String voucherId){
        if (CollectionUtils.isNotEmpty(fileIds)){
            fileService.updateRelation("voucher",voucherId,fileIds.toArray(new String[fileIds.size()]));
        }
    }

    @Override
    public void commitVoucher(VoucherVo voucherVo) {
        Voucher voucher = new Voucher();
        BeanUtil.copyProperties(voucher,voucherVo);
        voucher.setStatus(DICT.STATUS_CMT);
        voucher.setPostingStatus(DICT.VOUCHER_POST_STATUS_UNPOST);
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_MANUAL_VOUCHER);
        if(!(DICT.VOUCHER_DEAL_TYPE_OTHER.equals(voucher.getDealType()))){
            voucher.setTickState(DICT.VOUCHER_TICK_STATE_UNTICK);
        }
        if (StringUtils.isNotBlank(voucher.getId())){
            voucherMapper.updateByPrimaryKeySelective(voucher);
            accountTempMapper.deleteByVoucherId(voucher.getId());
        }else {
            voucher.setCode(CodeUtil.getVoucherCode());
            voucher.setId(CodeUtil.getId());
            voucherMapper.insert(voucher);
        }
        updateAccountTemp(voucherVo.getAccountTempList(),voucher.getId());
        updateAppendix(voucherVo.getFileIds(),voucher.getId());
        activityService.runStart(DICT.VOUCHER_MANUAL_WORK_KEY,voucher.getId(),new HashMap());
    }

    @Override
    public void updateStatusById(String id, String status) {
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        voucher.setStatus(status);
        voucherMapper.updateByPrimaryKeySelective(voucher);
    }

    @Override
    public void lastApprove(String id) {
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        if(!(DICT.VOUCHER_DEAL_TYPE_OTHER.equals(voucher.getDealType()))){
            voucher.setTickState(DICT.VOUCHER_TICK_STATE_UNTICK);
        }
        voucher.setStatus(DICT.STATUS_EXE);
        voucher.setAuditer((String)CommonUtil.getCurrentUser().get("id"));
        voucherMapper.updateByPrimaryKeySelective(voucher);
    }

    @Override
    public PageInfo<VoucherVo> findByCondition(VoucherVo voucherVo) {
        CommonUtil.startPage(voucherVo);
        List<Voucher> voucherList = voucherMapper.findAllByCondition(voucherVo);
        long total = CommonUtil.getPageTotal(voucherList);
        List<VoucherVo> voucherVoList = transList(voucherList);
        PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(voucherVoList);
        voucherVoPageInfo.setTotal(total);
        return voucherVoPageInfo;
    }

    @Override
    public Map findTaskMapList() {
        VoucherVo voucherVo = new VoucherVo();
        voucherVo.setOriginator((String)CommonUtil.getCurrentUser().get("id"));
        List<Voucher> taskList = voucherMapper.findTaskList(voucherVo);
//        //草稿
        List<VoucherVo> draftList = new ArrayList<>();
        //审核中的列表
        List<VoucherVo> cmtList = new ArrayList<>();
        //正在执行的列表
        List<VoucherVo> exeList = new ArrayList<>();
        Map<String, List<Map>> taskMap = CommonUtil.arrangeTaskList(taskList);
        arrangeData(taskMap.get(DICT.TASK_NAME_DRAFT_LIST),draftList);
        arrangeData(taskMap.get(DICT.TASK_NAME_EXE_LIST),cmtList);
        arrangeData(taskMap.get(DICT.TASK_NAME_CMT_LIST),exeList);
        Map map = new HashMap<>();
        map.put(DICT.TASK_NAME_DRAFT_LIST,draftList);
        map.put(DICT.TASK_NAME_EXE_LIST,cmtList);
        map.put(DICT.TASK_NAME_CMT_LIST,exeList);
        return map;
    }

    private void arrangeData(List<Map> taskList,List<VoucherVo> targetList){
        taskList.forEach(item->{
            VoucherVo temp = new VoucherVo();
            BeanUtil.copyProperties(temp,item);
            transString(temp);
            targetList.add(temp);
        });
    }
    @Override
    public void generateVoucher(Voucher voucher, String debitAccountId, String creditAccountId) {
        voucherMapper.insert(voucher);
        Map<String,String> accountIdMap = new HashMap<>();
        accountIdMap.put(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT,debitAccountId);
        accountIdMap.put(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT,creditAccountId);
        Map<String, BigDecimal> amountMap = new HashMap<>();
        amountMap.put(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT,voucher.getDebitTotal());
        amountMap.put(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT,voucher.getCreditTotal());
        List<AccountTemp> accountTemps = CommonUtil.initAccountTemp(voucher.getId(), accountIdMap, amountMap);
        accountTempMapper.batchInsert(accountTemps);
    }

    /**
     * 翻译
     * @param voucherVo
     */
    private void transString(VoucherVo voucherVo){
        voucherVo.setOriginatorName((String) CommonUtil.findUserInfo(voucherVo.getOriginator()).get("name"));
        voucherVo.setAuditerName((String) CommonUtil.findUserInfo(voucherVo.getAuditer()).get("name"));
        voucherVo.setKeeperName((String) CommonUtil.findUserInfo(voucherVo.getKeeper()).get("name"));
        voucherVo.setBizName(transMap.get(voucherVo.getBizType()));
        voucherVo.setDealName(transMap.get(voucherVo.getDealType()));
        voucherVo.setStatus(transMap.get(voucherVo.getStatus()));
        voucherVo.setPostingStatus(transMap.get(voucherVo.getPostingStatus()));
        voucherVo.setTickState(transMap.get(voucherVo.getTickState()));
        if (CollectionUtils.isNotEmpty(voucherVo.getAccountTempList())){
            List<AccountTempVo> accountTempVoList = new ArrayList<>();
            for (AccountTemp accountTemp:voucherVo.getAccountTempList()){
                AccountTempVo accountTempVo = new AccountTempVo();
                BeanUtil.copyProperties(accountTempVo,accountTemp);
                accountTempVo.setAcconutName(CommonUtil.getAccountLongName(accountTemp.getAccount()));
                accountTempVoList.add(accountTempVo);
            }
            voucherVo.setAccountTempVoList(accountTempVoList);
        }
    }

    @Override
    public VoucherVo findById(String id) {
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        VoucherVo voucherVo = new VoucherVo();
        BeanUtil.copyProperties(voucherVo,voucher);
        transString(voucherVo);
        List<Appendix> appendixList = fileService.queryByBizId(id,false);
        List<String> collect = appendixList.stream().map(appendix -> appendix.getId()).collect(Collectors.toList());
        voucherVo.setFileIds(collect);
        return voucherVo;
    }

    @Override
    public PageInfo<VoucherVo> findUnpostVoucher(VoucherVo voucherVo) {
        CommonUtil.startPage(voucherVo);
        List<Voucher> unPostVoucherList = voucherMapper.findUnpostVoucherList(voucherVo);
        long total = CommonUtil.getPageTotal(unPostVoucherList);
        List<VoucherVo> voucherVoList = transList(unPostVoucherList);
        PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(voucherVoList);
        voucherVoPageInfo.setTotal(total);
        return voucherVoPageInfo;
    }

    @Override
    public PageInfo<VoucherVo> findTaskListByCondition(VoucherVo voucherVo) {
        voucherVo.setOriginator((String)CommonUtil.getCurrentUser().get("id"));
        CommonUtil.startPage(voucherVo);
        List<Voucher> allCurrentUserTask = voucherMapper.findAllCurrentUserTask(voucherVo);
        long total = CommonUtil.getPageTotal(allCurrentUserTask);
        List<VoucherVo> voucherVoList = transList(allCurrentUserTask);
        PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(voucherVoList);
        voucherVoPageInfo.setTotal(total);
        return voucherVoPageInfo;
    }

    @Override
    public PageInfo<VoucherVo> findCmtTaskList(VoucherVo voucherVo) {
        List<String> ids = activityService.findCmtBizIds(DICT.VOUCHER_MANUAL_WORK_KEY);
        if (CollectionUtils.isNotEmpty(ids)) {
            CommonUtil.startPage(voucherVo);
            List<Voucher> vouchers = voucherMapper.findByIds(ids);
            long total = CommonUtil.getPageTotal(vouchers);
            List<VoucherVo> list = transList(vouchers);
            PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(list);
            voucherVoPageInfo.setTotal(total);
            return voucherVoPageInfo;
        }
        return new PageInfo<>(new ArrayList<VoucherVo>());
    }

    /**
     * 转换
     * @param voucherList
     * @return
     */
    private List<VoucherVo> transList(List<Voucher> voucherList){
        List<VoucherVo> list = new ArrayList<>();
        voucherList.forEach(item->{
            VoucherVo temp = new VoucherVo();
            BeanUtil.copyProperties(temp,item);
            transString(temp);
            list.add(temp);
        });
        return list;
    }
    @Override
    public PageInfo<VoucherVo> findCashierList(VoucherVo voucherVo,String tickState) {
        voucherVo.setTickState(tickState);
        CommonUtil.startPage(voucherVo);
        List<Voucher> untickVoucherList = voucherMapper.findCashierList(voucherVo);
        long total = CommonUtil.getPageTotal(untickVoucherList);
        List<VoucherVo> list = transList(untickVoucherList);
        PageInfo<VoucherVo> voucherVoPageInfo = new PageInfo<>(list);
        voucherVoPageInfo.setTotal(total);
        return voucherVoPageInfo;
    }

    @Override
    public void tickVoucher(String id) {
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        voucher.setTickState(DICT.VOUCHER_TICK_STATE_TICKED);
        voucher.setTickDate(new Date());
        voucherMapper.updateByPrimaryKeySelective(voucher);
    }

    @Override
    public void postVoucher(VoucherVo voucherVo) {
        List<String> ids = voucherVo.getIds();
        if (CollectionUtils.isNotEmpty(ids)){
            List<Voucher> voucherList = voucherMapper.findByIds(ids);
            List<AccountDetail> accountDetailList = new ArrayList<>();
            for (Voucher voucher:voucherList){
                List<AccountTemp> accountTempList = voucher.getAccountTempList();
                for (AccountTemp accountTemp:accountTempList){
                    AccountDetail accountDetail = new AccountDetail();
                    accountDetail.setAccountId(accountTemp.getAccountId());
                    if (DICT.LENDER_ACCOUNT_DIRECTION_CREDIT.equals(accountTemp.getDirection())){
                        accountDetail.setCreditAmount(accountTemp.getCreditAmt());
                    }
                    if (DICT.LENDER_ACCOUNT_DIRECTION_DEBIT.equals(accountTemp.getDirection())){
                        accountDetail.setDebitAmount(accountTemp.getDebitAmt());
                    }
                    accountDetail.setId(CodeUtil.getId());
                    accountDetail.setVoucherId(voucher.getId());
                    accountDetail.setAccountPeriod(voucher.getAccountPeriod());
                    accountDetailList.add(accountDetail);
                }
                voucher.setPostingDate(new Date());
                voucher.setPostingStatus(DICT.VOUCHER_POST_STATUS_POSTED);
                voucher.setKeeper((String) CommonUtil.getCurrentUser().get("id"));
                voucherMapper.updateByPrimaryKeySelective(voucher);
            }
            List<String> collect = accountDetailList.stream().map(accountDetail -> accountDetail.getAccountId()).distinct().collect(Collectors.toList());
            //拿到所有需要更新会计科目余额信息
            List<AccountBalance> accountBalanceList = accountBalanceMapper.findByAccIds(collect);
            List<AccountBalanceVo> accountBalanceVoList = new ArrayList<>();
            for(int i=0;i<accountBalanceList.size();i++){
                AccountBalance accountBalance = accountBalanceList.get(i);
                AccountBalanceVo accountBalanceVo = new AccountBalanceVo();
                BeanUtil.copyProperties(accountBalanceVo,accountBalance);
                String accountId = accountBalanceVo.getAccountId();
                List<String> parentIds = new ArrayList<>();
                CommonUtil.getParentIds(accountId,parentIds);
                List<AccountDetail> accountDetails = accountDetailList.stream().filter(accountDetail -> accountId.equals(accountDetail.getAccountId())).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(accountDetails)){
                    for (AccountDetail item:accountDetails){
                        if (item.getCreditAmount()!=null){
                            accountBalanceVo.setCreditCurrperiodAmt(accountBalanceVo.getCreditCurrperiodAmt().add(item.getCreditAmount()));
                            accountBalanceVo.setCreditAccumyearAmt(accountBalanceVo.getCreditAccumyearAmt().add(item.getCreditAmount()));
                        }
                        if (item.getDebitAmount()!=null){
                            accountBalanceVo.setDebitCurrperiodAmt(accountBalanceVo.getDebitCurrperiodAmt().add(item.getDebitAmount()));
                            accountBalanceVo.setDebitAccumyearAmt(accountBalanceVo.getDebitAccumyearAmt().add(item.getDebitAmount()));
                        }
                    }
                }
                accountBalanceVoList.add(accountBalanceVo);
                List<AccountBalanceVo> parentAccountBalanceVoList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(parentIds)){
                    //父级科目
                    List<AccountBalance> parentAccountBalanceList = accountBalanceMapper.findByAccIds(parentIds);
                    for (int j = 0;j<parentAccountBalanceList.size();j++){
                        AccountBalance parentAccountBalance = parentAccountBalanceList.get(j);
                        AccountBalanceVo parentAccountBalanceVo = new AccountBalanceVo();
                        BeanUtil.copyProperties(parentAccountBalanceVo,parentAccountBalance);
                        if(CollectionUtils.isNotEmpty(accountDetails)){
                            for (AccountDetail item:accountDetails){
                                if (item.getCreditAmount()!=null){
                                    parentAccountBalanceVo.setCreditCurrperiodAmt(parentAccountBalanceVo.getCreditCurrperiodAmt().add(item.getCreditAmount()));
                                    parentAccountBalanceVo.setCreditAccumyearAmt(parentAccountBalanceVo.getCreditAccumyearAmt().add(item.getCreditAmount()));
                                }
                                if (item.getDebitAmount()!=null){
                                    parentAccountBalanceVo.setDebitCurrperiodAmt(parentAccountBalanceVo.getDebitCurrperiodAmt().add(item.getDebitAmount()));
                                    parentAccountBalanceVo.setDebitAccumyearAmt(parentAccountBalanceVo.getDebitAccumyearAmt().add(item.getDebitAmount()));
                                }
                            }
                        }
                        parentAccountBalanceVoList.add(parentAccountBalanceVo);
                    }
                }
                accountBalanceVoList.addAll(parentAccountBalanceVoList);
            }
            accountBalanceMapper.batchUpdate(accountBalanceVoList);
            accountDetailMapper.batchInsert(accountDetailList);
        }
    }
}
