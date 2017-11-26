<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/22
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>JavaDemo</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/app.css?t=154">
    <link rel="stylesheet" href="/static/font-awesome/css/font-awesome.min.css" media="all">
</head>

<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">JavaDomo</div>
        <div class="layui-logo kit-logo-mobile">P</div>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/static/img/userface.jpg" class="layui-nav-img"> ${realName}
                </a>
            </li>
            <li class="layui-nav-item"><a href="/login/loginOut"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">
                        <i class="fa fa-id-card"></i> &nbsp;&nbsp;<span>权限管理</span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="/admin/index" data-icon="&#xe715;" data-title="管理员管理" kit-target data-id="1"><i class="fa fa-user-o"></i>  &nbsp;&nbsp;<span style="cursor: pointer">管理员管理</span></a></dd>
                        <dd><a data-url="/role/index" data-icon="&#xe715;" data-title="角色管理" kit-target data-id="2"><i class="fa fa-user-circle-o"></i>  &nbsp;&nbsp;<span style="cursor: pointer">角色管理</span></a></dd>
                        <dd><a data-url="/auth/index" data-icon="&#xe715;" data-title="权限因子" kit-target data-id="3"><i class="fa fa-id-card"></i>  &nbsp;&nbsp;<span style="cursor: pointer">权限因子</span></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">主体内容加载中,请稍等...</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2017 &copy; JavaDemo  Power by SpringMvc
    </div>
</div>

<script src="/static/layui/layui.js?s=36"></script>
<script>
    var message;
    layui.config({
        base: '/static/js/'
    }).use(['app', 'message'], function() {
        var app = layui.app,
            $ = layui.jquery,
            layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        //主入口
        app.set({
            type: 'iframe'
        }).init();
    });
</script>
</body>

</html>