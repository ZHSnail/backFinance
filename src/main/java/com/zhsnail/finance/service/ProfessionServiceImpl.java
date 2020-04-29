package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.mapper.ProfessionMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.vo.ProfessionVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionMapper professionMapper;

    @Override
    public void deleteProfession(String id) {
        Profession profession = professionMapper.selectByPrimaryKey(id);
        if (DICT.BOOLEAN_STATE_TRUE.equals(profession.getIsLeaf())){
            professionMapper.deleteByPrimaryKey(id);
        }else {
            List<Profession> professingList = professionMapper.findByParentId(profession.getId());
            if (CollectionUtils.isEmpty(professingList)){
                professionMapper.deleteByPrimaryKey(id);
            }else {
                throw new BaseRuningTimeException("请先删除它的叶子节点");
            }
        }
    }

    @Override
    public void updateProfession(ProfessionVo professionVo) {
        Profession profession = professionMapper.selectByPrimaryKey(professionVo.getId());
        if (DICT.BOOLEAN_STATE_TRUE.equals(profession.getIsLeaf())){
            BeanUtil.copyProperties(profession,professionVo);
            professionMapper.updateByPrimaryKeySelective(profession);
        }else {
            List<Profession> professingList = professionMapper.findByParentId(profession.getId());
            if (CollectionUtils.isEmpty(professingList)){
                BeanUtil.copyProperties(profession,professionVo);
                professionMapper.updateByPrimaryKeySelective(profession);
            }else {
                throw new BaseRuningTimeException("请先删除它的叶子节点");
            }
        }
    }

    @Override
    public void saveProfession(ProfessionVo professionVo) {
        Profession profession = new Profession();
        BeanUtil.copyProperties(profession,professionVo);
        profession.setId(CodeUtil.getId());
        professionMapper.insert(profession);
    }

    @Override
    public PageInfo<Profession> findAll(ProfessionVo professionVo) {
        PageHelper.startPage(professionVo.getPageNum(),professionVo.getPageSize(),true);
        List<Profession> professionList = professionMapper.findAllByCondition(professionVo);
        PageInfo<Profession> professionPageInfo = new PageInfo<>(professionList);
        return professionPageInfo;
    }


    @Override
    public List<Profession> findParentProession() {
        return professionMapper.findParentProession();
    }

    @Override
    public Profession findByParentId(String parentId) {
        if (StringUtils.isBlank(parentId)){
            throw new BaseRuningTimeException("父节点id不能为空");
        }
        return professionMapper.selectByPrimaryKey(parentId);
    }
}
