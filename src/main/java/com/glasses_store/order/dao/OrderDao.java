package com.glasses_store.order.dao;

import com.glasses_store.order.entity.Order;
import com.glasses_store.order.frameworkutil.JDBCUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author HordEmpty
 */
public class OrderDao {
    //随机数生成器，用于生成订单编号，格式为当天日期(yyyyMMdd)+8位随机数=16位编号
    public String randomid() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datestr = date.format(dtf);
        Random random = new Random();
        int randomNum = random.nextInt(100000000);
        String tgtid = datestr + String.format("%08d", randomNum);
        System.out.println(tgtid);
        return tgtid;
    }

    //新增订单
    public int[] inserts(List<Order> orders) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int[] flags = null;
        try {
            conn = JDBCUtils.getConnection();
            StringBuffer sqlin = new StringBuffer();
            sqlin.append(" insert into `orders` (  ");
            sqlin.append(" `order_id`,");
            sqlin.append(" `order_type`,");
            sqlin.append(" `to_custom_id` , ");
            sqlin.append(" `product_name` , ");
            sqlin.append(" `order_price` , ");
            sqlin.append(" `factory`");
            sqlin.append(" ) ");
            sqlin.append(" values ");
            sqlin.append("  ( ?,?,?,?,?,?)");
            stmt = conn.prepareStatement(sqlin.toString());

            for (Order order : orders) {
                String tgtid;
                do {
                    tgtid = randomid();
                } while (selectById(tgtid) != null);

                stmt.setString(1, tgtid);
                stmt.setString(2, order.getOrder_type());
                stmt.setString(3, order.getTo_custom_id());
                stmt.setString(4, order.getProduct_name());
                stmt.setBigDecimal(5, order.getOrder_price());
                stmt.setString(6, order.getFactory());
                stmt.addBatch();
            }

            flags = stmt.executeBatch();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, stmt, null);
        }
        return flags;
    }

    //通过编号更新订单信息
    public int update(Order order) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int flag = 0;
        try {
            conn = JDBCUtils.getConnection();
            StringBuffer sqlin = new StringBuffer();
            sqlin.append(" update `orders` ");
            sqlin.append(" 	set	");
            sqlin.append(" `order_type` = ?,");
            sqlin.append(" `to_custom_id` = ? , ");
            sqlin.append(" `product_name` = ? , ");
            sqlin.append(" `order_price` = ? , ");
            sqlin.append(" `factory` = ? ");
            sqlin.append(" where order_id = ? ");
            stmt = conn.prepareStatement(sqlin.toString());
            stmt.setString(1, order.getOrder_type());
            stmt.setString(2, order.getTo_custom_id());
            stmt.setString(3, order.getProduct_name());
            stmt.setBigDecimal(4, order.getOrder_price());
            stmt.setString(5, order.getFactory());
            stmt.setString(6, order.getOrder_id());
            flag = stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, stmt, null);
        }
        return flag;
    }

    //通过编号来删除订单
    public int deleteById(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int flag = 0;
        try {
            String sqlin = "delete from orders where order_id = ?";
            conn = JDBCUtils.getConnection();
            stmt = conn.prepareStatement(sqlin);
            stmt.setString(1, id);
            flag = stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, stmt, null);
        }
        return flag;
    }

    //通过编号来选择一条订单记录
    public Order selectById(String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order = null;
        try {
            conn = JDBCUtils.getConnection();
            StringBuffer sqlin = new StringBuffer();
            sqlin.append(" select * from orders where order_id = ? ");
            stmt = conn.prepareStatement(sqlin.toString());
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int index = 0;
                String order_id = rs.getString(++index);
                String order_type = rs.getString(++index);
                String to_custom_id = rs.getString(++index);
                String product_name = rs.getString(++index);
                BigDecimal order_price = rs.getBigDecimal(++index);
                String factory = rs.getString(++index);
                order = new Order(order_id, order_type, to_custom_id, product_name, order_price, factory);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {

            JDBCUtils.close(conn, stmt, rs);
        }
        return order;
    }

    //获取订单列表
    public List<Order> selectList(String how) {
        List list = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        if (how.equals("undo")) {
            try {//获取未完成订单列表
                conn = JDBCUtils.getConnection();
                stmt = conn.createStatement();
                StringBuffer sqlin = new StringBuffer();
                sqlin.append(" select  ");
                sqlin.append(" `order_id`, ");
                sqlin.append(" `order_type`, ");
                sqlin.append(" `to_custom_id`, ");
                sqlin.append(" `product_name`, ");
                sqlin.append(" `order_price`, ");
                sqlin.append(" `factory`  ");
                sqlin.append(" from ");
                sqlin.append(" orders where order_type = '未完成' ");
                rs = stmt.executeQuery(sqlin.toString());
                while (rs.next()) {
                    int index = 0;
                    String order_id = rs.getString(++index);
                    String order_type = rs.getString(++index);
                    String to_custom_id = rs.getString(++index);
                    String product_name = rs.getString(++index);
                    BigDecimal order_price = rs.getBigDecimal(++index);
                    String factory = rs.getString(++index);
                    Order order = new Order(order_id, order_type, to_custom_id, product_name, order_price, factory);
                    list.add(order);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(conn, stmt, rs);
            }
            return list;
        } else if (how.equals("done")) {
            try {//获取已完成订单列表
                conn = JDBCUtils.getConnection();
                stmt = conn.createStatement();
                StringBuffer sqlin = new StringBuffer();
                sqlin.append(" select  ");
                sqlin.append(" `order_id`, ");
                sqlin.append(" `order_type`, ");
                sqlin.append(" `to_custom_id`, ");
                sqlin.append(" `product_name`, ");
                sqlin.append(" `order_price`, ");
                sqlin.append(" `factory`  ");
                sqlin.append(" from ");
                sqlin.append(" orders where order_type = '已完成' ");
                rs = stmt.executeQuery(sqlin.toString());
                while (rs.next()) {
                    int index = 0;
                    String order_id = rs.getString(++index);
                    String order_type = rs.getString(++index);
                    String to_custom_id = rs.getString(++index);
                    String product_name = rs.getString(++index);
                    BigDecimal order_price = rs.getBigDecimal(++index);
                    String factory = rs.getString(++index);
                    Order order = new Order(order_id, order_type, to_custom_id, product_name, order_price, factory);
                    list.add(order);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(conn, stmt, rs);
            }
            return list;
        } else {
            try {//获取全部订单列表
                conn = JDBCUtils.getConnection();
                stmt = conn.createStatement();
                StringBuffer sqlin = new StringBuffer();
                sqlin.append(" select  ");
                sqlin.append(" `order_id`, ");
                sqlin.append(" `order_type`, ");
                sqlin.append(" `to_custom_id`, ");
                sqlin.append(" `product_name`, ");
                sqlin.append(" `order_price`, ");
                sqlin.append(" `factory`  ");
                sqlin.append(" from ");
                sqlin.append(" orders ");
                rs = stmt.executeQuery(sqlin.toString());
                while (rs.next()) {
                    int index = 0;
                    String order_id = rs.getString(++index);
                    String order_type = rs.getString(++index);
                    String to_custom_id = rs.getString(++index);
                    String product_name = rs.getString(++index);
                    BigDecimal order_price = rs.getBigDecimal(++index);
                    String factory = rs.getString(++index);
                    Order order = new Order(order_id, order_type, to_custom_id, product_name, order_price, factory);
                    list.add(order);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(conn, stmt, rs);
            }
            return list;
        }
    }

    //将订单状态修改为已完成（完成订单）
    public int completeOrder(String tgtid) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int flag = 0;
        try {
            conn = JDBCUtils.getConnection();
            StringBuffer sqlin = new StringBuffer();
            sqlin.append(" update orders ");
            sqlin.append(" 	set	");
            sqlin.append(" `order_type` = '已完成' ");
            sqlin.append(" where order_id = ? ");
            stmt = conn.prepareStatement(sqlin.toString());
            stmt.setString(1, tgtid);
            flag = stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, stmt, null);
        }
        return flag;
    }
}
