<%@ page import="entity.Book" %>
<%@ page import="java.util.ArrayList" pageEncoding="UTF-8" %>
<%@ page import="dao.DBOperator" %>
<%@ page import="entity.Page" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2021/11/23
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书详情</title>
    <link rel="stylesheet" href="/kk/css/bookInfo.css"  type="text/css" />
    <script type="text/javascript" src="../js/bookInfo.js"></script>
</head>
<body>
    <%
        String msg = (String)request.getAttribute("msg");
        if(msg==null)
            msg="";
    %>
    <form action="/kk/search" method="post" id="searchForm">
        <div id="search" class="search">
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

    <div class="addBookDiv">
        <button type="submit" style="height: 35px;" onclick="openAdd()">增加图书</button>
    </div>

    <div class="bookInfo">
        <h1>查询结果：</h1>
        <table class="bookTable" border="1px"  cellspacing="0px" style="border-collapse:collapse" >
            <tr>
                <th class="id">图书编号</th>
                <th class="name">图书名称</th>
                <th class="price">图书价格</th>
                <th class="author">图书作者</th>
                <th class="publish_house">出版社</th>
                <th class="operate">操作</th>
            </tr>
            <%
                //ArrayList<Book> booklists = (ArrayList<Book>)application.getAttribute("bookInfo");
                //for (Book book : booklists) {
                Page page1 = (Page) application.getAttribute("p");
                for (Book book : page1.getBook()) {
            %>
                <tr>
                    <td><%=book.getId()%></td>
                    <td><%=book.getName()%></td>
                    <td><%=book.getPrice()%></td>
                    <td><%=book.getAuthor()%></td>
                    <td><%=book.getPublishName()%></td>
                    <td align="left" style="padding-left:20px;padding-right:20px;padding-top: 10px;padding-bottom: 10px;">
                        <button type="submit" onclick="deleteBook('<%=book.getId()%>','<%=book.getName()%>')">删除</button><br>
                        <button type="submit" onclick="updateBook('<%=book.getId()%>','<%=book.getName()%>','<%=book.getPrice()%>','<%=book.getAuthor()%>','<%=book.getPublishName()%>')">修改</button>
                    </td>
                </tr>
            <% }%>
            <%--<%
                if (booklists.size()!=0) {
                    for (int i = 0; i < booklists.size(); i++) {
                        out.println("<tr><td>" + booklists.get(i).getId() + "</td>");
                        out.println("<td>" + booklists.get(i).getName() + "</td>");
                        out.println("<td>" + booklists.get(i).getPrice() + "</td>");
                        out.println("<td>" + booklists.get(i).getAuthor() + "</td>");
                        out.println("<td>" + booklists.get(i).getPublishName() + "</td>");
                        out.print("<td><button >" + "</button></td></tr>");
                    }
                }
            %>--%>
        </table>
        <div style="position: absolute; top: 400px; left: 100px; margin-top: 20px; font-size: 1em">
            <a href="../search?n=1">首页</a>
            <a href="../search?n=<%=page1.getPageNow()-1%>">上一页</a>
            <a href="../search?n=<%=page1.getPageNow()+1%>">下一页</a>
            <a href="../search?n=<%=page1.getTotalPage()%>">尾页</a>
            <br>
            共有<%=page1.getTotalPage()%>页，当前是<%=page1.getPageNow()%>/<%=page1.getTotalPage()%>页
        </div>
    </div>

    <form action="../update" method="post" id="updateForm">
        <div id="updateBook" class="updateBook">
            <div class="form-group">
                <label class="control-label" >图书id:&nbsp;&nbsp;&nbsp;</label>
                <input type="text" class="form-control" name="bookid" id="bookid" readonly="readonly"/>
            </div>
            <div class="form-group">
                <label class="control-label">图书名称:</label>
                <input type="text" class="form-control" name="bookname" id="bookname"  />
            </div>
            <div class="form-group">
                <label class="control-label">图书价格:</label>
                <input type="text" class="form-control" name="bookprice" id="bookprice" />
            </div>
            <div class="form-group">
                <label class="control-label">图书作者:</label>
                <input type="text" class="form-control" name="bookauthor" id="bookauthor" />
            </div>
            <div class="form-group">
                <label class="control-label">出版社:&nbsp;&nbsp;&nbsp;</label>
                <input type="text" class="form-control" name="publishhouse" id="publishhouse"  />
            </div>
            <div class="form-group" style="text-align: center">
                <button type="submit">提交修改</button>
                <button type="submit" onclick="closeUp()">取消修改</button>
            </div>
        </div>
    </form>
    <form action="../add" method="post" id="addForm">
        <div id="addBook" class="addBook">
            <div class="form-group">
                <label class="control-label" >图书id:&nbsp;&nbsp;&nbsp;</label>
                <input type="text" class="form-control" name="bookid" id="bookidAdd"/>
            </div>
            <div class="form-group">
                <label class="control-label">图书名称:</label>
                <input type="text" class="form-control" name="bookname" id="booknameAdd"  />
            </div>
            <div class="form-group">
                <label class="control-label">图书价格:</label>
                <input type="text" class="form-control" name="bookprice" id="bookpriceAdd" />
            </div>
            <div class="form-group">
                <label class="control-label">图书作者:</label>
                <input type="text" class="form-control" name="bookauthor" id="bookauthorAdd" />
            </div>
            <div class="form-group">
                <label class="control-label">出版社:&nbsp;&nbsp;&nbsp;</label>
                <input type="text" class="form-control" name="publishhouse" id="publishhouseAdd"  />
            </div>
            <div class="form-group" style="text-align: center">
                <button type="submit">提交</button>
                <button type="button" onclick="closeAdd()">取消</button>
            </div>
        </div>
    </form>


</body>
</html>
