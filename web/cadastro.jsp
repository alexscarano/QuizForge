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
        
        <main class="form-signin w-100 m-auto">
            <form>
                <img class="mb-4" src="assets/images/logo_quizforge_trans.png" alt="" width="92" height="92">
                <h1 class="h3 mb-3 fw-normal">Crie sua conta</h1>
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="nome@exemplo.com">
                    <label for="floatingInput">Usuário</label>
                </div>
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="nome@exemplo.com">
                    <label for="floatingInput">Endereço de email</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Senha">
                    <label for="floatingPassword">Senha</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Senha">
                    <label for="floatingPassword">Confirmar senha</label>
                </div>
                <div>
                    <p>Já tem uma conta? Faça <a href="/QuizForge/login.jsp">Login</a></p>
                </div>
                <button class="btn btn-primary w-100 py-2" type="submit">Cadastre-se</button>
            </form>
        </main>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>