package com.webapp.controller;

import com.webapp.model.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "RouterServlet", value = "/RouterServlet")
public class RouterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String pkg;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
        pkg = getServletConfig().getInitParameter("package");
    }

    /**
     * identifying the action through the URL and uses reflection for calling the correct method.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * Check what the controller is
         * Check what the action is
         * Activate a function with reflection*/
        String text = request.getRequestURI();
        int x;
        String[]  arr = text.split("/");
        String controller = null;
        String action = null;

        if(arr[4].equals("user")){
            x=4;
        }else{
            x=3;
        }
        controller = arr[x];
        action = arr[++x].toLowerCase();

        try {
            //setting and activating the reflection.
            String className = pkg + "." + controller.substring(0, 1).toUpperCase() + controller.substring(1).toLowerCase() + "Controller";
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(com.webapp.model.IProductsDAO.class);
            Object object = constructor.newInstance(com.webapp.model.ProductsDAO.getInstance());
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(object, request,response);

            //using switch to identify the action, using switch to catch error.
            switch(action) {
                case "getcostitems":
                case "getcostpermonth":
                case "addcostitem":
                case "signup":
                case "login":
                case "chat":
                case "movies":
                case "moviedetails":
                    RequestDispatcher dispatcher0 = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
                    dispatcher0.include(request,response);
                    break;
                case "logout":
                    RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/views/user/login.jsp");
                    dispatcher1.include(request,response);
                    break;
                default:
                    showErrorMessage(request,response,"OOPS something went wrong.");
            }

            //exceptions
        } catch(ClassNotFoundException e) {
            showErrorMessage(request,response,"The requested controller doesn't exist");
        } catch(NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (InvocationTargetException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class or invoking the action");
        }
    }

    /**
     * identifying the action through the URL and uses reflection for calling the correct method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check what the controller is
         * Check what the action is
         * Activate a function with reflection*/

        String text = request.getRequestURI();
        PrintWriter writer = response.getWriter();

        int x;
        String[]  arr = text.split("/");
        String controller = null;
        String action = null;
        if(arr[4].equals("user")){
            x=4;
        }else{
            x=3;
        }
        controller = arr[x];
        action = arr[++x].toLowerCase();

        try {
            //setting and activating the reflection.
            String className = pkg + "." + controller.substring(0, 1).toUpperCase() + controller.substring(1).toLowerCase() + "Controller";
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(com.webapp.model.IProductsDAO.class);
            Object object = constructor.newInstance(com.webapp.model.ProductsDAO.getInstance());
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            Object u = method.invoke(object, request,response);

            //u=null , unless login method was called.
            //u=null, also for login with empty fields. if user was not found DAO will throw exception.
            if(u!=null) {
                String userName =  ((Users) u).getUsername();
                //starting session.
                HttpSession session = request.getSession();
                //setting the username attribute in the session.
                session.setAttribute("username", userName);

                //forward to addcostitem jsp.
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/user/addcostitem.jsp");
                dispatcher.forward(request,response);
            } else {
                //if user does not exist or required fields are empty.
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/user/"+action+".jsp");
                dispatcher.forward(request,response);
            }

            //exceptions
        } catch(ClassNotFoundException e) {
            showErrorMessage(request,response,"The requested controller does not exist");
        } catch(NoSuchMethodException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (InvocationTargetException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class or invoking the action");
        } catch (InstantiationException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        } catch (IllegalAccessException e) {
            showErrorMessage(request,response,"Problem with instantiating the Model class");
        }
    }

    /**
     * display error function.
     * @param request
     * @param response
     * @param text
     * @throws ServletException
     * @throws IOException
     */
    protected void showErrorMessage(HttpServletRequest request, HttpServletResponse response,String text) throws ServletException, IOException {
        //sending message through request.
        request.setAttribute("errormessage",text);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/error.jsp");
        dispatcher.forward(request,response);
    }
}
