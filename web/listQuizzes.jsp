<%@page import="model.Quiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/listQuizzes.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <title>QuizForge - Quizzes Salvos</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <!-- Sidebar do usuário -->
        <nav id="sidebarMenu" class="collapse d-lg-block sidebar tema">
            <div class="position-sticky tema">
                <div class="list-group list-group-flush mx-3 mt-4 mt-3 tema">
                    <a href="/QuizForge/mudar_usuario.jsp" class="list-group-item list-group-item-action tema py-2 roboto-side">
                        <i class="bi bi-person-gear fa-fw me-3"></i><span>Gerenciar conta</span>
                    </a>

                    <a href="/QuizForge/listQuizzes" class="list-group-item list-group-item-action py-2 active bg-white roboto-side" aria-current="true">
                        <i class="fa-solid fa-wand-magic-sparkles tema-white fa-fw me-3 mt-3"></i><span class="tema-white">Meus quizzes</span>
                    </a>

                    <a href="<%= request.getContextPath() %>/logoutServlet" class="tema list-group-item list-group-item-action roboto-side py-2 ripple">
                        <i class="fa-solid fa-right-from-bracket fa-fw me-3 mt-3"></i><span>Log out</span>
                    </a>
                </div>
            </div>
        </nav>

        <div class="container">
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                String successMessage = (String) request.getAttribute("successMessage");
                
                if (errorMessage != null) {
            %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
            <%
                }
                if (successMessage != null) {
            %>
                    <div class="alert alert-success" role="alert">
                        <%= successMessage %>
                    </div>
            <%
                }
            %>

            <%
                ArrayList<Quiz> quizzes = (ArrayList<Quiz>) request.getAttribute("quizzes");
                if (quizzes == null || quizzes.isEmpty()) {
            %>
                    <div class="alert alert-info" role="alert">
                        Você ainda não salvou nenhum quiz. Crie um novo quiz <a href="<%= request.getContextPath() %>/index.jsp" class="alert-link">aqui</a>!
                    </div>
            <%
                } else {
            %>
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr class="title">
                                <th scope="col">ID</th>
                                <th scope="col">Tema do Quiz</th>
                                <th scope="col">Data de Criação</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody class="table-body">
                            <%
                                int quizIndex = 0;
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                for (Quiz quiz : quizzes) {
                            %>
                                <tr>
                                    <td><%= ++quizIndex %></td>
                                    <td><%= quiz.getPrompt() %></td>
                                    <td><%= quiz.getCreatedAt() != null ? quiz.getCreatedAt().format(formatter) : "N/A" %></td>
                                    <td class="action-buttons">
                                        <%-- Botão para "Consultar Formulário" - Simula refazer o quiz --%>
                                        <form action="<%= request.getContextPath() %>/consultQuiz" method="get" style="display:inline-block;">
                                            <input type="hidden" name="quizId" value="<%= quiz.getId() %>">
                                            <button type="submit" class="btn btn-sm btn-success" title="Consultar Formulário">
                                                <i class="fas fa-eye"></i> Consultar
                                            </button>
                                        </form>

                                        <%-- Botão para "Baixar PDF" --%>
                                    <form action="<%= request.getContextPath() %>/downloadPdf" method="get" class="d-inline-block">
                                        <input type="hidden" name="quizId" value="<%= quiz.getId() %>">
                                        <input type="hidden" name="includeCorrectAnswers" value="true"> <%-- <<< ESTA LINHA É A CHAVE! --%>
                                        <button type="submit" class="btn btn-info" title="Baixar PDF">
                                            <i class="fas fa-file-pdf"></i> Baixar PDF com Respostas
                                        </button>
                                    </form>

                                        <%-- Botão para "Excluir Quiz" --%>
                                        <form action="<%= request.getContextPath() %>/deleteQuiz" method="post" style="display:inline-block;" onsubmit="return confirm('Tem certeza que deseja excluir este quiz (Tema: <%= quiz.getPrompt()%>) ? Esta ação não pode ser desfeita.');">
                                            <input type="hidden" name="quizId" value="<%= quiz.getId() %>">
                                            <button type="submit" class="btn btn-sm btn-danger" title="Excluir">
                                                <i class="fas fa-trash-alt"></i> Excluir
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            <%
                                } // Fim do loop for (Quiz quiz : quizzes)
                            %>
                        </tbody>
                    </table>
            <%
                } // Fim do if (quizzes == null || quizzes.isEmpty())
            %>
        </div>
        

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-e9sGFM9f3X6S5r9I01h9X6y6aR6q2aQ9t0p1l1C7p2n8D5r0q3a9Z9t2w8o7a6" crossorigin="anonymous"></script>
    </body>
</html>