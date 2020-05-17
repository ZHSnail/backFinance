package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.entity.AssetsKind;
import com.zhsnail.finance.mapper.AssetsKindMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.AssetsKindVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssetsKindServiceImpl implements AssetsKindService {
    @Autowired
    private AssetsKindMapper assetsKindMapper;
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

    /**
     * 翻译字典值
     * @param assetsKindVo
     */
    private void transString(AssetsKindVo assetsKindVo){
        List<Account> allAccount = CommonUtil.findAllAccount();
        List<Account> creditAssetsAccount = allAccount.stream().filter(account -> assetsKindVo.getCreditAssetsAccId().equals(account.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(creditAssetsAccount)){
            assetsKindVo.setCreditAssetsAccName(CommonUtil.getAccountLongName(creditAssetsAccount.get(0)));
        }
        List<Account> debitAssetsAccount = allAccount.stream().filter(account -> assetsKindVo.getDebitAssetsAccId().equals(account.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(creditAssetsAccount)){
            assetsKindVo.setDebitAssetsAccName(CommonUtil.getAccountLongName(debitAssetsAccount.get(0)));
        }
        List<Account> creditDepreAccount = allAccount.stream().filter(account -> assetsKindVo.getCreditDepreAccId().equals(account.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(creditAssetsAccount)){
            assetsKindVo.setCreditDepreAccName(CommonUtil.getAccountLongName(creditDepreAccount.get(0)));
        }
        List<Account> debitDepreAccount = allAccount.stream().filter(account -> assetsKindVo.getDebitDepreAccId().equals(account.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(creditAssetsAccount)){
            assetsKindVo.setDebitDepreAccName(CommonUtil.getAccountLongName(debitDepreAccount.get(0)));
        }
        assetsKindVo.setDepreMethodName(transMap.get(assetsKindVo.getDepreMethod()));
    }

    /**
     * List<AssetsKind>转翻译后的List<AssetsKindVo>
     * @param list
     * @return
     */
    private List<AssetsKindVo> transToAssetsKindVo(List<AssetsKind> list){
        List<AssetsKindVo> assetsKindVoList = new ArrayList<>();
        list.forEach(item->{
            AssetsKindVo assetsKindVo = new AssetsKindVo();
            BeanUtil.copyProperties(assetsKindVo,item);
            transString(assetsKindVo);
            assetsKindVoList.add(assetsKindVo);
        });
        return assetsKindVoList;
    }

    @Override
    public void deleteById(String id) {
        assetsKindMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<AssetsKindVo> findByCondition(AssetsKindVo assetsKindVo) {
        CommonUtil.startPage(assetsKindVo);
        List<AssetsKind> assetsKindList = assetsKindMapper.findAll();
        PageInfo<AssetsKind> assetsKindPageInfo = new PageInfo<>(assetsKindList);
        long total = assetsKindPageInfo.getTotal();
        PageInfo<AssetsKindVo> assetsKindVoPageInfo = new PageInfo<>(transToAssetsKindVo(assetsKindList));
        assetsKindVoPageInfo.setTotal(total);
        return assetsKindVoPageInfo;
    }

    @Override
    public List<AssetsKindVo> findAll() {
        List<AssetsKind> assetsKindList = assetsKindMapper.findAll();
        return transToAssetsKindVo(assetsKindList);
    }

    @Override
    public void saveAssetsKind(AssetsKindVo assetsKindVo) {
        AssetsKind assetsKind = new AssetsKind();
        BeanUtil.copyProperties(assetsKind,assetsKindVo);
        assetsKind.setId(CodeUtil.getId());
        assetsKindMapper.insert(assetsKind);
    }
}
