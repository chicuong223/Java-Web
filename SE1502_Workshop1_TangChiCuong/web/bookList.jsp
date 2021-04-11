<%-- 
    Document   : bookList
    Created on : 05-Feb-2021, 21:26:24
    Author     : chiuy
--%>

<%@page import="DTOS.Account"%>
<%@page import="DTOS.Category"%>
<%@page import="DAOS.CategoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTOS.Book"%>
<%@page import="java.sql.Array"%>
<%@page import="DAOS.BookDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Manager</title>
        <link rel="stylesheet" href="styles.css">
        
    </head>
    <body>
        <%
            Account username = (Account) request.getAttribute("username");
            CategoryDAO catDAO = new CategoryDAO();
            ArrayList<Category> catList =catDAO.getAllCategories();
            ArrayList<Book> bookList = (ArrayList<Book>) request.getAttribute("bookList");
            BookDAO bookDAO = new BookDAO();
        %>
        <%if(username == null){%>
        <h1>Please login to continue</h1>
        <form action="LoginController">
            <input type="submit" value="Login"/>
        </form>
        <%}
            else{
        %>
                <h1>Hello <%=username%></h1>
                <div id="navigation">
                    <h3>Choose a category</h3>
                    <%
                    for (Category cat : catList) {%>
                    <button class="catBtn" value="<%=cat.getCategoryName()%>"><%=cat.getCategoryName()%></button>
                    <%}%>
                </div>
                <div id="lst" style="margin:5%">
                <%
                    for (Category cat : catList) {%>
                    <div id="<%=cat.getCategoryName()%>" class="cat">
                        <h2><%=cat.getCategoryName()%></h4>
                        <% ArrayList<Book> bookByCat = bookDAO.getBooksByCategory(cat.getCategoryID());%>
                        <table border="1"<%
                            if(bookByCat.size() <1) {%>style="display:none">
                            <p style="font-size: 120%">Nothing to show</p>
                            <%}%>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Author</th>
                                    <th>Year of Publication</th>
                                    <th>Short Description</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (Book book : bookByCat) {%>
                                <tr>
                                    <td><%=book.getBookID()%></td>
                                    <td><%=book.getName()%></td>
                                    <td><%=book.getAuthor()%></td>
                                    <td><%=book.getYear()%></td>
                                    <td><%=book.getDescription()%></td>
                                    <td><%=book.getStatus()%></td>
                                    <td><a href="BookController?action=delete&bid=<%=book.getBookID()%>" onclick="return confirm('Are you sure ?')">Delete</a></td>
                                    <td><a href="BookController?action=updateForm&bid=<%=book.getBookID()%>">Update</a></td>
                                </tr>
                            <%}%>       
                            </tbody>
                        </table>
                    </div>        
                    <%}%>
                </div>
                <a href="BookController?action=insertBookForm">Insert a book</a> <br>
                <button><a href="BookController?action=logout" style="text-decoration: none; color:#000000" />Logout</a></button>
    <%}%>
    <script src="script.js" defer></script>
    </body>
</html>
