package com.webapp.model;

public class ProductsManagementException extends Exception {
    public ProductsManagementException(String message){
        super(message);
    }
    public ProductsManagementException(String message, Throwable cause){
        super(message,cause);
    }

}
