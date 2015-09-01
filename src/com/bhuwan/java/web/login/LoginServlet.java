package com.bhuwan.java.web.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", description = "Login Servlet", urlPatterns = { "/login", "/signup" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    EmployeeService service = new EmployeeServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        RequestDispatcher rd;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: " + username + " password: " + password);
        Employee emp = null;
        Boolean isLoggedIn = false;
        try (PrintWriter out = response.getWriter();) {
            if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
                service.insertUserIfNotExist(Role.admin);
                emp = service.login(username, password);
                isLoggedIn = emp != null;
                if (isLoggedIn) {
                    // out.println("Welcome - " + emp.getFullname());
                    rd = getServletContext().getRequestDispatcher("/dashboard.html");
                    rd.forward(request, response);
                }
                else {
                    rd = getServletContext().getRequestDispatcher("/login.html");
                    out.println("<font color=red>Either user name or password is wrong.</font>");
                    rd.include(request, response);
                }
            }
            else {
                rd = getServletContext().getRequestDispatcher("/login.html");
                out.println("<font color=red>username or password are required.</font>");
                rd.include(request, response);
                // response.sendRedirect("login.html");
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
