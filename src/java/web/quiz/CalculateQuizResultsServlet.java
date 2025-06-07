package web.quiz;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "CalculateQuizResultsServlet", urlPatterns = {"/quizResults"})
public class CalculateQuizResultsServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        String quizQuestionsJsonFromSource = null;
        String quizTopicFromSource = null;
        
        String quizIdParam = request.getParameter("quizId");
        Integer quizId = null;

        try {
            if (quizIdParam != null && !quizIdParam.isEmpty()) {
                quizId = Integer.parseInt(quizIdParam);
                request.setAttribute("errorMessage", "Quiz não encontrado ou você não tem permissão para acessá-lo.");
                request.setAttribute("quizQuestionsJson", session.getAttribute("quizQuestionsJson"));
                request.setAttribute("quizTopic", session.getAttribute("quizTopic"));
                if (quizIdParam != null && !quizIdParam.isEmpty()) {
                     request.setAttribute("quizId", quizId);
                }
                request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                return;
            } else {
                quizQuestionsJsonFromSource = (String) session.getAttribute("quizQuestionsJson");
                quizTopicFromSource = (String) session.getAttribute("quizTopic");
            }

            if (quizQuestionsJsonFromSource == null || quizQuestionsJsonFromSource.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Não foi possível processar o quiz. As questões não foram encontradas. Tente gerar/consultar novamente.");
                request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                return;
            }

            int correctAnswersCount = 0;
            int totalQuestions = 0;
            List<String> resultsFeedback = new ArrayList<>();

            JSONArray questionsInJson = new JSONArray(quizQuestionsJsonFromSource);
            totalQuestions = questionsInJson.length();

            for (int i = 0; i < totalQuestions; i++) {
                JSONObject questionObj = questionsInJson.getJSONObject(i);
                // Capturar a letra da resposta correta do JSON (ex: "A", "B", "C")
                String correctAnswerLetter = questionObj.getString("respostaCorreta").trim().toUpperCase(); 
                
                // Capturar a letra da resposta do usuário do parâmetro (ex: "A", "B", "C")
                String userAnswerLetter = request.getParameter("q_" + i); 
                if (userAnswerLetter != null) {
                    userAnswerLetter = userAnswerLetter.trim().toUpperCase();
                } else {
                    userAnswerLetter = ""; // Tratamento de erros caso o usuário manipule o HTML
                }
                
                JSONArray opcoesArray = questionObj.getJSONArray("opcoes");                
                String correctOptionText = getOptionTextByLetter(opcoesArray, correctAnswerLetter);
                String userOptionText = getOptionTextByLetter(opcoesArray, userAnswerLetter);

                String feedback;
                if (userAnswerLetter.equals(correctAnswerLetter)) {
                    correctAnswersCount++;
                    feedback = "Pergunta " + (i + 1) + ": <span class='text-success'><i class='fas fa-check-circle'></i> Correto!</span> Sua resposta: '" + (userOptionText != null ? userOptionText : "Não respondida") + "'.";
                } else {
                    feedback = "Pergunta " + (i + 1) + ": <span class='text-danger'><i class='fas fa-times-circle'></i> Incorreto.</span> Sua resposta: '" + (userOptionText != null ? userOptionText : "Não respondida") + "'. Correta: '" + (correctOptionText != null ? correctOptionText : "N/A") + "'.";
                }
                
                resultsFeedback.add(feedback);
            }

            request.setAttribute("totalQuestions", totalQuestions);
            request.setAttribute("correctAnswersCount", correctAnswersCount);
            request.setAttribute("quizTopic", quizTopicFromSource);
            request.setAttribute("quizResults", resultsFeedback); 

            request.getRequestDispatcher("/quizResult.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID do quiz inválido.");
            request.setAttribute("quizQuestionsJson", session.getAttribute("quizQuestionsJson")); 
            request.setAttribute("quizTopic", session.getAttribute("quizTopic"));
           
            if (quizIdParam != null && !quizIdParam.isEmpty()) {
                 request.setAttribute("quizId", quizId);
            }
            
            request.getRequestDispatcher("/formulario.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro interno ao processar suas respostas: " + e.getMessage());
            request.setAttribute("quizQuestionsJson", quizQuestionsJsonFromSource); 
            request.setAttribute("quizTopic", quizTopicFromSource);
           
            if (quizId != null) {
                request.setAttribute("quizId", quizId);
            }
            
            request.getRequestDispatcher("/formulario.jsp").forward(request, response);
        }
    }

    // Método para auxiliar a captura da letra 
    private String getOptionTextByLetter(JSONArray optionsArray, String letter) {
        if (optionsArray == null || letter == null || letter.isEmpty()) {
            return null;
        }
        for (int j = 0; j < optionsArray.length(); j++) {
            String option = optionsArray.getString(j);
            if (option != null && option.trim().toUpperCase().startsWith(letter.toUpperCase() + ")")) {
                return option; // Retorna o texto completo da opção (ex: "A) Samurai")
            }
        }
        return null; 
    }

    @Override
    public String getServletInfo() {
        return "Servlet para calcular os resultados de um quiz.";
    }
}