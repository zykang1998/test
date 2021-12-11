package dao;

import entity.Book;
import utils.JdbcUtils;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;

public class DBOperator {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public boolean login(String username, String password){
        boolean isValid = false;
        JdbcUtils jdbcUtils = new JdbcUtils();
        try {
            conn = jdbcUtils.getConnection();
            String sql;
            sql = "select count(*) from user where name=? and password=?";

            Object[] ob = new Object[]{username, password};
            rs = jdbcUtils.resultSet(sql,ob);
            if(rs.next()){
                int count = rs.getInt(1);
                if (count == 0){
                    isValid = false;
                }
                else isValid = true;
            }
            return isValid;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try
            {
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
                //System.out.println("数据库连接已关闭！");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return isValid;
    }
    //查询总数量
    public int lineCount(){
        JdbcUtils jdbc = new JdbcUtils();
        int count = -1;
        try {
            conn = jdbc.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "select count(*) from book_info ";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) count = rs.getInt(1);
            return count;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public boolean addBook(int bookId, String bookName, Float bookPrice, String bookAuthor, String publishHouse){
        JdbcUtils jdbc = new JdbcUtils();
        try {
            conn = jdbc.getConnection();
            String sql;
            String sqlCheck;

            sqlCheck = "select * from book_info where book_name=?";

            Object[] obCheck = new Object[]{bookName};
            rs = jdbc.resultSet(sqlCheck,obCheck);
            if(rs.next()){//已经有相同的用户名了
                return false;
            }
            else {
                sql = "insert into book_info(book_id,book_name,book_price,book_author,publish_house) values(?,?,?,?,?)";

                Object[] ob = new Object[]{bookId, bookName, bookPrice, bookAuthor, publishHouse};

                int flag = jdbc.psSet(sql,ob);
                if(flag > 0){
                    return true;
                }
                else return false;
            }

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            jdbc.closeConnection(rs, ps, conn);
        }
    }

    public ArrayList<Book> searchBook(String flag, String bookKey, int pageNow, int pageSize){
        String sql;
        int limit_x = (pageNow - 1) * pageSize;
        int limit_y = pageSize;
        JdbcUtils jdbc = new JdbcUtils();
        ArrayList<Book> booklist = new ArrayList<>();
        try {
            conn = jdbc.getConnection();
            stmt = conn.createStatement();
            if (bookKey != null && !bookKey.equals("") && flag.equals("T")){
                sql = "select * from book_info where book_name regexp '"+ bookKey +"' limit "+ limit_x + "," + limit_y;
            }
            else if (bookKey != null && !bookKey.equals("") && flag.equals("A")){
                sql = "select * from book_info where book_author regexp '"+ bookKey +"' limit "+ limit_x + "," + limit_y;
            }
            else if (bookKey != null && !bookKey.equals("") && flag.equals("H")){
                sql = "select * from book_info where publish_house regexp '"+ bookKey +"' limit "+ limit_x + "," + limit_y;
            }
            else sql = "select * from book_info limit " + limit_x + "," + limit_y;

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                booklist.add(new Book(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                        rs.getString(4), rs.getString(5)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try
            {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
                //System.out.println("数据库连接已关闭！");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return booklist;
    }

    public boolean deleteBook(int bookId){
        JdbcUtils jdbc = new JdbcUtils();
        try {
            conn = jdbc.getConnection();
            String sql;
            sql = "delete from book_info where book_id=?";
            Object[] ob = new Object[]{bookId};
            int flag = jdbc.psSet(sql,ob);
            if(flag > 0){
                return true;
            }
            else return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            jdbc.closeConnection(rs, ps, conn);
        }
    }

    public boolean updateBook(int bookId, String bookName, Float bookPrice, String bookAuthor, String publishHouse){
        JdbcUtils jdbc = new JdbcUtils();
        try {
            conn = jdbc.getConnection();
            String sql;
            sql = "update book_info set book_name=?,book_price=?,book_author=?,publish_house=? where book_id=?";

            Object[] ob = new Object[]{bookName, bookPrice, bookAuthor, publishHouse, bookId};

            int flag = jdbc.psSet(sql,ob);
            if(flag > 0){
                return true;
            }
            else return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            jdbc.closeConnection(rs, ps, conn);
        }
    }
}
