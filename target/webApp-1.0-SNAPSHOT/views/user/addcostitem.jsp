<%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 08/08/2021
  Time: 06:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css" integrity="sha384-5QFXyVb+lrCzdN228VS3HmzpiE7ZVwLQtkt+0d9W43LQMzz4HBnnqvVxKg6O+04d" crossorigin="anonymous">
    <title>webApp-addItems</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="row mt-3">
    <div class="col-md-6 mx-auto">
        <div class="card card-body">
            <h3 class="text-center">Add item</h3>

            <form action="http://localhost:8081/webApp_war/router/user/addcostitem" method="post">
                <!-- fields -->
                <div class="form-group mb-4">
                    <input placeholder="Category" type="text" class="form-control" name="category">
                </div>
                <div class="form-group mb-4">
                    <input placeholder="Title" type="text" class="form-control" name="title">
                </div>
                <div class="form-group mb-4">
                    <input placeholder="Price" type="number" class="form-control" name="price">
                </div>
                <div class="form-group mb-4">
                    <input placeholder="Description" type="text" class="form-control" name="description">
                </div>

                <!-- error message -->
                <% String message = (String)request.getAttribute("message"); %>
                <%
                    if(message != null){
                %>
                <h6 style="color:red;" class="mb-3 mt-3"><%out.println(message);%></h6>
                <%
                    }
                %>

                <!-- button -->
                <div class="container">
                    <button class="btn btn-success" type="submit" style=" width: 150px; margin-top:10px; margin-left: auto; margin-right: auto;">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
