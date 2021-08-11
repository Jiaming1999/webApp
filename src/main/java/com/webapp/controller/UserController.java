package com.webapp.controller;

import com.webapp.model.*;
import entity.Chat;
import com.webapp.model.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * all function name are lowercase!
 */

public class UserController extends BasicController{
    public UserController(IProductsDAO dao) {
        super(dao);
    }

    /**
     * login function -checking for a matching user in the DB.
     * @param request
     * @param response
     * @return
     * @throws ServletException
     */
    public Users login(HttpServletRequest request, HttpServletResponse response) throws ProductsManagementException {
        try {
            //getting necessary  fields
            PrintWriter out = response.getWriter();
            String userName = request.getParameter("name");
            String userPassword = request.getParameter("password");

            if(userName==null || userName=="" || userPassword==""){
                if(userName=="" || userPassword==""){
                    //only if user pressed submit with empty fields.
                    String message = "I Need All The Details.";
                    request.setAttribute("message", message);
                }
            }else{
                try {
                    //creating dao object, using singleton pattern.
                    ProductsDAO dao = ProductsDAO.getInstance();
                    Users u = new Users();
                    //login func, from ProductsDAO. if user details wrong throws exception.
                    u = dao.login(userName, userPassword);
                    //return user to RouterServlet.
                    return u;

                } catch(NumberFormatException e) {
                    out.println("<br/>Problem with converting" + e.getMessage());
                }catch (ProductsManagementException e) {
                    if(!e.getMessage().equals("User not found.")){
                        throw new ProductsManagementException(e.getMessage());
                    }
                    request.setAttribute("message", e.getMessage());
                }
            }
        } catch (IOException e1) {
            throw new ProductsManagementException(e1.getMessage(),e1.getCause());
        }
        return null;
    }

    /**
     * logout function - close the session http request.
     * @param request
     * @param response
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //closing session before logout.
        HttpSession session = request.getSession(false);
        session.invalidate();
    }

    /**
     * sign up function - enter new user to users table in the DB.
     * @param request
     * @param response
     * @throws ProductsManagementException
     */
    public void signup(HttpServletRequest request, HttpServletResponse response) throws ProductsManagementException {
        try {
            //getting necessary fields.
            PrintWriter out = response.getWriter();
            String userName = request.getParameter("name");
            String userPassword = request.getParameter("password");

            if (userName == null || userName == "" || userPassword == "") {
                if(userName == "" || userPassword == ""){
                    //only if user pressed submit with empty fields.
                    String message = "I Need All The Details.";
                    request.setAttribute("message", message);
                }
            } else {
                try {
                    Users u = new Users(userName, userPassword);
                    //creating dao object, using singleton pattern.
                    ProductsDAO dao = ProductsDAO.getInstance();
                    //calling sign up method from ProductsDAO.
                    dao.signUp(u);
                } catch (NumberFormatException e) {
                    out.println("<br/>Problem with converting a string to double " + e.getMessage());
                } catch (ProductsManagementException e) {
                    if(!e.getMessage().equals("This username already exists")) {
                        throw new ProductsManagementException(e.getMessage());
                    }
                    request.setAttribute("message", e.getMessage());
                }
            }
        }catch (IOException e) {
            throw new ProductsManagementException(e.getMessage(),e.getCause());
        }
    }

    /**
     * add cost item function - adds producst to products table in the DB.
     * @param request
     * @param response
     */
    public void addcostitem(HttpServletRequest request, HttpServletResponse response) throws ProductsManagementException {
        try {
            //getting necessary fields.
            PrintWriter out = response.getWriter();
            String category = request.getParameter("category");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String priceString = request.getParameter("price");

            //category=null, to not show message when navigating with navbar.
            if(category==null || category=="" || title=="" |description=="" || priceString==""){
                if(category=="" || title=="" |description=="" || priceString==""){
                    String message = "I Need All The Details.";
                    request.setAttribute("message", message);
                }
            }else{
                try{
                    ProductsDAO dao = ProductsDAO.getInstance();
                    //getting http session.
                    HttpSession session = request.getSession();
                    String userName = (String) session.getAttribute("username");

                    //converting price to int.
                    double price = Double.parseDouble(priceString);
                    Products p = new Products(category, title, description, price, userName);
                    //calling add product func. from ProductsDAO.
                    dao.addProduct(p);

                } catch (ProductsManagementException e) {
                    throw new ProductsManagementException(e.getMessage(),e.getCause());
                }catch(NumberFormatException e) {
                    out.println("<br/>Problem with converting" + e.getMessage());
                }
            }
        } catch (IOException e1) {
            throw new ProductsManagementException(e1.getMessage(),e1.getCause());
        }
    }

    /**
     * get cost item function - get all products from products table in the DB.
     * @param request
     * @param response
     */
    public void getcostitems(HttpServletRequest request, HttpServletResponse response) throws ProductsManagementException {
        try {
            PrintWriter out = response.getWriter();
            try {
                ProductsDAO dao = ProductsDAO.getInstance();
                //getting session, to match products that belong to the user.
                HttpSession session = request.getSession();
                String userName = null;
                //getting the correct username.
                userName = (String) session.getAttribute("username");

                //enter if user exist.
                if(userName != null) {
                    //calling the get products method from ProductsDAO.
                    List<Products> products = dao.getProducts();
                    List<Products> filterProducts = new LinkedList();

                    for(Products p : products) {
                        //getting only the product that match the username.
                        if(p.getUsername().equals(userName)) {
                            filterProducts.add(p);
                        }
                    }
                    //for displaying products in the getcostitems.jsp file
                    session.setAttribute("products", filterProducts);
                    } else {
                        throw new ProductsManagementException("no products in this user.");
                        //sand massage that no products in this user.
                    }
            } catch(NumberFormatException e) {
                out.println("<br/>Problem with converting" + e.getMessage());
            }catch (ProductsManagementException e) {
                throw new ProductsManagementException(e.getMessage(),e.getCause());
            }
        } catch (IOException e1) {
            throw new ProductsManagementException(e1.getMessage(),e1.getCause());
        }
    }

    /**
     * get cost per month function - get all products that were added in the same month, from the products table
     * @param request
     * @param response
     */
    public void getcostpermonth(HttpServletRequest request, HttpServletResponse response) throws ProductsManagementException {
        try {
            //getting necessary fields.
            PrintWriter out = response.getWriter();
            String month = request.getParameter("month");

            if (month == null || month == "") {
                if(month == ""){
                    String message = "I Need a Number.";
                    request.setAttribute("message", message);
                }
            } else {
                try {
                    //creating dao object , using singleton pattern.
                    ProductsDAO dao = ProductsDAO.getInstance();

                    //getting session.
                    HttpSession session = request.getSession();
                    String userName = null;
                    //getting the correct username.
                    userName = (String) session.getAttribute("username");

                    //if user exists.
                    if (userName != null) {

                        List<Products> productsFilter = new LinkedList();

                        //calling get products method from the ProductsDAO.
                        List<Products> products = dao.getProducts();

                        //getting matching products and adding to productsFiters list, for displaying in getcostpermonth.jsp.
                        int monthItem = Integer.parseInt(month);
                        //getting all the product that match the username.
                        for (Products p : products) {
                            if (p.getMonth() + 1 == monthItem && p.getUsername().equals(userName)) {
                                productsFilter.add(p);
                            }
                        }
                        //adding the product list to the session for display.
                        session.setAttribute("productspermonth", productsFilter);

                    } else {
                        throw new ProductsManagementException("no products in this user.");
                    }
                }catch (ProductsManagementException e){
                    throw new ProductsManagementException(e.getMessage(),e.getCause());
                }catch(NumberFormatException e){
                    out.println("<br/>Problem with converting" + e.getMessage());
                }
            }
        }catch (IOException e1) {
            throw new ProductsManagementException(e1.getMessage(),e1.getCause());
        }
    }

    public void chat (HttpServletRequest request,HttpServletResponse response) throws ProductsManagementException {
        try {
            PrintWriter out = response.getWriter();
            String message = request.getParameter("message");
            String msgDelete = request.getParameter("id");
            try {
                ProductsDAO dao = ProductsDAO.getInstance();
                //getting session, to match products that belong to the user.
                HttpSession session = request.getSession();
                String userName = null;
                //getting the correct username.
                userName = (String) session.getAttribute("username");

                //enter if user exist.
                if(userName != null) {
                    //calling the get products method from ProductsDAO.
                    List<Chat> chat = dao.getChat();
                    //if chat is empty.
                    if(chat.isEmpty()){
                        chat.add(new Chat("default message","root",new Timestamp(System.currentTimeMillis())));
                    }
                    //for displaying chat in the chat.jsp file
                    session.setAttribute("chat", chat);
                    //adding the message to db.
                    if(message!=null && message!=""){
                        Chat chat1 = new Chat(message,userName,new Timestamp(System.currentTimeMillis()));
                        dao.addChatMessage(chat1);
                    }
                    if(msgDelete!=null && msgDelete!=""){
                        dao.deleteChat(Integer.parseInt(msgDelete),userName);
                    }
                } else {
                    throw new ProductsManagementException("no chat yet.");
                }
            } catch(NumberFormatException e) {
                out.println("<br/>Problem with converting" + e.getMessage());
            }catch (ProductsManagementException e) {
                if(!e.getMessage().equals("Cannot delete message: mismatching users")){
                    throw new ProductsManagementException(e.getMessage(),e.getCause());
                }
                request.setAttribute("message", e.getMessage());
            }
        } catch (IOException e1) {
            throw new ProductsManagementException(e1.getMessage(),e1.getCause());
        }
    }
    public void movies(HttpServletRequest request,HttpServletResponse response){

    }
    public void moviedetails(HttpServletRequest request,HttpServletResponse response){

    }

}
