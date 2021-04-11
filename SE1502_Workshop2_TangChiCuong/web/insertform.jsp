<%-- 
    Document   : insertform
    Created on : 13-Mar-2021, 21:31:42
    Author     : chiuy
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="year" value="${now}" pattern="yyyy"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Form</title>
    </head>
    <body>
        <h1>Add a new Laptop</h1>
        <form action="InsertController" method="POST">
            <input type="hidden" name="action" value="insert"/>
            <table>
                <tr>
                    <th>ID</th>
                    <td>
                        <input type="text" name="id"/>
                        <font color="red">${requestScope.INVALID.IDError}</font>
                    </td>
                </tr>
                <tr>
                    <th>Laptop Name</th>
                    <td>
                        <input type="text" name="lapName" />
                        <font color="red">${requestScope.INVALID.nameError}</font>
                    </td>
                </tr>
                <tr>
                    <th>Producer</th>
                    <td>
                        <input type="text" name="producer"/>
                        <font color="red">${requestScope.INVALID.producerError}</font>
                    </td>
                </tr>
                <tr>
                    <th>Year of Manufacture</th>
                    <td>
                        <select name="year">
                            <c:forEach begin="2000" end="${year}" var="y">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Technical Information</th>
                    <td>
                        <textarea name="techInfo"></textarea>
                        <font color="red">${requestScope.INVALID.techInfoError}</font>
                    </td>
                </tr>
                <tr>
                    <th>Supplier</th>
                    <td>
                        <select name="supplierID">
                            <c:forEach items="${supListObj}" var="sup">
                                <option value="${sup.supplierID}">${sup.supplierName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>
                        <input type="radio" name="status" value="Available" id="avai" checked/>
                        <label for="avai">Available</label>
                        <input type="radio" name="status" value="Out of Stock" id="oos"/>
                        <label for="oos">Out of Stock</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Insert"/>
                        <button><a href="LaptopController" style="color:black; text-decoration: none">Cancel</a></button>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
