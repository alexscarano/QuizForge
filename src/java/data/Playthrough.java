package data;

import java.time.LocalDateTime;

public class Playthrough {
    private final String userID;
    private final String quizID;
    private final LocalDateTime playSessionDate;
    private final int totalCorrectAnswers[];
    private int userAnswers[];

    public Playthrough(String userID, String quizID, int[] totalCorrectAnswers) {
        this.playSessionDate = LocalDateTime.now();
        this.userID = userID;
        this.quizID = quizID;
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

    public String getUserID() {
        return userID;
    }

    public String getQuizID() {
        return quizID;
    }

    public LocalDateTime getPlaySessionDate() {
        return playSessionDate;
    }

    public int[] getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public int[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(int[] userAnswers) {
        this.userAnswers = userAnswers;
    }
    
}
