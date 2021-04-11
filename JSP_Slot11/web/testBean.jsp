<%-- 
    Document   : testBean
    Created on : 27-Jan-2021, 15:20:32
    Author     : chiuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="javaBean.Employee"%>--%>

<jsp:useBean class="javaBean.Employee" id="emp" scope="request" />
<jsp:setProperty name="emp" property="*" />  <%--all properties of bean object will be accessed using the name"emp --%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test JavaBean JSP</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:setProperty name="emp" property="empNo" value="1"/>
        <jsp:setProperty name="emp" property="empName" value="Cuong"/>
        
       <jsp:getProperty name="emp" property="empNo"/>
       <jsp:getProperty name="emp" property="empName"/>
        <%--
            Employee emp = new Employee();
            emp.setEmpName("Cuong");
            emp.setEmpNo("1");
            out.print(emp.getEmpNo() +" ");
            out.print(emp.getEmpName());
        --%>
        
    </body>
</html>
