<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8">
    <title>QuizForge - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
  </head>

  <body class="bg-light d-flex flex-column min-vh-100">
    <%@include file="WEB-INF/jspf/header.jspf" %>
        <div class="container d-flex justify-content-center align-items-center flex-grow-1">
          <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
            <div class="text-center mb-4">
              <img src="assets/images/logo_quizforge_trans.png" alt="Logo" width="80" height="80">
              <h2 class="mt-2 acme">Faça Login!</h2>
            </div>

            <% String errorMessage = (String) request.getAttribute("errorMessage");
               if (errorMessage != null) { %>
              <div class="alert alert-danger text-center" role="alert">
                <%= errorMessage %>
              </div>
            <% } %>

            <% String registerSuccess = request.getParameter("registerSuccess");
               if ("true".equals(registerSuccess)) { %>
              <div class="alert alert-success text-center roboto" role="alert">
                 Cadastro realizado com sucesso! Faça login.
              </div>
            <% } %>

            <form action="loginServlet" method="post">
              <div class="mb-3">
                <label for="emailOrLogin" class="form-label roboto">
                    <i class="fa-solid fa-user"></i> Email ou Login
                </label>
                <input type="text" class="form-control" id="emailOrLogin" name="emailOrLogin" placeholder="Digite seu email ou login" required>
              </div>

              <div class="mb-3">
                <label for="password" class="form-label roboto">
                  <i class="fa-solid fa-lock"></i> Senha
                </label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Digite sua senha" required>
              </div>

              <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" id="rememberMe">
                <label class="form-check-label roboto" for="rememberMe">
                  Lembre de mim
                </label>
              </div>

              <div class="d-grid mb-3">
                <button class="btn btn-warning roboto" type="submit">
                    Entrar
                </button>
              </div>

              <div class="text-center roboto">
                  <small class="roboto">Não tem uma conta? <a href="/QuizForge/cadastro.jsp" class="roboto">Cadastre-se</a></small>
              </div>
            </form>
          </div>
        </div>

    <%@include file="WEB-INF/jspf/footer.jspf" %>   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
