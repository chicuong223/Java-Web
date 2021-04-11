<%-- 
    Document   : update
    Created on : 13-Mar-2021, 17:20:17
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
        <title>Update Form</title>
    </head>
    <body>
        <h1>Update a Laptop</h1>
        <c:set var="lap" value="${lapObj}"/>
        <form action="UpdateController" method="POST">
            <input type="hidden" name="action" value="update"/>
            <table>
                <tr>
                    <th>ID</th>
                    <td><input type="text" name="id" value="${lap.id}" readonly/></td>
                </tr>
                <tr>
                    <th>Laptop Name</th>
                    <td><input type="text" name="lapName" value="${lap.laptopName}" required/></td>
                </tr>
                <tr>
                    <th>Producer</th>
                    <td><input type="text" name="producer" value="${lap.producer}" required</td>
                </tr>
                <tr>
                    <th>Year of Manufacture</th>
                    <td>
                        <select name="year">
                            <c:forEach begin="2000" end="${year}" var="y">
                                <option value="${y}" <c:if test="${y == lap.yearOfManufacture}">selected</c:if>>${y}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Technical Information</th>
                    <td><textarea name="techInfo">${lap.technicalInfo}</textarea></td>
                </tr>
                <tr>
                    <th>Supplier</th>
                    <td>
                        <select name="supplierID">
                            <c:forEach items="${supListObj}" var="sup">
                                <option value="${sup.supplierID}" <c:if test="${sup.supplierID eq lap.supplier.supplierID}">selected</c:if>>${sup.supplierName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>
                        <input type="radio" name="status" value="Available" id="avai" <c:if test="${lap.status eq 'Available'}">checked</c:if>/>
                        <label for="avai">Available</label>
                        <input type="radio" name="status" value="Out of Stock" id="oos"<c:if test="${lap.status eq 'Out of Stock'}">checked</c:if>/>
                        <label for="oos">Out of Stock</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Update"/>
                        <button><a href="LaptopController" style="color:black; text-decoration: none">Cancel</a></button>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
