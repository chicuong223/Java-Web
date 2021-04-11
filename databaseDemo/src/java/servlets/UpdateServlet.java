/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.ProductDAO;
import dtos.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chiuy
 */
public class UpdateServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            String proID = request.getParameter("pid");
            
            ProductDAO dao = new ProductDAO();
            Product p = dao.getProductById(proID);
            
            out.println("<form action=\"UpdateServlet\" method=\"post\" name=\"f1\">");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Product ID: </td>");
            out.println("<td><input type=\"text\" name=\"id\" value=\"" + p.getId() + "\" readonly/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Name: </td>");
            out.println("<td><input type=\"text\" name=\"name\" value=\"" + p.getProName() +"\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Description: </td>");
            out.println("<td><input type=\"text\" name=\"description\" value=\"" + p.getProDesc() + "\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Quantity: </td>");
            out.println("<td><input type=\"text\" name=\"quantity\" value=\"" + p.getQuantity() + "\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Price: </td>");
            out.println("<td><input type=\"text\" name=\"price\" value=\"" + p.getPrice() + "\"/></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>ImageURL: </td>");
            out.println("<td><input type=\"text\" name=\"url\" value=\"" + p.getImageURL() + "\"/></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<input type=\"submit\" value=\"Update\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String desc = request.getParameter("description");
            String quantity = request.getParameter("quantity");
            String price = request.getParameter("price");
            String url = request.getParameter("url");
            
            //tạo object Product
            Product p = new Product(id, name, desc, Integer.parseInt(quantity), Double.parseDouble(price), url);
            
            //gọi tới lớp xử lí CSDL và gọi hàm update
            ProductDAO dao = new ProductDAO();
            if(dao.updateProduct(p)){
                response.sendRedirect("ProductListServlet");
            }
            else{
                response.sendRedirect("error.html");
            }
        } 
        catch (Exception e) {
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
