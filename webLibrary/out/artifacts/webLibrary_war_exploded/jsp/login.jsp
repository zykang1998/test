<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2021/11/22
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="/kk/css/login.css" type="text/css" />
</head>
<body>
<div class="login">
    <h1>Login</h1>
    <form action="/kk/login" method="post" id="loginForm">
        <table align="center">
            <%--<span id="msg" style="font-size: 12px;color: red"></span></br>--%>
            <tr>
                <td>用户名:</td>
                <td>
                    <input type="text" name="username" id="username" placeholder="用户名" required="required">
                </td>
            </tr>
            <tr>
                <td align="center">密&nbsp;&nbsp;&nbsp;码:</td>
                <td>
                    <input type="password" name="password" id="password" placeholder="密  码" required="required">
                </td>
            </tr>
        </table>
        <button type="submit" id="loginBtn">登录</button>
        <button type="reset">重置</button>
    </form>
</div>

<%--<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
    $("#loginBtn").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if (isEmpty(username)){
            $("#msg").html("用户名不能为空！");
            return;
        }
        if (isEmpty(password)){
            $("#msg").html("用户密码不能为空！");
            return;
        }
        $("#loginForm").submit();
    });

    function isEmpty(str) {
        if (str == null || str.trim() == ""){//去除前后空格
            {return true}
        }
        else return false;
    }
</script>--%>
</body>
</html>
