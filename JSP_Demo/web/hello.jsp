<%-- 
    Document   : hello
    Created on : 25 Jan 2021, 14:56:19
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
       
        <%! String name = "Keung"; %>
        
        <%!
            int calculateTotal(int a, int b){
                return a+b;
            }
        %>
        
        <h1>Hello <%=name%></h1>
        <h1>Total of 5 + 3 = <%=calculateTotal(5,3)%></h1>
        
        <%
            String n = request.getParameter("name");
            String ua = application.getInitParameter("useradmin");
            
            out.println("<h1>Hello from Scriptlet: " + n + ". Variable from web.xml: " + ua + "</h1>");
        %>
    </body>
</html>
