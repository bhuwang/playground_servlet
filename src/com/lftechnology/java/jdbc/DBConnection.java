/**
 * 
 */
package com.lftechnology.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Facade class for Database connection
 * 
 * @author Bhuwan Guatam <bhuwangautam@lftechnology.com>
 */
public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    // private static final String
    // MYSQL_CONNECTION_STRING="jdbc:mysql://localhost:3306/playground";
    private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/playground?rewriteBatchedStatements=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "liferay";

    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String ORACLE_CONNECTION_STRING = "jdbc:oracle:thin:@localhost:3306:playground";

    private Connection connection;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public static Connection getMySqlConnection() {
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "DBConnection#getConnection exception occured - Class: " + e.getClass()
                    + " message: " + e.getMessage());
        }
        return connection;
    }

    public DBConnection(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName(MYSQL_DRIVER);
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public static Connection getJNDIConnection() throws SQLException {
        Context ctx;
        DataSource ds = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/playgroundDB");
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
        return ds.getConnection();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static Connection getOracleConnection() {
        Connection connection = null;
        try {
            Class.forName(ORACLE_DRIVER);
            connection = DriverManager.getConnection(ORACLE_CONNECTION_STRING, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "DBConnection#getOracleConnection exception occured - Class: " + e.getClass()
                    + " message: " + e.getMessage());
        }
        return connection;
    }

}
