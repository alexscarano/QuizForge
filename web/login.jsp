<!DOCTYPE html>
<<<<<<< Updated upstream
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/login_cadastro.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <p style="color: red; text-align: center;"><%= errorMessage %></p>
        <%
            }
        %>
              
        <%
            String registerSucess = request.getParameter("registerSuccess");
            if ("true".equals(registerSucess)) {
        %>
            <p style="color: green;">Cadastro realizado com sucesso! Faça login.</p>
        <%
            }
        %>
        <main class="form-signin w-100 m-auto">
            <form action="loginServlet" method="post">
                <img class="mb-4" src="assets/images/logo_quizforge_trans.png" alt="" width="92" height="92">
                <h1 class="h3 mb-3 fw-normal">Faça Login</h1>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="nome@exemplo.com" name="emailOrLogin">
                    <label for="floatingInput">Email ou Login</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Senha" name="password">
                    <label for="floatingPassword">Senha</label>
                </div>
                <div class="form-check text-start my-3">
                    <input class="form-check-input" type="checkbox" value="remember-me" id="checkDefault">
                    <label class="form-check-label" for="checkDefault">Lembre de mim</label>
                </div>
                <div>
                    <p>Ainda não tem uma conta? <a href="/QuizForge/cadastro.jsp">Registre-se!</a></p>
                </div>
                <button class="btn btn-primary w-100 py-2" type="submit">Login</button>
            </form>
        </main>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>
=======
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
>>>>>>> Stashed changes
