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
        
        <style>
            html,
            body {
              height: 100vh;
            }

            body {
                background-color: var(--cor_fundo);
            }

            .form-signin {
              max-width: 420px;
              padding: 2rem 3rem;
              border-radius: 2rem;
              color: var(--cor_fundo);
            }

            .form-signin .form-floating:focus-within {
              z-index: 2;
            }

            .form-signin input {
              margin-bottom: -1px;
              border-bottom-right-radius: 0;
              border-bottom-left-radius: 0;
            }

            .form-signin input[type="password"] {
              border-top-left-radius: 0;
              border-top-right-radius: 0;
            }
            
            .btn {
                background-color: var(--cor_secundaria);
                border-color: var(--cor_secundaria);
            }
            
            .fundo {
                background-color: var(--cor_primaria);
            }
            
            .registro a {
                color: var(--cor_secundaria);
            }

        </style>
        
        <div class="form-signin w-100 m-auto fundo">
            <form>
                <img class="mb-4" src="assets/images/logo_quizforge_trans.png" alt="" width="92" height="92">
                <h1 class="h3 mb-3 fw-normal">Faça Login</h1>
                <div class="form-floating">
                    <input type="email" class="form-control" name="email" id="floatingInput" placeholder="nome@exemplo.com" required>
                    <label for="floatingInput">Endereço de email</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" name="senha" id="floatingPassword" placeholder="Senha" required>
                    <label for="floatingPassword">Senha</label>
                </div>
                <div class="form-check text-start my-3">
                    <input class="form-check-input" type="checkbox" value="remember-me" id="checkDefault">
                    <label class="form-check-label" for="checkDefault">Lembre de mim</label>
                </div>
                <div class="registro">
                    <p>Ainda não tem uma conta? <a href="/QuizForge/cadastro.jsp">Registre-se!</a></p>
                </div>
                <button class="btn btn-primary w-100 py-2" type="submit">Login</button>
            </form>
        </div>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>