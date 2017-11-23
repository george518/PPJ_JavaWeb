<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/23
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>

    <div class="nav-title">
        <div class="tl fl">
            ${pageTitle}
        </div>
        <div class="tr fr">
            <button class="layui-btn layui-btn-radius layui-btn-primary layui-btn-xs" onclick="javascript:history.go(-1);"><i class="fa fa-arrow-left" aria-hidden="true"></i></button>
            <button class="layui-btn layui-btn-radius layui-btn-primary layui-btn-xs" onclick="javascript:window.location.reload();"><i class="fa fa-refresh" aria-hidden="true"></i></button>
        </div>
    </div>

    <div class="layui-layout layui-layout-admin" style="padding-left: 10px;margin-top: 20px;">
        系统首页
    </div>

<c:import url="footer.jsp"/>