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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author chiuy
 */
@WebServlet(name = "ProductListServlet_UploadImages", urlPatterns = {"/ProductListServlet_UploadImages"})

@MultipartConfig(
        fileSizeThreshold = 10*1024*1024,
        maxFileSize = 1024*1024*50,
        maxRequestSize = 1024 * 1024 * 100
)

public class ProductListServlet_UploadImages extends HttpServlet {
    
    private static final String UPLOAD_DIR = "images";


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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String url = uploadFile(request);
        
        ProductDAO dao = new ProductDAO();
        Product p = new Product(id, name, description, Integer.parseInt(quantity), Double.parseDouble(price), url);
        
        boolean kq = dao.createProduct(p);
        request.setAttribute("product", p);
        request.getRequestDispatcher("UploadResultServlet").forward(request, response);
    }
    
    private String uploadFile(HttpServletRequest request) throws IOException, ServletException{
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String)getFileName(filePart);
            
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes, 0, read);
                }
            } 
            catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            }
            finally{
                if(inputStream != null) inputStream.close();
                if(outputStream != null) outputStream.close();
            }
        }
        catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }
    
    private String getFileName(Part part){
        for (String content : part.getHeader("content-disposition").split(";")) {
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf('=')+1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
