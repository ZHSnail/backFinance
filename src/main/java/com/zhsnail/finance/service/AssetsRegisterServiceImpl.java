package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.entity.AssetsRegister;
import com.zhsnail.finance.mapper.AssetsMapper;
import com.zhsnail.finance.mapper.AssetsRegisterMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import com.zhsnail.finance.vo.AssetsVo;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssetsRegisterServiceImpl implements AssetsRegisterService{
    @Autowired
    private AssetsRegisterMapper assetsRegisterMapper;
    @Autowired
    private AssetsMapper assetsMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private VoucherService voucherService;
    private static Map<String,String> transMap;

    static {
        transMap = new HashMap<>();
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_PURCHASE,"采购");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_LEASE,"租赁");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_DEVELOPMENT,"研制转入");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_INFRASTRUCTURE,"基建转入");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_DONATIONS,"接受捐赠");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_EXTERNAL,"外部调入");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_INVENTORY,"盘盈");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_COOPERATIVE,"合作建所方投入");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_FINANCING,"融资租入");
        transMap.put(DICT.ASSETS_OBTAIN_METHOD_OTHER,"其他");
        transMap.put(DICT.ASSETS_DEPRE_METHOD_STRLINE,"年限平均法(直线法)");
        transMap.put(DICT.ASSETS_DEPRE_METHOD_WORKLOAD,"工作量法");
        transMap.put(DICT.ASSETS_DEPRE_METHOD_DOUDECBAL,"双倍余额递减法");
        transMap.put(DICT.ASSETS_DEPRE_METHOD_SUMYEAR,"年数总和法");
        transMap.put(DICT.STATUS_EXE,"审核通过");
        transMap.put(DICT.STATUS_CMT,"审核中");
        transMap.put(DICT.STATUS_BACK,"退回");
        transMap.put(DICT.STATUS_DRAFT,"草稿");
    }

    private void transString(AssetsRegisterVo assetsRegisterVo){
        assetsRegisterVo.setStatus(transMap.get(assetsRegisterVo.getStatus()));
        if(assetsRegisterVo.getAssets() != null){
            Assets assets = assetsRegisterVo.getAssets();
            assets.setDepreMethod(transMap.get(assets.getDepreMethod()));
            assets.setObtainMethod(transMap.get(assets.getObtainMethod()));
        }
    }

    private List<AssetsRegisterVo> transList(List<AssetsRegister> list){
        List<AssetsRegisterVo> assetsRegisterVoList = new ArrayList<>();
        list.forEach(item->{
            AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
            BeanUtil.copyProperties(assetsRegisterVo,item);
            transString(assetsRegisterVo);
            assetsRegisterVoList.add(assetsRegisterVo);
        });
        return assetsRegisterVoList;
    }

    @Override
    public void saveOrUpdate(AssetsRegisterVo assetsRegisterVo) {
        AssetsRegister assetsRegister = initAssetsRegister(assetsRegisterVo);
        assetsRegister.setStatus(DICT.STATUS_DRAFT);
        Assets assets = assetsRegisterVo.getAssets();
        if(StringUtils.isNotBlank(assetsRegister.getId())) {
            assetsMapper.updateByPrimaryKeySelective(assets);
            assetsRegisterMapper.updateByPrimaryKeySelective(assetsRegister);
        }else {
            assetsRegister.setId(CodeUtil.getId());
            assets.setId(CodeUtil.getId());
            assets.setState(DICT.BOOLEAN_STATE_FALSE);
            assetsRegister.setAssetsId(assets.getId());
            assetsMapper.insert(assets);
            assetsRegisterMapper.insert(assetsRegister);
        }
    }

    private AssetsRegister initAssetsRegister(AssetsRegisterVo assetsRegisterVo){
        AssetsRegister assetsRegister = new AssetsRegister();
        BeanUtil.copyProperties(assetsRegister,assetsRegisterVo);
        if(StringUtils.isNotBlank(assetsRegister.getId())){
            assetsRegister.setUpdateTime(new Date());
            assetsRegister.setUpdater((String) CommonUtil.getCurrentUser().get("id"));
        }else {
            assetsRegister.setCreater((String) CommonUtil.getCurrentUser().get("id"));
            assetsRegister.setCreateTime(new Date());
            assetsRegister.setCode(CodeUtil.getCode("AR"));
        }
        return assetsRegister;
    }
    @Override
    public void commitAssetsRegister(AssetsRegisterVo assetsRegisterVo) {
        AssetsRegister assetsRegister = initAssetsRegister(assetsRegisterVo);
        assetsRegister.setStatus(DICT.STATUS_CMT);
        Assets assets = assetsRegisterVo.getAssets();
        if(StringUtils.isNotBlank(assetsRegister.getId())) {
            assetsMapper.updateByPrimaryKeySelective(assets);
            assetsRegisterMapper.updateByPrimaryKeySelective(assetsRegister);
        }else {
            assetsRegister.setId(CodeUtil.getId());
            assets.setId(CodeUtil.getId());
            assets.setState(DICT.BOOLEAN_STATE_FALSE);
            assetsRegister.setAssetsId(assets.getId());
            assetsMapper.insert(assets);
            assetsRegisterMapper.insert(assetsRegister);
        }
        activityService.runStart(DICT.ASSETS_REGISTER_WORK_KEY,assetsRegister.getId(),new HashMap());
    }

    @Override
    public void updateStatusById(String id, String status) {
        AssetsRegister assetsRegister = assetsRegisterMapper.selectByPrimaryKey(id);
        assetsRegister.setStatus(status);
        assetsRegisterMapper.updateByPrimaryKeySelective(assetsRegister);
    }

    @Override
    public void lastApprove(String id) {
        AssetsRegister assetsRegister = assetsRegisterMapper.selectByPrimaryKey(id);
        assetsRegister.setStatus(DICT.STATUS_EXE);
        Assets assets = assetsRegister.getAssets();
        assets.setState(DICT.BOOLEAN_STATE_TRUE);
        AssetsKind assetsKind = assets.getAssetsKind();
        String creditAssetsAccId = assetsKind.getCreditAssetsAccId();
        String debitAssetsAccId = assetsKind.getDebitAssetsAccId();
        Voucher voucher = initVoucher(assetsRegister);
        assetsRegisterMapper.updateByPrimaryKeySelective(assetsRegister);
        assetsMapper.updateByPrimaryKeySelective(assets);
        voucherService.generateVoucher(voucher,debitAssetsAccId,creditAssetsAccId);
    }

    /**
     * 生成凭证实体
     * @param assetsRegister
     * @return
     */
    private Voucher initVoucher(AssetsRegister assetsRegister){
        Assets assets = assetsRegister.getAssets();
        Voucher voucher = CommonUtil.initVoucher(assetsRegister.getCreater());
        //业务日期
        voucher.setBizDate(new Date());
        //业务id
        voucher.setBizId(assetsRegister.getId());
        //业务类型
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_ASSETS_REG);
        //源单号
        voucher.setBizCode(assetsRegister.getCode());
        //交易类型
        voucher.setDealType(DICT.VOUCHER_DEAL_TYPE_OTHER);
        BigDecimal orival = assets.getOrival();
        String num = assets.getNum();
        //借方金额
        voucher.setDebitTotal(orival.multiply(new BigDecimal(num)));
        //贷方金额
        voucher.setCreditTotal(orival.multiply(new BigDecimal(num)));
        //审核人
        voucher.setAuditer((String) CommonUtil.getCurrentUser().get("id"));
        //备注
        voucher.setMemo(assetsRegister.getMemo());
        return voucher;
    }

    @Override
    public AssetsRegisterVo findById(String id) {
        AssetsRegister assetsRegister = assetsRegisterMapper.selectByPrimaryKey(id);
        AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
        BeanUtil.copyProperties(assetsRegisterVo,assetsRegister);
        transString(assetsRegisterVo);
        return assetsRegisterVo;
    }

    @Override
    public PageInfo<AssetsRegisterVo> findByCondition(AssetsRegisterVo assetsRegisterVo) {
        CommonUtil.startPage(assetsRegisterVo);
        List<AssetsRegister> assetsRegisterList = assetsRegisterMapper.findAllByCondition(assetsRegisterVo);
        long pageTotal = CommonUtil.getPageTotal(assetsRegisterList);
        List<AssetsRegisterVo> assetsRegisterVoList = transList(assetsRegisterList);
        PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = new PageInfo<>(assetsRegisterVoList);
        assetsRegisterVoPageInfo.setTotal(pageTotal);
        return assetsRegisterVoPageInfo;
    }

    @Override
    public Map findTaskMapList() {
        AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
        assetsRegisterVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        List<AssetsRegister> taskList = assetsRegisterMapper.findTaskList(assetsRegisterVo);
        Map<String, List<Map>> taskMap = CommonUtil.arrangeTaskList(taskList);
        taskMap.get(DICT.TASK_NAME_DRAFT_LIST).forEach(map -> {
            map.put("status",transMap.get(map.get("status")));
        });
        taskMap.get(DICT.TASK_NAME_CMT_LIST).forEach(map -> {
            map.put("status",transMap.get(map.get("status")));
        });
        taskMap.get(DICT.TASK_NAME_EXE_LIST).forEach(map -> {
            map.put("status",transMap.get(map.get("status")));
        });
        return taskMap;
    }

    @Override
    public PageInfo<AssetsRegisterVo> findCmtTaskList(AssetsRegisterVo assetsRegisterVo) {
        List<String> ids = activityService.findCmtBizIds(DICT.ASSETS_PURCHASE_WORK_KEY);
        if (CollectionUtils.isNotEmpty(ids)) {
            CommonUtil.startPage(assetsRegisterVo);
            List<AssetsRegister> assetsRegisters = assetsRegisterMapper.findByIds(ids);
            long total = CommonUtil.getPageTotal(assetsRegisters);
            List<AssetsRegisterVo> list = transList(assetsRegisters);
            PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = new PageInfo<>(list);
            assetsRegisterVoPageInfo.setTotal(total);
            return assetsRegisterVoPageInfo;
        }
        return new PageInfo<>(new ArrayList<AssetsRegisterVo>());
    }

    @Override
    public PageInfo<AssetsRegisterVo> findTaskListByCondition(AssetsRegisterVo assetsRegisterVo) {
        assetsRegisterVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        CommonUtil.startPage(assetsRegisterVo);
        List<AssetsRegister> allCurrentUserTask = assetsRegisterMapper.findAllCurrentUserTask(assetsRegisterVo);
        long total = CommonUtil.getPageTotal(allCurrentUserTask);
        List<AssetsRegisterVo> assetsRegisterVoList = transList(allCurrentUserTask);
        PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = new PageInfo<>(assetsRegisterVoList);
        assetsRegisterVoPageInfo.setTotal(total);
        return assetsRegisterVoPageInfo;
    }
}
