<%-- 
    Document   : 2
    Created on : 03-Mar-2021, 15:07:34
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
        Hello ${sessionScope.user}
        
        <%
            Cookie ck = new Cookie("name", "Chi-Cuong");
            response.addCookie(ck);
            %>
            <a href="3.jsp">Link to 3</a>
    </body>
</html>
