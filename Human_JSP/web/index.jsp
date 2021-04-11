<%-- 
    Document   : index
    Created on : 02-Feb-2021, 10:44:53
    Author     : chiuy
--%>

<%@page import="daos.HumanTypeDAO"%>
<%@page import="dtos.HumanType"%>
<%@page import="dtos.Human"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
                    ArrayList<Human> humanList = new ArrayList<>();
                    humanList = (ArrayList<Human>) request.getAttribute("data");
                    ArrayList<HumanType> typeList = new ArrayList<>();
                    HumanTypeDAO typeDAO = new HumanTypeDAO();
                    typeList = typeDAO.getAllTypes();
         %>
        <table border="1">
            <thead>
                <tr>
                    <th>Human ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Human elem : humanList) {%>
                    <tr>
                        <td><%=elem.getHumanid()%></td>
                        <td><%=elem.getHumanname()%></td>
                        <%
                            if(elem.getHumangender().equals("m")) out.println("<td>Male</td>");
                            else out.println("<td>Female</td>");
                         %>
                         <td><%=elem.getHumandob()%></td>
                        <%  
                            for (HumanType type : typeList) {
                                    if(elem.getHumantypeid().equals(type.getTypeid())){%>
                                       <td><%=type.getTypename()%></td>
                                    <%}%>
                            <%}%>
                            <td><a href="HumanManagementServlet?action=updateForm&hid=<%=elem.getHumanid()%>">Update</a></td>
                            <td><a href="HumanManagementServlet?action=delete&hid=<%=elem.getHumanid()%>">Delete</a></td>
                    </tr>
                    <%}%>
            </tbody>
        </table>
        <a href="HumanManagementServlet?action=insertForm">Insert a Human</a>
    </body>
</html>
