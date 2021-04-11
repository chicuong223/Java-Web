<%-- 
    Document   : JSTL_demo
    Created on : 05-Mar-2021, 15:09:52
    Author     : chiuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="i" value="${2000*2}"></c:set>
        <c:out value="${i}"></c:out>
        
        <c:forEach var="k" begin="1" end="1000000" step="10000">
            <c:out value="${k}"></c:out>
        </c:forEach>
    </body>
</html>
