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
                try {
                    //ArrayList<String> employers = new ArrayList<>();
                    response.setContentType("text/html");

                    String dbPerson = "", address = "", schedule = "";
                    int age = 0;
                    String person = request.getParameter("person");
                        try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT employers.fullname, employers.age,\n" +
                                    "addresses.county, addresses.neighbourhood, addresses.full_address, \n" +
                                    "schedule.begintime, schedule.endtime\n" +
                                    "FROM employers\n" +
                                    "JOIN addresses ON \n" +
                                    "addresses.id = employers.id_address\n" +
                                    "JOIN schedule ON\n" +
                                    "schedule.id = employers.id_schedule\n" +
                                    "WHERE fullname = '" +
                                    person + "'");
                            while (resultSet.next()) {
                                if(Objects.equals(person, resultSet.getString(1))) {
                                    dbPerson = resultSet.getString(1);
                                    age = resultSet.getInt(2);
                                    address = resultSet.getString(3) + ", " +
                                            resultSet.getString(4) + ", " +
                                            resultSet.getString(5);
                                    schedule = resultSet.getString(6) + " — " +
                                            resultSet.getString(7);

                                    break;
                                } else {
                                    dbPerson = "404";
                                    break;
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        if (Objects.equals("404", dbPerson)) {
                            getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
                            //тут будет редирект на страницу ошибки
                            
                        } else {
                            request.setAttribute("person", dbPerson);
                            request.setAttribute("age", age);
                            request.setAttribute("address", address);
                            request.setAttribute("schedule", schedule);
                            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
                        }
                    }
                catch (Exception e){
                    e.printStackTrace();
                }
    }
}
