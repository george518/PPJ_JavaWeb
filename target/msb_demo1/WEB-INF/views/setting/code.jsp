<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/28
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/public/header.jsp"/>
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
    <div class="layui-row">
        <div style="margin: 10px 20px">
            <blockquote class="layui-elem-quote">
                测试状态码权限
            </blockquote>
        </div>

        <fieldset class="layui-elem-field" style="margin-top: 30px;">
            <legend>增删改</legend>
            <div class="layui-field-box">
                <button lay-submit class="layui-btn" lay-filter="add">新增操作</button>
                <button lay-submit class="layui-btn layui-btn-normal" lay-filter="edit">编辑操作</button>
                <button lay-submit class="layui-btn layui-btn-warm" lay-filter="save">保存操作</button>
                <button lay-submit class="layui-btn layui-btn-danger" lay-filter="delete">删除操作</button>
            </div>
        </fieldset>
    </div>
</div>

<script>
    var $,form;
    layui.config({
        base : "js/"
    }).use(['form','element','layer','jquery'],function(){
        form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        $ = layui.jquery;
        form.on("submit",function(data) {
            var sub_type = data.elem.getAttribute("lay-filter");

            $.ajax({
                type: 'GET',
                url: "/code/"+sub_type ,
                data: {
                    id:1
                } ,
                dataType: 'json',
                success: function(res){
                    layer.alert(
                        res.message,
                        function (index) {
                            window.location.reload();
                            layer.close(index);
                        }
                    )
                    return;
                },
            });
        });

    });
</script>


<c:import url="/WEB-INF/views/public/footer.jsp"/>
