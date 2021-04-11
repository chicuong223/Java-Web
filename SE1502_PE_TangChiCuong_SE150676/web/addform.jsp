<%-- 
    Document   : addform
    Created on : 17-Mar-2021, 15:35:25
    Author     : chiuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item</title>
    </head>
    <body>
        <h1>ADD NEW ITEM</h1>
        <form action='AddServlet' method="POST">
            <input type="hidden" name="action" value="add"/>
            <table>
                <tr>
                    <td>Item Code</td>
                    <td><input type="text" name="itemCode" required/></td>
                </tr>
                <tr>
                    <td>Item Name</td>
                    <td><input type="text" name="itemName" required/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="number" name="price" required/></td>
                </tr>
                <tr>
                    <td>Unit</td>
                    <td><input type="text" name="unit" required/></td>
                </tr>
                <tr>
                    <td>Supplying</td>
                    <td><input type="checkbox" value="true" name="supplying"/></td>
                </tr>
                <tr>
                    <td>Supplier Name</td>
                    <td>
                        <select name="supCode">
                            <c:forEach items="${supListObj}" var="sup">
                                <option value="${sup.supCode}">${sup.supName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save item"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
