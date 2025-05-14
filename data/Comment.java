import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comment {
    private final String commentID;
    private final LocalDateTime commentPostDate;
    private final String quizID;
    private final String userID;
    private String commentText;
    private List<String> userLikeID = new ArrayList<>();

    public Comment(String quizID, String userID, String commentText) {
        this.commentID = UUID.randomUUID().toString();
        this.commentPostDate = LocalDateTime.now();
        this.userID = userID;
        this.quizID = quizID;
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentID() {
        return commentID;
    }

    public LocalDateTime getCommentPostDate() {
        return commentPostDate;
    }

    public String getQuizID() {
        return quizID;
    }

    public String getUserID() {
        return userID;
    }

    public List<String> getUserLikeID() {
        return userLikeID;
    }

    public void setNewUserLikeID(String userID) {
        this.userLikeID.add(userID);
    }
    
    public void removeFromUserLike(String userID){
        this.userLikeID.remove(userLikeID.indexOf(userID));
    }
}
