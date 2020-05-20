package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.entity.AssetsKind;
import com.zhsnail.finance.entity.AssetsTemp;
import com.zhsnail.finance.mapper.AssetsMapper;
import com.zhsnail.finance.mapper.AssetsTempMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsVo;
import com.zhsnail.finance.vo.AssetsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssetsServiceImpl implements AssetsService {
    @Autowired
    private AssetsMapper assetsMapper;
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
        transMap.put(DICT.STATUS_EXE,"审核通过");
        transMap.put(DICT.STATUS_CMT,"审核中");
        transMap.put(DICT.STATUS_BACK,"退回");
        transMap.put(DICT.STATUS_DRAFT,"草稿");
    }

    private void transString(AssetsVo assetsVo){
        assetsVo.setDepreMethod(transMap.get(assetsVo.getDepreMethod()));
        assetsVo.setObtainMethod(transMap.get(assetsVo.getObtainMethod()));
    }

    private List<AssetsVo> transList(List<Assets> list){
        List<AssetsVo> assetsVoList = new ArrayList<>();
        list.forEach(item->{
            AssetsVo assetsVo = new AssetsVo();
            BeanUtil.copyProperties(assetsVo,item);
            transString(assetsVo);
            assetsVoList.add(assetsVo);
        });
        return assetsVoList;
    }
    
    @Override
    public List<Assets> findPurchaseAssetsList() {
        return assetsMapper.findPurchaseAssetsList();
    }

    @Override
    public PageInfo<AssetsVo> findByCondition(AssetsVo assetsVo) {
        CommonUtil.startPage(assetsVo);
        List<Assets> allByCondition = assetsMapper.findAllByCondition(assetsVo);
        long pageTotal = CommonUtil.getPageTotal(allByCondition);
        List<AssetsVo> assetsVos = transList(allByCondition);
        PageInfo<AssetsVo> assetsVoPageInfo = new PageInfo<>(assetsVos);
        assetsVoPageInfo.setTotal(pageTotal);
        return assetsVoPageInfo;
    }

    @Override
    public PageInfo<AssetsVo> findAllChangeAssets(AssetsVo assetsVo) {
        CommonUtil.startPage(assetsVo);
        List<Assets> allByCondition = assetsMapper.findAllByCondition(assetsVo);
        long pageTotal = CommonUtil.getPageTotal(allByCondition);
        List<AssetsVo> assetsVos = transList(allByCondition);
        List<String> assetsTempIds = assetsTempMapper.findAllId();
        List<AssetsVo> collect = assetsVos.stream().filter(item -> !assetsTempIds.contains(item.getId())).collect(Collectors.toList());
        PageInfo<AssetsVo> assetsVoPageInfo = new PageInfo<>(collect);
        assetsVoPageInfo.setTotal(pageTotal);
        return assetsVoPageInfo;
    }
}
