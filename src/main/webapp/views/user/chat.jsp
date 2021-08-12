<%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 08/08/2021
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Chat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css" integrity="sha384-5QFXyVb+lrCzdN228VS3HmzpiE7ZVwLQtkt+0d9W43LQMzz4HBnnqvVxKg6O+04d" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <title>webApp-Chat</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="row mt-3">
    <div class="col-md-10 mx-auto">
        <div class="card card-body">
            <h3 class="text-center">Chat</h3>
            <!-- javascript -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
            <script src="${pageContext.request.contextPath}/views/js/movies-page.js"></script>

            <form action="http://localhost:8081/webApp_war/router/user/chat" method="post">
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
                    <div id="chatMovieId" class="well"></div>
                </div>

                <div class="input-group" style="margin-bottom:20px;">
                    <input placeholder="Message" class="form-control" type="text" style="width:50px;" name="message">
                    <input placeholder="MovieId (OPTIONAL)"  class="form-control" type="text" style="width:50px;" name="chatMovieTitle">
                    <button id="submitBtn" class="btn btn-success" type="submit">Send</button>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Message</th>
                        <th scope="col">User</th>
                        <th scope="col">Time</th>
                        <th scope="col">Movie</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Actors</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Chat> pl = (List<Chat>)session.getAttribute("chat");
                        if(pl!=null){
                            for(Chat p : pl){
                                String movieCheck=p.getMovie();
                    %>
                    <!-- table-success for, messages with movie -->
                    <tr <% if(movieCheck==null){ %>  class="table-light">  <% }else { %> class="table-success" <% } %>
                        <td ><%out.println(p.getId()); %></td>
                        <td><%out.println(p.getMessage());%></td>
                        <td><%out.println(p.getUser());%></td>
                        <% if(p.getMovie()!=null){ %>
                        <td><%out.println(p.getMovie());%></td>
                        <td><%out.println(p.getMovieRating());%></td>
                        <td><%out.println(p.getMovieActors());%></td>
                        <%  }   %>
                        <td><%out.println(p.getDate());%></td>

                    </tr>
                    <%
                                 }
                    }
                    %>
                    </tbody>
                </table>
                <div class="input-group" style="margin-bottom:20px;">
                    <input class="form-control" type="number" style="width:50px;" name="id">
                    <span class="input-group-btn">
						<button class="btn btn-success" type="submit">Delete</button>
				    </span>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript"> getMoviesChat2();  </script>
</body>
</html>
