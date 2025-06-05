package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordUtils;

@WebServlet(name = "ProfileUpdateServlet", urlPatterns = {"/profileUpdate"})
public class ProfileUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        /* 
        Capturando o atributo de sessão de usuário e fazendo casting da 
        string da sessão para um objeto do tipo user
        */
        User currentUser = (User) session.getAttribute("user");
        String action = request.getParameter("action");
        
        try {
            switch (action) {
                case "updateLogin":
                    String newLogin = request.getParameter("newLogin");
                    
                    if (currentUser.getLogin().equals(newLogin)){
                        request.setAttribute("errorMessage", "Coloque um login diferente do antigo.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                   
                    if (newLogin != null && !newLogin.trim().isEmpty()) {
                        User.updateUserLogin(newLogin, currentUser.getEmail());
                        currentUser.setLogin(newLogin);
                        session.setAttribute("user", currentUser);
                    }
                    break;

                case "updateEmail":
                    String newEmail = request.getParameter("newEmail");
                    
                    if (currentUser.getEmail().equals(newEmail)){
                        request.setAttribute("errorMessage", "Coloque um email diferente do antigo.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    
                    if (newEmail != null && !newEmail.trim().isEmpty()) {
                        User.updateUserEmail(currentUser.getLogin(), newEmail);
                        currentUser.setEmail(newEmail);
                        session.setAttribute("user", currentUser);
                    }
                    break;

                case "updatePassword":
                    String currentPassword = request.getParameter("currentPassword");
                    String newPassword = request.getParameter("newPassword");
                   
                    if (currentPassword.equals(newPassword)){
                        request.setAttribute("errorMessage", "Por favor coloque uma senha diferente da atual.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    
                    if (PasswordUtils.checkPassword(currentPassword, currentUser.getPassword())) {
                        User.updateUserPassword(currentUser.getEmail(), newPassword);
                        currentUser.setPassword(PasswordUtils.hashPassword(newPassword));
                        session.setAttribute("user", currentUser);
                    }              
                    else {
                          request.setAttribute("errorMessage", "Senha atual incorreta.");
                          request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    
                    break;
                case "deleteAccount": 
                     String userEmail = (String) session.getAttribute("userLogged");
                     String deletePassword = request.getParameter("deletePassword");
                     
                     try {
                        User user = User.getUserByEmail(userEmail);
                        if(user == null){
                            request.setAttribute("errorMessage", "Usuário não encontrado.");
                            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                            return;
                        }
                        
                        if (!PasswordUtils.checkPassword(deletePassword, user.getPassword())) {
                            request.setAttribute("errorMessage", "Senha incorreta. Conta não excluída.");
                            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                            return;
                        }
                       
                        User.deleteUser(userEmail);
                        
                        session.invalidate();
                        
                        response.sendRedirect("login.jsp");
                        return;

                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute("errorMessage", "Erro ao excluir a conta.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);                 
                    }                 
                    break;        
            }
            // mostra mensagem de dado atualizado
            request.setAttribute("successMessage", "Dado atualizado com sucesso.");
            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);  
            
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao atualizar dados: " + e.getMessage());
            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);;
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Um servlet responsável por operações de update de dados do usuário";
    }// </editor-fold>

}
