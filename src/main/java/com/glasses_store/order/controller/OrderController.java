package com.glasses_store.order.controller;

import com.glasses_store.order.entity.Custom;
import com.glasses_store.order.entity.Order;
import com.glasses_store.order.entity.Product;
import com.glasses_store.order.service.CustomService;
import com.glasses_store.order.service.OrderService;
import com.glasses_store.order.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {
    private OrderService orderService = new OrderService();
    private CustomService customService = new CustomService();
    private ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //编码设置
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //控制标记
        String type = request.getParameter("_type");
        if (type == null || "list".equals(type)) {
            list(request, response);
        } else if ("toAdd".equals(type)) {
            toAdd(request, response);
        } else if ("save".equals(type)) {
            save(request, response);
        } else if ("toEdit".equals(type)) {
            toEdit(request, response);
        } else if ("update".equals(type)) {
            update(request, response);
        } else if ("removeById".equals(type)) {
            removeById(request, response);
        } else if ("factorylistundo".equals(type)) {
            factorylistundo(request, response);
        } else if ("factorylistdone".equals(type)) {
            factorylistdone(request, response);
        } else if ("toComplete".equals(type)) {
            factorylistcomplete(request, response);
        }

    }
    //将订单完成
    private void factorylistcomplete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tgtid = request.getParameter("completeid");
        orderService.completeOrder(tgtid);
        request.getRequestDispatcher("/order?_type=factorylistundo").forward(request, response);
    }

    //已完成订单列表页面
    private void factorylistdone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.queryList("done");
        request.setAttribute("orderList", orders);
        request.getRequestDispatcher("/WEB-INF/jsp/factory/factory_donelist.jsp").forward(request, response);
    }

    //未完成订单列表页面
    private void factorylistundo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.queryList("undo");
        request.setAttribute("orderList", orders);
        request.setAttribute("bottom", "<input type=\"submit\" value=\"完成订单\" class=\"btn btn-primary radius\">");
        request.getRequestDispatcher("/WEB-INF/jsp/factory/factory_undolist.jsp").forward(request, response);
    }

    //将订单删除
    private void removeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("deleteid");
        orderService.deleteById(id);
        String root = request.getContextPath();
        response.sendRedirect(root + "/order?_type=list");
    }

    //将订单修改
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String order_id = request.getParameter("orderid");
        String order_type = request.getParameter("orderStatus");
        String to_custom_id = request.getParameter("customId");
        String product_name = request.getParameter("productName");
        String order_price_str = request.getParameter("orderPrice");
        String factory = request.getParameter("factory");
        try {
            BigDecimal order_price = new BigDecimal(order_price_str);
            Order order = new Order(order_id, order_type, to_custom_id, product_name, order_price, factory);
            orderService.update(order);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String root = request.getContextPath();
        response.sendRedirect(root + "/order?_type=list");
    }

    //修改订单页面
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("editid");
        Order order = orderService.getOne(id);
        List<Custom> customs = customService.queryList();
        List<Product> products = productService.queryList();
        request.setAttribute("customList", customs);
        request.setAttribute("productList", products);
        request.setAttribute("editorder", order);
        request.getRequestDispatcher("/WEB-INF/jsp/order/order_edit.jsp").forward(request, response);
    }

    //将订单新增
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String order_type = request.getParameter("orderStatus");
        String to_custom_id = request.getParameter("customId");
        String product_name = request.getParameter("productName");
        String order_price_str = request.getParameter("orderPrice");
        String factory = request.getParameter("factory");
        try {
            BigDecimal order_price = new BigDecimal(order_price_str);
            Order order = new Order("ready", order_type, to_custom_id, product_name, order_price, factory);
            orderService.save(order);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String root = request.getContextPath();
        response.sendRedirect(root + "/order?_type=list");
    }


    //订单列表页面
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.queryList("how");
        request.setAttribute("orderList", orders);
        request.getRequestDispatcher("/WEB-INF/jsp/order/order_list.jsp").forward(request, response);
    }

    //新增订单页面
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Custom> customs = customService.queryList();
        List<Product> products = productService.queryList();
        request.setAttribute("customList", customs);
        request.setAttribute("productList", products);
        request.getRequestDispatcher("/WEB-INF/jsp/order/order_add.jsp").forward(request, response);
    }
}
