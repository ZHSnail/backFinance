package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.FloatWage;
import com.zhsnail.finance.vo.FloatWageVo;

import java.util.List;
import java.util.Map;

public interface FloatWageService {
    /**
     * 删除浮动工资信息
     * @param id 浮动工资信息id
     */
    void deleteFloatWage(String id);

    /**
     * 修改浮动工资信息
     * @param floatWageVo 浮动工资信息
     */
    void updateFloatWage(FloatWageVo floatWageVo);

    /**
     * 保存浮动工资信息
     * @param floatWageVo 浮动工资信息
     */
    void saveFloatWage(FloatWageVo floatWageVo);

    /**
     * 分页查询所有浮动工资信息
     * @param floatWageVo 浮动工资信息
     * @return
     */
    PageInfo<FloatWageVo> findAll(FloatWageVo floatWageVo);

    /**
     * 导出信息
     * @param floatWageVo
     * @return
     */
    List<FloatWageVo> exportFloatWageVo(FloatWageVo floatWageVo);

    /**
     * 根据扣减分类查找浮动工资项
     * @param signType
     * @return
     */
    List<FloatWageVo> findBySignType(String signType);

    /**
     * 生成前端框所需的浮动工资
     * @return
     */
    Map<String,List<FloatWage>> generateFloatWageInfo();
}
