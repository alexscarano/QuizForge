package model;

import java.util.Date;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONObject;
import util.PasswordUtils;

public class Quiz {
    private int id;
    private String prompt;
    private JSONObject content;
    private LocalDateTime createdAt;
    private int userId;

    public static String getCreateStatement(){
       return "CREATE TABLE IF NOT EXISTS quiz(\n"
       + "  quiz_id INT AUTO_INCREMENT \n"
       + ", prompt TEXT \n"
       + ", content JSON \n"
       + ", created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n"
       + ", user_id INT\n"
       + ", CONSTRAINT pk_quiz PRIMARY KEY (quiz_id)\n"
       + ", CONSTRAINT fk_quiz_user FOREIGN KEY (user_id) REFERENCES user (user_id)\n"     
       + ")"; 
    }
    
    
    

    public Quiz(int id, String prompt, JSONObject content, LocalDateTime createdAt, int userId) {
        this.id = id;
        this.prompt = prompt;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
           
    
}
