package web.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Quiz;
import model.User;

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
        
        String userEmailFromCookie = null;
        if (userId == null || userId <= 0){
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie cookie : cookies){
                     if (cookie.getName().equals("userLogged")) {
                        userEmailFromCookie = cookie.getValue();
                        break;
                    }
                }
            }
        }
        
        if (userEmailFromCookie != null && !userEmailFromCookie.isEmpty()) {
                try {
                    /* Buscando o usuário pelo e-mail no banco de dados, 
                    pois o cookie tem o valor do email */
                   User userFromDB = User.getUserByEmail(userEmailFromCookie); 
                    if (userFromDB != null) {
                        // Recriando atributos da sessão
                        session.setAttribute("user", userFromDB);
                        session.setAttribute("userLogged", userFromDB.getEmail());
                        session.setAttribute("userId", userFromDB.getId());
                        userId = userFromDB.getId(); // Atualiza userId para que o fluxo continue                        System.out.println("DEBUG: Sessão recriada para userId: " + userId);
                    } else {
                        // Deletando o cookie e seus dados
                        Cookie deleteCookie = new Cookie("userLogged", "");
                        deleteCookie.setMaxAge(0); 
                        deleteCookie.setPath("/");
                        response.addCookie(deleteCookie);
                    }
                } catch (Exception e) {
                    return;
                }
            }
             
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try {
            quizzes = Quiz.getQuizzesByUserId(userId);
            request.setAttribute("quizzes", quizzes); 
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao carregar seus quizzes: " + e.getMessage());
        }

        request.getRequestDispatcher("/listQuizzes.jsp").forward(request, response);
    }
        
      
    @Override
    public String getServletInfo() {
        return "Um Servlet para listar os quizzes já feitos pelo usuário";
    }

}
