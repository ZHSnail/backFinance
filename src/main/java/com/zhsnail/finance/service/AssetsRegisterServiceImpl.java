package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.entity.AssetsRegister;
import com.zhsnail.finance.mapper.AssetsMapper;
import com.zhsnail.finance.mapper.AssetsRegisterMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsRegisterVo;
import com.zhsnail.finance.vo.AssetsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssetsRegisterServiceImpl implements AssetsRegisterService{
    @Autowired
    private AssetsRegisterMapper assetsRegisterMapper;
    @Autowired
    private AssetsMapper assetsMapper;
    @Autowired
    private ActivityService activityService;
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
            assetsRegister.setCode(CodeUtil.getCode("AR"));
            assets.setId(CodeUtil.getId());
            assets.setState(DICT.BOOLEAN_STATE_FALSE);
            assetsRegister.setAssetsId(assets.getId());
            assetsMapper.insert(assets);
            assetsRegisterMapper.insert(assetsRegister);
        }
    }

    @Override
    public void updateStatusById(String id, String status) {
        AssetsRegister assetsRegister = assetsRegisterMapper.selectByPrimaryKey(id);
        assetsRegister.setStatus(status);
        assetsRegisterMapper.updateByPrimaryKeySelective(assetsRegister);
    }

    @Override
    public void lastApprove(String id) {

    }

    @Override
    public AssetsRegisterVo findById(String id) {
        AssetsRegister assetsRegister = assetsRegisterMapper.selectByPrimaryKey(id);
        AssetsRegisterVo assetsRegisterVo = new AssetsRegisterVo();
        BeanUtil.copyProperties(assetsRegisterVo,assetsRegister);
        return assetsRegisterVo;
    }

    @Override
    public PageInfo<AssetsRegisterVo> findByCondition(AssetsRegisterVo assetsRegisterVo) {
        return null;
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
        return null;
    }

    @Override
    public PageInfo<AssetsRegisterVo> findTaskListByCondition(AssetsRegisterVo assetsRegisterVo) {
        assetsRegisterVo.setCreater((String)CommonUtil.getCurrentUser().get("id"));
        CommonUtil.startPage(assetsRegisterVo);
        List<AssetsRegister> allCurrentUserTask = assetsRegisterMapper.findAllCurrentUserTask(assetsRegisterVo);
        PageInfo<AssetsRegister> assetsRegisterPageInfo = new PageInfo<>(allCurrentUserTask);
        long total = assetsRegisterPageInfo.getTotal();
        List<AssetsRegisterVo> assetsRegisterVoList = transList(allCurrentUserTask);
        PageInfo<AssetsRegisterVo> assetsRegisterVoPageInfo = new PageInfo<>(assetsRegisterVoList);
        assetsRegisterVoPageInfo.setTotal(total);
        return assetsRegisterVoPageInfo;
    }
}
