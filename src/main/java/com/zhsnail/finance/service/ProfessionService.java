package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.vo.ProfessionVo;

public interface ProfessionService {
    /**
     * 删除专业信息
     * @param id 专业信息id
     */
    void deleteProfession(String id);

    /**
     * 修改专业信息
     * @param professionVo 专业信息
     */
    void updateProfession(ProfessionVo professionVo);

    /**
     * 保存专业信息
     * @param professionVo 专业信息
     */
    void saveProfession(ProfessionVo professionVo);

    /**
     * 分页查询所有专业信息
     * @param professionVo 专业信息
     * @return
     */
    PageInfo<Profession> findAll(ProfessionVo professionVo);
}
