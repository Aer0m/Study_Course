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

@WebServlet("/dsorted")
public class DownloadSorted extends HttpServlet {
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
        String sort = request.getParameter("list");
        ArrayList<String> employers = new ArrayList<>();
        String[] values = sort.substring(1, sort.length() - 1).split(",");
        for (String value : values) {
            employers.add(value.trim());
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Список");
        int rownum = 0;
        for (int i = 0; i < employers.size(); i++) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(employers.get(i));
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employers.xlsx");
        workbook.write(response.getOutputStream());
        response.sendRedirect(request.getContextPath() + "/getdata");
    }
}
