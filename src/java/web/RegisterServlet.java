/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.PasswordUtils;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login = request.getParameter("login").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();
        
        if (login == null || login.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
         
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "As senhas não coincidem.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
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
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
