<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/formulario.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <title>Questionário QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>

        <div class="form-template">
            <%-- Exibe mensagens de sucesso (você tinha um erro, não erro-message) --%>
            <%
                String successMessage = (String) request.getAttribute("successMessage");
                String errorMessage = (String) request.getAttribute("errorMessage"); // Adiciona o errorMessage aqui
                if (successMessage != null && !successMessage.trim().isEmpty()) {
            %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="fas fa-check-circle"></i>
                        <%= successMessage %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
            <%
                }
                if (errorMessage != null && !errorMessage.trim().isEmpty()) { // Adiciona exibição de erro
            %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fas fa-exclamation-triangle"></i>
                        <%= errorMessage %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
            <%
                }
            %>
             
            <%
                String quizQuestionsJson = (String) request.getAttribute("quizQuestionsJson");
                String quizTopic = (String) request.getAttribute("quizTopic");
                Integer quizId = (Integer) request.getAttribute("quizId"); // Carrega da request primeiro

                // >>> IMPORTANTE: Se o quiz não está nos atributos da requisição (por exemplo, após um redirect
                // ou um erro no SubmitQuizServlet que fez forward de volta), tenta pegar da sessão.
                if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty()) {
                    quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
                }
                if (quizTopic == null || quizTopic.trim().isEmpty()) {
                    quizTopic = (String) session.getAttribute("quizTopic");
                }
                // Adicione o fallback para quizId também
                if (quizId == null) {
                    quizId = (Integer) session.getAttribute("currentQuizId"); // ID do último quiz gerado/salvo na sessão
                }

                if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty()) {
            %>
                    <div class="alert alert-warning" role="alert">
                        Nenhum quiz foi gerado ou carregado. <a href="<%= request.getContextPath() %>/index.jsp" class="alert-link">Crie um novo quiz</a>.
                    </div>
            <%
                } else {
                    JSONArray questions = null;
                    try {
                        questions = new JSONArray(quizQuestionsJson);
                    } catch (Exception e) {
                        System.err.println("Erro ao parsear JSON do quiz no formulario.jsp: " + e.getMessage());
                        e.printStackTrace();
            %>
                        <div class="alert alert-danger" role="alert">
                            Ocorreu um erro ao carregar as perguntas do quiz. Por favor, tente novamente.
                        </div>
            <%
                        questions = null; // Garante que o loop não será executado
                    }

                    if (questions != null && questions.length() > 0) {
            %>
            <h2 class="quiz-title">Tema: <%= quizTopic != null ? quizTopic : "Tema Desconhecido" %></h2>

                        <form method="post">
                            <%-- Campo hidden para o quizId (se o quiz foi carregado do DB ou salvo e está na sessão) --%>
                            <% if (quizId != null) { %>
                                <input type="hidden" name="quizId" value="<%= quizId %>">
                            <% } %>

                            <%
                                for (int i = 0; i < questions.length(); i++) {
                                    JSONObject question = questions.getJSONObject(i);
                                    String pergunta = question.getString("pergunta");
                                    JSONArray opcoesArray = question.getJSONArray("opcoes");
                            %>
                            <div class="bloco">
                                <p class="question-text"><%= (i + 1) %>. <%= pergunta %></p>
                                <div class="options-group">
                                    <%
                                        for (int j = 0; j < opcoesArray.length(); j++) {
                                            String optionText = opcoesArray.getString(j);
                                            String optionLetter = String.valueOf((char) ('A' + j));
                                    %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="q_<%= i %>" id="q_<%= i %>_<%= optionLetter %>" value="<%= optionLetter %>" required>
                                        <label class="form-check-label" for="q_<%= i %>_<%= optionLetter %>"><%= optionText %></label>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                                </div>
                            <%
                                } // Fim do loop de perguntas
                            %>
                            
                            <div class="btn-group-custom mt-4">
                                <button type="submit" formaction="<%= request.getContextPath() %>/quizResults" class="btn btn-warning me-2">
                                    <i class="fas fa-check"></i> Ver Resultado
                                </button>
                                    
                                <button type="submit" formaction="<%= request.getContextPath() %>/downloadPdf" formmethod="get" class="btn btn-danger me-2">
                                    <i class="fas fa-file-pdf"></i> Baixar PDF (sem respostas)
                                </button>
                                    
                            </div>
                        </form>
            <%
                    } else {
            %>
                        <div class="alert alert-info" role="alert">
                            Nenhuma pergunta encontrada para este quiz. Tente gerar novamente.
                        </div>
            <%
                    }
                }
            %>
        </div>

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>