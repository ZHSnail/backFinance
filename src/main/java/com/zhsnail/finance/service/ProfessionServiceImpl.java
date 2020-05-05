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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionMapper professionMapper;

    @Override
    public void deleteProfession(String id) {
        Profession profession = professionMapper.selectByPrimaryKey(id);
        if (DICT.BOOLEAN_STATE_TRUE.equals(profession.getIsLeaf())) {
            professionMapper.deleteByPrimaryKey(id);
        } else {
            List<Profession> professingList = professionMapper.findByParentId(profession.getId());
            if (CollectionUtils.isEmpty(professingList)) {
                professionMapper.deleteByPrimaryKey(id);
            } else {
                throw new BaseRuningTimeException("请先删除它的叶子节点");
            }
        }
    }

    @Override
    public void updateProfession(ProfessionVo professionVo) {
        Profession profession = professionMapper.selectByPrimaryKey(professionVo.getId());
        if (DICT.BOOLEAN_STATE_TRUE.equals(profession.getIsLeaf())) {
            BeanUtil.copyProperties(profession, professionVo);
            if (DICT.BOOLEAN_STATE_FALSE.equals(professionVo.getIsLeaf())) {
                profession.setGrade("");
                profession.setParentId("");
            }
            professionMapper.updateByPrimaryKeySelective(profession);
        } else {
            List<Profession> professingList = professionMapper.findByParentId(profession.getId());
            if (CollectionUtils.isEmpty(professingList)) {
                BeanUtil.copyProperties(profession, professionVo);
                professionMapper.updateByPrimaryKeySelective(profession);
            } else {
                throw new BaseRuningTimeException("请先删除它的叶子节点");
            }
        }
    }

    @Override
    public void saveProfession(ProfessionVo professionVo) {
        Profession profession = new Profession();
        BeanUtil.copyProperties(profession, professionVo);
        profession.setId(CodeUtil.getId());
        professionMapper.insert(profession);
    }

    @Override
    public PageInfo<Profession> findAll(ProfessionVo professionVo) {
        PageHelper.startPage(professionVo.getPageNum(), professionVo.getPageSize(), true);
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
        if (StringUtils.isBlank(parentId)) {
            throw new BaseRuningTimeException("父节点id不能为空");
        }
        return professionMapper.selectByPrimaryKey(parentId);
    }

    @Override
    public List<Map> generateProession(boolean isObj) {
        List<Profession> professionList = professionMapper.findAllByCondition(new ProfessionVo());
        ArrayList<Map> list = new ArrayList<>();
        arrangeData(list, professionList.stream().map(profession -> BeanUtil.beanToMap(profession)).collect(Collectors.toList()), isObj);
        return list;
    }

    /**
     * 生成级联选择框
     *
     * @param list           结果
     * @param professionList 所有专业信息
     * @param isObj          是否生成对象
     */
    private void arrangeData(List<Map> list, List<Map> professionList, boolean isObj) {
        //拿到所有根节点
        List<Map> rootList = professionList.stream().filter(map -> DICT.BOOLEAN_STATE_FALSE.equals(map.get("isLeaf"))).collect(Collectors.toList());
        List<Map> tempList = new ArrayList<>();
        for (Map map : rootList) {
            Map temp = new HashMap<>();
            temp.put("label", map.get("name"));
            if (isObj) {
                temp.put("value", map);
            } else {
                temp.put("value", map.get("id"));
            }
            tempList.add(temp);
        }
        list.addAll(tempList);
        for (Map map : list) {
            String id = isObj ? (String) ((Map) map.get("value")).get("id") : (String) map.get("value");
            //拿到每一个对应的叶子节点去重后的名字
            List<String> names = distinctByName(professionList.stream().filter(profession -> id.equals(profession.get("parentId"))).collect(Collectors.toList()));
            //第二层节点
            List<Map> leafList = new ArrayList<>();
            //遍历
            for (String name : names) {
                Map leafMap = new HashMap<>();
                leafMap.put("label", name);
                leafMap.put("value", name);
                //拿到名字对应的所有值
                List<Map> childrenList = professionList.stream().filter(profession -> id.equals(profession.get("parentId")) && name.equals(profession.get("name")))
                        .map(profession -> {
                            Map temp = new HashMap<>();
                            temp.put("label", profession.get("grade"));
                            if (isObj) {
                                temp.put("value", profession);
                            } else {
                                temp.put("value", profession.get("id"));
                            }
                            return temp;
                        }).collect(Collectors.toList());
                leafMap.put("children", childrenList);
                leafList.add(leafMap);
            }
            map.put("children", leafList);
        }
    }

    private List<String> distinctByName(List<Map> professionList) {
        List<String> list = new ArrayList<>();
        for (Map map : professionList) {
            if (!list.contains((String) map.get("name"))) {
                list.add((String) map.get("name"));
            }
        }
        return list;
    }
}
