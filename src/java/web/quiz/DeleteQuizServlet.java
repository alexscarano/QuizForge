package web.quiz;

import model.Quiz;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteQuizServlet", urlPatterns = {"/deleteQuiz"})
public class DeleteQuizServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer userId = null;

        if (session != null && session.getAttribute("userId") != null) {
            try {
                userId = (Integer) session.getAttribute("userId");
            } catch (ClassCastException e) {

            }
        }
        
        // verificação adicional
        if (userId == null || userId <= 0){
            request.setAttribute("errorMessage", "Você precisa estar logado para ver seus quizzes.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        int quizIdToDelete = -1;
        try {
            quizIdToDelete = Integer.parseInt(request.getParameter("quizId"));
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "ID do quiz inválido para exclusão.");
            response.sendRedirect(request.getContextPath() + "/listQuizzes"); // Redireciona de volta para a lista
            return;
        }

        try {
            Quiz quiz = Quiz.getQuizById(quizIdToDelete);

            if (quiz != null && quiz.getUserId() == userId) {
                boolean deleted = Quiz.deleteQuiz(quizIdToDelete);
                if (deleted) {
                    session.setAttribute("successMessage", "Quiz (ID: " + quizIdToDelete + ") excluído com sucesso!");
                } else {
                    session.setAttribute("errorMessage", "Não foi possível excluir o quiz (ID: " + quizIdToDelete + "). Tente novamente.");
                }
            } else {
                session.setAttribute("errorMessage", "Você não tem permissão para excluir este quiz ou ele não existe.");
            }
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Erro ao excluir o quiz: " + e.getMessage());
        }

        // forçando o navegador a fazer uma nova requisição GET para /listQuizzes
        response.sendRedirect(request.getContextPath() + "/listQuizzes");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para excluir quizzes.";
    }
}