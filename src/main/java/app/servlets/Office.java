package app.servlets;

import database.config.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/getdata")
public class Office extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");

        if (authenticated == null || !authenticated) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            super.service(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            request.setCharacterEncoding("UTF-8");
            try {
                response.setContentType("text/html");
                ArrayList<String> employers = new ArrayList<>();
                    try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM employers ORDER BY fullname");
                        while(resultSet.next()){
                            employers.add(resultSet.getString(2));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("employers", employers);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
                    getServletContext().getRequestDispatcher("/Chart.jsp").forward(request, response);
                }

                catch (Exception e) {
                    e.printStackTrace();
                }
    }
}