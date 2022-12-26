package app.servlets;

import database.config.DbConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet("/profile")
public class Profile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
                //ArrayList<String> employers = new ArrayList<>();
                response.setContentType("text/html");

                String person, dbPerson = "";
                int age = 0;
                //PrintWriter output = response.getWriter();
                try{
                    try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                        person = request.getParameter("person");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM employers WHERE fullname='" +
                                person + "'");
                        while (resultSet.next()){
                            if(Objects.equals(person, resultSet.getString(2))){
                                dbPerson = resultSet.getString(2);
                                age = resultSet.getInt(3);
                                break;
                            }
                        }
                        if(!Objects.equals(person, dbPerson)){
                            dbPerson = "404 not found";
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("person", dbPerson);
                request.setAttribute("age", age);
                getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
