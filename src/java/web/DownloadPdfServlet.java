package web;

import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Quiz;
import util.Pdf;

@WebServlet(name = "DownloadPdfServlet", urlPatterns = {"/downloadPdf"})
public class DownloadPdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); 

        Integer sessionUserId = null;
        if (session != null) {
            sessionUserId = (Integer) session.getAttribute("userId");    
        }

        // Se o usuário não estiver logado, proíbe o download
        if (sessionUserId == null || sessionUserId <= 0) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Você precisa estar logado para baixar PDFs.");
            return;
        }

        String quizQuestionsJson = null;
        String quizTopic = null;
        
        // Determina se as respostas corretas devem ser incluídas
        boolean includeCorrectAnswers = "true".equalsIgnoreCase(request.getParameter("includeCorrectAnswers"));

        String idParam = request.getParameter("quizId");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                Integer quizId = Integer.parseInt(idParam);
                Quiz quiz = Quiz.getQuizById(quizId); // Supondo que você tem esse método

                // Quiz encontrado e o userId logado é o dono do quiz
                if (quiz != null && quiz.getUserId() == sessionUserId) { 
                    quizQuestionsJson = quiz.getContent();
                    quizTopic = quiz.getPrompt();
                } else if (quiz != null && quiz.getUserId() != sessionUserId) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Você não tem permissão para baixar este quiz.");
                    return;
                } else { // quiz == null
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz com ID " + quizId + " não encontrado ou não disponível.");
                    return;
                }
            } catch (NumberFormatException e) {
                // Se o ID é inválido, então tentamos carregar da sessão como fallback (caso seja um quiz recém-gerado sem ID válido)
                // Mensagem de debugging caso seja necessário
            } catch (Exception ex) {
               
            }
        }

        // Se o quiz não foi carregado do banco de dados (porque não tinha ID válido ou era um quiz recém-gerado),
        // tentamos carregá-lo da sessão.
        if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty()) {
            quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
            quizTopic = (String) session.getAttribute("quizTopic");
        }

        // Validação final dos dados do quiz antes de gerar o PDF
        if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty() ||
            quizTopic == null || quizTopic.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Não há quiz para baixar. Gere um quiz primeiro ou selecione um existente.");
            return;
        }

        byte[] pdfBytes = null;
        try {
            pdfBytes = Pdf.generateQuizPdf(quizTopic, quizQuestionsJson, includeCorrectAnswers); 

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + quizTopic.replaceAll("[^a-zA-Z0-9_.-]", "_") + "_Quiz.pdf\"");
            response.setContentLength(pdfBytes.length);

            // Saída do PDF
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