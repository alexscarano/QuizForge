package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Quiz {
    private final String quizID;
    private final LocalDateTime quizCreationDate;
    private final String quizCreatorID;
    private String quizName;
    private List<String> questionsIDList = new ArrayList<>();
    private List<String> userFavoriteIDList = new ArrayList<>();
    
    public Quiz(String quizName, String quizCreatorID) {
        this.quizID = UUID.randomUUID().toString();
        this.quizCreationDate = LocalDateTime.now();
        this.quizName = quizName;
        this.quizCreatorID = quizCreatorID;
    }
    
    public String getQuizID() {
        return quizID;
    }

    public LocalDateTime getQuizCreationDate() {
        return quizCreationDate;
    }    
    
    public String getQuizCreatorID() {
        return quizCreatorID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public List<String> getQuestionsIDList() {
        return questionsIDList;
    }
    
    public int getQuizLength(){
        return questionsIDList.size();
    }
    
    public void setQuestionsIDList(List<String> questionsIDList) {
        this.questionsIDList = questionsIDList;
    }
    
    public void setQuestionIDfromList(String oldID, String newID){
        this.questionsIDList.set(questionsIDList.indexOf(oldID), newID);
    }

    public List<String> getUserFavoriteIDList() {
        return userFavoriteIDList;
    }
    
    public void setNewUserFav(String userID){
        this.userFavoriteIDList.add(userID);        
    }
    
    public void removeFromUserFav(String userID){
        this.userFavoriteIDList.remove(userFavoriteIDList.indexOf(userID));
    }
}
