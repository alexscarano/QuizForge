package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quiz; // Importe sua classe Quiz

// Verifique se o nome do Servlet e o URL pattern estão como você deseja
// Se o botão da lista está enviando para /consultQuiz, mantenha /consultQuiz aqui
// Se você está usando o nome "viewQuizServlet" para este Servlet, ok, mas o URL pattern é importante
@WebServlet(name = "ConsultQuizServlet", urlPatterns = {"/consultQuiz"}) // <<< Use o URL pattern que o botão da lista envia
public class ConsultQuizServlet extends HttpServlet { // Ou "viewQuizServlet" se você o renomeou

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer userId = null;

        if (session != null && session.getAttribute("userId") != null) {
            try {
                userId = (Integer) session.getAttribute("userId");
            } catch (ClassCastException e) {
           
            }
        } else {
           
        }

        if (userId == null || userId <= 0) {
            request.setAttribute("errorMessage", "Você precisa estar logado para consultar quizzes.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        int quizIdToConsult = -1;
        String quizIdParam = request.getParameter("quizId");

        try {
            quizIdToConsult = Integer.parseInt(quizIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID do quiz inválido para consulta.");
            request.getRequestDispatcher("/listQuizzes").forward(request, response);
            return;
        }

        try {
            Quiz quiz = Quiz.getQuizById(quizIdToConsult); // Ou Quiz.getQuizContentById() se for o nome do seu método

            if (quiz != null && quiz.getUserId() == userId) {
                String quizQuestionsJson = quiz.getContent();
                String quizTopic = quiz.getPrompt();

                request.setAttribute("quizQuestionsJson", quizQuestionsJson);
                request.setAttribute("quizTopic", quizTopic);
                request.setAttribute("currentQuizId", quizIdToConsult); 

                request.getRequestDispatcher("/viewQuiz.jsp").forward(request, response);

            } else {
                request.setAttribute("errorMessage", "Quiz não encontrado ou você não tem permissão para consultá-lo.");
                request.getRequestDispatcher("/listQuizzes").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao consultar o quiz: " + e.getMessage());
            request.getRequestDispatcher("/listQuizzes").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "";
    }
}