<%-- 
    Document   : computerform
    Created on : 12-Mar-2021, 10:48:33
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
        <h1>login for staff member</h1>
        <font color="red">${requestScope.ERRORNOTLOGIN}</font>
        <font color="red">${requestScope.ERROR}</font>
        <form action="login" method="POST">
            <table>
                <tr>
                    <th>Username</th>
                    <td><input type="text" name="txtUsername" value="${param.txtUsername}"/></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td><input type="password" name="txtPassword"/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
