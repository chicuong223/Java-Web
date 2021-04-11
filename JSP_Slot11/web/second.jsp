<%-- 
    Document   : second
    Created on : 27-Jan-2021, 15:04:28
    Author     : chiuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Second</h1>
        <%String u = request.getParameter("user"); %>
        <%= u%>
    </body>
</html>
