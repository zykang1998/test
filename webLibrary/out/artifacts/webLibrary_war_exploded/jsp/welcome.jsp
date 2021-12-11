<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2021/11/22
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="/kk/css/search.css" type="text/css" />
</head>
<body>
<%
    String msg = (String)request.getAttribute("msg");
    if(msg==null)
        msg="";
%>
    <form action="/kk/search" method="post" id="searchForm">
        <div id="search" >
            <div class="searchBox" >
                <div class="search-sel" style="float: left;margin-right: 5px;">
                    <div class="sel">
                        <select name="fieldSelect" style="height: 35px">
                            <option name="T" value="T">标题</option>
                            <option name="A" value="A">作者</option>
                            <option name="H" value="H">出版社</option>
                        </select>
                    </div>
                </div>
                <div id="searchInput" style="float: left;margin-right: 5px;">
                    <input type="text" name="txtKey" placeholder="请输入信息" class maxlength="100" autocomplete="off" style="color: rgb(0,0,0);width: 300px;height: 35px;">
                </div>
                <button type="submit" style="height: 35px;">查找</button>
                <span style="color: red"><%=msg %></span><br>
            </div>
        </div>
    </form>
</body>
</html>
