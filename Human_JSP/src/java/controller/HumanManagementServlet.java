/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.HumanDAO;
import daos.HumanTypeDAO;
import dtos.Human;
import dtos.HumanType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chiuy
 */
public class HumanManagementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String action = request.getParameter("action");
        String index = "index.jsp";
        String form = "form.jsp";
        String controller = "HumanManagementServlet";
        PrintWriter out = response.getWriter();
        HumanDAO dao = new HumanDAO();
         
//        out.println(humanList);
        
        if(action == null){
           ArrayList<Human> humanList = new ArrayList<>();
            humanList = dao.getAllHumans();
            request.setAttribute("data", humanList);
            RequestDispatcher rd = request.getRequestDispatcher(index);
            rd.forward(request, response);
        }
        else{
            switch(action){
                case "insertForm":
                    Human h = new Human();
                    request.setAttribute("humanObject", h);
                    request.setAttribute("header", "Create a human");
                    request.setAttribute("action", "Create");
                    RequestDispatcher rd = request.getRequestDispatcher(form);
                    rd.forward(request, response);
                    break;
                case "Create":
                    String id = request.getParameter("humanid");
                    String name = request.getParameter("humanname");
                    String gender = request.getParameter("gender");
                    String dob = request.getParameter("humandob");
                    String typeid = request.getParameter("humantypeid");
                    Human newHuman = new Human(id, name, gender, dob, typeid);
                    boolean addHuman = dao.addHuman(newHuman);
                    response.sendRedirect(controller);
//                    out.println(action);
                    break;
                case "delete":
                    String hid = request.getParameter("hid");
                    dao.deleteHuman(hid);
                    response.sendRedirect(controller);
                    break;
                case "updateForm":
                    id = request.getParameter("hid");
                    Human human = dao.getHumanByID(id);
                    request.setAttribute("humanObject", human);
                    request.setAttribute("header", "Update Human");
                    request.setAttribute("action", "update");
                    rd = request.getRequestDispatcher(form);
                    rd.forward(request, response);
                    break;
                case "update":
                    id = request.getParameter("humanid");
                    name = request.getParameter("humanname");
                    gender = request.getParameter("gender");
                    dob = request.getParameter("humandob");
                    typeid = request.getParameter("humantypeid");
                    Human updateHuman = new Human(id, name, gender, dob, typeid);
                    dao.updateHuman(updateHuman);
                    response.sendRedirect(controller);
                    break;
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HumanManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HumanManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
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
