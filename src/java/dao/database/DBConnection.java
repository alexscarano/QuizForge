package dao.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import util.ConstantesDB;


public class DBConnection {
  
    public static Connection getConnection() throws SQLException{
        
        try {
            
           Class.forName("com.mysql.cj.jdbc.Driver");
           
           return DriverManager.getConnection(
               ConstantesDB.DB_URL,
               ConstantesDB.DB_USER,
               ConstantesDB.DB_USER_PASSWORD
           );
        }
        catch (ClassNotFoundException e){
            throw new SQLException("Não foi possível fazer a conexão com a instância : " + ConstantesDB.DB_URL + " Usuário: " + ConstantesDB.DB_USER);
        }
        
    }
    
}
