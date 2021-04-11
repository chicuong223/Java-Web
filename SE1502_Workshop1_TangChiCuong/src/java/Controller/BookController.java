/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOS.BookDAO;
import DTOS.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chiuy
 */
public class BookController extends HttpServlet {
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
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        
//        String username = (String )request.getAttribute("username");
        PrintWriter out = response.getWriter();
//        out.print("Hello " + username);
//        out.print("Hello " + username);
        String action = (String) request.getParameter("action");
//        PrintWriter out = response.getWriter();
//        out.println(action);
        if(action == null || action.equals("login")){
            BookDAO dao = new BookDAO();
            ArrayList<Book> books = new ArrayList<>();
            books = dao.getAllBooks();
            request.setAttribute("bookList", books);
            RequestDispatcher rd = request.getRequestDispatcher("bookList.jsp");
            rd.forward(request, response);
        }
        else if(action.equals("delete")){
            if(getServletContext().getAttribute("username")==null){
                response.sendRedirect("BookController");
            }
            else{
                BookDAO dao = new BookDAO();
                String bookId = request.getParameter("bid");
                dao.deleteBook(bookId);
                response.sendRedirect("BookController");
            }
        }
        else if(action.equals("insertBookForm")){
            Book b = new Book();
            request.setAttribute("bookObject", b);
            request.setAttribute("action", "Create");
            RequestDispatcher rd = request.getRequestDispatcher("bookForm.jsp");
            rd.forward(request, response);
        }
        else if(action.equals("Create")){
             String bookID = request.getParameter("bookID");
             String name = request.getParameter("name");
             String author = request.getParameter("author");
             String description = request.getParameter("description");
             int year= Integer.parseInt(request.getParameter("year"));
             String status = request.getParameter("status");
             String categoryID = request.getParameter("categoryID");
             BookDAO dao = new BookDAO();
//             Book book = dao.getBookByID(bookID);
             Book newBook = new Book(bookID, name, author, year, description, status, categoryID);
//             BookDAO dao = new BookDAO();
             boolean create = dao.createBook(newBook);
             if(create == false) {
                 response.sendRedirect("error.html");
             }
             else{
                 RequestDispatcher rd = request.getRequestDispatcher("BookController?action=login");
                 rd.forward(request, response);
             }
         }
         else if(action.equals("updateForm")){
             String bookID = request.getParameter("bid");
             BookDAO dao = new BookDAO();
             Book updateBook = dao.getBookByID(bookID);
             request.setAttribute("bookObject", updateBook);
             request.setAttribute("action", "Update");
             RequestDispatcher rd = request.getRequestDispatcher("bookForm.jsp");
             rd.forward(request, response);
         }
        else if(action.equals("Update")){
             String bookID = request.getParameter("bookID");
             String name = request.getParameter("name");
             String author = request.getParameter("author");
             String description = request.getParameter("description");
             int year= Integer.parseInt(request.getParameter("year"));
             String status = request.getParameter("status");
             String categoryID = request.getParameter("categoryID");
             
             Book updateBook = new Book(bookID, name, author, year, description, status, categoryID);
             BookDAO dao = new BookDAO();
             dao.updateBook(updateBook);
             request.getRequestDispatcher("BookController?action=login").forward(request, response);
         }
        else if(action.equals("logout")){
            ServletContext ctx = getServletContext();
            ctx.removeAttribute("username");
            response.sendRedirect("LoginController");
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
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
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
