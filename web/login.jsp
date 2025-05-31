<!DOCTYPE html>
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
                    <label for="floatingInput">Endereço de email ou nome de usuário</label>
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