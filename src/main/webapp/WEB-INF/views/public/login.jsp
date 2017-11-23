<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/22
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="shortcut icon" href="/static/img/favicon.ico">

    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css?t=1504439386550" media="all">
    <link rel="stylesheet" href="/static/css/login.css?t=1504439386550" media="all">
</head>
<body>
<div class="layui-carousel video_mask bg-img" id="login_carousel" >

    <div class="login layui-anim layui-anim-up">
        <h1>Java 管理后台</h1></p>
        <form class="layui-form" action="" onsubmit="javascript:return false;" method="post">
            <div class="layui-form-item">
                <input type="text" name="loginName" id="loginName" lay-verify="required" placeholder="请输入账号" autocomplete="off"  value="admin" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" name="password" id="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" value="george518" class="layui-input">
            </div>
            <input type="submit" id="submit" value="登录系统" class="layui-btn login_btn" lay-filter="login">
        </form>
    </div>
</div>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/1.8.3/jquery.js"></script>-->
<script type="text/javascript">
    layui.use(['layer','jquery'], function(){
        var layer = layui.layer; //弹层
        var $ = layui.jquery;
        $("#submit").on("click",function(){
            var loginName = $("#loginName").val();
            var password = $("#password").val();
            $.ajax({
                type: 'POST',
                url: "/login/loginIn" ,
                data: {loginName:loginName,password:password},
                dataType: 'json',
                success: function(res){
                    if (res.status==200){
                        layer.msg(res.message);
                        window.location.href = "/home/index";
                    }else{
                        layer.alert(
                            res.message,
                            function (index) {
                                window.location.reload();
                                layer.close(index);
                            }
                        )
                    }

                    return;
                },
            });
        });

    })
</script>
</body>
</html>