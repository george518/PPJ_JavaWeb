package com.msb_demo1.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb_demo1.common.BaseController;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.service.PpUcAdminService;
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
 * @create 2017-11-23 17:11
 **/
@Controller
@RequestMapping(value="/admin")
public class AdminController extends BaseController {

    @Autowired
    private PpUcAdminService ppUcAdminService;
    //管理员页面
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","管理员管理");
        modelAndView.setViewName("auth/admin");
        return modelAndView;
    }

    //查询列表，页面显示
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

}
