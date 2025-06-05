<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8">
    <title>QuizForge - Cadastro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/cadastro.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  </head>

  <body class="bg-light d-flex flex-column min-vh-100">
    <%@include file="WEB-INF/jspf/header.jspf" %>
        <main>
            <div class="container d-flex justify-content-center align-items-center flex-grow-1">
              <div class="card shadow-lg p-4" style="max-width: 450px; width: 100vh;">
                <div class="text-center mb-4">
                  <img src="assets/images/logo_quizforge_trans.png" alt="Logo" width="80" height="80">
                  <h2 class="mt-2 acme">Crie sua conta</h2>
                </div>

                <% String errorMessage = (String) request.getAttribute("errorMessage");
                   if (errorMessage != null) { %>
                  <div class="alert alert-danger text-center" role="alert">
                    <%= errorMessage %>
                  </div>
                <% } %>

                <form action="RegisterServlet" method="post">
                  <div class="mb-3">
                    <label for="login" class="form-label roboto">
                      <i class="fa-solid fa-user"></i> Usuário
                    </label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Escolha um nome de usuário" required>
                  </div>

                  <div class="mb-3">
                    <label for="email" class="form-label roboto">
                      <i class="fa-solid fa-envelope"></i> Email
                    </label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required>
                  </div>

                  <div class="mb-3">
                    <label for="password" class="form-label roboto">
                      <i class="fa-solid fa-lock"></i> Senha
                    </label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Crie uma senha segura" required>
                  </div>

                  <div class="mb-3">
                    <label for="confirmPassword" class="form-label roboto">
                      <i class="fa-solid fa-lock"></i> Confirmar Senha
                    </label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirme sua senha" required>
                  </div>

                  <div class="d-grid mb-3">
                    <button class="btn btn-success btn-warning roboto" type="submit">
                        Cadastrar
                    </button>
                  </div>

                  <div class="text-center">
                      <small class="roboto">Já tem uma conta? <a href="/QuizForge/login.jsp" class="roboto">Faça login</a></small>
                  </div>
                </form>
              </div>
            </div>
        </main>

    <%@include file="WEB-INF/jspf/footer.jspf" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
