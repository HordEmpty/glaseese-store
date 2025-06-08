package com.glasses_store.order.dao;

import com.glasses_store.order.entity.Product;
import com.glasses_store.order.frameworkutil.JDBCUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    //用于获取全部产品信息列表
    public List<Product> selectProduct() {
        List list = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            StringBuffer sqlin = new StringBuffer();
            sqlin.append(" select  * from product");
            rs = stmt.executeQuery(sqlin.toString());
            while (rs.next()) {
                int index = 0;
                String to_brand_id = rs.getString(++index);
                String product_id = rs.getString(++index);
                String product_name = rs.getString(++index);
                BigDecimal product_price = rs.getBigDecimal(++index);
                BigInteger product_num = BigInteger.valueOf(rs.getLong(++index));
                Product product = new Product(to_brand_id, product_id, product_name, product_price, product_num);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, stmt, rs);
        }
        return list;
    }
}
