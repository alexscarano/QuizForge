package web; // Ou o pacote do seu servlet de consulta

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quiz; 

@WebServlet(name = "ConsultQuizServlet", urlPatterns = {"/consultQuiz"}) // Exemplo de URL pattern
public class ConsultQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Integer userId = null;
        if (session != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        if (userId == null || userId <= 0) {
            response.sendRedirect(request.getContextPath() + "/login.jsp"); // Ou exiba uma mensagem de erro
            return;
        }

        String quizIdParam = request.getParameter("quizId");
        Quiz quiz = null;

        if (quizIdParam != null && !quizIdParam.isEmpty()) {
            try {
                Integer quizId = Integer.parseInt(quizIdParam);
                quiz = Quiz.getQuizById(quizId); 

                if (quiz != null && quiz.getUserId() == userId) {
                    request.setAttribute("quizQuestionsJson", quiz.getContent());
                    request.setAttribute("quizTopic", quiz.getPrompt());
                    request.setAttribute("quizId", quiz.getId()); 
                    
                    // Opcional: Atualizar a sessão para o caso de o usuário querer salvar novamente
                    session.setAttribute("quizQuestionsJson", quiz.getContent());
                    session.setAttribute("quizTopic", quiz.getPrompt());
                    session.setAttribute("currentQuizId", quiz.getId()); 

                } else if (quiz != null && quiz.getUserId() != userId) {
                    request.getRequestDispatcher("/listQuizzes").forward(request, response); 
                    return;
                } else {
                    request.getRequestDispatcher("/listQuizzes").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("/listQuizzes").forward(request, response);
                return;
            } catch (Exception ex) {
               
            }
        } else {
            // Se não houver quizId na URL (ex: acessou consultQuiz sem ID)
            request.getRequestDispatcher("/listQuizzes").forward(request, response);
            return;
        }
        
        // Encaminha para o formulário.jsp (ou viewQuiz.jsp) para exibição
        request.getRequestDispatcher("/formulario.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para consultar e exibir um quiz salvo.";
    }
}