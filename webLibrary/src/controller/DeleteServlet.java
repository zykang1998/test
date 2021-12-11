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

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        boolean isValid = false;
        DBOperator dbOperator = new DBOperator();
        isValid = dbOperator.deleteBook(bookId);

        ArrayList<Book> bookArrayList = dbOperator.searchBook("","",1,3);
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("bookInfo", bookArrayList);

        if (isValid){
            System.out.println("删除成功！");
            resp.sendRedirect("jsp/bookInfo.jsp");
        }
        else {
            System.out.println("删除失败！");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
