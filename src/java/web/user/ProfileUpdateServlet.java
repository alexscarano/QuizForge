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
import util.Password;

@WebServlet(name = "ProfileUpdateServlet", urlPatterns = {"/profileUpdate"})
public class ProfileUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession(true); 
        User currentUser = null;
        Integer userId = null;

        if (session.getAttribute("user") != null) {
            try {
                currentUser = (User) session.getAttribute("user");
                userId = currentUser.getId(); // Captura o ID do usuário logado
            } catch (ClassCastException e) {
                session.invalidate();
                response.sendRedirect("login.jsp");
                return;
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
        
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
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
                        // Atualiza o userLogged na sessão, não no cookie
                        session.setAttribute("userLogged", newEmail); 
                        Cookie loginCookie = new Cookie("userLogged", newEmail);
                        loginCookie.setMaxAge(60 * 60 * 24 * 7); // 7 dias 
                        loginCookie.setPath("/");
                        response.addCookie(loginCookie);    
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
                    
                    if (Password.checkPassword(currentPassword, currentUser.getPassword())) {
                        User.updateUserPassword(currentUser.getEmail(), newPassword);
                        currentUser.setPassword(Password.hashPassword(newPassword));
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
                        
                        if (!Password.checkPassword(deletePassword, user.getPassword())) {
                            request.setAttribute("errorMessage", "Senha incorreta. Conta não excluída.");
                            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                            return;
                        }
                        // Invalidar sessão e destruir o cookie
                        User.deleteUser(userEmail);
                        session.invalidate();
                        Cookie deleteCookie = new Cookie("userLogged", "");
                        deleteCookie.setMaxAge(0); 
                        deleteCookie.setPath("/");
                        response.addCookie(deleteCookie);
                        
                        response.sendRedirect("login.jsp");
                        return;
         
                    } catch (Exception e) {
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
    }

}
