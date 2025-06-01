/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import ia.Gemini;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "GenerateQuizServlet", urlPatterns = {"/generateQuiz"})
public class GenerateQuizServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quizTopic = request.getParameter("captureQuiz");

        // 2. Validar o tópico
        if (quizTopic == null || quizTopic.trim().isEmpty()) {
            request.setAttribute("errorMessage", "O tópico do quiz não pode estar vazio.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String rawJsonResponseFromGemini = null;
        try {
            // 3. Chamar o método da sua classe Gemini para interagir com a API
            // Passamos apenas o tópico, já que o número de questões é fixo na IA
            rawJsonResponseFromGemini = Gemini.getCompletion(quizTopic);

            // 4. Armazenar a resposta JSON e o tópico na requisição
            request.setAttribute("quizQuestionsJson", rawJsonResponseFromGemini);
            request.setAttribute("quizTopic", quizTopic); // Pode ser útil exibir na JSP do quiz

            // 5. Encaminhar para a JSP dinâmica que exibirá o quiz
            request.getRequestDispatcher("/formulario.jsp").forward(request, response);

        } catch (Exception e) {
            // 6. Tratar erros da API ou do parsing JSON
            String userFriendlyErrorMessage = "Ocorreu um erro ao gerar o quiz. Por favor, tente novamente mais tarde.";
            
            if (e.getMessage() != null && e.getMessage().contains("Resposta da IA não está em formato JSON esperado")) {
                 userFriendlyErrorMessage = "A IA não conseguiu gerar o quiz no formato esperado para o tópico '" + quizTopic + "'. Tente refinar seu tópico ou gere novamente.";
            }
            request.setAttribute("errorMessage", userFriendlyErrorMessage);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
