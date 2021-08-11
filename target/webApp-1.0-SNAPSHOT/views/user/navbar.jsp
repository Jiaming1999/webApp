<%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 08/08/2021
  Time: 05:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Navbar</title>
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
                    <a class="nav-link active" href="http://localhost:8081/webApp_war/router/views/user/addcostitem">AddItem</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8081/webApp_war/router/views/user/getcostitems">Items</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8081/webApp_war/router/views/user/getcostpermonth">Items-perMonth</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8081/webApp_war/router/views/user/chat">Chat</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8081/webApp_war/router/views/user/movies">Movies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8081/webApp_war/router/views/user/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
