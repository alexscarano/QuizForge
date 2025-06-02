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
import jakarta.servlet.http.HttpSession;
import model.Quiz;


@WebServlet(name = "SaveQuizServlet", urlPatterns = {"/saveQuiz"})
public class SaveQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false); 

        if (session == null) {
            request.setAttribute("errorMessage", "Sessão expirada. Por favor, gere um novo quiz para salvar.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String quizQuestionsJson =  request.getParameter("quizQuestionsJson");
        String quizTopic = (String) request.getParameter("quizTopic");
        Integer userId = (Integer) session.getAttribute("userId"); 

        if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty() || 
            quizTopic == null || quizTopic.trim().isEmpty() ||
            userId == null || userId <= 0) { // userId deve ser válido
            
            request.setAttribute("errorMessage", "Não foi possível salvar o quiz. Dados incompletos na sessão. Certifique-se de estar logado e ter gerado um quiz recentemente.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {
            Quiz.insertQuiz(quizTopic, quizQuestionsJson, userId);
            request.setAttribute("successMessage", "Quiz salvo com sucesso!");
            request.setAttribute("quizQuestionsJson", quizQuestionsJson);
            request.setAttribute("quizTopic", quizTopic);
            request.getRequestDispatcher("/formulario.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao salvar o quiz: " + e.getMessage());
            request.setAttribute("quizQuestionsJson", quizQuestionsJson);
            request.setAttribute("quizTopic", quizTopic);
            request.getRequestDispatcher("/formulario.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
