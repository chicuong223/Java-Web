/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
public class CalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double f;
        double s;
        String op;
        double r;
        if (request.getAttribute("first") == null && request.getAttribute("second") == null && request.getAttribute("operator")==null && request.getAttribute("result")==null){
                f=0;
                s=0;
                op="+";
                r=0;
        }
            else{
                f= (double) request.getAttribute("first");
                s= (double) request.getAttribute("second");
                op= (String) request.getAttribute("operator");
                r= (double) request.getAttribute("result");
            }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"CalServlet\" method=\"POST\" name=\"Calculator\">");
            
            out.println("First: <input type='number' name='first' value='" + f + "' /><br/>");
            out.println("Second: <input type='number' name='second' value='" + s + "' /><br/>");
            out.println("Operator: <select name='operator' value='" + op + "'>");
            out.println("<option>+</option>");
            out.println("<option>-</option>");
            out.println("<option>*</option>");
            out.println("<option>/</option>");
            out.println("</select><br/>");
            out.println("<input type='submit' value='Compute'/><br/>");
            out.println("Result: <input type='text' name='result' value='" + r + "'/><br/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        double result = 0;
        double first = Double.parseDouble(request.getParameter("first"));
        double second = Double.parseDouble(request.getParameter("second"));
        String operant = request.getParameter("operator");
        switch(operant){
            case "+":
                result=first+second;
                break;
            case "-":
                result=first-second;
                break;
            case "*":
                result=first*second;
                break;
            case "/":
                if(second==0){
                    result=Double.POSITIVE_INFINITY;
                }
                else{
                    result=first/second;
                }
                break;
        }
        
        request.setAttribute("first", first);
        request.setAttribute("second", second);
        request.setAttribute("operator", operant);
        request.setAttribute("result", result);
        processRequest(request, response);
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
