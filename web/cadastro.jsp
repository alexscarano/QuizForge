<%-- 
    Document   : cadastro
    Created on : 29 de abr. de 2025, 13:53:32
    Author     : gekki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/cadastro.css">
    <title>cadastro usuario</title>
</head>
<body>
    <%@include file="WEB-INF/jspf/design.jspf" %>
    <%@include file="WEB-INF/jspf/header.jspf" %>
    <main>
        <div class="cadastro">
            <form action="" method="post">
                <div class="titulo"><h3>Cadastro</h3></div>
                <div class="campo-texto">
                    <label for="nome">Nome</label>
                    <input type="text" name="nome" id="nome" required>
                    <label for="email">E-mail</label>
                    <input type="email" name="email" id="email" required>
                    <label for="senha">Senha</label>
                    <input type="password" name="senha" id="senha" required>
                    <label for="conf-senha">Confirmar Senha</label>
                    <input type="password" name="conf-senha" id="conf-senha" required>
                </div>
                <div class="confirmar">
                    <input type="checkbox" name="concordar" id="concordar" required>
                    <label for="concordar">concordo com isso</label>
                </div>
                <button type="submit">Confirmar</button>
                <div class="ir-login">
                    <p>Fazer <a href="/Forge/login.jsp">login</a></p>
                </div>
            </form>
        </div>
    </main>
    <%@include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
