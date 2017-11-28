package com.msb_demo1.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb_demo1.common.BaseController;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcRole;
import com.msb_demo1.service.PpUcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-23 17:13
 **/
@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController {

    @Autowired
    private PpUcRoleService ppUcRoleService;
    /**
     * 角色页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","角色管理");
        modelAndView.setViewName("auth/role");
        return modelAndView;
    }

    /**
     * 角色列表页数据
     * @param page
     * @param limit
     * @param roleName
     * @param request
     * @param response
     */
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public void getList(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer limit,
                        @RequestParam(defaultValue = "") String roleName,
                        HttpServletRequest request, HttpServletResponse response){
        PageHelper.startPage(page,limit);
        Short status = -1;//查出全部
        List<PpUcRole> roles = ppUcRoleService.getRolesForPage(roleName,status);
        PageInfo p = new PageInfo(roles);
        List<PpUcRole> list = p.getList();
        for(PpUcRole item : list)
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
     * 添加角色 页面
     * @return
     */
    @RequestMapping(value = "roleAdd",method = RequestMethod.GET)
    public ModelAndView roleAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","新增角色");
        modelAndView.setViewName("auth/roleAdd");
        return modelAndView;
    }

    /**
     * 修改角色 页面
     * @return
     */
    @RequestMapping(value = "roleEdit",method = RequestMethod.GET)
    public ModelAndView roleEdit(@RequestParam(defaultValue = "0") Integer id,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        PpUcRole role = ppUcRoleService.getOneRoleById(id);
        modelAndView.addObject("role",role);
        modelAndView.addObject("pageTitle","编辑角色");
        modelAndView.setViewName("auth/roleEdit");
        return modelAndView;
    }

    /**
     * 保存角色
     * @param id
     * @param roleName
     * @param detail
     * @param authNodes
     * @param request
     * @param response
     */
    @RequestMapping(value = "saveRole",method = RequestMethod.POST)
    public void saveNode(@RequestParam(defaultValue = "0") Integer id,
                         @RequestParam(defaultValue = "") String roleName,
                         @RequestParam(defaultValue = "") String detail,
                         @RequestParam(defaultValue = "") String authNodes,
                         HttpServletRequest request, HttpServletResponse response){

        long time = System.currentTimeMillis();
        long nowTimeStamp = time/1000;
        Short status = 1;
        PpUcRole ppUcRole = new PpUcRole();

        ppUcRole.setAuthNodes(authNodes);
        ppUcRole.setRoleName(roleName);
        ppUcRole.setDetail(detail);
        ppUcRole.setStatus(status);

        Object obj = request.getSession().getAttribute("admin");
        PpUcAdmin admin = (PpUcAdmin) obj;
        Integer userId = admin.getId();

        Integer resNum;
        if (id==0){
            ppUcRole.setCreateId(userId);
            ppUcRole.setCreateTime(nowTimeStamp);
            ppUcRole.setUpdateId(userId);
            ppUcRole.setUpdateTime(nowTimeStamp);
            resNum = ppUcRoleService.createRole(ppUcRole);

        }else{
            ppUcRole.setUpdateId(userId);
            ppUcRole.setUpdateTime(nowTimeStamp);
            ppUcRole.setId(id);
            resNum = ppUcRoleService.updateRole(ppUcRole);
        }
        if (resNum==0){
            renderErrorString(response,"操作失败");
        }else{
            renderSuccessString(response,null,"操作成功");
        }
    }


    /**
     * 角色删除，逻辑删除
     * @param id
     * @param status
     * @param request
     * @param response
     */
    @RequestMapping(value = "changeRoleStatus",method = RequestMethod.POST)
    public void deleteRole(@RequestParam Integer id,
                           @RequestParam String status,
                           HttpServletRequest request,
                           HttpServletResponse response){
        Short st = 0;
        if (status.equals("enable")){
            st = 1;
        }
        //逻辑删除
        Integer delNum = ppUcRoleService.changeRoleStatus(id,st);
        if (delNum==1){
            renderSuccessString(response,null,"操作成功");
        }else {
            renderErrorString(response, "操作失败");
        }
    }

}
