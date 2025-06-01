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
   
    public static User getUserByEmail(String email) throws Exception{
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM user WHERE user_email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
                String storedHashedPassword = rs.getString("user_password");
                int id = rs.getInt("user_id");
                String login = rs.getString("user_login");
                Timestamp ts = rs.getTimestamp("created_at");
                LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                return new User(id, login, email, storedHashedPassword, createdAt);
            }

        rs.close();
        stmt.close();
        conn.close();
        return null;
    }
    
    public static User getUser(String loginOrEmail, String plainPassword) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM user WHERE user_email = ? OR user_login = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, loginOrEmail);
        stmt.setString(2, loginOrEmail);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String storedHashedPassword = rs.getString("user_password");

            if (PasswordUtils.checkPassword(plainPassword, storedHashedPassword)) {
                int id = rs.getInt("user_id");
                String login = rs.getString("user_login");
                String email = rs.getString("user_email");
                Timestamp ts = rs.getTimestamp("created_at");
                LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                return new User(id, login, email, storedHashedPassword, createdAt);
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
    
    public static void updateUserLogin(String login, String email) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE user SET user_login=? WHERE user_email=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, login);
        stmt.setString(2, email);

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
    
    public static void updateUserPassword(String email, String password) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE user SET user_password=? WHERE user_email=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, PasswordUtils.hashPassword(password));
        stmt.setString(2, email);

        stmt.execute();
        stmt.close();
        conn.close();
    }
    
    public static void deleteUser(String emailOrLogin) throws Exception { 
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM user WHERE user_email = ? OR user_login = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, emailOrLogin);
        stmt.setString(2, emailOrLogin);
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
