package com.bhuwan.java.web.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExceptionHandlingServelt
 * 
 * @author Bhuwan Guatam <bhuwangautam@lftechnology.com>
 */
@WebServlet({ "/ExceptionHandlingServelt", "/exception" })
public class ExceptionHandlingServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExceptionHandlingServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside ExceptionHandlingServelt#doGet method.");
        processError(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("Inside ExceptionHandlingServelt#doPost method.");
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Error Page</title></head><body>");
        out.write("<br><br><br><br><p style='text-align: center;'>Something is not working. We have informed our headquaters. <br><br>Thank you for your patience.");
        out.write("<br><br>");
        out.write("<a href=\"" + request.getServletContext().getContextPath() + "/login.jsp\">Home Page</a></p>");
        out.write("</body></html>");
    }

}
