package com.bhuwan.java.web.empmgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lftechnology.java.jdbc.smallapp.model.Employee;
import com.lftechnology.java.jdbc.smallapp.service.EmployeeService;
import com.lftechnology.java.jdbc.smallapp.service.EmployeeServiceImpl;
import com.lftechnology.java.jdbc.smallapp.util.Role;

/**
 * Servlet implementation class Employee
 */
@WebServlet(description = "Employee Management Application", urlPatterns = { "/employee", "*.do" })
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String HTML_START = "<html><body>";
    public static final String HTML_END = "</body></html>";
    EmployeeService service = new EmployeeServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Enumeration<String> headers = request.getHeaderNames();

        // get list of headers
        while (headers.hasMoreElements()) {
            String headerKey = headers.nextElement();
            System.out.println("Header -> Key :: " + headerKey + " -> value :: " + request.getHeader(headerKey));
        }

        // get content type
        System.out.println("\nContent Type:: " + request.getContentType());

        // http://localhost:8080/emp_mgmt/employee?token=abccc
        System.out.println("\nToken value:: " + request.getParameter("token"));

        System.err.println("\nRead request body:: ");
        BufferedReader reader = request.getReader();
        while (reader.readLine() != null) {
            System.out.println(reader.readLine());
        }

        // you can set the content type
        // response.setContentType("text/xml");

        List<Employee> empList;
        StringBuilder output = new StringBuilder();
        try {
            for (String line : Files
                    .readAllLines(
                            Paths.get("/home/bhuwan/Documents/workspace-ggts-3.6.4.RELEASE/playground_servlet/WebContent/header.html"),
                            Charset.defaultCharset())) {
                output.append(line);
            }
            empList = service.getAllEmployees();
            for (Employee emp : empList) {
                output.append("<tr>");
                output.append("<td>");
                output.append(emp.getFullname());
                output.append("</td>");
                output.append("<td>");
                output.append(emp.getUsername());
                output.append("</td>");
                output.append("<td>");
                output.append(emp.getPassword());
                output.append("</td>");
                output.append("<td>");
                output.append(emp.getAddress());
                output.append("</td>");
                output.append("<td>");
                output.append(emp.getDepartment());
                output.append("</td>");
                output.append("<td>");
                output.append(emp.getRole());
                output.append("</td>");
                output.append("<td>");
                output.append(emp.isTerminated());
                output.append("</td>");
                output.append("</tr>");
            }
            for (String line : Files
                    .readAllLines(
                            Paths.get("/home/bhuwan/Documents/workspace-ggts-3.6.4.RELEASE/playground_servlet/WebContent/footer.html"),
                            Charset.defaultCharset())) {
                output.append(line);
            }
            out.println(output);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/list.html");
            rd.include(request, response);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("Inside employee post method.");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        Role role = Role.valueOf(request.getParameter("role"));
        String department = request.getParameter("department");
        String address = request.getParameter("address");

        Employee emp = service.constructEmployee(username, password, fullname, address, department, role);
        try {
            service.addUser(emp);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println("<div><font color=green>New employee added successfully.</font></div>");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/add.html");
        rd.include(request, response);
    }

}
