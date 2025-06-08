package com.glasses_store.order.entity;

import java.math.BigDecimal;

//订单(Order)实体类
public class Order {
    private String order_id;
    private String order_type;
    private String to_custom_id;
    private String product_name;
    private BigDecimal order_price;
    private String factory;

    public Order(String order_id, String order_type, String to_custom_id, String product_name, BigDecimal order_price, String factory) {
        this.order_id = order_id;
        this.order_type = order_type;
        this.to_custom_id = to_custom_id;
        this.product_name = product_name;
        this.order_price = order_price;
        this.factory = factory;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getTo_custom_id() {
        return to_custom_id;
    }

    public void setTo_custom_id(String to_custom_id) {
        this.to_custom_id = to_custom_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public BigDecimal getOrder_price() {
        return order_price;
    }

    public void setOrder_price(BigDecimal order_price) {
        this.order_price = order_price;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
