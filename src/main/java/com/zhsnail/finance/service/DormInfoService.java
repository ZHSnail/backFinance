package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.DormInfo;
import com.zhsnail.finance.vo.DormInfoVo;

public interface DormInfoService {
    /**
     * 删除宿舍信息
     * @param id 宿舍信息id
     */
    void deleteDormInfo(String id);

    /**
     * 修改宿舍信息
     * @param dormInfoVo 宿舍信息
     */
    void updateDormInfo(DormInfoVo dormInfoVo);

    /**
     * 保存宿舍信息
     * @param dormInfoVo 宿舍信息
     */
    void saveDormInfo(DormInfoVo dormInfoVo);

    /**
     * 分页查询所有宿舍信息
     * @param dormInfoVo 宿舍信息
     * @return
     */
    PageInfo<DormInfo> findAll(DormInfoVo dormInfoVo);
}
