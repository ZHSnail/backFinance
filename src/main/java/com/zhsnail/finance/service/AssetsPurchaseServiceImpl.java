package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.mapper.AssetsMapper;
import com.zhsnail.finance.mapper.AssetsPurchaseMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsPurchaseVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssetsPurchaseServiceImpl implements AssetsPurchaseService{
    @Autowired
    private AssetsPurchaseMapper assetsPurchaseMapper;
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
        transMap.put(DICT.ASSETS_PURCHASE_METHOD_OPEN_TENDER,"公开招标");
        transMap.put(DICT.ASSETS_PURCHASE_METHOD_FIXED_PURCHASE,"定点采购");
        transMap.put(DICT.ASSETS_PURCHASE_METHOD_HOSPITAL_TENDER,"院内招标");
        transMap.put(DICT.ASSETS_PURCHASE_METHOD_PROTOCOL_SUPPLY,"协议供货");
        transMap.put(DICT.ASSETS_PURCHASE_METHOD_ONLINE_AUCTION,"网上竞价");
        transMap.put(DICT.ASSETS_PURCHASE_METHOD_E_SHOP,"网上商城");
        transMap.put(DICT.STATUS_EXE,"审核通过");
        transMap.put(DICT.STATUS_CMT,"审核中");
        transMap.put(DICT.STATUS_BACK,"退回");
        transMap.put(DICT.STATUS_DRAFT,"草稿");
    }

    private void transString(AssetsPurchaseVo assetsPurchaseVo){
        assetsPurchaseVo.setStatus(transMap.get(assetsPurchaseVo.getStatus()));
        assetsPurchaseVo.setPurchaseMethodName(transMap.get(assetsPurchaseVo.getPurchaseMethod()));
        String name = (String)CommonUtil.findUserInfo(assetsPurchaseVo.getCreater()).get("name");
        assetsPurchaseVo.setCreaterName(name);
    }

    private List<AssetsPurchaseVo> transList(List<AssetsPurchase> list){
        List<AssetsPurchaseVo> assetsPurchaseVoList = new ArrayList<>();
        list.forEach(item->{
            AssetsPurchaseVo assetsPurchaseVo = new AssetsPurchaseVo();
            BeanUtil.copyProperties(assetsPurchaseVo,item);
            transString(assetsPurchaseVo);
            assetsPurchaseVoList.add(assetsPurchaseVo);
        });
        return assetsPurchaseVoList;
    }

    private AssetsPurchase initAssetsPurchase(AssetsPurchaseVo assetsPurchaseVo){
        AssetsPurchase assetsPurchase = new AssetsPurchase();
        BeanUtil.copyProperties(assetsPurchase,assetsPurchaseVo);
        if(StringUtils.isNotBlank(assetsPurchase.getId())){
            assetsPurchase.setUpdateTime(new Date());
            assetsPurchase.setUpdater((String) CommonUtil.getCurrentUser().get("id"));
        }else {
            assetsPurchase.setCreater((String) CommonUtil.getCurrentUser().get("id"));
            assetsPurchase.setCreateTime(new Date());
            assetsPurchase.setCode(CodeUtil.getCode("AP"));
        }
        return assetsPurchase;
    }

    @Override
    public void saveOrUpdate(AssetsPurchaseVo assetsPurchaseVo) {
        AssetsPurchase assetsPurchase = initAssetsPurchase(assetsPurchaseVo);
        assetsPurchase.setStatus(DICT.STATUS_DRAFT);
        List<Assets> assetsList = assetsPurchase.getAssetsList();
        if (StringUtils.isNotBlank(assetsPurchase.getId())){
            assetsPurchaseMapper.updateByPrimaryKeySelective(assetsPurchase);
            assetsMapper.deleteByPurchaseId(assetsPurchase.getId());
        }else {
            assetsPurchase.setId(CodeUtil.getId());
            assetsPurchaseMapper.insert(assetsPurchase);
        }
        assetsList.forEach(assets -> {
            assets.setState(DICT.BOOLEAN_STATE_FALSE);
            assets.setPurchaseId(assetsPurchase.getId());
            assets.setId(CodeUtil.getId());
        });
        assetsMapper.batchInsert(assetsList);
    }

    @Override
    public void commitAssetsPurchase(AssetsPurchaseVo assetsPurchaseVo) {
        AssetsPurchase assetsPurchase = initAssetsPurchase(assetsPurchaseVo);
        assetsPurchase.setStatus(DICT.STATUS_CMT);
        List<Assets> assetsList = assetsPurchase.getAssetsList();
        if (StringUtils.isNotBlank(assetsPurchase.getId())){
            assetsPurchaseMapper.updateByPrimaryKeySelective(assetsPurchase);
            assetsMapper.deleteByPurchaseId(assetsPurchase.getId());
        }else {
            assetsPurchase.setId(CodeUtil.getId());
            assetsPurchaseMapper.insert(assetsPurchase);
        }
        assetsList.forEach(assets -> {
            assets.setState(DICT.BOOLEAN_STATE_FALSE);
            assets.setPurchaseId(assetsPurchase.getId());
            assets.setId(CodeUtil.getId());
        });
        assetsMapper.batchInsert(assetsList);
        activityService.runStart(DICT.ASSETS_PURCHASE_WORK_KEY,assetsPurchase.getId(),new HashMap());
    }

    @Override
    public void updateStatusById(String id, String status) {
        AssetsPurchase assetsPurchase = assetsPurchaseMapper.selectByPrimaryKey(id);
        assetsPurchase.setStatus(status);
        assetsPurchaseMapper.updateByPrimaryKeySelective(assetsPurchase);
    }

    @Override
    public void lastApprove(String id) {

    }

    @Override
    public AssetsPurchaseVo findById(String id) {
        AssetsPurchase assetsPurchase = assetsPurchaseMapper.selectByPrimaryKey(id);
        AssetsPurchaseVo assetsPurchaseVo = new AssetsPurchaseVo();
        BeanUtil.copyProperties(assetsPurchaseVo,assetsPurchase);
        transString(assetsPurchaseVo);
        return assetsPurchaseVo;
    }

    @Override
    public PageInfo<AssetsPurchaseVo> findByCondition(AssetsPurchaseVo assetsPurchaseVo) {
        CommonUtil.startPage(assetsPurchaseVo);
        List<AssetsPurchase> assetsPurchaseList = assetsPurchaseMapper.findAllByCondition(assetsPurchaseVo);
        long total = CommonUtil.getPageTotal(assetsPurchaseList);
        List<AssetsPurchaseVo> assetsPurchaseVoList = transList(assetsPurchaseList);
        PageInfo<AssetsPurchaseVo> assetsPurchaseVoPageInfo = new PageInfo<>(assetsPurchaseVoList);
        assetsPurchaseVoPageInfo.setTotal(total);
        return assetsPurchaseVoPageInfo;
    }

    @Override
    public Map findTaskMapList() {
        AssetsPurchaseVo assetsPurchaseVo = new AssetsPurchaseVo();
        assetsPurchaseVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        List<AssetsPurchase> taskList = assetsPurchaseMapper.findTaskList(assetsPurchaseVo);
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
    public PageInfo<AssetsPurchaseVo> findCmtTaskList(AssetsPurchaseVo assetsPurchaseVo) {
        List<String> ids = activityService.findCmtBizIds(DICT.ASSETS_PURCHASE_WORK_KEY);
        if (CollectionUtils.isNotEmpty(ids)) {
            CommonUtil.startPage(assetsPurchaseVo);
            List<AssetsPurchase> assetsPurchaseList = assetsPurchaseMapper.findByIds(ids);
            long pageTotal = CommonUtil.getPageTotal(assetsPurchaseList);
            List<AssetsPurchaseVo> assetsPurchaseVoList = transList(assetsPurchaseList);
            PageInfo<AssetsPurchaseVo> assetsPurchaseVoPageInfo= new PageInfo<>(assetsPurchaseVoList);
            assetsPurchaseVoPageInfo.setTotal(pageTotal);
            return assetsPurchaseVoPageInfo;
        }
        return new PageInfo<>(new ArrayList<AssetsPurchaseVo>());
    }

    @Override
    public PageInfo<AssetsPurchaseVo> findTaskListByCondition(AssetsPurchaseVo assetsPurchaseVo) {
        assetsPurchaseVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        CommonUtil.startPage(assetsPurchaseVo);
        List<AssetsPurchase> allCurrentUserTask = assetsPurchaseMapper.findAllCurrentUserTask(assetsPurchaseVo);
        long total = CommonUtil.getPageTotal(allCurrentUserTask);
        List<AssetsPurchaseVo> assetsPurchaseVoList = transList(allCurrentUserTask);
        PageInfo<AssetsPurchaseVo> assetsPurchaseVoPageInfo = new PageInfo<>(assetsPurchaseVoList);
        assetsPurchaseVoPageInfo.setTotal(total);
        return assetsPurchaseVoPageInfo;
    }
}
