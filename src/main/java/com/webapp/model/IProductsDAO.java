package com.webapp.model;

import entity.Chat;

import java.util.List;

public interface IProductsDAO {
    public Users login(String userName, String password) throws ProductsManagementException;
    public void signUp(Users user) throws ProductsManagementException;
    public void addProduct(Products product) throws ProductsManagementException;
    public List<Products> getProducts() throws ProductsManagementException;
    public List<Chat> getChat() throws ProductsManagementException;
    public void addChatMessage(Chat chatMessage) throws ProductsManagementException;
    public void deleteChat(int id,String username)throws ProductsManagementException;
}
