package web.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Quiz;

@WebServlet(name = "ListQuizzesServlet", urlPatterns = {"/listQuizzes"})
public class ListQuizzesServlet extends HttpServlet {
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer userId = null; 

        if (session != null && session.getAttribute("userId") != null) {
            try {
                userId = (Integer) session.getAttribute("userId");
            } catch (ClassCastException e) {
                
            }
        }
        
        if (userId == null || userId <= 0){
            // verificação adicional
            request.setAttribute("errorMessage", "Você precisa estar logado para ver seus quizzes.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
             
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            quizzes = Quiz.getQuizzesByUserId(userId);
            request.setAttribute("quizzes", quizzes); 
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao carregar seus quizzes: " + e.getMessage());
        }

        // Encaminha para a JSP para exibir a lista
        request.getRequestDispatcher("/listQuizzes.jsp").forward(request, response);
    }
        
      
    @Override
    public String getServletInfo() {
        return "Um Servlet para listar os quizzes já feitos pelo usuário";
    }

}
