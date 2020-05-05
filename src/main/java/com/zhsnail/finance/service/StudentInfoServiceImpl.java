package com.zhsnail.finance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.mapper.StudentInfoMapper;
import com.zhsnail.finance.mapper.UserMapper;
import com.zhsnail.finance.util.BeanUtil;
import com.zhsnail.finance.util.CodeUtil;
import com.zhsnail.finance.vo.StudentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void deleteStudentInfo(String id) {
        userMapper.deleteByStudentId(id);
        studentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateStudentInfo(StudentInfoVo studentInfoVo) {
        StudentInfo studentInfo = studentInfoMapper.selectByPrimaryKey(studentInfoVo.getId());
        if (!(studentInfo.getStuNo().equals(studentInfoVo.getStuNo()))){
            User user = userMapper.findUserByStuId(studentInfo.getId());
            user.setUserName(studentInfo.getStuNo());
            userMapper.updateByPrimaryKeySelective(user);
        }
        BeanUtil.copyProperties(studentInfo,studentInfoVo);
        studentInfoMapper.updateByPrimaryKeySelective(studentInfo);
    }

    @Override
    public void saveStudentInfo(StudentInfoVo studentInfoVo) {
        StudentInfo studentInfo = new StudentInfo();
        BeanUtil.copyProperties(studentInfo,studentInfoVo);
        studentInfo.setId(CodeUtil.getId());
        studentInfoMapper.insert(studentInfo);
        User user = new User();
        user.setId(CodeUtil.getId());
        user.setPsw("123456");
        user.setUserName(studentInfo.getStuNo());
        user.setStudentId(studentInfo.getId());
        userMapper.insert(user);
    }

    @Override
    public PageInfo<StudentInfo> findByCondition(StudentInfoVo studentInfoVo) {
        PageHelper.startPage(studentInfoVo.getPageNum(),studentInfoVo.getPageSize(),true);
        List<StudentInfo> studentInfoList = studentInfoMapper.findAllByCondition(studentInfoVo);
        PageInfo<StudentInfo> studentPageInfo = new PageInfo<>(studentInfoList);
        return studentPageInfo;
    }

    @Override
    public List<StudentInfo> findAll() {
        return studentInfoMapper.findAllByCondition(new StudentInfoVo());
    }

    @Override
   public StudentInfo findById(String id){
        return studentInfoMapper.selectByPrimaryKey(id);
    }
}
