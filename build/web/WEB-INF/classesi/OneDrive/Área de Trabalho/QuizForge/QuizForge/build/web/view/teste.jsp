<!-- <%-- 
    Document   : cadastro
    Created on : 29 de abr. de 2025, 13:53:32
    Author     : gekki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="assets/css/teste.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
     <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
    <title>QuizForge</title>
</head>
<body>
    <!-- <%@include file="../WEB-INF/jspf/design.jspf" %>
    <%@include file="../WEB-INF/jspf/header.jspf" %> -->
    <main>
        <!-- <h2>Weekly Coding Challenge #1: Sign in/up Form</h2> -->

         <!--   Cadastro    -->
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="#">
			<h1>Cadastrar</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fa-brands fa-facebook"></i></a>
				<a href="#" class="social"><i class="fa-brands fa-google"></i></a>
				<a href="#" class="social"><i class="fa-brands fa-linkedin"></i></a>
			</div>
			<span>Ou use seu email para se cadastrar</span>
			<input type="text" placeholder="Nome" />
			<input type="email" placeholder="Email" />
			<input type="password" placeholder="Senha" />
			<button>Cadastrar</button>
		</form>
	</div>

    <!--    Login  -->
	<div class="form-container sign-in-container">
		<form action="#">
			<h1>Login</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fa-brands fa-facebook"></i></a>
				<a href="#" class="social"><i class="fa-brands fa-google"></i></a>
				<a href="#" class="social"><i class="fa-brands fa-linkedin"></i></a>
			</div>
			<span>Ou use sua conta</span>
			<input type="email" placeholder="Email" />
			<input type="password" placeholder="Password" />
			<a href="#">Esqueceu sua senha?</a>
			<button>Logar</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Bem Vindo!</h1>
				<p>Se já tiver uma conta, faça login para se conectar :3</p>
				<button class="ghost" id="signIn">Logar</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Olá, Chará</h1>
				<p>Se Cadastre e venha, criar Quizes com a gente!</p>
				<button class="ghost" id="signUp">Cadastrar</button>
			</div>
		</div>
	</div>
</div>
    </main>
    <!-- <%@include file="../WEB-INF/jspf/footer.jspf"%> -->
    <script src="assets/js/teste.js"></script>

</body>
</html>
