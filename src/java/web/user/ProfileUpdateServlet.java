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
import util.InputValidation;

@WebServlet(name = "ProfileUpdateServlet", urlPatterns = {"/profileUpdate"})
public class ProfileUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(true); 
        User currentUser = null;
        Integer userId = null;


        if (session.getAttribute("user") != null) {
            try {
                currentUser = (User) session.getAttribute("user");
                userId = currentUser.getId();
            } catch (ClassCastException e) {
                session.invalidate(); // Invalida a sessão corrompida
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        }
        

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

            if (InputValidation.isNotNullOrEmpty(userEmailFromCookie)) { // Use InputValidator aqui
                try {
                    User userFromDB = User.getUserByEmail(userEmailFromCookie); 
                    if (userFromDB != null) {
                        // Recria/atualiza os atributos da sessão
                        session.setAttribute("user", userFromDB);
                        session.setAttribute("userLogged", userFromDB.getEmail());
                        session.setAttribute("userId", userFromDB.getId());
                        currentUser = userFromDB; 
                        userId = userFromDB.getId(); 
                    } else {
                        // Usuário do cookie não encontrado no DB (cookie pode ser inválido/antigo)
                        Cookie deleteCookie = new Cookie("userLogged", "");
                        deleteCookie.setMaxAge(0); 
                        deleteCookie.setPath("/");
                        response.addCookie(deleteCookie);
                    }
                } catch (Exception e) {

                }
            }
        }
        
        if (currentUser == null || userId == null || userId <= 0) {
            session.setAttribute("errorMessage", "Sua sessão expirou ou você não está logado. Por favor, faça login novamente.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        // action do form
        String action = request.getParameter("action"); 
        try {
            switch (action) {
                case "updateLogin":
                    String newLogin = request.getParameter("newLogin");
                    
                    if (!InputValidation.isNotNullOrEmpty(newLogin)) {
                        request.setAttribute("errorMessage", "O campo 'Login' não pode estar vazio.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    if (!InputValidation.isValidUsername(newLogin)) { // Adiciona validação de formato
                        request.setAttribute("errorMessage", "Formato de login inválido. Use letras, números, hífens ou underscores (3-20 caracteres).");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    if (currentUser.getLogin().equals(newLogin)){
                        request.setAttribute("errorMessage", "O novo login deve ser diferente do atual.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    
                    // Adicione verificação de login já existente no DB
                    if (User.getUserByLogin(newLogin) != null) {
                        request.setAttribute("errorMessage", "Este login já está em uso. Por favor, escolha outro.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }

                    User.updateUserLogin(newLogin, currentUser.getEmail());
                    currentUser.setLogin(newLogin);
                    session.setAttribute("user", currentUser); // Atualiza o objeto User na sessão
                    request.setAttribute("successMessage", "Login atualizado com sucesso!");
                    break;

                case "updateEmail":
                    String newEmail = request.getParameter("newEmail");
                    
                    if (!InputValidation.isNotNullOrEmpty(newEmail)) {
                        request.setAttribute("errorMessage", "O campo 'Email' não pode estar vazio.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    if (!InputValidation.isValidEmail(newEmail)) { // Adiciona validação de formato
                        request.setAttribute("errorMessage", "Formato de e-mail inválido.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    if (currentUser.getEmail().equals(newEmail)){
                        request.setAttribute("errorMessage", "O novo e-mail deve ser diferente do atual.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    // Adicione verificação de e-mail já existente no DB
                    if (User.getUserByEmail(newEmail) != null) {
                        request.setAttribute("errorMessage", "Este e-mail já está em uso. Por favor, escolha outro.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }

                    User.updateUserEmail(currentUser.getLogin(), newEmail);
                    currentUser.setEmail(newEmail);
                    session.setAttribute("user", currentUser);
                    session.setAttribute("userLogged", newEmail); // Atualiza o email na sessão
                    
                    // Atualiza o cookie também, para manter a consistência
                    Cookie loginCookie = new Cookie("userLogged", newEmail);
                    loginCookie.setMaxAge(60 * 60 * 24 * 7); // 7 dias 
                    loginCookie.setPath("/");
                    response.addCookie(loginCookie);
                    
                    request.setAttribute("successMessage", "E-mail atualizado com sucesso!");
                    break;
                    
                case "updatePassword":
                    String currentPassword = request.getParameter("currentPassword");
                    String newPassword = request.getParameter("newPassword");
                    
                    if (!InputValidation.isNotNullOrEmpty(currentPassword) || !InputValidation.isNotNullOrEmpty(newPassword)) {
                        request.setAttribute("errorMessage", "Os campos de senha não podem estar vazios.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    if (currentPassword.equals(newPassword)){
                        request.setAttribute("errorMessage", "A nova senha deve ser diferente da atual.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    if (!InputValidation.isValidPassword(newPassword)) { 
                        request.setAttribute("errorMessage", "A nova senha é muito fraca. Ela deve ter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }

                    if (Password.checkPassword(currentPassword, currentUser.getPassword())) {
                        User.updateUserPassword(currentUser.getEmail(), newPassword); 
                        currentUser.setPassword(Password.hashPassword(newPassword));
                        session.setAttribute("user", currentUser);
                        request.setAttribute("successMessage", "Senha atualizada com sucesso!");
                    } else {
                        request.setAttribute("errorMessage", "Senha atual incorreta.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    break;
                    
                case "deleteAccount": 
                    String userEmailToDelete = (String) session.getAttribute("userLogged");
                    String deletePassword = request.getParameter("deletePassword");
                    
                    if (!InputValidation.isNotNullOrEmpty(deletePassword)) {
                        request.setAttribute("errorMessage", "Por favor, digite sua senha para confirmar a exclusão da conta.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    
                    // Reutiliza o currentUser que já está autenticado
                    if (!Password.checkPassword(deletePassword, currentUser.getPassword())) {
                        request.setAttribute("errorMessage", "Senha incorreta. A conta não foi excluída.");
                        request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
                        return;
                    }
                    
                    User.deleteUser(userEmailToDelete);
                    session.invalidate(); // Invalida a sessão
                    // Deleta o cookie
                    Cookie deleteCookie = new Cookie("userLogged", "");
                    deleteCookie.setMaxAge(0); 
                    deleteCookie.setPath("/");
                    response.addCookie(deleteCookie);
                    
                    response.sendRedirect(request.getContextPath() + "/login.jsp"); // Redireciona para o login após exclusão
                    return; // Retorna para evitar o forward final

                default:
                    request.setAttribute("errorMessage", "Ação inválida.");
                    break;
            }
            
            // Redireciona para a mesma página com mensagem de sucesso (se não foi uma exclusão)
            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response); 
            
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro inesperado ao atualizar dados: " + e.getMessage());
            request.getRequestDispatcher("mudar_usuario.jsp").forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Um servlet responsável por operações de update de dados do usuário";
    }
}