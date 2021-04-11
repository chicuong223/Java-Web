/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.LaptopDAO;
import daos.SupplierDAO;
import dtos.ErrorObject;
import dtos.Laptop;
import dtos.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "InsertController", urlPatterns = {"/InsertController"})
public class InsertController extends HttpServlet {
    
    private static final String SUCCESS = "LaptopController";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "InsertController?action=insertform";

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
        if(action.equals("insertform")){
            SupplierDAO supDAO = new SupplierDAO();
            ArrayList<Supplier> supList = supDAO.getAllSuppliers();
            request.setAttribute("supListObj", supList);
            request.getRequestDispatcher("insertform.jsp").forward(request, response);
        }
        else if(action.equals("insert")){
            boolean valid = true;
            String url = ERROR;
            LaptopDAO dao = new LaptopDAO();
            String id = request.getParameter("id");
            String lapname = request.getParameter("lapName");
            String producer = request.getParameter("producer");
            int year = Integer.parseInt(request.getParameter("year"));
            String techinfo = request.getParameter("techInfo");
            String status = request.getParameter("status");
            String supplierid = request.getParameter("supplierID");
            Supplier sup = new SupplierDAO().getSupplierByID(supplierid);
            ErrorObject errorObj = new ErrorObject();
            if(id.trim().isEmpty()){
                errorObj.setIDError("ID can not be empty");
                valid = false;
            }
            if(lapname.trim().isEmpty()){
                errorObj.setNameError("Laptop name can not be empty");
                valid = false;
            }
            if(producer.trim().isEmpty()){
                errorObj.setProducerError("Producer can not be empty");
                valid = false;
            }
            if(techinfo.trim().isEmpty()){
                errorObj.setTechInfoError("Technical Information can not be empty");
                valid = false;
            }
            if(dao.getLapByID(id) != null){
                errorObj.setIDError("Duplicated ID");
                valid = false;
            }
            Laptop lap = new Laptop(id, lapname, techinfo, year, producer, status, sup);
            if(valid){
                if(dao.addLaptop(lap)){
                    url = SUCCESS;
                }
                else{
                    request.setAttribute("ERROR", "Insert Failed");
                }
            }
            else{
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
            request.getRequestDispatcher(url).forward(request, response);
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
