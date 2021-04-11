<%-- 
    Document   : cart
    Created on : 28-Feb-2021, 17:36:23
    Author     : chiuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart Page</title>
        <style type="text/css">
            header{
                height: 120px;
                align-items: center;
            }
            
            .img{
                text-align: center;
                border: 1px solid green;
                padding: 5px;
                margin: 5px;
                height: 250px;
                width: 200px;
                float: left;
            }
            footer{
                clear: both;
                height: 120px;
                align-items: center
            }
        </style>
    </head>
    <body>
         <header>
            <img alt="LOGO" src="images/greenflower.png" width="150"/>
        </header>
        <section>
            <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                    <th>Option</th>
                </tr>
                <%
                    ArrayList<ShoppingCartItem> ldt = new ArrayList<>();
                    ldt = (ArrayList<ShoppingCartItem>) session.getAttribute("cart");
                    double total = 0;
                    if(ldt != null){
                        for (ShoppingCartItem dt : ldt) {
                                total = total + (dt.getProduct().getPrice() * dt.getQuantity());
                    %>
                    <tr>
                        <td><%=dt.getProduct().getId()%></td>
                        <td><%=dt.getProduct().getName()%></td>
                        <td><%=dt.getProduct().getPhoto()%></td>
                        <td><%=dt.getProduct().getPrice()%></td>
                        <td><%=dt.getQuantity()%></td>
                        <td><%=(dt.getProduct().getPrice() * dt.getQuantity())%></td>
                        <td align="center">
                            <a href="CartServlet?action=remove&id=<%=dt.getProduct().getId()%>" onclick="return confirm('Are you sure?')">Remove</a>
                        </td>
                    </tr>
                    <%}
                    }
                    else{%>
                    <tr>
                        <th colspan="7" align='left'><br/> Your cart is empty!</th>
                    </tr>
                    <%}%>
                    <tr>
                        <td colspan='6' align='right'>Total</td>
                        <td><%=total%></td>
                    </tr>
            </table>
        </section>
        <footer>
            <br><br>
            <a href='ProductServlet'>View Product List</a>
        </footer>
    </body>
</html>
