<%--
  Created by IntelliJ IDEA.
  User: liorm
  Date: 09/08/2021
  Time: 01:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Error Message</h2>
    <%=  request.getAttribute("errormessage").toString() %>
</body>
</html>
