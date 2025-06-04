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
        
        // --- PONTO CHAVE DE MUDANÇA: PEGAR DA SESSÃO, NÃO DO REQUEST.PARAMETER ---
        String quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
        String quizTopic = (String) session.getAttribute("quizTopic");        

        // 1. Verifique se a sessão existe e se o userId está nela
        if (session == null || session.getAttribute("userId") == null) {
            // Se a sessão expirou ou o usuário não está logado
            request.setAttribute("errorMessage", "Sessão expirada ou você não está logado. Por favor, faça login e gere um novo quiz para salvar.");
            request.getRequestDispatcher("/login.jsp").forward(request, response); // Redireciona para o login
            return;
        }

        // Tente obter o userId da sessão de forma segura
        Integer userId = null;
        try {
            userId = (Integer) session.getAttribute("userId");
        } catch (ClassCastException e) {
            System.err.println("Erro ao converter userId da sessão em SaveQuizServlet: " + e.getMessage());
        }

        // Validação inicial do userId
        if (userId == null || userId <= 0) {
            request.setAttribute("errorMessage", "ID de usuário inválido na sessão. Faça login novamente.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 2. Validação dos dados do quiz obtidos da sessão
        if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty() || 
            quizTopic == null || quizTopic.trim().isEmpty()) {
            
            // Se os dados do quiz não estão na sessão (ou estão vazios)
            // Isso pode acontecer se o usuário veio direto para esta página sem gerar um quiz
            // ou se a sessão foi limpa ou os atributos não foram setados corretamente.
            session.setAttribute("errorMessage", "Não foi possível salvar o quiz. Dados do quiz ausentes na sessão. Por favor, gere um quiz primeiro.");
            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redireciona para a página inicial para gerar quiz
            return;
        }
        
        // Se tudo estiver OK, tenta salvar
        try {
            Quiz.insertQuiz(quizTopic, quizQuestionsJson, userId);
            
            // Use a sessão para mensagens de sucesso que persistem após um redirecionamento
            session.setAttribute("successMessage", "Quiz salvo com sucesso!");
            
            // Redirecione para a lista de quizzes, que é mais lógico após salvar.
            // Isso também evita que a mensagem de sucesso seja exibida novamente se a página for recarregada.
            response.sendRedirect(request.getContextPath() + "/listQuizzes"); 
            
        } catch (Exception e) {
            e.printStackTrace(); // Logar o erro completo no console do servidor
            String errorMessage = "Erro ao salvar o quiz: " + e.getMessage();
            
            // Use a sessão para mensagens de erro que persistem após um redirecionamento
            session.setAttribute("errorMessage", errorMessage);
            
            // Redirecione de volta para o formulário ou para uma página de erro
            response.sendRedirect(request.getContextPath() + "/formulario.jsp"); // Ou para "/error.jsp"
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para salvar um quiz (sempre como um novo).";
    }
}   