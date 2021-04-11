<%-- 
    Document   : form
    Created on : 02-Feb-2021, 11:44:18
    Author     : chiuy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="daos.HumanTypeDAO"%>
<%@page import="dtos.HumanType"%>
<%@page import="dtos.Human"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Human h = new Human();
        String action = (String) request.getAttribute("action");
        h = (Human) request.getAttribute("humanObject");
        String header = (String) request.getAttribute("header");
        HumanTypeDAO typeDAO = new HumanTypeDAO();
        ArrayList<HumanType> typeList = new ArrayList<>();
        typeList = typeDAO.getAllTypes();
      %>
    <body>
        <h1><%=header%></h1>
        <form action="HumanManagementServlet" method="POST" name="f1">
            <input type="hidden" value="<%=action%>" name="action"/> 
           <table>
                <tr>
                    <th>ID</th>
                    <!--<td><input type="text" name="humanid" value="<%=h.getHumanid()%>"/></td>-->
                    <td>
                        <% 
                            if(action.equals("update")){
                            out.print("<input type=\"text\" name=\"humanid\" value=\"" + h.getHumanid() + "\" readonly/>");
                             }
                            else{
                                 out.print("<input type=\"text\" name=\"humanid\" value=\"" + h.getHumanid() + "\"/>");
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><input type="text" name="humanname" value="<%=h.getHumanname()%>"/></td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td>
                        <%
                            if(h.getHumangender().equals("m")){
                                out.println("<input type=\"radio\" id=\"male\" name=\"gender\" value=\"m\" checked/>");
                                out.println("<label for=\"male\">Male</label>");
                                out.println("<input type=\"radio\" id=\"female\" name=\"gender\" value=\"f\" />");
                                out.println("<label for=\"female\">Female</label>");
                            }
                            else if(h.getHumangender().equals("f")){
                                out.println("<input type=\"radio\" id=\"male\" name=\"gender\" value=\"m\"/>");
                                out.println("<label for=\"male\">Male</label>");
                                out.println("<input type=\"radio\" id=\"female\" name=\"gender\" value=\"f\"  checked/>");
                                out.println("<label for=\"female\">Female</label>");
                            }
                            else{
                                out.println("<input type=\"radio\" id=\"male\" name=\"gender\" value=\"m\"/>");
                                out.println("<label for=\"male\">Male</label>");
                                out.println("<input type=\"radio\" id=\"female\" name=\"gender\" value=\"f\"/>");
                                out.println("<label for=\"female\">Female</label>");
                            }
                           %>
                    </td>
                </tr>
                <tr>
                    <th>Date of Birth</th>
                    <td><input type="date" name="humandob" value="<%=h.getHumandob()%>"</td>
                </tr>
                <tr>
                    <th>Type</th>
                    <td>
                        <select name="humantypeid">
                            <%
                                for (HumanType elem : typeList) {%>
                                <option value="<%=elem.getTypeid()%>"><%=elem.getTypename()%></option>
                                <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="<%=action%>" />
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
