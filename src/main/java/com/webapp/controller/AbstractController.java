package com.webapp.controller;

import com.webapp.model.IProductsDAO;

public abstract class AbstractController {
    protected IProductsDAO dao;

    AbstractController(IProductsDAO dao){
        this.dao = dao;
    }
    public abstract void init();
}
