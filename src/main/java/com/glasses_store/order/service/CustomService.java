package com.glasses_store.order.service;

import com.glasses_store.order.dao.CustomDao;
import com.glasses_store.order.entity.Custom;

import java.util.List;

public class CustomService {
    private CustomDao customDao = new CustomDao();
    public List<Custom> queryList(){
        return customDao.selectCustoms();
    }
}
