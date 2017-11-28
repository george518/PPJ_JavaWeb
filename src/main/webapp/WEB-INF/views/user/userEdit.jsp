<%--
  Created by IntelliJ IDEA.
  User: haodaquan
  Date: 17/11/28
  Time: 15:10
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
            <label class="layui-form-label">是否修改</label>
            <div class="layui-input-inline">
                <input type="radio" name="reset_pwd" lay-verify="reset_pwd" value="1" title="是">
                <input type="radio" name="reset_pwd" lay-verify="reset_pwd" value="2" title="否" checked>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item password" style="display:none;">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password_old" id="password_old" lay-verify="" autocomplete="off" placeholder="旧密码" class="layui-input" value="">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item password" style="display:none;">
            <label class="layui-form-label">设置密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password_new1" id="password_new1" lay-verify="" autocomplete="off" placeholder="新密码" class="layui-input" value="">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item password" style="display:none;">
            <label class="layui-form-label">重复密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password_new2" id="password_new2" lay-verify="" autocomplete="off" placeholder="新密码" class="layui-input" value="">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

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

        form.on('radio', function(data){
            if(data.value==1){
                $(".password").show()
            }else{
                $(".password").hide()
            }
        });

        form.on('submit(sub)', function(data){
            $.post('/user/save', data.field, function (out) {
                if (out.status == 200) {
                    layer.alert(out.message+",请重新登录", {icon: 1},function(index){
                        parent.window.location.href="/login/loginOut";
                        layer.close(index);
                    });
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