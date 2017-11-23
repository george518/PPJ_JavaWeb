package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import com.msb_demo1.entity.PpUcAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author haodaquan
 * @create 2017-11-23 14:52
 **/
@Controller
@RequestMapping(value="/home")
public class HomeController extends BaseController {

    //登录页面
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Object obj = request.getSession().getAttribute("admin");
        //转为实体
        PpUcAdmin admins = (PpUcAdmin) obj;
        modelAndView.addObject("realName",admins.getRealName());
        modelAndView.setViewName("public/home");
        return modelAndView;
    }

    @RequestMapping(value = "start", method = RequestMethod.GET)
    public ModelAndView start() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","系统首页");
        modelAndView.setViewName("public/start");
        return modelAndView;
    }

}
