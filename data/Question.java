import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    private final String questionID;
    private String question;
    private List<String> alternatives = new ArrayList<>();
    private int correctAnswer;
    private int difficultyLevel;

    public Question(String question, List<String> alternatives, int correctAnswer, int difficultyLevel) {
        this.questionID = UUID.randomUUID().toString();
        this.question = question;
        this.alternatives = alternatives;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public void setAlternativefromList(int index, String newAlternative){
        this.alternatives.set(index, newAlternative);
    }
    
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    
    
}
