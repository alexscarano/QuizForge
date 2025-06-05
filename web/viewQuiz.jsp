<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/viewQuiz.css">
        
        <title>Visualizar Quiz Salvo</title>
        <style>
         
        </style>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>

        <div class="form-template">
            <%
                // Recupera os dados do quiz do request (vindos do ConsultQuizServlet)
                String quizQuestionsJson = (String) request.getAttribute("quizQuestionsJson");
                String quizTopic = (String) request.getAttribute("quizTopic");
                             
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
            <%
                }
            %>

            <%
                if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty()) {
            %>
                    <h2>Erro ao Carregar Quiz</h2>
                    <div class="alert alert-danger" role="alert">
                        Não foi possível carregar as questões do quiz.
                        <a href="<%= request.getContextPath() %>/listQuizzes" class="alert-link">Voltar para Meus Quizzes</a>.
                    </div>
            <%
                } else {
                    try {
                        JSONArray questions = new JSONArray(quizQuestionsJson);
                        if (questions.length() == 0) {
            %>
                            <h2>Quiz Vazio</h2>
                            <div class="alert alert-info" role="alert">
                                Este quiz não contém nenhuma questão.
                                <a href="<%= request.getContextPath() %>/listQuizzes" class="alert-link">Voltar para Meus Quizzes</a>.
                            </div>
            <%
                        } else {
            %>
                            <h2>Visualizando Quiz: <%= quizTopic %></h2>
                            <hr/>
                            <%
                                // Loop para exibir cada questão
                                for (int i = 0; i < questions.length(); i++) {
                                    JSONObject question = questions.getJSONObject(i);
                                    String pergunta = question.getString("pergunta");
                                    JSONArray opcoesArray = question.getJSONArray("opcoes");
                                    String respostaCorreta = null; 
                                    if (question.has("respostaCorreta")) {
                                        respostaCorreta = question.getString("respostaCorreta");
                                    }
                            %>
                                <div class="bloco">
                                    <p><%= (i + 1) %>. <%= pergunta %></p>
                                    <div>
                                        <%
                                            // Loop para exibir as opções de cada questão
                                            for (int j = 0; j < opcoesArray.length(); j++) {
                                                String optionText = opcoesArray.getString(j);
                                                String optionLetter = String.valueOf((char) ('A' + j)); // Para A, B, C, D
                                        %>
                                            <div>
                                                <%-- Apenas exibe as opções como texto, sem inputs de rádio --%>
                                                <span><%= optionLetter %>.</span> <label><%= optionText %></label>
                                            </div>
                                        <%
                                            }
                                        %>
                                        <% if (respostaCorreta != null && !respostaCorreta.isEmpty()) { %>
                                        <p class="correct-answer-label">
                                            Resposta Correta: 
                                            <% 
                                                // Se respostaCorreta é uma letra (A, B, C...)
                                                if (respostaCorreta.length() == 1 && Character.isLetter(respostaCorreta.charAt(0))) {
                                                    int correctIndex = respostaCorreta.charAt(0) - 'A';
                                                    if (correctIndex >= 0 && correctIndex < opcoesArray.length()) {
                                                        out.print(opcoesArray.getString(correctIndex));
                                                    } else {
                                                        out.print(respostaCorreta + " (Opção inválida)");
                                                    }
                                                } else { // Se respostaCorreta é o texto da opção
                                                    out.print(respostaCorreta);
                                                }
                                            %>
                                        </p>
                                    <% } %>
                                    </div>
                                </div>
                            <%
                                } // Fim do loop for
                            %>

                            <div class="d-grid gap-2 col-6 mx-auto my-4">
                                <a href="<%= request.getContextPath() %>/listQuizzes" class="btn btn-secondary btn-lg">
                                    <i class="fas fa-arrow-left"></i> Voltar para Meus Quizzes
                                </a>
                            </div>

            <%
                        } // Fim do if (questions.length() == 0)
                    } catch (Exception e) {
            %>
                        <h2>Erro de Processamento</h2>
                        <div class="alert alert-danger" role="alert">
                            Erro ao carregar as questões. A estrutura do quiz salvo pode estar incorreta.
                            <a href="<%= request.getContextPath() %>/listQuizzes" class="alert-link">Voltar para Meus Quizzes</a>.
                        </div>
            <%
                    } // Fim do try-catch
                } // Fim do if (quizQuestionsJson == null...)
            %>
        </div>

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-e9sGFM9f3X6S5r9I01h9X6y6aR6q2aQ9t0p1l1C7p2n8D5r0q3a9Z9t2w8o7a6" crossorigin="anonymous"></script>
    </body>
</html>