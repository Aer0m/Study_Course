package app.servlets;

import database.config.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import app.model.*;

@WebServlet("/show")
public class Show extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ArrayList<User> list = new ArrayList<>();
        try {
            response.setContentType("text/html");
            String sort = request.getParameter("sort");
            String county = request.getParameter("county");
            String neigh = request.getParameter("neigh");

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT employers.id, employers.fullname, employers.age,\n" +
                    "addresses.county, addresses.neighbourhood, addresses.full_address, \n" +
                    "schedule.begintime, schedule.endtime\n" +
                    "FROM employers\n" +
                    "JOIN addresses ON \n" +
                    "addresses.id = employers.id_address\n" +
                    "JOIN schedule ON\n" +
                    "schedule.id = employers.id_schedule ");

            if (Objects.equals(county, "All")) {
                if (!neigh.isEmpty()) {
                    sql.append("WHERE neighbourhood = '").append(neigh).append("' ");
                }
            } else {
                if (!county.isEmpty() && (!neigh.isEmpty())) {
                    sql.append("WHERE county = '").append(county).append("' AND WHERE neighbourhood ='").append(neigh).append("' ");
                } else if ((!county.isEmpty()) && (neigh.isEmpty())) {
                    sql.append("WHERE county='").append(county).append("' ");
                } else if ((county.isEmpty()) && !neigh.isEmpty()) {
                    sql.append("WHERE neighbourhood").append(neigh).append("' ");
                }
            }

                if (sort.equalsIgnoreCase("namealph")) {
                    sql.append("ORDER BY fullname");
                } else if (sort.equalsIgnoreCase("age")) {
                    sql.append("ORDER BY age");
                } else if (sort.equalsIgnoreCase("countyalph")) {
                    sql.append("ORDER BY county");
                } else if (sort.equalsIgnoreCase("neighalph")) {
                    sql.append("ORDER BY neighbourhood");
                } else if (sort.equalsIgnoreCase("default")) {
                    sql.append("ORDER BY id");
                }

            String sqlSet = sql.toString();
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword());
                 Statement statement = connection.createStatement();) {
                ResultSet resultSet = statement.executeQuery(sqlSet);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String fullname = resultSet.getString(2);
                    int age = resultSet.getInt(3);
                    String setCounty = resultSet.getString(4);
                    String neighbourhood = resultSet.getString(5);
                    String full_address = resultSet.getString(6);
                    String schedule = resultSet.getString(7) + "—" + resultSet.getString(8);
                    User user = new User(id, fullname, age, setCounty, neighbourhood, full_address, schedule);
                    list.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if ((list == null) || (list.isEmpty())) {
                User user = new User("Ничего не найдено");
                list.add(user);
            }
            request.setAttribute("sqlSet", sqlSet);
            request.setAttribute("list", list);
            getServletContext().getRequestDispatcher("/show.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}