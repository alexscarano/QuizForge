<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/formulario.css">
        <link rel="stylesheet" href="assets/css/quizResult.css"> <%-- Verifique este arquivo --%>
        <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <title>Resultados do Quiz</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>

        <div class="form-template">
            <h2 class="quiz-title text-center mb-4">Resultados do Quiz</h2> <%-- Adicionado text-center e margin --%>

            <%
                String quizTopic = (String) request.getAttribute("quizTopic");
                Integer totalQuestions = (Integer) request.getAttribute("totalQuestions");
                Integer correctAnswersCount = (Integer) request.getAttribute("correctAnswersCount");
                List<String> quizResults = (List<String>) request.getAttribute("quizResults");
                Integer quizIdFromRequest = (Integer) request.getAttribute("quizId"); 
                boolean isQuizAlreadySaved = (quizIdFromRequest != null);
            %>

            <h3 class="topic-heading">Tópico: <%= quizTopic != null ? quizTopic : "Quiz sem Tópico Definido" %></h3>

            <div class="score-summary mt-3 mb-4 p-3 bg-light border rounded"> <%-- Adicionado padding, background, border --%>
                <p class="mb-1">Você acertou **<%= correctAnswersCount != null ? correctAnswersCount : 0 %>** de **<%= totalQuestions != null ? totalQuestions : 0 %>** perguntas.</p>
                <% if (totalQuestions != null && totalQuestions > 0) {
                    double percentage = (double) correctAnswersCount / totalQuestions * 100;
                %>
                    <p class="mb-0">Sua pontuação: **<%= String.format("%.2f", percentage) %>%**</p>
                <% } %>
            </div>

            <div class="questions-feedback mt-4">
                <h4 class="mb-3">Detalhamento das Respostas:</h4>
                <%
                    if (quizResults != null && !quizResults.isEmpty()) {
                        for (String feedback : quizResults) {
                %>
                            <div class="feedback-item mb-2 p-2 border rounded"> <%-- Novo div para cada item de feedback --%>
                                <%= feedback %>
                            </div>
                <%
                        }
                    } else {
                %>
                        <p class="text-muted">Nenhum feedback detalhado disponível.</p>
                <%
                    }
                %>
            </div>

            <div class="btn-group-custom mt-5 d-flex justify-content-center"> <%-- Adicionado d-flex e justify-content-center para centralizar botões --%>
                <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-primary me-3"> <%-- Aumentei margin --%>
                    <i class="fas fa-plus-circle"></i> Criar Novo Quiz
                </a>
                <%-- Botão para Salvar Quiz (aparece apenas se o quiz NÃO foi carregado do banco de dados) --%>
                <% if (!isQuizAlreadySaved) { %>
                    <form action="<%= request.getContextPath() %>/saveQuiz" method="post" class="me-3">
                        <button type="submit" class="btn btn-success">
                            <i class="fas fa-save"></i> Salvar Quiz
                        </button>
                    </form>
                <% } %>               
            </div>
        </div>

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>