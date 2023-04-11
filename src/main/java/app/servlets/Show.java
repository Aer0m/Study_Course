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

@WebServlet("/show")
public class Show extends HttpServlet {
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
            String county = request.getParameter("county");
            String neigh = request.getParameter("neigh");
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                Statement statement = connection.createStatement();
                ResultSet resultSet;
                if(neigh=="") {
                    resultSet = statement.executeQuery("SELECT employers.fullname FROM employers \n" +
                            "JOIN addresses ON addresses.id = employers.id_address\n" +
                            "WHERE addresses.county = '"+county+"';");
                }
                else {
                    resultSet = statement.executeQuery("SELECT employers.fullname FROM employers \n" +
                            "JOIN addresses ON addresses.id = employers.id_address\n" +
                            "WHERE addresses.county = '" + county + "' \n" +
                            "AND addresses.neighbourhood = '" + neigh + "';");
                }
                while (resultSet.next()){

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}