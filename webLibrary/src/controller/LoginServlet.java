package controller;

import dao.DBOperator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isValid = false;
        if (username == null || "".equals(username.trim())){
            System.out.println("用户名不能为空");
            resp.sendRedirect("/kk/jsp/login.jsp");
            return;
        }
        if (password == null || "".equals(password.trim())){
            System.out.println("用户密码不能为空");
            resp.sendRedirect("/kk/jsp/login.jsp");
            return;
        }
        DBOperator dbOperator = new DBOperator();
        isValid = dbOperator.login(username,password);
        if (isValid){
            System.out.println("登陆成功！");
            resp.sendRedirect("/kk/jsp/welcome.jsp");
        }
        else {
            System.out.println("登录失败！");
            resp.sendRedirect("/kk/jsp/login.jsp");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

