<%-- 
    Document   : login
    Created on : 05-Feb-2021, 21:23:36
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
        <h1>Login</h1>
        <form action="LoginController" name="f1" method="POST">
            <table>
                <tr>
                    <th>Username</th>
                    <td><input type="text" name="username" value=""/></td>
                </tr>
                 <tr>
                    <th>Password</th>
                    <td><input type="password" name="password" value=""/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="action" value="login"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
