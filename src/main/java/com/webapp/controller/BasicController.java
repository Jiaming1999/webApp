package com.webapp.controller;

import com.webapp.model.IProductsDAO;

public class BasicController extends AbstractController{
    public BasicController(IProductsDAO dao){
        super(dao);
    }
    @Override
    public void init() {}
}
