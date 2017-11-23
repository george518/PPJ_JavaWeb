package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.service.PpUcAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 登录相关
 * @author haodaquan
 * @create 2017-11-22 14:35
 **/

@Controller
@SessionAttributes
@RequestMapping(value="/login")
public class LoginController extends BaseController{

    @Autowired
    private PpUcAdminService ppUcAdminService;
    //登录页面
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){

        //如果已经登录转到后台首页
        Object obj = request.getSession().getAttribute("admin");

        if (obj==null){
            PpUcAdmin admins = (PpUcAdmin) obj;
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        return new ModelAndView("redirect:/home/index");

    }

    //登录
    @RequestMapping(value = "loginIn",method = RequestMethod.POST)
    public void loginIn(@RequestParam String loginName, String password, HttpServletRequest request, HttpServletResponse response){

        if ("".equals(loginName == null ? "" : loginName) || "".equals(password == null ? "" : password)){
            renderErrorString(response,"用户名或密码不能为空");
            return;
        }
        PpUcAdmin admin = ppUcAdminService.getAdminInfoByLoginName(loginName);

        if (admin.getPassword()!=null){
            try {
                boolean checkResult = CheckPassword(password+admin.getSalt(),admin.getPassword());
                if(checkResult){
                    //session 处理
                    request.getSession().setAttribute("admin",admin);

                    renderSuccessString(response,admin.getPassword(),"登录成功");
                }else{
                    renderErrorString(response,"用户名或密码错误");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            renderErrorString(response,"用户名或密码错误");
        }
    }

    //退出登录
    @RequestMapping(value = "loginOut",method = RequestMethod.GET)
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("admin",null);
        return new ModelAndView("redirect:/login/index");
    }
}
