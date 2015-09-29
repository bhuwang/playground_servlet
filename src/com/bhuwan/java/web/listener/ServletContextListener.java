package com.bhuwan.java.web.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lftechnology.java.jdbc.DBConnection;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 * 
 * @author Bhuwan Guatam <bhuwangautam@lftechnology.com>
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ServletContextListener.class.getName());

    /**
     * Default constructor.
     */
    public ServletContextListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("Inside ServletContextListener#contextInitialized method.");

        try {
            servletContextEvent.getServletContext().setAttribute("DBConnection", DBConnection.getJNDIConnection());
            LOGGER.info("Database connection initialized successfully !!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Inside ServletContextListener#contextDestroyed method.");
        Connection con = (Connection)servletContextEvent.getServletContext().getAttribute("DBConnection");
        try {
            con.close();
            LOGGER.info("Database connection closed properly !!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
