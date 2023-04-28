package app.ApachePOI;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import app.model.*;

@WebServlet("/download")
public class Download extends HttpServlet {
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
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String sort = request.getParameter("sort");
        ArrayList<User> employers = new ArrayList<>();
        try {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Список");
        int rownum = 0;
        for (int i = 0; i < employers.size(); i++) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(employers.get(i).getFullname());
            row.createCell(1).setCellValue(employers.get(i).getAge());
            row.createCell(2).setCellValue(employers.get(i).getCounty()+", "+employers.get(i).getNeighbourhood()+", "+
                    employers.get(i).getFull_address());
            row.createCell(3).setCellValue(employers.get(i).getSchedule());
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employers.xlsx");
        workbook.write(response.getOutputStream());
        response.sendRedirect(request.getContextPath() + "/getdata");
    }
}
