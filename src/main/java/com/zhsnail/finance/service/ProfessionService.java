package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.Profession;
import com.zhsnail.finance.vo.ProfessionVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询所有不是叶子节点的专业
     * @return
     */
    List<Profession> findParentProession();

    /**
     * 查找父节点
     * @param parentId
     * @return
     */
    Profession findByParentId(String parentId);

    /**
     * 生成级联选择的专业
     * @param isObj 是否生成对象
     * @return 级联选择
     */
    List<Map> generateProession(boolean isObj);
}
