package com.zhsnail.finance.service;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.vo.StudentInfoVo;

import java.util.List;

public interface StudentInfoService {
    /**
     * 删除学生信息
     * @param id 学生信息id
     */
    void deleteStudentInfo(String id);

    /**
     * 修改学生信息
     * @param studentInfoVo 学生信息
     */
    void updateStudentInfo(StudentInfoVo studentInfoVo);

    /**
     * 保存学生信息
     * @param studentInfoVo 学生信息
     */
    void saveStudentInfo(StudentInfoVo studentInfoVo);

    /**
     * 分页查询所有学生信息
     * @param studentInfoVo 学生信息
     * @return
     */
    PageInfo<StudentInfo> findByCondition(StudentInfoVo studentInfoVo);

    /**
     * 查询所有信息
     * @return
     */
    List<StudentInfo> findAll();

    /**
     * 根据id查找信息
     * @param id 学生id
     * @return
     */
    StudentInfo findById(String id);
}
