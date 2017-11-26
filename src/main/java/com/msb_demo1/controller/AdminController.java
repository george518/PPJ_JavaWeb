package com.msb_demo1.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb_demo1.common.BaseController;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcAuth;
import com.msb_demo1.entity.PpUcRole;
import com.msb_demo1.service.PpUcAdminService;
import com.msb_demo1.service.PpUcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author haodaquan
 * @create 2017-11-23 17:11
 **/
@Controller
@RequestMapping(value="/admin")
public class AdminController extends BaseController {

    @Autowired
    private PpUcAdminService ppUcAdminService;

    @Autowired
    private PpUcRoleService ppUcRoleService;

    /**
     * 列表页页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","管理员管理");
        modelAndView.setViewName("auth/admin");
        return modelAndView;
    }

    /**
     * 列表页数据
     * @param page
     * @param limit
     * @param realName
     * @param loginName
     * @param request
     * @param response
     */
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public void getList(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer limit,
                        @RequestParam(defaultValue = "") String realName,
                        @RequestParam(defaultValue = "") String loginName,
                        HttpServletRequest request, HttpServletResponse response){
        PageHelper.startPage(page,limit);
        List<PpUcAdmin> admins = ppUcAdminService.getAdminsForPage(loginName,realName);
        PageInfo p = new PageInfo(admins);
        List<PpUcAdmin> list = p.getList();

        for(PpUcAdmin item : list)
        {
            if(item.getStatus()==1){
                item.setStatusText("<font>正常</font>");
            }else{
                item.setStatusText("<font color='red'>禁用</font>");
            }
        }
        renderPageSuccessString(response,list,p.getTotal());
    }

    /**
     * 添加管理员 页面
     * @return
     */
    @RequestMapping(value = "adminAdd",method = RequestMethod.GET)
    public ModelAndView adminAdd(){
        ModelAndView modelAndView = new ModelAndView();
        //获取所有可用角色
        Short status=1;
        List<PpUcRole> roles = ppUcRoleService.getRolesForPage("",status);
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("pageTitle","新增管理员");
        modelAndView.setViewName("auth/adminAdd");
        return modelAndView;
    }

    /**
     * 编辑管理员 页面
     * @return
     */
    @RequestMapping(value = "adminEdit",method = RequestMethod.GET)
    public ModelAndView adminEdit(@RequestParam(defaultValue = "0") Integer id,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        PpUcAdmin admin = ppUcAdminService.getOneAdminById(id);
        modelAndView.addObject("admin",admin);

        List<String> roleIds = new ArrayList();
        if(!"".equals(admin.getRoleIds() == null ? "" : admin.getRoleIds())){
            roleIds = java.util.Arrays.asList(admin.getRoleIds().split(","));
        }
        Short status = 1;
        List<PpUcRole> roles = ppUcRoleService.getRolesForPage("",status);
        Short checked = 1;
        for(PpUcRole role : roles){
            if(roleIds.contains(role.getId().toString())) {
                role.setChecked(checked);
            }
        }
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("pageTitle","编辑角色");
        modelAndView.setViewName("auth/adminEdit");
        return modelAndView;
    }


    @RequestMapping(value = "saveAdmin",method = RequestMethod.POST)
    public void saveNode(@RequestParam(defaultValue = "0") Integer id,
                         @RequestParam(defaultValue = "") String loginName,
                         @RequestParam(defaultValue = "") String realName,
                         @RequestParam(defaultValue = "") String phone,
                         @RequestParam(defaultValue = "") String email,
                         @RequestParam(defaultValue = "") String roleIds,
                         @RequestParam(defaultValue = "2") Integer resetPwd,
                         HttpServletRequest request, HttpServletResponse response){

        long time = System.currentTimeMillis();
        long nowTimeStamp = time/1000;
        Short status = 1;
        PpUcAdmin ppUcAdmin = new PpUcAdmin();

        ppUcAdmin.setStatus(status);
        ppUcAdmin.setLoginName(loginName);
        ppUcAdmin.setRealName(realName);
        ppUcAdmin.setPhone(phone);
        ppUcAdmin.setEmail(email);
        ppUcAdmin.setRoleIds(roleIds);

        //登录信息
        Object obj = request.getSession().getAttribute("admin");
        PpUcAdmin admin = (PpUcAdmin) obj;
        Integer userId = admin.getId();

        //初始化密码
        String salt = getRandomString(4);
        String password = "";
        try {
            password = EncoderByMd5("geroge518"+salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Integer resNum;
        if (id==0){
            //loginName重复性检测
            PpUcAdmin isExists = ppUcAdminService.getAdminInfoByLoginName(loginName);
            if (isExists.getLoginName()!=null){
                renderErrorString(response,"登录账号已经存在，请更换");
                return;
            }
            ppUcAdmin.setCreateId(userId);
            ppUcAdmin.setCreateTime(nowTimeStamp);
            ppUcAdmin.setUpdateId(userId);
            ppUcAdmin.setUpdateTime(nowTimeStamp);
            ppUcAdmin.setLastIp("0");
            ppUcAdmin.setLastLogin(Long.valueOf(0));
            ppUcAdmin.setSalt(salt);
            ppUcAdmin.setPassword(password);
            resNum = ppUcAdminService.createAdmin(ppUcAdmin);
        }else{
            //重置密码
            if (resetPwd==1){
                ppUcAdmin.setSalt(salt);
                ppUcAdmin.setPassword(password);
            }
            ppUcAdmin.setUpdateId(userId);
            ppUcAdmin.setUpdateTime(nowTimeStamp);
            ppUcAdmin.setId(id);
            resNum = ppUcAdminService.updateAdmin(ppUcAdmin);
        }
        if (resNum==0){
            renderErrorString(response,"操作失败");
        }else{
            renderSuccessString(response,null,"操作成功");
        }
    }

    /**
     * 逻辑删除
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "changeAdminStatus",method = RequestMethod.POST)
    public void changeAdminStatus(@RequestParam Integer id,
                            @RequestParam String status,
                            HttpServletRequest request,
                            HttpServletResponse response){

        if (id==1){
            renderErrorString(response, "超级管理员账户不允许操作");
            return;
        }

        Short st = 0;
        if (status.equals("enable")){
            st = 1;
        }

        //逻辑删除
        Integer delNum = ppUcAdminService.changeAdminStatus(id,st);
        if (delNum==1){
            renderSuccessString(response,null,"操作成功");
        }else {
            renderErrorString(response, "操作失败");
        }
    }

}
