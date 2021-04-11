package fu.demo.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("link.html").include(request, response);

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (password.equals("123")) {
            out.print("You are successfully logged in!");
            out.print("<br>Welcome, " + name);

            Cookie ck = new Cookie("name", name);  /// Tạo đối tượng Cookie ck
            response.addCookie(ck); // Response lại cho Client đối tượng Cookie ck này
        } else {
            out.print("sorry, username or password error!");
            request.getRequestDispatcher("login.html").include(request, response);
        }

        out.close();
    }

}
