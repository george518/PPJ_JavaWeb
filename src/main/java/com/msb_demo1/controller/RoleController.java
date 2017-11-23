package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haodaquan
 * @create 2017-11-23 17:13
 **/
@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController {

    //登录页面
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","角色管理");
        modelAndView.setViewName("auth/role");
        return modelAndView;
    }
}
