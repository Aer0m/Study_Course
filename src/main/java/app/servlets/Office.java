package app.servlets;

import database.config.DbConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/getdata")
public class Office extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
                response.setContentType("text/html");
                ArrayList<String> employers = new ArrayList<>();
                try{
                    try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM employers ORDER BY fullname");
                        while(resultSet.next()){
                            employers.add(resultSet.getString(2));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("employers", employers);
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}