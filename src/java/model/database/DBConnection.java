package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import util.credentials.DBCredentials;


public class DBConnection {
  
    public static Connection getConnection() throws SQLException{
        
        try {
            
           Class.forName("com.mysql.cj.jdbc.Driver");
           
           return DriverManager.getConnection(DBCredentials.getDBUrl(),
               DBCredentials.getDBUser(),
               DBCredentials.getDBUserPassword()
           );
        }
        catch (ClassNotFoundException e){
            throw new SQLException("Não foi possível fazer a conexão com o BD");
        }
        
    }
    
}
