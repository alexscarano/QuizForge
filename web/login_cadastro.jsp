<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/login_cadastro.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <main>
            <div class="container" id="container">
                <div class="form-container sign-up-container">
                    <form action="#">
                        <h1>Crie sua conta</h1>
                        <div class="social-container">
                            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                        <span>use seu email para se registrar</span>
                        <input type="text" placeholder="Nome" />
                        <input type="email" placeholder="Email" />
                        <input type="password" placeholder="Senha" />
                        <button>Cadastre-se</button>
                    </form>
                </div>
                <div class="form-container sign-in-container">
                    <form action="#">
                        <h1>Faça login</h1>
                        <div class="social-container">
                            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                        <span>use sua conta</span>
                        <input type="email" placeholder="Email"/>
                        <input type="password" placeholder="Senha"/>
                            <a href="#">Esqueceu sua senha?</a>
                        <button>Login</button>
                    </form>
                </div>
                <div class="overlay-container">
                    <div class="overlay">
                        <div class="overlay-panel overlay-left">
                            <h1>Bem vindo de volta!</h1>
                            <p>Para se manter conectado, entre suas informações de login</p>
                            <button class="ghost" id="signIn">Login</button>
                        </div>
                        <div class="overlay-panel overlay-right">
                            <h1> Faça sua conta!</h1>
                            <p>Entre suas informações e tenha acesso ao criador de quizzes</p>
                            <button class="ghost" id="signUp">Cadastre-se</button>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="/QuizForge/view/assets/js/login_cadastro.js"></script>
    </body>
</html>
</html>