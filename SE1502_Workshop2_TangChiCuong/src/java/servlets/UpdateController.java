/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.LaptopDAO;
import daos.SupplierDAO;
import dtos.Laptop;
import dtos.Supplier;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chiuy
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

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
        String action = request.getParameter("action");
        if(action.equals("updateForm")){
            String id = request.getParameter("id");
            LaptopDAO lapDAO = new LaptopDAO();
            Laptop lap = lapDAO.getLapByID(id);
            if(lap == null){
                request.setAttribute("ERROR", "This Laptop ID was not found");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            else{
                SupplierDAO supDAO =new SupplierDAO();
                ArrayList<Supplier> supLst = supDAO.getAllSuppliers();
                request.setAttribute("supListObj", supLst);
                request.setAttribute("lapObj", lap);
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }
        }
        else if(action.equals("update")){
            LaptopDAO dao = new LaptopDAO();
            String id = request.getParameter("id");
            String lapname = request.getParameter("lapName");
            String producer = request.getParameter("producer");
            int year = Integer.parseInt(request.getParameter("year"));
            String techinfo = request.getParameter("techInfo");
            String status = request.getParameter("status");
            String supplierid = request.getParameter("supplierID");
            Supplier sup = new SupplierDAO().getSupplierByID(supplierid);
            Laptop lap = new Laptop(id, lapname, techinfo, year, producer, status, sup);
            dao.updateLaptop(lap);
            response.sendRedirect("LaptopController");
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
