package com.msb_demo1.controller;

import com.msb_demo1.common.BaseController;
import com.msb_demo1.common.TreeResult;
import com.msb_demo1.entity.PpUcAdmin;
import com.msb_demo1.entity.PpUcAuth;
import com.msb_demo1.service.PpUcAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-23 17:11
 **/
@Controller
@RequestMapping(value="/auth")
public class AuthController extends BaseController {

    @Autowired
    private PpUcAuthService ppUcAuthService;

    /**
     * 管理页面
     * @param response
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","权限管理");
        modelAndView.setViewName("auth/auth");
        return modelAndView;
    }

    /**
     * 查询列表，目录树显示
     * @param response
     */
    @RequestMapping(value = "getNodes",method = RequestMethod.POST)
    public void getNodes(HttpServletResponse response){
        List<PpUcAuth> list = ppUcAuthService.getAllAuth();
        List<TreeResult> listTrees = new ArrayList<TreeResult>();
        long count = list.size();
        for (PpUcAuth ppUcAuth : list){
            TreeResult listTree = new TreeResult();
            listTree.setId(ppUcAuth.getId());
            listTree.setName(ppUcAuth.getAuthName());
            listTree.setOpen(true);
            listTree.setpId(ppUcAuth.getPid());
            listTrees.add(listTree);
        }
        renderPageSuccessString(response,listTrees,count);
    }

    /**
     * 查询单个节点
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "getNode",method = RequestMethod.POST)
    public void getNode(@RequestParam(defaultValue = "0") Integer id, HttpServletRequest request, HttpServletResponse response){
        PpUcAuth ppUcAuth = ppUcAuthService.getOneAuthById(id);
        renderSuccessString(response,ppUcAuth,"1");
    }

//    authName=dsd&pid=17&authUrl=%2Fdd&icon=ddd&sort=1&isShow=0&id=0
    @RequestMapping(value = "saveNode",method = RequestMethod.POST)
    public void saveNode(@RequestParam(defaultValue = "0") Integer id,
                         @RequestParam(defaultValue = "0") Short isShow,
                         @RequestParam(defaultValue = "") String authUrl,
                         @RequestParam(defaultValue = "") Integer pid,
                         @RequestParam(defaultValue = "") String authName,
                         @RequestParam(defaultValue = "") String icon,
                         @RequestParam(defaultValue = "999") Integer sort,
                         HttpServletRequest request, HttpServletResponse response){
        long time = System.currentTimeMillis();
        long nowTimeStamp = time/1000;

        Short status = 1;

        PpUcAuth ppUcAuth = new PpUcAuth();
        ppUcAuth.setAuthName(authName);
        ppUcAuth.setAuthUrl(authUrl);
        ppUcAuth.setPid(pid);
        ppUcAuth.setSort(sort);
        ppUcAuth.setIsShow(isShow);
        ppUcAuth.setIcon(icon);
        ppUcAuth.setStatus(status);

        Object obj = request.getSession().getAttribute("admin");
        PpUcAdmin admin = (PpUcAdmin) obj;
        Integer userId = admin.getId();

        Integer resNum;
        if (id == 0){
            //新增
            ppUcAuth.setCreateId(userId);
            ppUcAuth.setCreateTime(nowTimeStamp);
            ppUcAuth.setUpdateId(userId);
            resNum = ppUcAuthService.createAuth(ppUcAuth);
        }else{
            //修改
            ppUcAuth.setUpdateTime(nowTimeStamp);
            ppUcAuth.setUpdateId(userId);
            ppUcAuth.setId(id);
            resNum = ppUcAuthService.updateAuth(ppUcAuth);
        }
        if (resNum==0){
            renderErrorString(response,"操作失败");
        }else{
            renderSuccessString(response,ppUcAuth,"操作成功");
        }

    }

    //删除
    @RequestMapping(value = "deleteNode",method = RequestMethod.POST)
    public void deleteNode(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response){

        //如果存在子节点，不允许删除
        List<PpUcAuth> auths = ppUcAuthService.getChildAuth(id);
        if (auths.size()>0){
            renderErrorString(response, "含有子节点，不允许删除");
            return;
        }
        //逻辑删除
        Integer delNum = ppUcAuthService.deleteAuth(id);
        if (delNum==1){
            renderSuccessString(response,null,"删除成功");
        }else {
            renderErrorString(response, "删除失败");
        }
    }
}
