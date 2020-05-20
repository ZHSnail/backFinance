package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.entity.AssetsDepreciation;
import com.zhsnail.finance.mapper.AssetsDepreciationMapper;
import com.zhsnail.finance.mapper.AssetsMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsDepreciationVo;
import com.zhsnail.finance.vo.AssetsDepreciationVo;
import com.zhsnail.finance.vo.AssetsDepreciationVo;
import com.zhsnail.finance.vo.AssetsDepreciationVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AssetsDepreciationServiceImpl implements AssetsDepreciationService {
    @Autowired
    private AssetsDepreciationMapper assetsDepreciationMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private AssetsMapper assetsMapper;
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

    private void transString(AssetsDepreciationVo assetsDepreciationVo){
        assetsDepreciationVo.setStatus(transMap.get(assetsDepreciationVo.getStatus()));
        String name = (String)CommonUtil.findUserInfo(assetsDepreciationVo.getCreater()).get("name");
        assetsDepreciationVo.setCreaterName(name);
        if(assetsDepreciationVo.getAssets() != null){
            Assets assets = assetsDepreciationVo.getAssets();
            assets.setDepreMethod(transMap.get(assets.getDepreMethod()));
            assets.setObtainMethod(transMap.get(assets.getObtainMethod()));
        }
    }

    private List<AssetsDepreciationVo> transList(List<AssetsDepreciation> list){
        List<AssetsDepreciationVo> assetsDepreciationVoList = new ArrayList<>();
        list.forEach(item->{
            AssetsDepreciationVo assetsDepreciationVo = new AssetsDepreciationVo();
            BeanUtil.copyProperties(assetsDepreciationVo,item);
            transString(assetsDepreciationVo);
            assetsDepreciationVoList.add(assetsDepreciationVo);
        });
        return assetsDepreciationVoList;
    }

    private AssetsDepreciation initAssetsDepreciation(AssetsDepreciationVo assetsDepreciationVo){
        AssetsDepreciation assetsDepreciation = new AssetsDepreciation();
        BeanUtil.copyProperties(assetsDepreciation,assetsDepreciationVo);
        if(StringUtils.isNotBlank(assetsDepreciation.getId())){
            assetsDepreciation.setUpdateTime(new Date());
            assetsDepreciation.setUpdater((String) CommonUtil.getCurrentUser().get("id"));
        }else {
            assetsDepreciation.setCreater((String) CommonUtil.getCurrentUser().get("id"));
            assetsDepreciation.setCreateTime(new Date());
            assetsDepreciation.setCode(CodeUtil.getCode("AD"));
        }
        return assetsDepreciation;
    }
    
    @Override
    public void saveOrUpdate(AssetsDepreciationVo assetsDepreciationVo) {
        AssetsDepreciation assetsDepreciation = initAssetsDepreciation(assetsDepreciationVo);
        Assets assets = assetsMapper.selectByPrimaryKey(assetsDepreciation.getAssetsId());
        assetsDepreciation.setStatus(DICT.STATUS_DRAFT);
        assetsDepreciation.setDepreTime(new Date());
        assetsDepreciation.setDepreAmount(calculateDepreciation(assets));
        if (StringUtils.isNotBlank(assetsDepreciation.getId())){
            assetsDepreciationMapper.updateByPrimaryKeySelective(assetsDepreciation);
        }else {
            assetsDepreciation.setId(CodeUtil.getId());
            assetsDepreciationMapper.insert(assetsDepreciation);
        }
    }

    /**
     * 计算折旧金额
     * @param assets
     * @return
     */
    private BigDecimal calculateDepreciation(Assets assets){
        BigDecimal result = null;
        //原价值
        BigDecimal orival = assets.getOrival();
        //预计使用年限
        String usefulLife = assets.getUsefulLife();
        //预计净残值率
        BigDecimal salvage = assets.getSalvage();
        //年限平均法(直线法)
        if (DICT.ASSETS_DEPRE_METHOD_STRLINE.equals(assets.getDepreMethod())){
            //预计净残值
            BigDecimal temp = orival.multiply(salvage);
            result = (orival.subtract(temp)).divide(new BigDecimal(usefulLife),2, BigDecimal.ROUND_HALF_UP);
        }
        //工作量法
        if (DICT.ASSETS_DEPRE_METHOD_WORKLOAD.equals(assets.getDepreMethod())){
            BigDecimal temp = (orival.multiply(new BigDecimal("1").subtract(salvage))).divide(new BigDecimal("1000"),2, BigDecimal.ROUND_HALF_UP);
            result = new BigDecimal("1200").multiply(temp);
        }
        //双倍余额递减法
        if (DICT.ASSETS_DEPRE_METHOD_DOUDECBAL.equals(assets.getDepreMethod())){
            result = (orival.multiply(new BigDecimal("2"))).divide(new BigDecimal(usefulLife),2, BigDecimal.ROUND_HALF_UP);
        }
        //年数总和法
        if (DICT.ASSETS_DEPRE_METHOD_SUMYEAR.equals(assets.getDepreMethod())){
            //预计净残值
            BigDecimal temp = orival.multiply(salvage);
            (orival.subtract(temp)).multiply(new BigDecimal("12").multiply(salvage));
        }
        return result;
    }

    @Override
    public void commitAssetsDepreciation(AssetsDepreciationVo assetsDepreciationVo) {
        AssetsDepreciation assetsDepreciation = initAssetsDepreciation(assetsDepreciationVo);
        Assets assets = assetsMapper.selectByPrimaryKey(assetsDepreciation.getAssetsId());
        assetsDepreciation.setStatus(DICT.STATUS_CMT);
        assetsDepreciation.setDepreTime(new Date());
        assetsDepreciation.setDepreAmount(calculateDepreciation(assets));
        if (StringUtils.isNotBlank(assetsDepreciation.getId())){
            assetsDepreciationMapper.updateByPrimaryKeySelective(assetsDepreciation);
        }else {
            assetsDepreciation.setId(CodeUtil.getId());
            assetsDepreciationMapper.insert(assetsDepreciation);
        }
        activityService.runStart(DICT.ASSETS_DEPRECIATION_WORK_KEY,assetsDepreciation.getId(),new HashMap());
    }

    @Override
    public void updateStatusById(String id, String status) {
        AssetsDepreciation assetsDepreciation = assetsDepreciationMapper.selectByPrimaryKey(id);
        assetsDepreciation.setStatus(status);
        assetsDepreciationMapper.updateByPrimaryKeySelective(assetsDepreciation);
    }

    @Override
    public void lastApprove(String id) {
        AssetsDepreciation assetsDepreciation = assetsDepreciationMapper.selectByPrimaryKey(id);
        assetsDepreciation.setStatus(DICT.STATUS_EXE);
        assetsDepreciationMapper.updateByPrimaryKeySelective(assetsDepreciation);
        Voucher voucher = initVoucher(assetsDepreciation);
        AssetsKind assetsKind = assetsDepreciation.getAssets().getAssetsKind();
        voucherService.generateVoucher(voucher,assetsKind.getDebitDepreAccId(),assetsKind.getCreditDepreAccId());
    }

    private Voucher initVoucher(AssetsDepreciation assetsDepreciation) {
        Voucher voucher = CommonUtil.initVoucher(assetsDepreciation.getCreater());
        //业务日期
        voucher.setBizDate(new Date());
        //业务id
        voucher.setBizId(assetsDepreciation.getId());
        //业务类型
        voucher.setBizType(DICT.VOUCHER_BIZ_TYPE_ASSETS_DEPRECIATED);
        //源单号
        voucher.setBizCode(assetsDepreciation.getCode());
        //交易类型
        voucher.setDealType(DICT.VOUCHER_DEAL_TYPE_OTHER);
        //借方金额
        voucher.setDebitTotal(assetsDepreciation.getDepreAmount());
        //贷方金额
        voucher.setCreditTotal(assetsDepreciation.getDepreAmount());
        //审核人
        voucher.setAuditer((String) CommonUtil.getCurrentUser().get("id"));
        //备注
        voucher.setMemo(assetsDepreciation.getMemo());
        return voucher;
    }

    @Override
    public AssetsDepreciationVo findById(String id) {
        AssetsDepreciation assetsDepreciation = assetsDepreciationMapper.selectByPrimaryKey(id);
        AssetsDepreciationVo assetsDepreciationVo = new AssetsDepreciationVo();
        BeanUtil.copyProperties(assetsDepreciationVo,assetsDepreciation);
        transString(assetsDepreciationVo);
        return assetsDepreciationVo;
    }

    @Override
    public PageInfo<AssetsDepreciationVo> findByCondition(AssetsDepreciationVo assetsDepreciationVo) {
        CommonUtil.startPage(assetsDepreciationVo);
        List<AssetsDepreciation> assetsDepreciationList = assetsDepreciationMapper.findAllByCondition(assetsDepreciationVo);
        long total = CommonUtil.getPageTotal(assetsDepreciationList);
        List<AssetsDepreciationVo> assetsDepreciationVoList = transList(assetsDepreciationList);
        PageInfo<AssetsDepreciationVo> assetsDepreciationVoPageInfo = new PageInfo<>(assetsDepreciationVoList);
        assetsDepreciationVoPageInfo.setTotal(total);
        return assetsDepreciationVoPageInfo;
    }

    @Override
    public Map findTaskMapList() {
        AssetsDepreciationVo assetsDepreciationVo = new AssetsDepreciationVo();
        assetsDepreciationVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        List<AssetsDepreciation> taskList = assetsDepreciationMapper.findTaskList(assetsDepreciationVo);
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
    public PageInfo<AssetsDepreciationVo> findCmtTaskList(AssetsDepreciationVo assetsDepreciationVo) {
        List<String> ids = activityService.findCmtBizIds(DICT.ASSETS_DEPRECIATION_WORK_KEY);
        if (CollectionUtils.isNotEmpty(ids)) {
            CommonUtil.startPage(assetsDepreciationVo);
            List<AssetsDepreciation> assetsDepreciationList = assetsDepreciationMapper.findByIds(ids);
            long pageTotal = CommonUtil.getPageTotal(assetsDepreciationList);
            List<AssetsDepreciationVo> assetsDepreciationVoList = transList(assetsDepreciationList);
            PageInfo<AssetsDepreciationVo> assetsDepreciationVoPageInfo= new PageInfo<>(assetsDepreciationVoList);
            assetsDepreciationVoPageInfo.setTotal(pageTotal);
            return assetsDepreciationVoPageInfo;
        }
        return new PageInfo<>(new ArrayList<AssetsDepreciationVo>());
    }

    @Override
    public PageInfo<AssetsDepreciationVo> findTaskListByCondition(AssetsDepreciationVo assetsDepreciationVo) {
        assetsDepreciationVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        CommonUtil.startPage(assetsDepreciationVo);
        List<AssetsDepreciation> allCurrentUserTask = assetsDepreciationMapper.findAllCurrentUserTask(assetsDepreciationVo);
        long total = CommonUtil.getPageTotal(allCurrentUserTask);
        List<AssetsDepreciationVo> assetsDepreciationVoList = transList(allCurrentUserTask);
        PageInfo<AssetsDepreciationVo> assetsDepreciationVoPageInfo = new PageInfo<>(assetsDepreciationVoList);
        assetsDepreciationVoPageInfo.setTotal(total);
        return assetsDepreciationVoPageInfo;
    }
}
