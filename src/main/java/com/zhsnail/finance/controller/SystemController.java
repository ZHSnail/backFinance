package com.zhsnail.finance.controller;

import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Appendix;
import com.zhsnail.finance.common.DICT;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.*;
import com.zhsnail.finance.entity.Account;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.service.FileService;
import com.zhsnail.finance.service.AccountService;
import com.zhsnail.finance.service.StudentInfoService;
import com.zhsnail.finance.service.SystemService;
import com.zhsnail.finance.util.*;
import com.zhsnail.finance.vo.RoleVo;
import com.zhsnail.finance.vo.SystemParamVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;
    @Autowired
    private FileService fileService;
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private AccountService accountService;
    @PostMapping("/login")
    public Result checkUser(@RequestBody User user){
        Map map = new HashMap();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),user.getPsw());
            subject.login(usernamePasswordToken);
            if (null != subject.getSession())
            {
                String sessionId = (String)subject.getSession().getId();
                map.put("token",sessionId);
                //设置session三小时过期
                SecurityUtils.getSubject().getSession().setTimeout(10800000);
                User loginUser = systemService.findUser(user.getUserName());
                if (StringUtils.isNotBlank(loginUser.getStudentId())){
                    //存当前登陆用户
                    StudentInfo studentInfo = studentInfoService.findById(loginUser.getStudentId());
                    Map userInfo = BeanUtil.beanToMap(studentInfo);
                    userInfo.put(DICT.LOGIN,DICT.LOGIN_STUDENT);
                    map.put("userInfo", userInfo);
                    SystemParam currentSysParam = systemService.getCurrentSysParam();
                    map.put("sysParam",currentSysParam);
                    subject.getSession().setAttribute("userInfo",userInfo);
                }
                if (StringUtils.isNotBlank(loginUser.getStaffId())){
                }
            }
        }catch (LockedAccountException e){
            e.printStackTrace();
            Result result = new Result(false, e.getMessage());
            return result;
        }catch (DisabledAccountException e)
        {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
        catch (UnknownAccountException e)
        {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
        catch (IncorrectCredentialsException e)
        {
            e.printStackTrace();
            return new Result(false,"账号/密码不正确");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new Result(false,"账户验证失败");
        }
        return new Result(map);
    }

    @GetMapping("/loginOut")
    public Result loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result();
    }

    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response,@RequestParam String name) throws UnsupportedEncodingException, FileNotFoundException {
        ExcelUtils.export2Web4File(response, name);
    }

    @DeleteMapping("/file/{id}")
    public Result deleteFile(@PathVariable String id){
        fileService.deleteFile(id);
        return new Result(true,"删除文件成功");
    }

    @PostMapping("/easyUpload")
    public Result uploadAccountTemplate(@RequestParam(name="file") MultipartFile multipartFile){
        try {
            Appendix appendix = new Appendix();
            appendix.setName(multipartFile.getOriginalFilename());
            appendix.setSize(multipartFile.getSize());
            appendix.setUploadDate(new Date());
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            appendix.setSuffix(suffix);
            appendix.setMd5(MD5Util.getMD5(multipartFile.getInputStream()));
            //将文件存入gridFs
            String gridfsId = fileService.uploadFileToGridFS(multipartFile.getInputStream() , multipartFile.getContentType());
            appendix.setGridfsId(gridfsId);
            appendix.setContentType(multipartFile.getContentType());
            appendix = fileService.saveFile(appendix);
            return new Result(appendix);
        }catch (Exception e){
            throw new BaseRuningTimeException("你上传的文件"+multipartFile.getOriginalFilename()+"出错啦！");
        }
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response,@RequestParam String id) throws UnsupportedEncodingException, FileNotFoundException {
        Appendix appendix = fileService.queryFileById(id);
        if (appendix == null) {
            throw new BaseRuningTimeException("文件不存在");
        }
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setContentType(appendix.getContentType());
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(appendix.getName(), "UTF-8"));
        try( ServletOutputStream outputStream = response.getOutputStream();) {
            outputStream.write(appendix.getContent());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseRuningTimeException("写出文件异常");
        }
    }
    @GetMapping("/importResult/{fileId}")
    public Result findImportResult(@PathVariable String fileId){
        return new Result(systemService.findImResult(fileId));
    }
    @GetMapping("/roleList")
    public Result findAllRole(@RequestParam String params){
        RoleVo roleVo = new RoleVo();
        if(StringUtils.isNotBlank(params)){
            roleVo = JsonUtil.string2Obj(params,RoleVo.class);
        }
        PageInfo<Role> allRole = systemService.findAllRole(roleVo);
        return new Result(allRole);
    }
    @GetMapping("/userList")
    public Result findAllUser(@RequestParam String pageNum,@RequestParam String pageSize){
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNum(Integer.valueOf(pageNum));
        pageEntity.setPageSize(Integer.valueOf(pageSize));
        PageInfo<User> allUser = systemService.findAllUser(pageEntity);
        return new Result(allUser);
    }

    @DeleteMapping("/role/{id}")
    public Result deleteRole(@PathVariable String id){
        systemService.deleteRole(id);
        return new Result(true,"成功删除角色");
    }
    @PostMapping("/role")
    public Result saveRole(@RequestBody RoleVo roleVo){
        systemService.saveRole(roleVo);
        return new Result(true,"添加角色成功");
    }

    @PutMapping("/role")
    public Result updateRole(@RequestBody RoleVo roleVo){
        systemService.updateRole(roleVo);
        return new Result(true,"修改角色成功");
    }

    @PostMapping("/saveSysParam")
    public Result saveSysParam(@RequestBody SystemParamVo systemParamVo){
        systemService.saveSystemParam(systemParamVo);
        return new Result(true,"暂存系统参数成功！");
    }

    @GetMapping("/sysParam/{id}")
    public Result findSysParamById(@PathVariable String id){
        return new Result(systemService.findSysParamById(id));
    }

    @GetMapping("/checkSysParam")
    public Result checkExistSysParam(){
        SystemParam currentSysParam = CommonUtil.getCurrentSysParam();
        List<Account> accountList = accountService.findAllAccount();
        if (CollectionUtils.isEmpty(accountList)){
            return new Result(false,"请先前往会计科目页面新增会计科目");
        }else if(currentSysParam != null){
            return new Result(false,"系统参数已存在请不要重复初始化系统");
        }else {
            return new Result(true,"开始初始化系统");
        }
    }

    @PostMapping("/commitSysParam")
    public Result commitSysParam(@RequestBody SystemParamVo systemParamVo){
        systemService.saveSystemParam(systemParamVo);
        return new Result(true,"提交系统参数成功！");
    }
}
