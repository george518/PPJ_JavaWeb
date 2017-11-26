<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/25
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/public/header.jsp"/>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/zTree3/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/static/zTree3/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/static/zTree3/js/jquery.ztree.exedit.js"></script>

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
                修改角色以及角色权限
            </blockquote>
        </div>

        <div class="layui-col-md5 zTreeDemoBackground" style="margin-left: 20px;margin-right: 0px">
            <ul id="treeDemo" class="ztree" style="height:320px; width: auto; margin-bottom: 10px;">
            </ul>
        </div>
        <div class="layui-col-md7">
            <div class="layui-layout layui-layout-admin" style="padding-left: 40px;margin-top: 20px;">
                <form class="layui-form" action="" onsubmit="javascript:;" name="form" method="post">

                    <div class="layui-form-item">
                        <label class="layui-form-label">角色名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="roleName" value="${role.roleName}" id="roleName" lay-verify="required" autocomplete="off" placeholder="请输入角色名称" class="layui-input" >
                        </div>
                        <div class="layui-form-mid layui-word-aux"></div>
                    </div>

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="detail" id="detail" placeholder="请输入内容" class="layui-textarea">${role.detail}</textarea>
                        </div>
                    </div>

                    <input type="hidden" value="${role.id}" name="id" id="id">

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var $,form;
    layui.config({
        base : "js/"
    }).use(['form','element','layer','jquery'],function(){
        form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        $ = layui.jquery;
        var layer = layui.layer
        $("form").submit(function () {
            var role_name = $("#roleName").val();
            if(!role_name){
                layer.msg('请填写角色名称');
                return false;
            }
            var id = $("#id").val()
            var detail = $("#detail").val();
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var nodes_data = nodes_select_data(nodes,{});
            var data = {'id':id,'roleName':role_name,'detail':detail,'authNodes':nodes_data}
            console.log(data)
            $.post('/role/saveRole', data, function (out) {
                if (out.status == 200) {
                    layer.alert(out.message, {icon: 1},function(index){
                        window.location.href="/role/index";
                        layer.close(index);
                    });
                } else {
                    layer.msg(out.message)
                }
            }, "json");
            return false;
        });

        form.render();
        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();

    });
</script>

<script type="text/javascript">
    var zNodes = [{ id:1, pId:0, name:"数据错误"}];
    var checkedStr = "${role.authNodes}";
    console.log(checkedStr);
    checkedStr =(checkedStr.substring(checkedStr.length-1)==',')?checkedStr.substring(0,checkedStr.length-1):checkedStr;
    var checked = checkedStr.split(",");
    console.log(checked)
    // $(document).ready(function(){
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    //加载树
    refresh_tree();

    function refresh_tree()
    {
        var time   = Date.parse(new Date());
        $.ajax({
            type: "POST",
            url: "/auth/getNodes",
            data: {time:time},
            dataType: 'json',
            success: function(data) {
                if(data.code==0){
                    zNodes = init_checked(data.data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }
        });
        setCheck();
        $("#py").bind("change", setCheck);
        $("#sy").bind("change", setCheck);
        $("#pn").bind("change", setCheck);
        $("#sn").bind("change", setCheck);
    }

    function init_checked(zNodes) {
        $.each(zNodes,function(k,v){
            if($.inArray(v.id+"",checked)>=0){
                v.checked = true;
            }

        });
        return  zNodes;
    }

    function nodes_select_data(obj,nodes)
    {
        var nodes_str = '';
        $.each(obj,function(k,v){
            if(!v['name']) return nodes;
            node_id = v["id"]
            var i = 0;
            for (var j in nodes) { i++}
            nodes_str += v['id']+","
        });

        return nodes_str;
    }

    function setCheck() {
        type = { "Y" : "ps", "N" : "ps" };
        setting.check.chkboxType = type;
    }

    function showCode(str) {
        var code = $("#code");
        code.empty();
        for (var i=0, l=str.length; i<l; i++) {
            code.append("<li>"+str[i]+"</li>");
        }
    }

    function formSubmit() {


    }

    // });
</script>


<c:import url="/WEB-INF/views/public/footer.jsp"/>

