/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chiuy
 */
@WebServlet(name = "formDemo", urlPatterns = {"/formDemo"})
public class formDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String bio = request.getParameter("bio");
        String job = request.getParameter("job");
        String[] interests = request.getParameterValues("int");
        
        out.println("Name: " + name + "<br>");
        out.println("Email: " + email + "<br>");
        out.println("Age: " + age);
        out.println("<p>" + bio + "</p>");
        out.println("Job: " + job +"<br><br>");
        out.println("Interests:");
        out.println("<ul>");
        for (String string : interests) {
            out.println("<li>" + string + "</li>");
        }
        out.println("</ul>");
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
