package entity;

import java.util.List;

public class Page {
    private int pageNow;//当前页
    private int pageSize;//页面大小
    private int pageCount;//总数据
    private int totalPage;//总页数
    private List<Book> book;

    public Page() {

    }

    public Page(int pageNow, int pageSize, int pageCount, int totalPage, List<Book> book) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalPage = totalPage;
        this.book = book;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }
    //总页数=数据总数%页面大小==0？数据总数/页面大小：数据总数/页面大小+1
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.totalPage = this.pageCount%this.pageSize==0 ? this.pageCount/this.pageSize : this.pageCount/this.pageSize + 1;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalPage() {
        return totalPage;
    }
    //给总页数赋值（自动）
//    public void setTotalPage(int totalPage) {
//        this.totalPage = totalPage;
//    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
