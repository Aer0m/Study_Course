package app.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/")
public class AuthentificationFilter extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");

        if ((request.getRequestURI().endsWith(".css")) || (request.getContextPath().endsWith(".jpg"))) {
            super.service(request, response); // пропустить запрос к файлу CSS request.GetPathInfo
            return;
        }

        if (authenticated == null || !authenticated) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/getdata");
        }
    }
}
