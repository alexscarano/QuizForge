package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final String userID;
    private final LocalDateTime userSignUpDate;
    private String username;
    private String userEmail;
    private String userPassword;
    private List<String> favoriteQuizIDList = new ArrayList<>();
    private boolean Admin;

    public User(String username, String userEmail, String userPassword) {
        this.userID = UUID.randomUUID().toString();
        this.userSignUpDate = LocalDateTime.now();
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.Admin = false;
    }
    
    public String getUserID() {
        return userID;
    }

    public LocalDateTime getUserSignUpDate() {
        return userSignUpDate;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public List<String> getFavoriteQuizIDList() {
        return favoriteQuizIDList;
    }
    
    public void setNewFavQuiz(String quizID){
        this.favoriteQuizIDList.add(quizID);        
    }
    
    public void removeFromFavQuiz(String quizID){
        this.favoriteQuizIDList.remove(favoriteQuizIDList.indexOf(quizID));
    }
    
    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }
}