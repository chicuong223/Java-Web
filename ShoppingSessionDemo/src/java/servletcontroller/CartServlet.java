/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletcontroller;

import demo.dao.ProductDAO;
import demo.entities.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chiuy
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {
    
    public CartServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action == null){
            displayCart(request, response);
        }
        else{
            if(action.equalsIgnoreCase("buy")){
                buyItem(request, response);
            }
            else if(action.equalsIgnoreCase("remove")){
                removeItem(request, response);
            }
        }
    }
    
    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
    
    protected void removeItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        cart.remove(index);
        
        session.setAttribute("cart", cart);
        
        if(cart.size()<1) session.invalidate();
        
        response.sendRedirect("CartServlet");
    }
    
    protected void buyItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDAO proDAO = new ProductDAO();
        
        HttpSession session = request.getSession();
        
        if(session.getAttribute("cart") == null){
            List<ShoppingCartItem> cart = new ArrayList<>();
            cart.add(new ShoppingCartItem(proDAO.find(request.getParameter("id")), 1));
            session.setAttribute("cart", cart);
        }
        else{
            List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
            int index = isExisting(request.getParameter("id"), cart);
            if(index == -1){
                cart.add(new ShoppingCartItem(proDAO.find(request.getParameter("id")), 1));
            }
            else{
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("CartServlet");
    }
    
    private int isExisting(String id, List<ShoppingCartItem> cart){
        for(int i=0; i<cart.size(); i++){
            if(cart.get(i).getProduct().getId().equalsIgnoreCase(id)) return i;
        }
        return -1;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
}
