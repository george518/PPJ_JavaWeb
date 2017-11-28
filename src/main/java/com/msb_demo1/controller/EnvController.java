package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haodaquan
 * @create 2017-11-28 14:45
 **/
@Controller
@RequestMapping(value="/env")
public class EnvController extends BaseController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","环境管理");
        modelAndView.setViewName("setting/env");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public void add(HttpServletRequest request, HttpServletResponse response) {
        renderSuccessString(response,null,"新增页面有权限");
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public void edit(@RequestParam(defaultValue = "0") Integer id,
                     HttpServletRequest request, HttpServletResponse response) {
        renderSuccessString(response,null,"编辑页面有权限");
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public void save(@RequestParam(defaultValue = "0") Integer id,
                     HttpServletRequest request, HttpServletResponse response){
        renderSuccessString(response,null,"保存操作有权限");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public void delete(@RequestParam(defaultValue = "0") Integer id,
                       HttpServletRequest request, HttpServletResponse response){
        renderSuccessString(response,null,"删除有权限");
    }
}