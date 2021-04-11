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
@WebServlet(name = "simpleFormDemo", urlPatterns = {"/simpleFormDemo"})
public class simpleFormDemo extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        String fname = request.getParameter("firstName");   //get parameter from firstName input tag
        String lname = request.getParameter("lastName");    //get parameter from lastName input tag
        String[] fav = request.getParameterValues("fav");
        
        out.println("<h1>Using GET</h1>");
        out.println("<h1> You have entered values: </h1>");
        out.println("First Name: " + fname + "<br>");
        out.println("Last Name: " + lname + "<br>");
        for (String string : fav) {
            out.println(string + " ");
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
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        String fname = request.getParameter("firstName");   //get parameter from firstName input tag
        String lname = request.getParameter("lastName");    //get parameter from lastName input tag
        String[] fav = request.getParameterValues("fav");

        
        out.println("<h1>Using POST</h1>");
        out.println("<h1> You have entered values: </h1>");
        out.println("First Name: " + fname + "<br>");
        out.println("Last Name: " + lname + "<br>");
        for (String string : fav) {
            out.println(string + " ");
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
