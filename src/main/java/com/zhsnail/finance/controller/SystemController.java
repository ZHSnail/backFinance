package com.zhsnail.finance.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageInfo;
import com.zhsnail.finance.common.Appendix;
import com.zhsnail.finance.common.Result;
import com.zhsnail.finance.entity.PageEntity;
import com.zhsnail.finance.entity.Role;
import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import com.zhsnail.finance.service.FileService;
import com.zhsnail.finance.service.SystemService;
import com.zhsnail.finance.util.ExcelUtils;
import com.zhsnail.finance.util.MD5Util;
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
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;
    @Autowired
    private FileService fileService;
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
            }
        }catch (LockedAccountException e){
            Result result = new Result(false, e.getMessage());
            return result;
        }catch (DisabledAccountException e)
        {
            return new Result(false, e.getMessage());
        }
        catch (UnknownAccountException e)
        {
            return new Result(false, e.getMessage());
        }
        catch (IncorrectCredentialsException e)
        {
            return new Result(false,"账号/密码不正确");
        }
        catch (Exception e)
        {
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
    public Result findAllRole(@RequestParam String pageNum,@RequestParam String pageSize){
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNum(Integer.valueOf(pageNum));
        pageEntity.setPageSize(Integer.valueOf(pageSize));
        PageInfo<Role> allRole = systemService.findAllRole(pageEntity);
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
}
