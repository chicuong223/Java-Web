<%-- 
    Document   : laptoplist
    Created on : 13-Mar-2021, 10:37:32
    Author     : chiuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laptops List</title>
    </head>
    <body>
        <h1>Welcome ${sessionScope.USER.name}</h1>
        <h1>Laptops Management</h1>
        <c:forEach items="${requestScope.supList}" var="sup">
            <h2>${sup.supplierName}</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Producer</th>
                        <th>Year of Manufacture</th>
                        <th>Technical Information</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.lapList}" var="lap">
                    <c:if test="${lap.supplier.supplierID eq sup.supplierID}">
                        <tr>
                            <td>${lap.id}</td>
                            <td>${lap.laptopName}</td>
                            <td>${lap.producer}</td>
                            <td>${lap.yearOfManufacture}</td>
                            <td>${lap.technicalInfo}</td>
                            <td>${lap.status}</td>
                            <td><a href="DeleteController?id=${lap.id}" onclick="return confirm('Do you want to delete this ID ?\nLaptop ID = ${lap.id}')">Delete</a></td>
                            <td><a href="UpdateController?action=updateForm&id=${lap.id}">Update</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </c:forEach>
        <a href="InsertController?action=insertform">Insert a new Laptop</a> <br>
        <button><a href="LogoutController" style="text-decoration: none; color:black">Logout</a></button>
    </body>
</html>
