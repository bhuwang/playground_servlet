package com.bhuwan.java.web.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lftechnology.java.jdbc.smallapp.model.Employee;
import com.lftechnology.java.jdbc.smallapp.service.EmployeeService;
import com.lftechnology.java.jdbc.smallapp.service.EmployeeServiceImpl;
import com.lftechnology.java.jdbc.smallapp.util.Role;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", description = "Login Servlet", urlPatterns = { "/login" }, initParams = { @WebInitParam(name = "admin-email", value = "login@gmail.com") })
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

                    /*// Session Management using cookies - START
                    Cookie loginCookie = new Cookie("user", emp.getFullname());
                    // setting cookie to expiry in 30 mins
                    loginCookie.setMaxAge(30 * 60);
                    response.addCookie(loginCookie);
                    // Session Management using cookies - END
*/                    
                    // Session Management using HttpSession - START
                    HttpSession session = request.getSession();
                    session.setAttribute("user", emp.getFullname());
                    // setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("user", emp.getFullname());
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    // Session Management using HttpSession - END
                    
                    rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
                    rd.forward(request, response);
                }
                else {
                    rd = request.getRequestDispatcher("/login.jsp");
                    StringBuilder output = new StringBuilder();
                    output.append("<p>Login Email: ");
                    output.append(getServletConfig().getInitParameter("admin-email"));
                    output.append("</p>");
                    output.append("<font color=red>Either user name or password is wrong.</font>");
                    out.println(output);
                    rd.include(request, response);
                }
            }
            else {
                rd = request.getRequestDispatcher("/login.jsp");
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
