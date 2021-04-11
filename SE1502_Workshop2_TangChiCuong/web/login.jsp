<%-- 
    Document   : login
    Created on : 13-Mar-2021, 13:17:40
    Author     : chiuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <font color="red">${requestScope.ERRORNOTLOGIN}</font> <br>
        <font color="red">${requestScope.ERROR}</font>
        <form action="LoginController" method="POST">
            <table>
                <tr>
                    <th>Username</th>
                    <td><input type="text" name="username" value="${param.username}"/></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
