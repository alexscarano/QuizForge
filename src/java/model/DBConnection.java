package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import util.DBCredentials;


public class DBConnection {
  
    public static Connection getConnection() throws SQLException{
        
        try {
            
           Class.forName("com.mysql.cj.jdbc.Driver");
           
           return DriverManager.getConnection(DBCredentials.DB_URL,
               DBCredentials.DB_USER,
               DBCredentials.DB_USER_PASSWORD
           );
        }
        catch (ClassNotFoundException e){
            throw new SQLException("Não foi possível fazer a conexão com a instância : " + DBCredentials.DB_URL + " Usuário: " + DBCredentials.DB_USER);
        }
        
    }
    
}
