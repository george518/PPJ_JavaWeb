package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcRole;
import com.msb_demo1.service.PpUcAdminService;
import com.msb_demo1.service.PpUcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-28 15:04
 **/
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

    @Autowired
    private PpUcAdminService ppUcAdminService;

    @Autowired
    private PpUcRoleService ppUcRoleService;

    /**
     * 编辑管理员 页面
     * @return
     */
    @RequestMapping(value = "userEdit",method = RequestMethod.GET)
    public ModelAndView adminEdit(
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        Object obj = request.getSession().getAttribute("admin");
        //转为实体
        PpUcAdmin user = (PpUcAdmin) obj;



        ModelAndView modelAndView = new ModelAndView();
        PpUcAdmin admin = ppUcAdminService.getOneAdminById(user.getId());
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
        modelAndView.addObject("pageTitle","资料修改");
        modelAndView.setViewName("user/userEdit");
        return modelAndView;
    }


    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void saveNode(@RequestParam(defaultValue = "0") Integer id,
                         @RequestParam(defaultValue = "") String loginName,
                         @RequestParam(defaultValue = "") String realName,
                         @RequestParam(defaultValue = "") String phone,
                         @RequestParam(defaultValue = "") String email,
                         @RequestParam(defaultValue = "2") Integer reset_pwd,
                         @RequestParam(defaultValue = "") String password_old,
                         @RequestParam(defaultValue = "") String password_new1,
                         @RequestParam(defaultValue = "") String password_new2,
                         HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //登录信息
        Object obj = request.getSession().getAttribute("admin");
        PpUcAdmin admin = (PpUcAdmin) obj;
        Integer userId = admin.getId();
        if (userId!=id){
            renderErrorString(response,"登录信息不符");
            return;
        }
        PpUcAdmin user = ppUcAdminService.getOneAdminById(id);


        long time = System.currentTimeMillis();
        long nowTimeStamp = time/1000;

        user.setLoginName(loginName);
        user.setRealName(realName);
        user.setPhone(phone);
        user.setEmail(email);

        //设置密码
        if (reset_pwd==1){
            //验证旧密码
            boolean checkResult = CheckPassword(password_old+user.getSalt(),user.getPassword());
            if(!checkResult){
                renderErrorString(response,"旧密码不符合");
                return;
            }

            //验证密码长度 6位
            if (password_new1.length()<6){
                renderErrorString(response,"密码六位以上");
                return;
            }

            //验证新密码是否相等
            if (!password_new1.equals(password_new2)){
                renderErrorString(response,"两次密码不一致");
                return;
            }

            String salt = getRandomString(4);
            String password = "";
            try {
                password = EncoderByMd5(password_new1+salt);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            user.setSalt(salt);
            user.setPassword(password);
        }

        user.setUpdateId(id);
        user.setUpdateTime(nowTimeStamp);
        user.setId(id);
        Integer resNum = ppUcAdminService.updateAdmin(user);

        if (resNum==0){
            renderErrorString(response,"操作失败");
        }else{
            renderSuccessString(response,null,"操作成功");
        }
    }
}
