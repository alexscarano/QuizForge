package web.quiz;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quiz;
import model.User; 

@WebServlet(name = "SaveQuizServlet", urlPatterns = {"/saveQuiz"})
public class SaveQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(true); 
        Integer userId = null;
        
        if (session.getAttribute("userId") != null) {
            try {
                userId = (Integer) session.getAttribute("userId");
            } catch (ClassCastException e) {
                session.invalidate(); 
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        }

        // Se a sessão não estiver disponível, captura do cookie
        if (userId == null || userId <= 0) {
            String userEmailFromCookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userLogged")) {
                        userEmailFromCookie = cookie.getValue();
                        break;
                    }
                }
            }

        if (userEmailFromCookie != null && !userEmailFromCookie.isEmpty()) {
                try {
                    // Buscar o usuário pelo e-mail no banco de dados 
                    User userFromDB = User.getUserByEmail(userEmailFromCookie); 
                    if (userFromDB != null) {
                        // Recriar atributos da sessão
                        session.setAttribute("user", userFromDB); 
                        session.setAttribute("userLogged", userFromDB.getEmail());
                        session.setAttribute("userId", userFromDB.getId());
                        userId = userFromDB.getId(); // Atualiza userId para o fluxo continuar
                    } else {
                        Cookie deleteCookie = new Cookie("userLogged", "");
                        deleteCookie.setMaxAge(0); // Faz o navegador deletar o cookie
                        deleteCookie.setPath("/");
                        response.addCookie(deleteCookie);
                    }
                } catch (Exception e) {
                    
                 }
            }
        }
        
        if (userId == null || userId <= 0){
            request.setAttribute("errorMessage", "Você precisa estar logado para salvar um quiz.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
        String quizTopic = (String) session.getAttribute("quizTopic");        

        if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty() || 
            quizTopic == null || quizTopic.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Não foi possível salvar o quiz. Dados do quiz ausentes na sessão. Por favor, gere um quiz primeiro.");
            response.sendRedirect(request.getContextPath() + "/formulario.jsp"); // Redireciona para a página de geração
            return;
        }
        
        try {
            Quiz.insertQuiz(quizTopic, quizQuestionsJson, userId);
            
            session.removeAttribute("quizQuestionsJson");
            session.removeAttribute("quizTopic");
            
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