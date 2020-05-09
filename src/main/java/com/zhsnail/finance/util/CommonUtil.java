package com.zhsnail.finance.util;


import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.StudentInfo;
import com.zhsnail.finance.entity.SysSequence;
import com.zhsnail.finance.entity.SystemParam;
import com.zhsnail.finance.service.StudentInfoService;
import com.zhsnail.finance.service.SysSequenceService;
import com.zhsnail.finance.service.SystemService;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtil {
    private static SysSequenceService sysSequenceService = SpringUtil.getBean(SysSequenceService.class);
    private static StudentInfoService studentInfoService = SpringUtil.getBean(StudentInfoService.class);
    private static SystemService systemService = SpringUtil.getBean(SystemService.class);

    /**
     * 获取session中的用户
     *
     * @return
     */
    public static Map getCurrentUser() {
        return (Map) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
    }

    /**
     * 获取字符串拼音的第一个字母
     *
     * @param chinese    字符串
     * @param needUpCase 是否需要大写
     * @return
     */
    public static String toFirstChar(String chinese, boolean needUpCase) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return needUpCase ? pinyinStr.toUpperCase() : pinyinStr;
    }

    /**
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese, boolean needUpCase) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return needUpCase ? pinyinStr.toUpperCase() : pinyinStr;
    }

    /**
     * 初始化单号序列
     *
     * @param prefixName 前缀名称
     * @return
     */
    public static void initSequence(String prefixName) {
        SysSequence sysSequence = new SysSequence();
        sysSequence.setName(prefixName);
        sysSequence.setValue(0);
        sysSequence.setStart(1);
        sysSequence.setStep((short) 1);
        sysSequenceService.saveSysSequence(sysSequence);
    }

    /**
     * 根据学生id或员工id查询信息
     *
     * @param id
     * @return
     */
    public static Map findUserInfo(String id) {
        Map userMap = new HashMap();
        List<StudentInfo> studentInfoList = studentInfoService.findAll();
        List<StudentInfo> result = studentInfoList.stream().filter(studentInfo -> studentInfo.getId().equals(id)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(result)) {
            userMap = BeanUtil.beanToMap(result.get(0));
        }
        return new HashMap();
        //TODO 查询员工的信息
//        if (DICT.LOGIN_STUDENT.equals(currentUser.get(DICT.LOGIN))){
//            StudentInfo studentInfo = studentInfoService.findById(id);
//            userMap = BeanUtil.beanToMap(studentInfo);
//        }
//        if (DICT.LOGIN_STAFF.equals(currentUser.get(DICT.LOGIN))){
//
//        }

    }

    /**
     * 获取当前系统参数
     * @return
     */
    public static SystemParam getCurrentSysParam() {
        return systemService.getCurrentSysParam();
    }
}
