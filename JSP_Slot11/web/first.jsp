<%-- 
    Document   : first
    Created on : 27-Jan-2021, 15:00:49
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
        <h1>Hello World!</h1>
        <jsp:forward page="second.jsp">
            <jsp:param name="user" value="Admin" />
            <jsp:param name="gender" value="male" />
        </jsp:forward>
    </body>
</html>
