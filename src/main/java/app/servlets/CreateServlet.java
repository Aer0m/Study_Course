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

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");

        if (authenticated == null || !authenticated) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            super.service(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            response.setContentType("text/html");
            String fullname = request.getParameter("fullname");
            int age = Integer.parseInt(request.getParameter("age"));
            String address = request.getParameter("address");
            String county = request.getParameter("county");
            String neighbourhood = request.getParameter("neigh");
            String begintime = request.getParameter("begintime");
            String endtime = request.getParameter("endtime");
            
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(), DbConfig.getPassword())) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("WITH new_address AS (\n" +
                        "\tINSERT INTO addresses (full_address, neighbourhood, county)\n" +
                        "\tVALUES ('"+address+"', '"+neighbourhood+"', '"+county+"')\n" +
                        "\tRETURNING id\n" +
                        "),\n" +
                        "new_time AS(\n" +
                        "\tINSERT INTO schedule (begintime, endtime)\n" +
                        "\tVALUES('"+begintime+"', '"+endtime+"')\n" +
                        "\tRETURNING id\n" +
                        ")" +
                        "INSERT INTO employers (fullname, age, id_address, id_schedule)\n" +
                        "VALUES ('"+fullname+"', '"+age+"', (SELECT id FROM new_address), (SELECT id FROM new_time));");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}