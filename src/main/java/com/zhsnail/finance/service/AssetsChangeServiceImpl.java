package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.mapper.AssetsMapper;
import com.zhsnail.finance.mapper.AssetsChangeMapper;
import com.zhsnail.finance.mapper.AssetsTempMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsChangeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssetsChangeServiceImpl implements AssetsChangeService{
    @Autowired
    private AssetsChangeMapper assetsChangeMapper;
    @Autowired
    private AssetsMapper assetsMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private AssetsTempMapper assetsTempMapper;

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

    private void transString(AssetsChangeVo assetsChangeVo){
        assetsChangeVo.setStatus(transMap.get(assetsChangeVo.getStatus()));
//        assetsChangeVo.setPurchaseMethodName(transMap.get(assetsChangeVo.getPurchaseMethod()));
        String name = (String)CommonUtil.findUserInfo(assetsChangeVo.getCreater()).get("name");
        assetsChangeVo.setCreaterName(name);
        if (CollectionUtils.isNotEmpty(assetsChangeVo.getAssetsList())){
            List<Assets> assetsList = assetsChangeVo.getAssetsList();
            assetsList.forEach(item->{
                item.setDepreMethod(transMap.get(item.getDepreMethod()));
                item.setObtainMethod(transMap.get(item.getObtainMethod()));
            });
        }
    }

    private List<AssetsChangeVo> transList(List<AssetsChange> list){
        List<AssetsChangeVo> assetsChangeVoList = new ArrayList<>();
        list.forEach(item->{
            AssetsChangeVo assetsChangeVo = new AssetsChangeVo();
            BeanUtil.copyProperties(assetsChangeVo,item);
            transString(assetsChangeVo);
            assetsChangeVoList.add(assetsChangeVo);
        });
        return assetsChangeVoList;
    }

    private AssetsChange initAssetsChange(AssetsChangeVo assetsChangeVo){
        AssetsChange assetsChange = new AssetsChange();
        BeanUtil.copyProperties(assetsChange,assetsChangeVo);
        if(StringUtils.isNotBlank(assetsChange.getId())){
            assetsChange.setUpdateTime(new Date());
            assetsChange.setUpdater((String) CommonUtil.getCurrentUser().get("id"));
        }else {
            assetsChange.setCreater((String) CommonUtil.getCurrentUser().get("id"));
            assetsChange.setCreateTime(new Date());
            assetsChange.setCode(CodeUtil.getCode("AC"));
        }
        return assetsChange;
    }

    @Override
    public void saveOrUpdate(AssetsChangeVo assetsChangeVo) {
        AssetsChange assetsChange = initAssetsChange(assetsChangeVo);
        assetsChange.setStatus(DICT.STATUS_DRAFT);
        if (StringUtils.isNotBlank(assetsChange.getId())){
            assetsChangeMapper.updateByPrimaryKeySelective(assetsChange);
            assetsTempMapper.deleteByChangeId(assetsChange.getId());
        }else {
            assetsChange.setId(CodeUtil.getId());
            assetsChangeMapper.insert(assetsChange);
        }
        List<AssetsTemp> assetsTempList = initAssetsTempList(assetsChange.getAssetsList(), assetsChange.getId());
        assetsTempMapper.batchInsert(assetsTempList);
    }

    private List<AssetsTemp> initAssetsTempList(List<Assets> assetsList,String changeId){
        List<AssetsTemp> assetsTempList = new ArrayList<>();
        assetsList.forEach(item->{
            AssetsTemp assetsTemp = new AssetsTemp();
            BeanUtil.copyProperties(assetsTemp,item);
            assetsTemp.setChangeId(changeId);
            assetsTemp.setDepreMethod(CommonUtil.getKeyByVal(transMap,assetsTemp.getDepreMethod()));
            assetsTemp.setObtainMethod(CommonUtil.getKeyByVal(transMap,assetsTemp.getObtainMethod()));
            assetsTempList.add(assetsTemp);
        });
        return assetsTempList;
    }
    @Override
    public void commitAssetsChange(AssetsChangeVo assetsChangeVo) {
        AssetsChange assetsChange = initAssetsChange(assetsChangeVo);
        assetsChange.setStatus(DICT.STATUS_CMT);
        if (StringUtils.isNotBlank(assetsChange.getId())){
            assetsChangeMapper.updateByPrimaryKeySelective(assetsChange);
            assetsTempMapper.deleteByChangeId(assetsChange.getId());
        }else {
            assetsChange.setId(CodeUtil.getId());
            assetsChangeMapper.insert(assetsChange);
        }
        List<AssetsTemp> assetsTempList = initAssetsTempList(assetsChange.getAssetsList(), assetsChange.getId());
        assetsTempMapper.batchInsert(assetsTempList);
        activityService.runStart(DICT.ASSETS_CHANGE_WORK_KEY,assetsChange.getId(),new HashMap());
    }

    @Override
    public void updateStatusById(String id, String status) {
        AssetsChange assetsChange = assetsChangeMapper.selectByPrimaryKey(id);
        assetsChange.setStatus(status);
        assetsChangeMapper.updateByPrimaryKeySelective(assetsChange);
    }

    @Override
    public void lastApprove(String id) {
        AssetsChange assetsChange = assetsChangeMapper.selectByPrimaryKey(id);
        assetsChange.setStatus(DICT.STATUS_EXE);
        assetsChangeMapper.updateByPrimaryKeySelective(assetsChange);
        List<AssetsTemp> assetsTempList = assetsChange.getAssetsTempList();
        List<Assets> assetsList = new ArrayList<>();
        assetsTempList.forEach(assetsTemp -> {
            Assets assets = new Assets();
            BeanUtil.copyProperties(assets,assetsTemp);
            assetsList.add(assets);
        });
        assetsMapper.batchUpdate(assetsList);
    }


    @Override
    public AssetsChangeVo findById(String id) {
        AssetsChange assetsChange = assetsChangeMapper.selectByPrimaryKey(id);
        AssetsChangeVo assetsChangeVo = new AssetsChangeVo();
        BeanUtil.copyProperties(assetsChangeVo,assetsChange);
        if (DICT.STATUS_EXE.equals(assetsChange.getStatus())){
            assetsChangeVo.setAssetsList(assetsChange.getAssetsList());
        }else {
           List<Assets> assetsList =  new ArrayList<>();
           assetsChange.getAssetsTempList().forEach(item->{
               Assets assets = new Assets();
               BeanUtil.copyProperties(assets,item);
               assetsList.add(assets);
           });
          assetsChangeVo.setAssetsList(assetsList);
        }
        transString(assetsChangeVo);
        return assetsChangeVo;
    }

    @Override
    public PageInfo<AssetsChangeVo> findByCondition(AssetsChangeVo assetsChangeVo) {
        CommonUtil.startPage(assetsChangeVo);
        List<AssetsChange> assetsChangeList = assetsChangeMapper.findAllByCondition(assetsChangeVo);
        long total = CommonUtil.getPageTotal(assetsChangeList);
        List<AssetsChangeVo> assetsChangeVoList = transList(assetsChangeList);
        PageInfo<AssetsChangeVo> assetsChangeVoPageInfo = new PageInfo<>(assetsChangeVoList);
        assetsChangeVoPageInfo.setTotal(total);
        return assetsChangeVoPageInfo;
    }

    @Override
    public Map findTaskMapList() {
        AssetsChangeVo assetsChangeVo = new AssetsChangeVo();
        assetsChangeVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        List<AssetsChange> taskList = assetsChangeMapper.findTaskList(assetsChangeVo);
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
    public PageInfo<AssetsChangeVo> findCmtTaskList(AssetsChangeVo assetsChangeVo) {
        List<String> ids = activityService.findCmtBizIds(DICT.ASSETS_CHANGE_WORK_KEY);
        if (CollectionUtils.isNotEmpty(ids)) {
            CommonUtil.startPage(assetsChangeVo);
            List<AssetsChange> assetsChangeList = assetsChangeMapper.findByIds(ids);
            long pageTotal = CommonUtil.getPageTotal(assetsChangeList);
            List<AssetsChangeVo> assetsChangeVoList = transList(assetsChangeList);
            PageInfo<AssetsChangeVo> assetsChangeVoPageInfo= new PageInfo<>(assetsChangeVoList);
            assetsChangeVoPageInfo.setTotal(pageTotal);
            return assetsChangeVoPageInfo;
        }
        return new PageInfo<>(new ArrayList<AssetsChangeVo>());
    }

    @Override
    public PageInfo<AssetsChangeVo> findTaskListByCondition(AssetsChangeVo assetsChangeVo) {
        assetsChangeVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        CommonUtil.startPage(assetsChangeVo);
        List<AssetsChange> allCurrentUserTask = assetsChangeMapper.findAllCurrentUserTask(assetsChangeVo);
        long total = CommonUtil.getPageTotal(allCurrentUserTask);
        List<AssetsChangeVo> assetsChangeVoList = transList(allCurrentUserTask);
        PageInfo<AssetsChangeVo> assetsChangeVoPageInfo = new PageInfo<>(assetsChangeVoList);
        assetsChangeVoPageInfo.setTotal(total);
        return assetsChangeVoPageInfo;
    }
}
