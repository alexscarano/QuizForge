package web.quiz;

import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie; 
import model.Quiz;
import model.User; 
import util.Pdf;
import util.InputValidation; 

@WebServlet(name = "DownloadPdfServlet", urlPatterns = {"/downloadPdf"})
public class DownloadPdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true); 

        Integer sessionUserId = (Integer) session.getAttribute("userId");
        
        if (sessionUserId == null || sessionUserId <= 0) {
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

            if (InputValidation.isNotNullOrEmpty(userEmailFromCookie)) {
                try {
                    User userFromDB = User.getUserByEmail(userEmailFromCookie); // Assumindo que User.getUserByEmail existe
                    if (userFromDB != null) {
                        session.setAttribute("user", userFromDB);
                        session.setAttribute("userLogged", userFromDB.getEmail());
                        session.setAttribute("userId", userFromDB.getId());
                        sessionUserId = userFromDB.getId(); 
                    } else {
                        // Invalida o cookie no cliente
                        Cookie deleteCookie = new Cookie("userLogged", "");
                        deleteCookie.setMaxAge(0); // Imediatamente expira o cookie
                        deleteCookie.setPath("/"); // Garante que o cookie seja removido do caminho correto
                        response.addCookie(deleteCookie);
                        session.invalidate(); 
                    }
                } catch (Exception e) {
                }
            }
        }

        // Se o usuário ainda não estiver logado após a tentativa de revalidação, proíbe o download
        if (sessionUserId == null || sessionUserId <= 0) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Você precisa estar logado para baixar PDFs.");
            return;
        }

        String quizQuestionsJson = null;
        String quizTopic = null;
        
        boolean includeCorrectAnswers = "true".equalsIgnoreCase(request.getParameter("includeCorrectAnswers"));

        String idParam = request.getParameter("quizId");
        
        if (idParam == null || idParam.isEmpty()) {
            // Esse é o cenário onde o quiz não foi salvo no DB ainda.
            quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
            quizTopic = (String) session.getAttribute("quizTopic");
            
            // Se ainda não temos o quiz, retornamos erro
            if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty() ||
                quizTopic == null || quizTopic.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Não há quiz para baixar. Gere um quiz primeiro.");
                return;
            }
            
        } else {
            // Cenário: quizId presente na URL (quiz já salvo no DB)
            Integer quizId = null;
            try {
                quizId = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do quiz inválido: " + idParam);
                return;
            }

            try {
                Quiz quiz = Quiz.getQuizById(quizId); 

                if (quiz != null) {
                    // ** Verificação de propriedade do quiz **
                    if (quiz.getUserId() != sessionUserId) { // Usa o sessionUserId revalidado/obtido
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Você não tem permissão para baixar este quiz.");
                        return;
                    }
                    quizQuestionsJson = quiz.getContent();
                    quizTopic = quiz.getPrompt();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz com ID " + quizId + " não encontrado.");
                    return;
                }
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar os dados do quiz do banco de dados.");
                return;
            }
        }

        // Se chegarmos aqui, temos quizQuestionsJson e quizTopic, seja da sessão ou do DB.
        byte[] pdfBytes = null;
        try {
            pdfBytes = Pdf.generateQuizPdf(quizTopic, quizQuestionsJson, includeCorrectAnswers); 

            response.setContentType("application/pdf");
            String safeQuizTopic = quizTopic.replaceAll("[^a-zA-Z0-9_.-]", "_");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + safeQuizTopic + "_Quiz.pdf\"");
            response.setContentLength(pdfBytes.length);

            try (OutputStream out = response.getOutputStream()) {
                out.write(pdfBytes);
                out.flush();
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o PDF do quiz: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para download de quiz em PDF.";
    }
}