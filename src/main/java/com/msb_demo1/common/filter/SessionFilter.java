package com.msb_demo1.common.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.msb_demo1.common.BaseController;

/**
 * Created by cc on 2017/11/17.
 */
public class SessionFilter extends OncePerRequestFilter{

    @Autowired
    private BaseController baseController;

    /** 登录验证过滤器 */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 不过滤的uri
        String[] notFilter =
                new String[] {"/static/img",
                        "/static/js", "/static/css", "/static/layui","/static/font-awesome","/static/zTree3","/login/index","/login/loginIn"};
        // 请求的uri
        String uri = request.getRequestURI();
        // 是否过滤
        boolean doFilter = true;
        for (String s : notFilter)
        {
            if (uri.indexOf(s) != -1)
            {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }

        if (doFilter)
        {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("admin");
            if (null == obj)
            {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest)
                {
                    response.setCharacterEncoding("UTF-8");
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                    return ;
                }
                response.sendRedirect("/login/index");
                return;
            }
            else
            {
//                //判断是否具有权限
//                Object objs = request.getSession().getAttribute("admin");
//                PpUcAdmin admin = (PpUcAdmin)obj;
//
//                List<String> allowUrl = baseController.getAdminAuthUrl(admin);
//                allowUrl.add("/home/index");
//                allowUrl.add("/home/start");
//                if(!allowUrl.contains(uri)){
//                    (new BaseController()).renderErrorString(response,"权限不足");
//                    return;
//                }

                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        }
        else
        {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }
}
