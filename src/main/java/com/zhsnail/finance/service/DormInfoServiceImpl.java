package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.DormInfo;
import com.zhsnail.finance.mapper.DormInfoMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.vo.DormInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormInfoServiceImpl implements DormInfoService {
    @Autowired
    private DormInfoMapper dormInfoMapper;

    @Override
    public void deleteDormInfo(String id) {
        dormInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateDormInfo(DormInfoVo dormInfoVo) {
        DormInfo dormInfo = new DormInfo();
        BeanUtil.copyProperties(dormInfo,dormInfoVo);
        dormInfoMapper.updateByPrimaryKeySelective(dormInfo);
    }

    @Override
    public void saveDormInfo(DormInfoVo dormInfoVo) {
        DormInfo dormInfo = new DormInfo();
        BeanUtil.copyProperties(dormInfo,dormInfoVo);
        dormInfo.setId(CodeUtil.getId());
        dormInfoMapper.insert(dormInfo);
    }

    @Override
    public PageInfo<DormInfo> findByCondition(DormInfoVo dormInfoVo) {
        PageHelper.startPage(dormInfoVo.getPageNum(),dormInfoVo.getPageSize(),true);
        List<DormInfo> dormInfoList = dormInfoMapper.findAll();
        PageInfo<DormInfo> pageInfo = new PageInfo<>(dormInfoList);
        return pageInfo;
    }

    @Override
    public List<DormInfo> findAll() {
        return dormInfoMapper.findAll();
    }
}
