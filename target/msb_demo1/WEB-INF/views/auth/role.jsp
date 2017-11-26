<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/23
  Time: 17:16
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
    <div class="layui-row" style="margin-top: 20px;">
        <div class="layui-col-xs6">
            <form class="layui-form" action="" onsubmit="javascript:return false;">
                <div class="demoTable">

                    <div class="layui-inline" style="width: 40%">
                        <input class="layui-input" name="roleName" id="roleName" autocomplete="off" placeholder="角色名称">
                    </div>

                    <button class="layui-btn" data-type="reload">查询</button>
                </div>
            </form>
        </div>
        <div class="layui-col-xs6">
            <button class="layui-btn layui-btn-primary" id="addRole">新增角色</button>
        </div>
    </div>


    <table class="layui-hide" id="table_list" lay-filter="user">


    </table>

</div>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>
<script>
    layui.use(['table','form','element'], function(){
        var table = layui.table;
        var form = layui.form;
        var element = layui.element;

        //方法级渲染
        table.render({
            elem: '#table_list'
            ,url: '/role/getList'
            ,cols: [[
                // {checkbox: true, fixed: true},
                {field:'id', title: 'ID', align:'center',sort: true, width:150}
                ,{field:'roleName', title: '角色名称'}
                ,{field:'detail', title: '备注'}
                ,{fixed: 'right', width:160, align:'center', toolbar: '#bar'}
            ]]
            ,id: 'listReload'
            ,page: true
            ,height: "full-220"
        });

        var $ = layui.$, active = {
            reload: function(){
                table.reload('listReload', {
                    where: {
                        roleName: $('#roleName').val(),

                    }
                });
            }
        };

        //监听工具条
        table.on('tool(user)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                window.location.href="/role/roleEdit?id="+data.id;
            } else if(obj.event === 'delete'){
                $.ajax({
                    type: 'POST',
                    url: "/role/deleteRole" ,
                    data: {
                        id:data.id
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
            } else{
                layer.msg('操作不存在');
            }
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //新增尺码，当前页新增
        $('#addRole').on('click', function(){
            window.location.href="/role/roleAdd";
        });
        form.render();
    });

</script>

<c:import url="/WEB-INF/views/public/footer.jsp"/>
