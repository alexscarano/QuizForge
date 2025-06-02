package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONObject; // Importa JSONObject
import org.json.JSONArray; // Também pode ser útil para o conteúdo do quiz
import org.json.JSONException; // Para tratamento de erros de parsing JSON
import model.DBConnection;// Assumindo que sua classe de conexão está em util.DatabaseConnection

public class Quiz {
    private int id; 
    private String prompt;
    private String content;
    private LocalDateTime createdAt;
    private int userId;

   
    // --- Métodos Estáticos para Operações de Banco de Dados ---

    public static String getCreateStatement() {
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

    
        public static ArrayList<Quiz> getQuizzes() throws Exception {
        ArrayList<Quiz> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM quiz");

        while (rs.next()) {
            int id = rs.getInt("quiz_id");
            String prompt = rs.getString("prompt");
            String content = rs.getString("content");
            Timestamp ts = rs.getTimestamp("created_at");
            LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
            int userId = rs.getInt("user_id");

            list.add(new Quiz(id, prompt, content, createdAt, userId));
        }

        rs.close();
        stmt.close();
        conn.close();

        return list;
    }

    public static Quiz getQuizById(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String prompt = rs.getString("prompt");
            String content = rs.getString("content");
            Timestamp ts = rs.getTimestamp("created_at");
            LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;
            int userId = rs.getInt("user_id");

            return new Quiz(id, prompt, content, createdAt, userId);
        }

        rs.close();
        stmt.close();
        conn.close();

        return null;
    }

    public static void insertQuiz(String prompt, String content, int userId) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO quiz(prompt, content, user_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, prompt);
        stmt.setString(2, content);
        stmt.setInt(3, userId);

        stmt.execute();
        stmt.close();
        conn.close();
    }

    public static void updateQuizPrompt(int id, String newPrompt) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE quiz SET prompt = ? WHERE quiz_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, newPrompt);
        stmt.setInt(2, id);

        stmt.execute();
        stmt.close();
        conn.close();
    }

    public static void deleteQuiz(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM quiz WHERE quiz_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        conn.close();
    }
    
    // --- Getters e Setters ---

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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
    
    // Construtor padrão
    public Quiz() {
    }

    // Construtor para criar um novo quiz antes de salvar no banco
    public Quiz(String prompt, String content, int userId) {
        this.prompt = prompt;
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    // Construtor completo usado ao recuperar do banco de dados
    public Quiz(int id, String prompt, String content, LocalDateTime createdAt, int userId) {
        this.id = id;
        this.prompt = prompt;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
    }

}