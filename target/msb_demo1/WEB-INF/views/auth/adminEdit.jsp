<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/26
  Time: 10:14
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
    <form class="layui-form" action="" method="post" >
        <div class="layui-form-item">
            <label class="layui-form-label">登录账号</label>
            <div class="layui-input-inline">
                <input type="text" name="loginName" readonly id="loginName" lay-verify="required" autocomplete="off" placeholder="登录账号" class="layui-input" value="${admin.loginName}">
            </div>
            <div class="layui-form-mid layui-word-aux">*登录不允许修改</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="realName" id="realName" lay-verify="required" autocomplete="off" placeholder="真实姓名" class="layui-input" value="${admin.realName}">
            </div>
            <div class="layui-form-mid layui-word-aux">*</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="phone|required" autocomplete="off" placeholder="手机号码" class="layui-input" value="${admin.phone}">
            </div>
            <div class="layui-form-mid layui-word-aux">*</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">电子邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" id="email" lay-verify="email" autocomplete="off" placeholder="电子邮箱" class="layui-input" value="${admin.email}">
            </div>
            <div class="layui-form-mid layui-word-aux">*</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">重置密码</label>
            <div class="layui-input-inline">
                <input type="radio" name="resetPwd" value="1" title="重置">
                <input type="radio" name="resetPwd" value="2" title="不重置" checked>
            </div>
            <div class="layui-form-mid layui-word-aux">默认密码:george518</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">选择角色</label>
            <div class="layui-input-block">
                <c:forEach items="${roles}" var="role">
                    <input type="checkbox" name="role_ids" lay-filter="role_ids" title="${role.roleName}" value="${role.id}" <c:if test="${role.checked == 1}">checked </c:if>>
                </c:forEach>
            </div>
            <div class="layui-form-mid layui-word-aux">*</div>
        </div>

        <input type="hidden" id="roleIds" name="roleIds" value="${admin.roleIds}}">
        <input type="hidden" id="id" name="id" value="${admin.id}">

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="sub">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    var $;
    layui.config({
        base : "js/"
    }).use(['form','element','layer','jquery'],function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        var $ = layui.jquery;
        var role_ids_str = "${admin.roleIds}"
        var role_ids = role_ids_str.split(",");

        form.on('checkbox(role_ids)', function(data){
            if(data.elem.checked==true){
                role_ids.push(data.value)
            }else{
                $.each(role_ids,function(index,item){
                    // index是索引值（即下标）   item是每次遍历得到的值；
                    if(item==data.value){
                        role_ids.splice(index,1);
                    }
                });
            }
            $("#roleIds").val(role_ids.join(","));
        });

        form.on('submit(sub)', function(data){
            $.post('/admin/saveAdmin', data.field, function (out) {
                if (out.status == 200) {
                    layer.msg(out.message,{icon: 1},function () {
                        window.location.href="/admin/index";
                    })
                } else {
                    layer.msg(out.message)
                }
            }, "json");
            return false;
        });
        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
        form.render();
    });
</script>


<c:import url="/WEB-INF/views/public/footer.jsp"/>
