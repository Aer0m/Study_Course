package app.servlets;

import database.config.DbConfig;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Chart", value = "/chart")
public class Chart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(),
                    DbConfig.getPassword())) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age >= 18 AND age <= 25  THEN age END) as young,\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age >= 26 AND age <= 30 THEN age END) as middle,\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age >= 31 AND age <= 45 THEN age END) as mature,\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age > 44 THEN age END) as old\n" +
                        "FROM employers\n");
                while (resultSet.next()){

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }



}
