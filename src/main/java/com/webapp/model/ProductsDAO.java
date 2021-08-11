package com.webapp.model;

import entity.Chat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ProductsDAO implements IProductsDAO{
    //session for connection.
    private SessionFactory factory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();

    //implements singleton pattern for ProductsDAO
    private static ProductsDAO dao;

    static {
        ProductsDAO.dao = new ProductsDAO();
    }
    /**
     * func. for singleton pattern.
     * @return
     */
    public static ProductsDAO getInstance() {
        return ProductsDAO.dao;
    }

    /**
     * login function - matches username and password with the users table in the DB.
     * @param userName
     * @param password
     * @return
     * @throws ProductsManagementException
     */
    @Override
    public Users login(String userName, String password) throws ProductsManagementException {
        //open session
        Session sess = factory.openSession();
        Transaction tx = null;
        try{
            tx = sess.beginTransaction();
            //looking for matching user in Users table.
            List<Users> users = (List<Users>) sess.createQuery("from Users where username = '"+userName+"' and password = '"+password+"'").list();
            //if user not found.
            if(users.size()==0){
                throw new ProductsManagementException("User not found.");
            }
            return users.get(0);

        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to login.");
        }
        //close connection.
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }
    }

    /**
     * sign up function - adds new user to the users table in the DB.
     * @param u
     * @throws ProductsManagementException
     */
    @Override
    public void signUp(Users u) throws ProductsManagementException {
        //open session.
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            //checks that users does not exist.
            List<Users> users = (List<Users>) sess.createQuery("from Users where username = '"+u.getUsername()+"'").list();
            //if username already exists.
            if(users.size()==1){
                throw new ProductsManagementException("This username already exists");
            }
            //save changes to the users table.
            sess.save(u);
            tx.commit();

        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to signup.");
        }
        //close connection.
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }
    }

    /**
     * add product function - adds new product to the products table in the DB
     * @param product
     * @throws ProductsManagementException
     */
    @Override
    public void addProduct(Products product) throws ProductsManagementException {
        //open session
        Session sess = factory.openSession();
        Transaction tx = null;
        try{
            tx = sess.beginTransaction();
            //saves product to the products table.
            sess.save(product);
            tx.commit();

        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to add a product.");
        }
        //close connection.
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }
    }

    /**
     * get product function - gets all products from the products table, and returns as list to the controller.
     * @return
     * @throws ProductsManagementException
     */
    @Override
    public List<Products> getProducts() throws ProductsManagementException {
        //open session
        Session sess = factory.openSession();
        Transaction tx = null;

        try{
            tx = sess.beginTransaction();
            //get products table query.
            List products = sess.createQuery("from Products").list();
            tx.commit();
            return products;

        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to get products.");
        }
        //close connection
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }

    }

    @Override
    public List<Chat> getChat() throws ProductsManagementException {
        //open session
        Session sess = factory.openSession();
        Transaction tx = null;

        try{
            tx = sess.beginTransaction();
            //get products table query.
            List chat = sess.createQuery("from Chat").list();
            tx.commit();
            return chat;

        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to get chat.");
        }
        //close connection
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }
    }

    @Override
    public void addChatMessage(Chat chatMessage) throws ProductsManagementException{
        //open session
        Session sess = factory.openSession();
        Transaction tx = null;

        try{
            tx = sess.beginTransaction();
            //saves product to the products table.
            sess.save(chatMessage);

            tx.commit();

        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to add a message.");
        }
        //close connection.
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }
    }

    @Override
    public void deleteChat(int id,String username)throws ProductsManagementException{
        //open session
        Session sess = factory.openSession();
        Transaction tx = null;
        try{
            tx = sess.beginTransaction();
            Chat entity = sess.load(Chat.class,id);
            if(entity.getUser().equals(username)){
                //deletes message from db.
                sess.delete(entity);
            }else{
                throw new ProductsManagementException("Cannot delete message: mismatching users");
            }
            tx.commit();
        } catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new ProductsManagementException("failed to add a message.");
        }
        //close connection.
        finally {
            if(sess != null) {
                try {
                    sess.close();
                } catch(HibernateException e) {
                    e.printStackTrace();
                    throw new ProductsManagementException("Problem with a close session.");
                }
            }
        }
    }

}
