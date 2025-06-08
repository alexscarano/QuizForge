package web.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.InputValidation;
import util.InputSanitization;
import model.User;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String loginRaw = request.getParameter("login").trim().toLowerCase();
        String emailRaw = request.getParameter("email").trim();
        String passwordRaw = request.getParameter("password").trim();
        String confirmPasswordRaw = request.getParameter("confirmPassword").trim();
        
        // Sanitização para evitar XSS
        String login = InputSanitization.removeHtmlTags(loginRaw);
        String email = InputSanitization.removeHtmlTags(emailRaw);
        String password = InputSanitization.removeHtmlTags(passwordRaw);
        String confirmPassword = InputSanitization.removeHtmlTags(confirmPasswordRaw);
        
        if (!InputValidation.isNotNullOrEmpty(login) ||
            !InputValidation.isNotNullOrEmpty(email) ||
            !InputValidation.isNotNullOrEmpty(password) ||
            !InputValidation.isNotNullOrEmpty(confirmPassword)) {
            request.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
        
        String trimmedLogin = login.trim();
        
        if (trimmedLogin.contains(" ")){
            request.setAttribute("errorMessage", "O login não deve ter espaço.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "As senhas não coincidem.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
        
        login = login.trim().replaceAll("\\s+", "").toLowerCase();
        
        if (!InputValidation.isValidUsername(login)){
            request.setAttribute("errorMessage", "Por favor, insira um login válido.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
        
        if (!InputValidation.isValidEmail(email)){
            request.setAttribute("errorMessage", "Por favor, insira um e-mail válido.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
        
        if (!InputValidation.isValidPassword(password)){
            request.setAttribute("errorMessage", "Por favor, insira uma senha válida.");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }
            
        try {
            User.insertUser(login, email, password);
            response.sendRedirect(request.getContextPath() + "/login.jsp?registerSuccess=true");
        }        
        catch(Exception e){
            String errorMessage = "Ocorreu um erro ao tentar cadastrar o usuário.";
            // Mensagens mais específicas para o usuário
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("'user_login'")) {
                    errorMessage = "Este login já está em uso. Por favor, escolha outro.";
                } else if (e.getMessage().contains("'user_email'")) {
                    errorMessage = "Este e-mail já está cadastrado. Por favor, use outro ou faça login.";
                }
            }
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Um servlet responsável por cadastrar o usuário";
    }

}