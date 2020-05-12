package com.zhsnail.finance.util;


import com.github.pagehelper.PageHelper;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.service.AccountService;
import com.zhsnail.finance.service.StudentInfoService;
import com.zhsnail.finance.service.SysSequenceService;
import com.zhsnail.finance.service.SystemService;
import com.zhsnail.finance.vo.AccountVo;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtil {
    private static SysSequenceService sysSequenceService = SpringUtil.getBean(SysSequenceService.class);
    private static StudentInfoService studentInfoService = SpringUtil.getBean(StudentInfoService.class);
    private static SystemService systemService = SpringUtil.getBean(SystemService.class);
    private static AccountService accountService = SpringUtil.getBean(AccountService.class);

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
            return userMap;
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
        return systemService.findCurrentSysParam();
    }


    /**
     * 查询所有会计科目
     * @return
     */
    public static  List<Account> findAllAccount() {
        return accountService.findAllAccount();
    }

    /**
     * 获取会计科目长名称
     * @param account
     * @return
     */
    public static String getAccountLongName(Account account){
        StringBuilder sb = new StringBuilder();
        List<Account> allAccount = findAllAccount();
        String level = account.getLevel();
        String tempParentId = "";
        if (Integer.parseInt(level)>1){
            for (int i = Integer.parseInt(level);i>0;i--){
                int tempLevel = i;
                if (tempLevel != Integer.parseInt(level)){
                    if (tempLevel == Integer.parseInt(level)-1){
                        List<Account> tempAccount = allAccount.stream().filter(item -> item.getLevel().equals(String.valueOf(tempLevel)) && item.getId().equals(account.getParentId())).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(tempAccount)){
                            sb.append(tempAccount.get(0).getAccountName()+"-");
                            tempParentId = tempAccount.get(0).getParentId();
                        }
                    }else {
                        String finalTempParentId = tempParentId;
                        List<Account> tempAccount = allAccount.stream().filter(item -> item.getLevel().equals(String.valueOf(tempLevel)) && item.getId().equals(finalTempParentId)).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(tempAccount)){
                            sb.append(tempAccount.get(0).getAccountName()+"-");
                            tempParentId = tempAccount.get(0).getParentId();
                        }
                    }

                }
            }
        }
        sb.append(account.getAccountName());
        String[] split = sb.toString().split("-");
        sb.setLength(0);
        if(Integer.parseInt(level)>1){
            for (int i = split.length-2;i>=0;i--){
                sb.append(split[i]+"-");
            }
        }
        sb.append(split[split.length-1]);
        return sb.toString();
    }

    /**
     * 开启分页
     * @param pageEntity 页面参数
     */
    public static void startPage(PageEntity pageEntity){
        PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),true);
    }

    /**
     * 根据会计科目id查询对应得所有父级科目id
     * @param accountId 当前会计科目id
     * @param accountParentIds 结果集
     */
    public static void getParentIds(String accountId,List<String> accountParentIds){
        List<Account> allAccount = findAllAccount();
        //当前会计科目
        List<Account> accountList = allAccount.stream().filter(account -> accountId.equals(account.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(accountList)){
            String parentId = accountList.get(0).getParentId();
            if (parentId != null){
                accountParentIds.add(parentId);
                getParentIds(parentId,accountParentIds);
            }
        }
    }

    /**
     * 初始化凭证实体
     * @return
     */
    public static Voucher initVoucher(String originator){
        Voucher voucher = new Voucher();
        //会计期间
        voucher.setAccountPeriod(getCurrentSysParam().getNowAccountPeriod());
        //id
        voucher.setId(CodeUtil.getId());
        //凭证审核状态
        voucher.setStatus(DICT.STATUS_EXE);
        //制单人
        voucher.setOriginator(originator);
        //过账状态
        voucher.setPostingStatus(DICT.VOUCHER_POST_STATUS_UNPOST);
        //凭证号
        voucher.setCode(CodeUtil.getVoucherCode());
        return voucher;
    }

    /**
     * 根据会计科目id查询会计科目
     * @param id
     * @return
     */
    public static Account findAccountById(String id){
        List<Account> allAccount = findAllAccount();
        List<Account> collect = allAccount.stream().filter(account -> id.equals(account.getId())).collect(Collectors.toList());
        return collect.get(0);
    }

    /**
     * 生成会计科目冻结表
     * @param voucherId 凭证id
     * @param accountIdMap 会计科目id key为借贷方向
     * @param amountMap 金额 key为借贷方向
     * @return
     */
    public static List<AccountTemp> initAccountTemp(String voucherId, Map accountIdMap, Map amountMap){
        List<AccountTemp> list = new ArrayList<>();
        AccountTemp creditAccountTemp = new AccountTemp();
        creditAccountTemp.setId(CodeUtil.getId());
        creditAccountTemp.setVoucherId(voucherId);
        creditAccountTemp.setCreditAmt((BigDecimal) amountMap.get(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT));
        creditAccountTemp.setAccountId((String) accountIdMap.get(DICT.LENDER_ACCOUNT_DIRECTION_CREDIT));
        list.add(creditAccountTemp);
        AccountTemp debitAccountTemp = new AccountTemp();
        debitAccountTemp.setId(CodeUtil.getId());
        debitAccountTemp.setVoucherId(voucherId);
        debitAccountTemp.setCreditAmt((BigDecimal) amountMap.get(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT));
        debitAccountTemp.setAccountId((String) accountIdMap.get(DICT.LENDER_ACCOUNT_DIRECTION_DEBIT));
        list.add(debitAccountTemp);
        return list;
    }
}
