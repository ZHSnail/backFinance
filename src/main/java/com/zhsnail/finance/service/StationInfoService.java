package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.StationInfo;
import com.zhsnail.finance.vo.StationInfoVo;

import java.util.List;
import java.util.Map;

public interface StationInfoService {
    /**
     * 删除岗位信息
     * @param id 岗位信息id
     */
    void deleteStationInfo(String id);

    /**
     * 修改岗位信息
     * @param stationInfoVo 岗位信息
     */
    void updateStationInfo(StationInfoVo stationInfoVo);

    /**
     * 保存岗位信息
     * @param stationInfoVo 岗位信息
     */
    void saveStationInfo(StationInfoVo stationInfoVo);

    /**
     * 分页查询所有岗位信息
     * @param stationInfoVo 岗位信息
     * @return
     */
    PageInfo<StationInfoVo> findAllByCondition(StationInfoVo stationInfoVo);

    /**
     * 生成级联选择的岗位信息
     * @return 级联选择
     */
    List<Map> generateStationInfo();

    /**
     * 查找岗位工资和薪级工资
     * @return
     */
    List<Map> findScaleAndStationList();

    /**
     * 更新岗位工资和薪级工资
     * @param stationInfoVo
     */
    void updateScaleAndStation(StationInfoVo stationInfoVo);

    /**
     * 导出信息
     * @param stationInfoVo
     * @return
     */
    List<StationInfoVo> exportStationInfoVo(StationInfoVo stationInfoVo);

    List<StationInfoVo> findAll();
}
