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

@WebServlet("/show")
public class Show extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        ArrayList<String>list = new ArrayList<>();
        try {
            response.setContentType("text/html");
            String sort = request.getParameter("sort");
            String county = request.getParameter("county");
            String neigh = request.getParameter("neigh");
            String sql = "SELECT employers.fullname FROM employers \n" +
                    "JOIN addresses ON addresses.id = employers.id_address\n" +
                    "WHERE addresses.county = '"+county+"'";
            String sqlNeigh = " AND addresses.neighbourhood = '"+neigh+"'\n";
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                Statement statement = connection.createStatement();
                ResultSet resultSet;
                if(sort=="namealph") {
                    if (neigh == "") {
                        resultSet = statement.executeQuery(sql + " ORDER BY fullname");
                    } else {
                        resultSet = statement.executeQuery(sql + sqlNeigh + " ORDER BY fullname");
                    }
                }
                else if (sort=="age") {
                    if (neigh == "") {
                        resultSet = statement.executeQuery(sql + " ORDER BY age");
                    } else {
                        resultSet = statement.executeQuery(sql + sqlNeigh + " ORDER BY age");
                    }
                }
                else if (sort=="countyalph"){
                    if (neigh == "") {
                        resultSet = statement.executeQuery(sql + " ORDER BY county");
                    } else {
                        resultSet = statement.executeQuery(sql + sqlNeigh + " ORDER BY county");
                    }
                }
                else if (sort=="neighalph") {
                    if (neigh == "") {
                        resultSet = statement.executeQuery(sql + " ORDER BY neighbourhood");
                    } else {
                        resultSet = statement.executeQuery(sql + sqlNeigh + " ORDER BY neighbourhood");
                    }
                }
                else {
                    if (neigh == "") {
                        resultSet = statement.executeQuery(sql);
                    } else {
                        resultSet = statement.executeQuery(sql + sqlNeigh);
                    }
                }
                while (resultSet.next()){
                    list.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if ((list == null)||(list.isEmpty())) {
                list.add("Ничего не найдено");
            }
            request.setAttribute("list", list);
            getServletContext().getRequestDispatcher("/show.jsp").forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
