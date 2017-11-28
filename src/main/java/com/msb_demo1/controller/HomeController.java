package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import com.msb_demo1.common.R;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcAuth;
import com.msb_demo1.entity.PpUcRole;
import com.msb_demo1.service.PpUcAuthService;
import com.msb_demo1.service.PpUcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author haodaquan
 * @create 2017-11-23 14:52
 **/
@Controller
@RequestMapping(value="/home")
public class HomeController extends BaseController {

    @Autowired
    private PpUcRoleService ppUcRoleService;
    @Autowired
    private PpUcAuthService ppUcAuthService;
    /**
     * 登录后台首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Object obj = request.getSession().getAttribute("admin");
        //转为实体
        PpUcAdmin admins = (PpUcAdmin) obj;
        modelAndView.addObject("realName",admins.getRealName());

        //左侧菜单
        List<PpUcAuth> menus = getAdminMenus(admins);
        modelAndView.addObject("menus",menus);
        modelAndView.setViewName("public/home");
        return modelAndView;
    }

    /**
     * 后台开始页
     * @return
     */
    @RequestMapping(value = "start", method = RequestMethod.GET)
    public ModelAndView start() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","系统首页");
        modelAndView.setViewName("public/start");
        return modelAndView;
    }


}
