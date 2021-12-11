package utils;

import java.sql.*;

public class JdbcUtils {
    String url = "jdbc:mysql://localhost:3306/weblibrary?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=utf8&useUnicode=true";
    String user = "root";
    String pass = "123456";
    Connection conn = null;
    PreparedStatement ps=null;

    public Connection getConnection()//连接数据库
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection conn)//关闭数据库
    {
        if(rs != null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null)
        {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int psSet(String sql, Object... objs)//修改数据
    {
        int flag = -1;
        conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < objs.length; i++)
        {
            try {
                ps.setObject(i + 1, objs[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        try {
            flag = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ResultSet resultSet(String sql, Object...objs)//查询数据
    {
        conn = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i = 0;i < objs.length; i++)
        {
            try {
                ps.setObject(i + 1,objs[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rs;
    }
}
