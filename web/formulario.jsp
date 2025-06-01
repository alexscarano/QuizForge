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
        <title>Quiz Gerado</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>

        <style>
            .form-template {
                max-width: 100vh;
                background-color: #ebebeb;
                padding: 0rem 2rem;
                margin: 2rem auto; /* Centraliza o bloco na página */
            }

            .form-template h2 {
                margin: 2rem 0rem;
            }

            .bloco {
                margin-bottom: 3rem; /* Espaçamento entre as questões */
                padding: 1rem;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #fcfcfc;
            }

            .bloco p {
                font-weight: bold;
                margin-bottom: 1rem;
                color: #333;
            }

            .bloco form {
                margin-top: 1rem;
            }

            .bloco form div {
                margin: 0.5rem 0; /* Espaçamento entre as opções */
            }

            .bloco form input[type="radio"] {
                margin-right: 0.5rem; /* Espaçamento entre o rádio e o label */
            }

            .btn-group-custom { /* Agrupa os botões de ação */
                display: flex;
                gap: 1rem; /* Espaçamento entre os botões */
                margin-top: 2rem;
                justify-content: center; /* Centraliza os botões */
            }

            .btn-group-custom .btn {
                margin: 0; /* Remove margem individual dos botões para usar gap */
                padding: 0.75rem 1.5rem;
                border-radius: 5px;
            }
        </style>

        <div class="form-template">
            <%
                // Recupera o JSON das questões do atributo da requisição
                String quizQuestionsJson = (String) request.getAttribute("quizQuestionsJson");
                String quizTopic = (String) request.getAttribute("quizTopic");

                // Verifica se há JSON para processar
                if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty()) {
            %>
                    <h2>Erro ao Gerar Quiz</h2>
                    <div class="alert alert-danger" role="alert">
                        Não foi possível carregar as questões do quiz. A IA pode não ter gerado uma resposta válida.
                        <a href="<%= request.getContextPath() %>/index.jsp" class="alert-link">Tentar novamente</a>.
                    </div>
            <%
                } else {
                    try {
                        JSONArray questions = new JSONArray(quizQuestionsJson);
                        if (questions.length() == 0) {
            %>
                            <h2>Quiz Vazio</h2>
                            <div class="alert alert-info" role="alert">
                                A IA não gerou nenhuma questão para o tópico "<strong><%= quizTopic %></strong>". Tente um tópico diferente.
                                <a href="<%= request.getContextPath() %>/index.jsp" class="alert-link">Criar novo quiz</a>.
                            </div>
            <%
                        } else {
            %>
                            <h2>Quiz sobre: <%= quizTopic %></h2>
                            <%-- O formulário principal que enviará todas as respostas --%>
                            <form action="<%= request.getContextPath() %>/submitQuizServlet" method="post">
                                <%
                                    // Loop para exibir cada questão
                                    for (int i = 0; i < questions.length(); i++) {
                                        JSONObject question = questions.getJSONObject(i);
                                        String pergunta = question.getString("pergunta");
                                        JSONArray opcoesArray = question.getJSONArray("opcoes");
                                        String respostaCorreta = question.getString("respostaCorreta"); // Captura a resposta correta para validação

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
                                                <input type="radio" name="q_<%= i %>" id="q_<%= i %>_<%= optionLetter %>" value="<%= optionLetter %>" required>
                                                <label for="q_<%= i %>_<%= optionLetter %>"><%= optionText %></label>
                                            </div>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <%-- Campos ocultos para passar a resposta correta e a pergunta para o servlet de submissão --%>
                                    <input type="hidden" name="q_<%= i %>_correct_answer" value="<%= respostaCorreta %>">
                                    <input type="hidden" name="q_<%= i %>_question_text" value="<%= pergunta %>">
                                    <input type="hidden" name="q_<%= i %>_options_json" value="<%= opcoesArray.toString() %>">
                                </div>
                                <%
                                    }
                                %>

                                <div class="btn-group-custom">
                                    <%-- O botão de "Ver Resultado" é o que submete o formulário --%>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-check"></i> Ver Resultado
                                    </button>
                                    <%-- Os botões "Salvar" e "Baixar PDF" seriam para funcionalidades futuras --%>
                                    <button type="button" class="btn btn-secondary" onclick="alert('Funcionalidade de salvar ainda não implementada!')">
                                        <i class="fas fa-save"></i> Salvar
                                    </button>
                                    <button type="button" class="btn btn-info" onclick="alert('Funcionalidade de baixar PDF ainda não implementada!')">
                                        <i class="fas fa-download"></i> Baixar PDF
                                    </button>
                                </div>
                            </form>
            <%
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao processar JSON na formulario.jsp: " + e.getMessage());
                        e.printStackTrace();
            %>
                        <h2>Erro de Processamento</h2>
                        <div class="alert alert-danger" role="alert">
                            Erro ao carregar as questões. A estrutura da resposta da IA pode estar incorreta ou houve um problema interno.
                            <a href="<%= request.getContextPath() %>/index.jsp" class="alert-link">Tentar novamente</a>.
                            Detalhes: <%= e.getMessage() %>
                        </div>
            <%
                    }
                }
            %>
        </div>

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-e9sGFM9f3X6S5r9I01h9X6y6aR6q2aQ9t0p1l1C7p2n8D5r0q3a9Z9t2w8o7a6" crossorigin="anonymous"></script>
    </body>
</html>