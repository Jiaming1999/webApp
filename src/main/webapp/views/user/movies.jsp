<%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 10/08/2021
  Time: 01:45
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/views/css/style.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>webApp-movies</title>
    <!-- bootswatch -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cyborg/bootstrap.min.css" integrity="sha384-nEnU7Ae+3lD52AK+RGNzgieBWMnEfgTbRHIwEvp1XXPdqdO6uLTd/NwXbzboqjc2" crossorigin="anonymous">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <div class="jumbotron">
        <h3 class="text-center">Search For Any Movie</h3>
        <form id="searchForm">
            <input type="text" class="form-control" id="searchText" placeholder="Search Movies...">
        </form>
    </div>
</div>

<div class="container">
<div id="movies" class="row"></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/views/js/movies-page.js"></script>
</body>
</html>
