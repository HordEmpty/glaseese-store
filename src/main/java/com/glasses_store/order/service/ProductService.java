package com.glasses_store.order.service;

import com.glasses_store.order.dao.ProductDao;
import com.glasses_store.order.entity.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    public List<Product> queryList(){
        return productDao.selectProduct();
    }
}
