<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/formulario.css">
        <title>Quiz Gerado</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>

        <div class="form-template">
            <%
                String successMessage = (String) request.getAttribute("successMessage");
                if (successMessage != null && !successMessage.trim().isEmpty()) {
            %>
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fas fa-check-circle"></i>
                    <%= successMessage %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            <%
                }
            %>
            
            <%
                String quizQuestionsJson = (String) request.getAttribute("quizQuestionsJson");
                String quizTopic = (String) request.getAttribute("quizTopic");

                // >>> IMPORTANTE: Se o quiz não está nos atributos da requisição (por exemplo, após um redirect),
                // tenta pegar da sessão. Isso garante que a JSP tenha os dados para EXIBIR.
                // Mas, para salvar, o servlet pegará DIRETAMENTE da sessão.
                if (quizQuestionsJson == null || quizQuestionsJson.trim().isEmpty()) {
                    quizQuestionsJson = (String) session.getAttribute("quizQuestionsJson");
                }
                if (quizTopic == null || quizTopic.trim().isEmpty()) {
                    quizTopic = (String) session.getAttribute("quizTopic");
                }


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

                <form method="post">
                    <%
                        for (int i = 0; i < questions.length(); i++) {
                            JSONObject question = questions.getJSONObject(i);
                            String pergunta = question.getString("pergunta");
                            JSONArray opcoesArray = question.getJSONArray("opcoes");
                    %>
                    <div class="bloco">
                        <p><%= (i + 1) %>. <%= pergunta %></p>
                        <div>
                            <%
                                for (int j = 0; j < opcoesArray.length(); j++) {
                                    String optionText = opcoesArray.getString(j);
                                    String optionLetter = String.valueOf((char) ('A' + j));
                            %>
                                <div>
                                    <input type="radio" name="q_<%= i %>" id="q_<%= i %>_<%= optionLetter %>" value="<%= optionLetter %>" required>
                                    <label for="q_<%= i %>_<%= optionLetter %>"><%= optionText %></label>
                                </div>
                            <%
                                }
                            %>
                        </div>
                        <input type="hidden" name="q_<%= i %>_question_text" value="<%= pergunta %>">
                        <input type="hidden" name="q_<%= i %>_options_json" value="<%= opcoesArray.toString() %>">
                    </div>
                    <%
                        }
                    %>

                    <div class="btn-group-custom">
                        <button type="submit" formaction="<%= request.getContextPath() %>/submitQuiz" class="btn btn-primary">
                            <i class="fas fa-check"></i> Ver Resultado
                        </button>

                        <button type="submit" formaction="<%= request.getContextPath() %>/saveQuiz" class="btn btn-secondary">
                            <i class="fas fa-save"></i> Salvar
                        </button>

                        <button type="submit" formaction="<%= request.getContextPath() %>/downloadPdf" formmethod="get" class="btn btn-info">
                            <i class="fas fa-download"></i> Baixar PDF
                        </button>
                    </div>
                </form>
                            
                <%
                        }
                    } catch (Exception e) {
                        // Sempre imprima a stack trace para depuração no servidor
                        e.printStackTrace(); 
            %>
                <h2>Erro de Processamento</h2>
                <div class="alert alert-danger" role="alert">
                    Erro ao carregar as questões. A estrutura da resposta da IA pode estar incorreta ou houve um problema interno.
                    <a href="<%= request.getContextPath() %>/index.jsp" class="alert-link">Tentar novamente</a>.<br>
                    Detalhes: <%= e.getMessage() %>
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