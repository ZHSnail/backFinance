package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.FeeKind;
import com.zhsnail.finance.entity.SysSequence;
import com.zhsnail.finance.mapper.FeeKindMapper;
import com.zhsnail.finance.mapper.SysSequenceMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.vo.FeeKindVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeKindServiceImpl implements FeeKindService {
    @Autowired
    private FeeKindMapper feeKindMapper;
    @Autowired
    private SysSequenceMapper sysSequenceMapper;

    @Override
    public void deleteFeeKind(String id) {
        feeKindMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateFeeKind(FeeKindVo feeKindVo) {
        FeeKind feeKind = new FeeKind();
        BeanUtil.copyProperties(feeKind,feeKindVo);
        FeeKind feeDb = feeKindMapper.selectByPrimaryKey(feeKindVo.getId());
        if (!feeDb.getName().equals(feeKind.getName())){
            CommonUtil.initSequence(CommonUtil.toPinyin(feeKind.getName(),true));
        }
        feeKindMapper.updateByPrimaryKeySelective(feeKind);
    }

    @Override
    public void saveFeeKind(FeeKindVo feeKindVo) {
        FeeKind feeKind = new FeeKind();
        BeanUtil.copyProperties(feeKind,feeKindVo);
        feeKind.setId(CodeUtil.getId());
        CommonUtil.initSequence(CommonUtil.toPinyin(feeKind.getName(),true));
        feeKindMapper.insert(feeKind);
    }

    @Override
    public PageInfo<FeeKind> findByCondition(FeeKindVo feeKindVo) {
        PageHelper.startPage(feeKindVo.getPageNum(),feeKindVo.getPageSize(),true);
        List<FeeKind> feeKindList = feeKindMapper.findByCondition(feeKindVo);
        PageInfo<FeeKind> feeKindPageInfo = new PageInfo<>(feeKindList);
        return feeKindPageInfo;
    }

    @Override
    public List<FeeKind> findAll() {
        return feeKindMapper.findAll();
    }
}
