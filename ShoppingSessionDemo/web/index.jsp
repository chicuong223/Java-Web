<%-- 
    Document   : index
    Created on : 28-Feb-2021, 17:36:34
    Author     : chiuy
--%>

<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
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
            <!--<img alt="LOGO" src="images/greenflower.png" width="150"/>-->
            <img src="${pageContext.request.contextPath}/images/greenflower.png" width="150"/>
        </header>
        <section>
            <c:forEach var="product" items="${products}">
                <div class="img">
                    ${product.id} - ${product.name} <br>
                    <img src="${pageContext.request.contextPath}/images/${product.photo}" width="120"/> <br>
                    <a href="CartServlet?action=buy&id=${product.id}">Buy</a>
                </div>
            </c:forEach>
        </section>
        
        <footer>
            <br><br>
            <a href="CartServlet">View Shopping Cart</a>
        </footer>
    </body>
</html>
