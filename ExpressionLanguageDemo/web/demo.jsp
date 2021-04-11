<%-- 
    Document   : demo
    Created on : 03-Mar-2021, 15:03:18
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
        Hello ${param["name"]} <br>
        2 + 1 = ${ (2+1)}
        
        <% session.setAttribute("user", "cuong");  %>
        <a href="2.jsp">Link to 2</a>
    </body>
</html>
