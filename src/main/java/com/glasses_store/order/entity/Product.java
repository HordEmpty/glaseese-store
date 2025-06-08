package com.glasses_store.order.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Product {
    private String to_brand_id;
    private String product_id;
    private String product_name;
    private BigDecimal product_price;
    private BigInteger product_num;

    public Product(String to_brand_id, String product_id, String product_name, BigDecimal product_price, BigInteger product_num) {
        this.to_brand_id = to_brand_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_num = product_num;
    }

    public String getTo_brand_id() {
        return to_brand_id;
    }

    public void setTo_brand_id(String to_brand_id) {
        this.to_brand_id = to_brand_id;
    }

    public String getVarchar() {
        return product_id;
    }

    public void setVarchar(String varchar) {
        this.product_id = varchar;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public BigInteger getProduct_num() {
        return product_num;
    }

    public void setProduct_num(BigInteger product_num) {
        this.product_num = product_num;
    }
}
