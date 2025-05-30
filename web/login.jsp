<%-- 
    Document   : login
    Created on : 29 de abr. de 2025, 13:53:45
    Author     : gekki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/login.css">
    <title>cadastro usuario</title>
</head>
<body>
    <%@include file="WEB-INF/jspf/design.jspf" %>
    <%@include file="WEB-INF/jspf/header.jspf"%>
    <main>
        <div class="cadastro">
            <form action="" method="post">
                <div class="titulo"><h3>Login</h3></div>
                <div class="campo-texto">
                    <label for="email">E-mail</label>
                    <input type="email" name="email" id="email" required>
                    <label for="senha">Senha</label>
                    <input type="password" name="senha" id="senha" required>
                </div>
                <button type="submit">Confirmar</button>
                <p>Fazer <a href="/Forge/cadastro.jsp">cadastro</a></p>
            </form>
        </div>
    </main>
    <%@include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
