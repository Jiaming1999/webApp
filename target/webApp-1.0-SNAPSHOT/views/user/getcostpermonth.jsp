<%@ page import="java.util.List" %>
<%@ page import="com.webapp.model.Products" %><%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 08/08/2021
  Time: 06:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css" integrity="sha384-5QFXyVb+lrCzdN228VS3HmzpiE7ZVwLQtkt+0d9W43LQMzz4HBnnqvVxKg6O+04d" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
  <title>webApp-getItems</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="row mt-3">
  <div class="col-md-10 mx-auto">
    <div class="card card-body">
      <h3 class="text-center">Items by month</h3>
      <form action="http://localhost:8081/webApp_war/router/user/getcostpermonth" method="get">

        <!-- error message -->
        <% String message = (String)request.getAttribute("message"); %>
        <%
          if(message != null){
        %>
        <h6 style="color:red;" class="mb-3 mt-3"><%out.println(message);%></h6>
        <%
          }
        %>

        <div class="input-group" style="margin-bottom:20px;">
          <input class="form-control" type="number" style="width:50px;" name="month">
          <span class="input-group-btn">
						<button class="btn btn-success" type="submit">Get items</button>
				    </span>
        </div>

        <table class="table">
          <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
            <th scope="col">Month</th>
          </tr>
          </thead>
          <tbody>
          <%
            List<Products> pl = (List<Products>)session.getAttribute("productspermonth");


            if(pl!=null){
              for(Products p : pl){
          %>
          <tr>
            <td><%out.println(p.getId()); %></td>
            <td><%out.println(p.getTitle());%></td>
            <td><%out.println(p.getPrice());%></td>
            <td><%out.println(p.getDescription());%></td>
            <td><%out.println(p.getMonth()+1);%></td>
          </tr>
          <%
              }
            }
          %>
          </tbody>
        </table>
      </form>
    </div>
  </div>
</div>
</body>
</html>
