package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.mapper.ProfessionMapper;
import com.zhsnail.finance.vo.ProfessionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionMapper professionMapper;

    @Override
    public void deleteProfession(String id) {

    }

    @Override
    public void updateProfession(ProfessionVo professionVo) {

    }

    @Override
    public void saveProfession(ProfessionVo professionVo) {

    }

    @Override
    public PageInfo<Profession> findAll(ProfessionVo professionVo) {
        return null;
    }
}
