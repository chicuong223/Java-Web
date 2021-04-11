/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.ComputerDAO;
import dtos.ComputerDTO;
import dtos.ComputerErrorObject;
import dtos.RoomDTO;
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
public class CreateController extends HttpServlet {

    private static final String SUCCESS = "LoadListComputerController";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "LoadRoomController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("txtID");
            String cpu = request.getParameter("txtCPU");
            String vga = request.getParameter("textVGA");
            String ram =request.getParameter("txtRAM");
            String hardDisk = request.getParameter("txtHardDisk");
            String monitor = request.getParameter("txtMonitor");
            String room = request.getParameter("cboRoom");
            boolean valid = true;
            ComputerErrorObject errorObj = new ComputerErrorObject();
            if(id.trim().isEmpty()){
                errorObj.setIdError("ID is not supposed to be empty");
                valid = false;
            }
            if(!id.trim().isEmpty() && !id.matches("[0-9]{2}")){
                errorObj.setIdError("Computer ID must be numberical, 2 digits");
                valid = false;
            }
            if(cpu.trim().isEmpty()){
                errorObj.setCpuError("CPU is not supposed to be empty");
                valid = false;
            }
            if(!cpu.trim().isEmpty() && cpu.length()<6){
                errorObj.setCpuError("CPU is greater than 6 characters");
                valid = false;
            }
            if(vga.trim().isEmpty()){
                errorObj.setCpuError("VGA is not supposed to be empty");
                valid = false;
            }
            if(ram.trim().isEmpty()){
                errorObj.setCpuError("RAM is not supposed to be empty");
                valid = false;
            }
            if(hardDisk.trim().isEmpty()){
                errorObj.setCpuError("Hard disk is not supposed to be empty");
                valid = false;
            }
            if(monitor.trim().isEmpty()){
                errorObj.setCpuError("Monitor is not supposed to be empty");
                valid = false;
            }
            ComputerDAO dao = new ComputerDAO();
            if(dao.getComputerByID(id) != null){
                errorObj.setIdError("ID is existed");
                valid = false;
            }
            RoomDTO roomToDB = new RoomDTO(room.split("-")[0].trim(), room.split("-")[1].trim(), "", 0);
            ComputerDTO computer = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, roomToDB);
            if(valid){
                if(dao.insert(computer)){
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
        } 
        catch (Exception e) {
            log("ERROR at CreateController: " + e.getMessage());
        }
        finally{
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
