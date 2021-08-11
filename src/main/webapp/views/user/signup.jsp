<%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 08/08/2021
  Time: 06:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css" integrity="sha384-5QFXyVb+lrCzdN228VS3HmzpiE7ZVwLQtkt+0d9W43LQMzz4HBnnqvVxKg6O+04d" crossorigin="anonymous">
    <title>webApp-signUp</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">webApp</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarColor02">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="http://localhost:8081/webApp_war/router/user/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8081/webApp_war/router/user/signup">Sign Up</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="row mt-3">
    <div class="col-md-6 mx-auto">
        <div class="card card-body">
            <h3 class="text-center">SignUp</h3>
            <form action="http://localhost:8081/webApp_war/router/views/user/signup" method="post">
                <div class="form-group mb-4 mt-4">
                    <input placeholder="Username" type="text" class="form-control" name="name">
                </div>
                <div class="form-group mb-4">
                    <input placeholder="password" type="password" class="form-control" name="password">
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

                <button class="btn btn-success" type="submit" style="margin-top:10px; margin-left: auto; margin-right: auto;">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
