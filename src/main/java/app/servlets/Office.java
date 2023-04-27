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
import app.model.*;
import java.util.ArrayList;

@WebServlet("/getdata")
public class Office extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            request.setCharacterEncoding("UTF-8");
            try {
                response.setContentType("text/html");
                ArrayList<User> employers = new ArrayList<>();
                    try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT employers.id, employers.fullname, employers.age,\n" +
                                "addresses.county, addresses.neighbourhood, addresses.full_address, \n" +
                                "schedule.begintime, schedule.endtime\n" +
                                "FROM employers\n" +
                                "JOIN addresses ON \n" +
                                "addresses.id = employers.id_address\n" +
                                "JOIN schedule ON\n" +
                                "schedule.id = employers.id_schedule ORDER BY fullname;");
                        while(resultSet.next()){
                            int id = resultSet.getInt(1);
                            String fullname = resultSet.getString(2);
                            int age = resultSet.getInt(3);
                            String county = resultSet.getString(4);
                            String neighbourhood = resultSet.getString(5);
                            String full_address = resultSet.getString(6);
                            String schedule = resultSet.getString(7) + "—" + resultSet.getString(8);
                            User user = new User(id, fullname, age, county, neighbourhood, full_address, schedule);
                            employers.add(user);
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
