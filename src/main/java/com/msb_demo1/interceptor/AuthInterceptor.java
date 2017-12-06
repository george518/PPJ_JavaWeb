package com.msb_demo1.interceptor;

import com.msb_demo1.common.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haodaquan
 * @create 2017-11-28 11:02
 **/
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
    HttpServletResponse response, Object handler) throws Exception{
        log.info("**************PreHandle****");

        //权限验证
        // 不过滤的uri
        String[] notFilter =
                new String[] {"/static/img",
                        "/static/js", "/static/css", "/static/layui","/static/font-awesome","/static/zTree3","/login/index","/login/loginIn"};
        // 请求的uri
        String uri = request.getRequestURI();
        // 是否过滤
        boolean doFilter = true;
        for (String s : notFilter){
            if (uri.indexOf(s) != -1)
            {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }

        if (doFilter)
        {

             //执行过滤
             //从session中获取登录者实体
            Object obj = request.getSession().getAttribute("admin");
            if (null == obj)
            {

                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest)
                {
                    response.setCharacterEncoding("UTF-8");
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                    return false;
                }
                response.sendRedirect("/login/index");
                return false;
            } else {
                //判断是否具有权限
                Object allowUrl = request.getSession().getAttribute("allowUrl");
                List<String> allowUrlList = (ArrayList<String>) allowUrl;

                if(!allowUrlList.contains(uri)){
                    (new BaseController()).renderErrorString(response,"权限不足");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("==============postHandle================");
        if(modelAndView != null){  //加入当前时间
            modelAndView.addObject("var", "测试postHandle");
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("==============afterCompletion================");
    }

    public static boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }


}
