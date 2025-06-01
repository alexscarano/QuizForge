<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="UTF-8">
    <title>QuizForge - Login</title>
    <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="assets/css/login_cadastro.css">
    <link rel="stylesheet" href="assets/css/style.css">
  </head>

  <body class="bg-light d-flex flex-column min-vh-100">
    <%@include file="WEB-INF/jspf/header.jspf" %>

    <div class="container d-flex justify-content-center align-items-center flex-grow-1">
      <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
        <div class="text-center mb-4">
          <img src="assets/images/logo_quizforge_trans.png" alt="Logo" width="80" height="80">
          <h2 class="mt-2">Bem-vindo ao QuizForge</h2>
        </div>

        <% String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) { %>
          <div class="alert alert-danger text-center" role="alert">
            <%= errorMessage %>
          </div>
        <% } %>

        <% String registerSuccess = request.getParameter("registerSuccess");
           if ("true".equals(registerSuccess)) { %>
          <div class="alert alert-success text-center" role="alert">
            Cadastro realizado com sucesso! Faça login.
          </div>
        <% } %>

        <form action="loginServlet" method="post">
          <div class="mb-3">
            <label for="emailOrLogin" class="form-label">
              <i class="fa-solid fa-user"></i> Email ou Login
            </label>
            <input type="text" class="form-control" id="emailOrLogin" name="emailOrLogin" placeholder="Digite seu email ou login" required>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label">
              <i class="fa-solid fa-lock"></i> Senha
            </label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Digite sua senha" required>
          </div>

          <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="rememberMe">
            <label class="form-check-label" for="rememberMe">
              <i class="fa-regular fa-circle-check"></i> Lembre de mim
            </label>
          </div>

          <div class="d-grid mb-3">
            <button class="btn btn-primary" type="submit">
              <i class="fa-solid fa-right-to-bracket"></i> Entrar
            </button>
          </div>

          <div class="text-center">
            <small>Não tem uma conta? <a href="/QuizForge/cadastro.jsp">Cadastre-se</a></small>
          </div>
        </form>
      </div>
    </div>

    <%@include file="WEB-INF/jspf/footer.jspf" %>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
