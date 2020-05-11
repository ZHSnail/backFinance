package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Appendix;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.listener.AccountImportListener;
import com.zhsnail.finance.service.AccountBalanceService;
import com.zhsnail.finance.service.AccountDetailService;
import com.zhsnail.finance.service.FileService;
import com.zhsnail.finance.service.AccountService;
import com.zhsnail.finance.util.CommonUtil;
import com.zhsnail.finance.util.ExcelUtils;
import com.zhsnail.finance.util.JsonUtil;
import com.zhsnail.finance.util.TaskUtil;
import com.zhsnail.finance.vo.AccountBalanceVo;
import com.zhsnail.finance.vo.AccountDetailVo;
import com.zhsnail.finance.vo.AccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lender")
public class LenderController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AccountBalanceService accountBalanceService;
    @Autowired
    private AccountDetailService accountDetailService;
    @PostMapping("/account")
    public Result saveAccount(@RequestBody AccountVo accountVo){
        accountService.saveAccount(accountVo);
        return new Result(true,"保存会计科目成功");
    }

    @DeleteMapping("/account/{id}")
    public Result deleteAccount(@PathVariable("id") String id){
        return accountService.deleteAccount(id);
    }

    @GetMapping("/accountCondition")
    public Result findAllByCondition(@RequestParam String params){
        AccountVo accountVo = new AccountVo();
        if (StringUtils.isNotBlank(params)){
            accountVo = JsonUtil.string2Obj(params, AccountVo.class);
        }
        List<Map> arrangeAccount = accountService.findArrangeAccount(accountVo);
        return new Result(arrangeAccount);
    }

    @GetMapping("/allAccount")
    public Result findAllAccount(@RequestParam String params){
        AccountVo accountVo = new AccountVo();
        if (StringUtils.isNotBlank(params)){
            accountVo = JsonUtil.string2Obj(params, AccountVo.class);
        }
        PageInfo<Account> accountPageInfo = accountService.findDetailAccount(accountVo);
        return new Result(accountPageInfo);
    }

    @GetMapping("/lastAccounts/{level}")
    public Result findUpAccount(@PathVariable("level") String level){
        List<Account> accounts = accountService.findUpAccount(level);
        return new Result(accounts);
    }
    @GetMapping("/accountExport")
    public void  exportAccount(HttpServletResponse response,@RequestParam String data){
        AccountVo accountVo = new AccountVo();
        if (StringUtils.isNotBlank(data)){
            accountVo = JsonUtil.string2Obj(data, AccountVo.class);
        }
        List<AccountVo> accountVos = accountService.exportAccount(accountVo);
        ExcelUtils.export2Web(response,"会计科目表"+new Date().getTime(),"会计科目表",AccountVo.class,accountVos);
    }
    @PostMapping("/importAccount/{id}")
    public void importAccount(@PathVariable String id){
        Appendix appendix = fileService.queryFileById(id);
        if (!".xlsx".equals(appendix.getSuffix())){
            throw new BaseRuningTimeException("当前上传的文件格式不正确，请重新上传");
        }else {
            TaskUtil.importData(id,2,new AccountImportListener(id));
        }
    }

    @GetMapping("/accBalanceCondition")
    public Result findAccBlanByCondition(@RequestParam String params){
        AccountBalanceVo accountBalanceVo = new AccountBalanceVo();
        if (StringUtils.isNotBlank(params)){
            accountBalanceVo = JsonUtil.string2Obj(params, AccountBalanceVo.class);
        }
        PageInfo<AccountBalanceVo> accountBalanceVos = accountBalanceService.findByCondition(accountBalanceVo);
        return new Result(accountBalanceVos);
    }

    @GetMapping("/accBalanceExport")
    public void  exportAccBalance(HttpServletResponse response,@RequestParam String data){
        AccountBalanceVo accountBalanceVo = new AccountBalanceVo();
        if (StringUtils.isNotBlank(data)){
            accountBalanceVo = JsonUtil.string2Obj(data, AccountBalanceVo.class);
        }
        List<AccountBalanceVo> list = accountBalanceService.exportByCondition(accountBalanceVo);
        ExcelUtils.export2Web(response,"会计科目余额表"+new Date().getTime(),"会计科目余额表",AccountBalanceVo.class,list);
    }

    @GetMapping("/accBalanceInitData")
    public Result findInitData(@RequestParam String params){
        AccountBalanceVo accountBalanceVo = new AccountBalanceVo();
        if (StringUtils.isNotBlank(params)){
            accountBalanceVo = JsonUtil.string2Obj(params, AccountBalanceVo.class);
        }
        PageInfo<AccountBalanceVo> accountBalanceVos = accountBalanceService.findInitData(accountBalanceVo);
        return new Result(accountBalanceVos);
    }

    @GetMapping("/accDetailCondition")
    public Result findAccDetailByCondition(@RequestParam String params){
        AccountDetailVo accountDetailVo = new AccountDetailVo();
        if (StringUtils.isNotBlank(params)){
            accountDetailVo = JsonUtil.string2Obj(params, AccountDetailVo.class);
        }
        PageInfo<AccountDetailVo> accountDetailVos = accountDetailService.findByCondition(accountDetailVo);
        return new Result(accountDetailVos);
    }

    @GetMapping("/accDetailExport")
    public void  exportAccDetail(HttpServletResponse response,@RequestParam String data){
        AccountDetailVo accountDetailVo = new AccountDetailVo();
        if (StringUtils.isNotBlank(data)){
            accountDetailVo = JsonUtil.string2Obj(data, AccountDetailVo.class);
        }
        List<AccountDetailVo> list = accountDetailService.exportByCondition(accountDetailVo);
        ExcelUtils.export2Web(response,"会计科目明细表"+new Date().getTime(),"会计科目明细表",AccountDetailVo.class,list);
    }

}
