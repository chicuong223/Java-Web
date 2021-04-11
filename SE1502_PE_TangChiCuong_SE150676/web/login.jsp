<%-- 
    Document   : login
    Created on : 17-Mar-2021, 14:26:18
    Author     : chiuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
         <c:if test="${LOGINERROR != null}">
             <p style="color:red">Username or Password is invalid</p>
         </c:if>
        <form action="LoginServlet" method="POST"/>
        <table>
            <tr>
                <td>Username: </td>
                <td><input type="text" name="username"/>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password"/>
            </tr>
            <tr>
                <td><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </body>
</html>
