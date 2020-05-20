package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.Assets;
import com.zhsnail.finance.vo.AssetsVo;

import java.util.List;

public interface AssetsService {
    List<Assets> findPurchaseAssetsList();

    PageInfo<AssetsVo> findByCondition(AssetsVo assetsVo);

    PageInfo<AssetsVo> findAllChangeAssets(AssetsVo assetsVo);
}
