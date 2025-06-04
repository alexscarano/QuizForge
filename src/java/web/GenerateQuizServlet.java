/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import ia.Gemini;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "GenerateQuizServlet", urlPatterns = {"/generateQuiz"})
public class GenerateQuizServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quizTopic = request.getParameter("captureQuiz");

        if (quizTopic == null || quizTopic.trim().isEmpty()) {
            request.setAttribute("errorMessage", "O tópico do quiz não pode estar vazio.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String rawJsonResponseFromGemini = null;
        try {
       
            rawJsonResponseFromGemini = Gemini.getCompletion(quizTopic);
            
            HttpSession session = request.getSession(); // Pega a sessão existente ou cria uma nova
            session.setAttribute("quizQuestionsJson", rawJsonResponseFromGemini); // <<< ESTA LINHA É CRUCIAL!
            session.setAttribute("quizTopic", quizTopic);                 // <<< ESTA LINHA É CRUCIAL!
            session.setAttribute("currentQuizId", null);
 
            JSONArray questions = new JSONArray(rawJsonResponseFromGemini);
            
            Map<Integer, String> correctAnswers = new HashMap<>();
            for (int i = 0; i < questions.length(); i++) {
                JSONObject question = questions.getJSONObject(i);
                String correctAnswer = question.getString("respostaCorreta");
                correctAnswers.put(i, correctAnswer);
            }

            session.setAttribute("respostasCorretas", correctAnswers);

            request.setAttribute("quizQuestionsJson", rawJsonResponseFromGemini);
            request.setAttribute("quizTopic", quizTopic);

            
            request.getRequestDispatcher("/formulario.jsp").forward(request, response);

        } catch (Exception e) {
            String userFriendlyErrorMessage = "Ocorreu um erro ao gerar o quiz. Por favor, tente novamente mais tarde.";
            
            if (e.getMessage() != null && e.getMessage().contains("Resposta da IA não está em formato JSON esperado")) {
                 userFriendlyErrorMessage = "A IA não conseguiu gerar o quiz no formato esperado para o tópico '" + quizTopic + "'. Tente refinar seu tópico ou gere novamente.";
            }
            request.setAttribute("errorMessage", userFriendlyErrorMessage);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
