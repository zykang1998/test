package controller;

import dao.DBOperator;
import entity.Book;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        int bookId = Integer.parseInt(req.getParameter("bookid"));
        String bookName = req.getParameter("bookname");
        Float bookPrice =  Float.parseFloat(req.getParameter("bookprice"));
        String bookAuthor = req.getParameter("bookauthor");
        String publishHouse = req.getParameter("publishhouse");

        boolean isValid = false;
        DBOperator dbOperator = new DBOperator();
        isValid = dbOperator.addBook(bookId, bookName, bookPrice, bookAuthor, publishHouse);

        ArrayList<Book> bookArrayList = dbOperator.searchBook("","",1,3);
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("bookInfo", bookArrayList);

        if (isValid){
            System.out.println("增加成功！");
            resp.sendRedirect("jsp/bookInfo.jsp");
        }
        else {
            System.out.println("增加失败！");
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
