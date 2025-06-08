package com.glasses_store.order.service;

import com.glasses_store.order.dao.OrderDao;
import com.glasses_store.order.entity.Order;

import java.util.Collections;
import java.util.List;

    //业务层
public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public List<Order> queryList(String how) {
        return orderDao.selectList(how);
    }

    public boolean save(Order order) {
        return orderDao.inserts(Collections.singletonList(order)).length > 0;
    }

    public Order getOne(String id) {
        return orderDao.selectById(id);
    }

    public boolean update(Order order) {
        return orderDao.update(order) > 0;
    }

    public boolean deleteById(String id) {
        return orderDao.deleteById(id) > 0;
    }

    public boolean completeOrder(String tgtid) {
        return orderDao.completeOrder(tgtid) > 0;
    }
}
