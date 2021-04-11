<%-- 
    Document   : bookForm
    Created on : 05-Feb-2021, 22:06:24
    Author     : chiuy
--%>

<%@page import="java.time.Year"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAOS.CategoryDAO"%>
<%@page import="DTOS.Category"%>
<%@page import="DTOS.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String action = (String) request.getAttribute("action");
    Book book = (Book) request.getAttribute("bookObject");
    CategoryDAO catDAO = new CategoryDAO();
    ArrayList<Category> catList = catDAO.getAllCategories();
    String username = (String) getServletContext().getAttribute("username");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=action%></title>
    </head>
    <body>
        <%
            if(username == null){%>
            <h1>Please login to continue</h1>
            <form action="LoginController">
                <input type="submit" value="Login"/>
            </form>
        <%}
        else{%>
        <h1><%=action%> a Book</h1>
        <form action="BookController" name="f2" method="POST">
            <table>
                <tr>
                    <th>ID</th>
                    <td><input type="text" name="bookID" value="<%=book.getBookID()%>" required id="ID" <%
                               if(action.equals("Update")) out.print("readonly");
                               %>/></td>
                </tr>
                 <tr>
                    <th>Name</th>
                    <td><input type="text" name="name" value="<%=book.getName()%>" required id="name"/></td>
                </tr>
                 <tr>
                    <th>Author</th>
                    <td><input type="text" name="author" value="<%=book.getAuthor()%>" required id="author"/></td>
                </tr>
                 <tr>
                    <th>Year of Publication</th>
                    <td>
                        <select name="year" id="year">
                            <%
                                for(int i=1900; i<=Year.now().getValue(); i++){%>
                                <option value="<%=i%>" <%
                                        if(book.getYear() == i) out.print("selected=\"selected\"");
                                        %>><%=i%></option>
                                <%}%>
                        </select>
                    </td>
                </tr>
                 <tr>
                    <th>Short Description</th>
                    <td><textarea name="description" id="desc"><%=book.getDescription()%></textarea></td>
                </tr>
                 <tr>
                    <th>Status</th>
                    <td>
                        <input type="radio" name="status" value="Available"  id="avai" required <%
                               if(book.getStatus().equals("Available")) {
                                   out.print("checked");
                           }%>/>
                        <label for="avai">Available</label> </br>
                        <input type="radio" name="status" value="Sold" id="sold" required <%
                               if(book.getStatus().equals("Sold")) {
                                   out.print("checked");
                           }%>/>
                         <label for="sold">Sold</label>
                    </td>
                </tr>
                 <tr>
                    <th>Category</th>
                    <td>
                        <select name="categoryID" id="category">
                            <%
                                for (Category elem : catList) {%>
                                <option class="catOption" value="<%=elem.getCategoryID()%>" <%
                                    if(elem.getCategoryID().equals(book.getCategoryID())) out.print("selected=\"selected\"");
                                %>><%=elem.getCategoryName()%></option>
                                  <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="<%=action%>" name="action" id="action"/>
                        <button id="clearBtn" type="button">Clear</button>
                        <button><a  style="text-decoration: none; color: #000000" href="BookController?action=login">Cancel</a></button>
                    </td>
                </tr>
            </table>
        </form>
       <script defer>
            const clearBtn = document.getElementById("clearBtn");
            const action = document.getElementById("action");
            clearBtn.addEventListener('click', event =>{
                if("Update".localeCompare(String(action.value)) !== 0){
                    document.getElementById("ID").value = "";
                }
                document.getElementById("name").value = "";
                document.getElementById("author").value = "";
                document.getElementById("year").value = 1900;
                document.getElementById("category").value = document.getElementsByClassName("catOption")[0].value;
                document.getElementById("avai").checked = false;
                document.getElementById("sold").checked = false;
                document.getElementById("desc").value = "";
            });
        </script>
        <%}%>
    </body>
</html>
