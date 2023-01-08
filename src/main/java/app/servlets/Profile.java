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
import java.util.Objects;

@WebServlet("/profile")
public class Profile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
                //ArrayList<String> employers = new ArrayList<>();
                response.setContentType("text/html");

                String dbPerson = "", address = "";
                int age = 0;
                String person = request.getParameter("person");

                try{
                    try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {

                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT employers.fullname, employers.age, " +
                                "addresses.full_address\n" +
                                "FROM employers\n" +
                                "INNER JOIN addresses ON addresses.id = employers.id_address\n" +
                                "WHERE fullname ='" +
                                person + "'");
                        while (resultSet.next()){
                            if(Objects.equals(person, resultSet.getString(1))){
                                dbPerson = resultSet.getString(1);
                                age = resultSet.getInt(2);
                                address = resultSet.getString(3);

                                break;
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                if(!Objects.equals(person, dbPerson)){
                    dbPerson = "404 not found";
                    request.setAttribute("person", dbPerson);
                    getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("person", dbPerson);
                    request.setAttribute("age", age);
                    request.setAttribute("address", address);
                    getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
                }
    }
}
