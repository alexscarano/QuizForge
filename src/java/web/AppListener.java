package web;

import model.DBConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quiz;
import model.User;

@WebListener
public class AppListener implements ServletContextListener{
    
    public static String initializeLog = "";
    public static Exception exception = null;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection conn = null;
        Statement s = null;

        
        initializeLog += "Initializing log: " + new Date() + "\n";
        try {
            conn = DBConnection.getConnection();
            initializeLog += "Connected to the database \n";
            s = conn.createStatement();
            initializeLog += new Date() + ": Initializing database creation \n";
            initializeLog += "Creating Users table if not exists...\n";
            
            s.execute(User.getCreateStatement());
            initializeLog += "User table has been created or already exists\n";
           
            if (User.getUsers().isEmpty()){
                initializeLog += "Adding default users...\n";
                User.insertUser("admin", "alexandrescarano@gmail.com", "123");
            }
            
            initializeLog += "Creating Quiz table if not exists...\n";
            s.execute(Quiz.getCreateStatement());
            initializeLog += "Quiz table has been created or already exists\n";
           
        } catch (Exception e) {
            initializeLog += "Error: \n" + e.getMessage();
        }
        
     
           
    }
            
}
