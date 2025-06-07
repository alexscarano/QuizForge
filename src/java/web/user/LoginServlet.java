package web.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailOrLogin = request.getParameter("emailOrLogin").trim();
        String password = request.getParameter("password").trim();
        String rememberMe = request.getParameter("rememberMe");
        User userAuth = null;

        try {
            userAuth = User.getUser(emailOrLogin, password);
            if (userAuth != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userAuth);
                session.setAttribute("userLogged", userAuth.getEmail());
                session.setAttribute("userId", userAuth.getId()); 

                if (rememberMe != null) {
                    Cookie loginCookie = new Cookie("userLogged", userAuth.getEmail());
                    loginCookie.setMaxAge(60 * 60 * 24 * 7); // 7 dias
                    loginCookie.setPath("/"); 
                    response.addCookie(loginCookie); 
                }

                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("errorMessage", "E-mail ou senha inválidos.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ocorreu um erro inesperado no servidor. Tente novamente mais tarde. " + e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    
       @Override
    public String getServletInfo() {
        return "Um Servlet para login do usuário";
    }
}
