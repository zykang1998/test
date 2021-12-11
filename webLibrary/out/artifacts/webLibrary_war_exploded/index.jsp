<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2021/11/20
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>图书管理系统</title>
  </head>
  <body>
    <h1 align="center">欢迎来到图书管理系统，请选择你要进行的操作：</h1>
    <table align="center">
      <tr>
        <td>
          <button type="button" id="loginBtn" onclick="window.location.href = '/kk/jsp/login.jsp'">登录</button>
        </td>
        <td>
          <button type="submit" id="registerBtn" >注册</button>
        </td>
      </tr>
    </table>

  </body>
</html>
