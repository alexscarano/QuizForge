package web;

import java.io.IOException;
import java.io.PrintWriter; // Não necessário se você está apenas encaminhando
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quiz;


@WebServlet(name = "SaveQuizServlet", urlPatterns = {"/saveQuiz"})
public class SaveQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false); 
        
        String quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
        String quizTopic = (String) session.getAttribute("quizTopic");        

        if (session == null || session.getAttribute("userId") == null) {
            // Se a sessão expirou ou o usuário não está logado
            request.setAttribute("errorMessage", "Sessão expirada ou você não está logado. Por favor, faça login e gere um novo quiz para salvar.");
            request.getRequestDispatcher("/login.jsp").forward(request, response); // Redireciona para o login
            return;
        }

        Integer userId = null;
        try {
            userId = (Integer) session.getAttribute("userId");
        } catch (ClassCastException e) {
            
        }

        // Validação inicial do userId
        if (userId == null || userId <= 0) {
            request.setAttribute("errorMessage", "ID de usuário inválido na sessão. Faça login novamente.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty() || 
            quizTopic == null || quizTopic.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Não foi possível salvar o quiz. Dados do quiz ausentes na sessão. Por favor, gere um quiz primeiro.");
            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redireciona para a página inicial
            return;
        }
        
        // Se tudo estiver OK, tenta salvar
        try {
            Quiz.insertQuiz(quizTopic, quizQuestionsJson, userId);
            
            // Use a sessão para mensagens de sucesso que persistem após um redirecionamento
            session.setAttribute("successMessage", "Quiz salvo com sucesso!");
            
            response.sendRedirect(request.getContextPath() + "/listQuizzes"); 
            
        } catch (Exception e) {
            String errorMessage = "Erro ao salvar o quiz: " + e.getMessage();
            
            session.setAttribute("errorMessage", errorMessage);
           
            response.sendRedirect(request.getContextPath() + "/formulario.jsp"); 
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para salvar um quiz (sempre como um novo).";
    }
}   