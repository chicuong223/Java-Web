<%-- 
    Document   : itemlist
    Created on : 17-Mar-2021, 15:04:29
    Author     : chiuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items Management</title>
    </head>
    <body>
    <c:if test="${LOGINERROR != null}">
        <p>${LOGINERROR}</p>
    </c:if>
    <c:if test="${itemListObj != null}">
        <h1>LIST OF ALL ITEMS</h1>
        <table>
            <thead>
                <tr>
                    <th>Item Code</th>
                    <th>Item Name</th>
                    <th>Unit</th>
                    <th>Price</th>
                    <th>Supplying</th>
                    <th>Supplier Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${itemListObj}" var="item">
                    <tr>
                        <td>${item.itemCode}</td>
                        <td>${item.itemName}</td>
                        <td>${item.unit}</td>
                        <td>${item.price}</td>
                        <td>${item.supplying}</td>
                        <td>${item.sup.supName}</td>
                        <td><a href="UpdateServlet?action=updateForm&id=${item.itemCode}">Update</a></td>
                        <td><a href="DeleteServlet?id=${item.itemCode}" onclick="return confirm('Are you sure?')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="AddServlet">Add new Item</a> <br>
        <a href="LogoutServlet">Logout</a>
    </c:if>
    </body>
</html>
