package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/loginServlet"}) // Lembre-se de verificar o case aqui!
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        User userAuth = null;

        try {
            userAuth = User.getUser(email, password);
            if (userAuth != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userAuth);
                session.setAttribute("userLogged", userAuth.getEmail());
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("errorMessage", "E-mail ou senha inválidos.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ocorreu um erro inesperado no servidor. Tente novamente mais tarde." + e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
