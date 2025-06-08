package com.glasses_store.order.dao;

import com.glasses_store.order.entity.Custom;
import com.glasses_store.order.frameworkutil.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomDao {
    //获取全部顾客信息列表
    public List<Custom> selectCustoms(){
        List list = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            StringBuffer sqlin = new StringBuffer();
            sqlin.append(" select  * from custom");
            rs = stmt.executeQuery(sqlin.toString());
            while (rs.next()){
                int index = 0;
                String custom_name = rs.getString(++index);
                String order_type = rs.getString(++index);
                String custom_id = rs.getString(++index);
                Custom custom = new Custom(custom_name,order_type,custom_id);
                list.add(custom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, stmt, rs);
        }
        return list;
    }
}
