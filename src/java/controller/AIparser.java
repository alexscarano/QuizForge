/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import data.Question;
import data.Quiz;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author edano
 */
public class AIparser {
    private Quiz ParsedQuiz;
    private Question ParsedQuestions[];
    
    public void parseAI(String response, String creator){
        JSONObject parse = new JSONObject(response);
        String quizname = parse.getString("Quiz Name");
        ParsedQuiz = new Quiz(quizname, creator);
        JSONArray questionsArray = parse.getJSONArray("Quiz Questions");
        ParsedQuestions = new Question[questionsArray.length()];
        for(int i = 0; i<questionsArray.length(); i++){
            String question = questionsArray.getJSONObject(i).getString("Question");
            int correct = questionsArray.getJSONObject(i).getInt("correct answer");
            int difficulty = questionsArray.getJSONObject(i).getInt("difficulty level");
            JSONArray alternatives = questionsArray.getJSONObject(i).getJSONArray("alternatives");
            List<String> a = new ArrayList<>();
            for (int j = 0; j < alternatives.length(); j++) {
                a.add(alternatives.getString(j));
            }
            ParsedQuestions[i] = new Question(question, a, correct, difficulty);
        }
        List<String> qID = new ArrayList<>();
        for (Question Question : ParsedQuestions) {
            qID.add(Question.getQuestionID());
        }
        ParsedQuiz.setQuestionsIDList(qID);
    }
    
    public Quiz getParsedQuiz(){
        return ParsedQuiz;
    }

    public Question[] getParsedQuestions() {
        return ParsedQuestions;
    }
}
