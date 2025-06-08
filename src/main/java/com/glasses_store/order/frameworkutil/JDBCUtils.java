package com.glasses_store.order.frameworkutil;

import java.sql.*;

public class JDBCUtils {
    public static final String URL = "jdbc:mysql://localhost:3306/glasses_store?useUnicode=true&characterEncoding=utf-8";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "114514";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
