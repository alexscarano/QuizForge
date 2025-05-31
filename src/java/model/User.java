package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import util.PasswordUtils;

public class User {
    private int id;
    private String login;
    private String email;
    private String password;
    private LocalDateTime createdAt;
           
    public static String getCreateStatement(){
       return "CREATE TABLE IF NOT EXISTS user (\n"
       + "  user_id INT AUTO_INCREMENT \n"
       + ", user_login VARCHAR(35) UNIQUE\n"
       + ", user_email VARCHAR(65) UNIQUE \n"
       + ", user_password VARCHAR(100)\n"
       + ", created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n"
       + ", CONSTRAINT pk_user PRIMARY KEY (user_id)\n"
       + ")"; 
    }
    
    public static ArrayList<User> getUsers() throws Exception{
        ArrayList<User> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while(rs.next()){
            int id = rs.getInt("user_id");
            String login = rs.getString("user_login");
            String email = rs.getString("user_email");
            String password = rs.getString("user_password");
            Timestamp ts = rs.getTimestamp("created_at");
            // Evitar nullpointer caso haja um valor null no banco
            LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;    
            list.add(new User(id, login, email, password, createdAt));
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public static User getUser(String login, String password) throws Exception{
      Connection conn = DBConnection.getConnection();
      String sql = "SELECT user_id, user_login, user_email, user_password FROM user WHERE user_login=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, login); // login=?
      ResultSet rs = stmt.executeQuery();
        if(rs.next()){ // If pois só retorna um registro, se retornasse varios seria um while
            int id = rs.getInt("user_id");
            String email = rs.getString("user_email");
            String hashedPassword = rs.getString("user_password");

            if (PasswordUtils.checkPassword(password, hashedPassword)){
              return new User(id, login, email, null, null);
            }
        }
      rs.close();
      stmt.close();
      conn.close();
      return null;
    }

    public static void insertUser(String login, String email, String password) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO user(user_login, user_email, user_password) " + "VALUES(?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, login);
        stmt.setString(2, email);
        stmt.setString(3, PasswordUtils.hashPassword(password));
        
        stmt.execute();
        stmt.close();
        conn.close();
    }
    
    public static void updateUserEmail(String login, String email) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE user SET user_email=? WHERE user_login=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, email);
        stmt.setString(2, login);

        stmt.execute();
        stmt.close();
        conn.close();
    }
    
    public static void updateUserPassword(String login, String password) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE user SET user_password=? WHERE user_login=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, PasswordUtils.hashPassword(password));
        stmt.setString(2, login);

        stmt.execute();
        stmt.close();
        conn.close();
    }
    
    public static void deleteUser(int id) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM user WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        conn.close();
    }
    
      
    public User(int id, String login, String email, String password, LocalDateTime createdAt) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
     
}
