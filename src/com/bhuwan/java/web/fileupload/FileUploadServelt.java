package com.bhuwan.java.web.fileupload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServelt
 */
@WebServlet({ "/FileUploadServelt", "/upload" })
@MultipartConfig(location = "/tmp/upload")
public class FileUploadServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServelt() {
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
        System.out.println("inside FileUploadServelt#doPost method.");
        if (ServletFileUpload.isMultipartContent(request)) {
            // get all the upload from parts and write to the upload dir
            String fileName = null;
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                part.write(fileName);
            }
            response.getWriter().println("<p>" + fileName + " File uploaded successfully!!</p>");
            getServletContext().getRequestDispatcher("/upload.jsp").include(request, response);
        }
        else {
            response.getWriter().println("<p> File content is not multipart. So coulnot upload file.</p>");
            getServletContext().getRequestDispatcher("/upload.jsp").include(request, response);
        }
    }

    /**
     * Utility method to get file name from HTTP header content-disposition
     * 
     * @author Bhuwan Guatam <bhuwangautam@lftechnology.com>
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

}
