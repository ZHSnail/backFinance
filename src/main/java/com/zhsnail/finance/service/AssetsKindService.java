package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.AssetsKind;
import com.zhsnail.finance.entity.DormInfo;
import com.zhsnail.finance.vo.AssetsKindVo;

import java.util.List;

public interface AssetsKindService {

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询所有资产类别信息
     * @param assetsKindVo 资产类别信息
     * @return
     */
    PageInfo<AssetsKindVo> findByCondition(AssetsKindVo assetsKindVo);

    /**
     * 查询所有信息
     * @return
     */
    List<AssetsKindVo> findAll();

    /**
     * 保存资产类别信息
     * @param assetsKindVo 资产类别信息
     */
    void saveAssetsKind(AssetsKindVo assetsKindVo);
}
