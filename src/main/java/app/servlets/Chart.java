package app.servlets;

import database.config.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;

@WebServlet("/chart")
public class Chart extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Boolean authenticated = (Boolean) session.getAttribute("authenticated");

        if (authenticated == null || !authenticated) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            super.service(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            int[] ages = new int[4];
            try (Connection connection = DriverManager.getConnection(DbConfig.getUrl(), DbConfig.getUser(),
                    DbConfig.getPassword())) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age >= 18 AND age <= 25  THEN age END) as young,\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age >= 26 AND age <= 30 THEN age END) as middle,\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age >= 31 AND age <= 45 THEN age END) as mature,\n" +
                        "\tCOUNT(DISTINCT CASE WHEN age > 44 THEN age END) as old\n" +
                        "FROM employers\n");
                while(resultSet.next()){
                    for(int i = 0; i < ages.length; i++){
                        ages[i] = resultSet.getInt(i+1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //PrintWriter output = response.getWriter();
            //output.println(Arrays.toString(ages));
            request.setAttribute("ages", ages);
            getServletContext().getRequestDispatcher("/chart.jsp").forward(request, response);
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }
}
