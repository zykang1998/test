package controller;

import dao.DBOperator;
import entity.Book;
import entity.Page;

import javax.print.DocFlavor;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lineCount;
        req.setCharacterEncoding("utf-8");
        //将分页所需的东西组装，4个
        Page page = new Page();

        String flag = req.getParameter("fieldSelect");
        String bookKey = req.getParameter("txtKey");

        String cPage = req.getParameter("n");//当前页
        if (cPage == null) cPage = "1";//第一次进入的初始化
        int pageNow = Integer.parseInt(cPage);//当前页
        page.setPageNow(pageNow);

        DBOperator dbOperator = new DBOperator();
        lineCount = dbOperator.lineCount();//总数据量
        page.setPageCount(lineCount);

        int pageSize = 3;//每一页的数量
        page.setPageSize(pageSize);


        ArrayList<Book> bookArrayList = dbOperator.searchBook(flag,bookKey,pageNow,pageSize);
        page.setBook(bookArrayList);

        ServletContext servletContext = getServletContext();
        //servletContext.setAttribute("bookInfo", bookArrayList);
        servletContext.setAttribute("p", page);

        if (bookArrayList.size() == 0) {
            req.setAttribute("msg","很抱歉,没有查询到该图书");
            req.getRequestDispatcher("jsp/welcome.jsp").forward(req,resp);
            return;
        }
        resp.sendRedirect("/kk/jsp/bookInfo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
