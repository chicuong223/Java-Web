<%-- 
    Document   : login
    Created on : 12-Mar-2021, 10:48:56
    Author     : chiuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a new computer</title>
    </head>
    <body>
        <h1>Add new computer information</h1>
        <form action="create" method="POST">
            <table>
                <tr>
                    <td>ID</td>
                    <td>
                        <input type="text" name="txtID" value="${param.txtID}"/>
                        <font color="red">${requestScope.INVALID.idError} </font>
                    </td>
                </tr>
                <tr>
                    <td>CPU</td>
                    <td>
                        <input type="text" name="txtCPU" value="${param.txtCPU}"/>
                        <font color="red">${requestScope.INVALID.cpuError}</font>
                    </td>
                </tr>
                 <tr>
                    <td>Hard Disk</td>
                    <td>
                        <input type="text" name="txtHardDisk" value="${param.HardDisk}"/>
                        <font color="red">${requestScope.INVALID.hardDiskError}</font>
                    </td>
                </tr>
                 <tr>
                    <td>RAM</td>
                    <td>
                        <input type="text" name="txtRAM" value="${param.txtRAM}"/>
                        <font color="red">${requestScope.INVALID.ramError}</font>
                    </td>
                </tr>
                 <tr>
                    <td>VGA</td>
                    <td>
                        <input type="text" name="txtVGA" value="${param.txtVGA}"/>
                        <font color="red">${requestScope.INVALID.vgaError}</font>
                    </td>
                </tr>
                 <tr>
                    <td>Monitor</td>
                    <td>
                        <input type="text" name="txtMonitor" value="${param.txtMonitor}"/>
                        <font color="red">${requestScope.INVALID.monitorError}</font>
                    </td>
                </tr>
                <tr>
                    <td>Room</td>
                    <td>
                        <select name="cboRoom">
                            <c:forEach items="${requestScope.listRooms}" var="dto">
                                <option>${dto.id} - ${dto.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Create"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
