package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import database.config.*;

@WebServlet("/find")
public class Search extends HttpServlet {
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
        ArrayList<String> list = new ArrayList<>();
        try {
            response.setContentType("text/html");
            String person = request.getParameter("person");
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employers WHERE fullname = '"+person+"'ORDER BY fullname");
                while(resultSet.next()){
                    list.add(resultSet.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if ((list == null)||(list.isEmpty())) {
                list.add("Ничего не найдено");
            }
            request.setAttribute("list", list);
            getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}