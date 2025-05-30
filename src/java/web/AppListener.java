package web;

import model.DBConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Date;
import java.sql.*;
public class AppListener implements ServletContextListener{
    
    public static String initializeLog = "";
    public static Exception exception = null;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection conn = null;
        
        initializeLog += "Initializing log: " + new Date();
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException e) {
            initializeLog += "Error on connecting to the database: " + e.getMessage();
        }
        
        if (conn != null){} // Continuar após terminar os models
        
    }
           
    
    
    
    
}
