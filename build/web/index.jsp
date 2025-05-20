<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/index.css">
        <link rel="stylesheet" href="assets/css/style.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <title>QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <main>
            <div class="texto">
                <h1>Bem vindo!</h1>
                <p>Escolha o tópico da prova que será gerada</p>
            </div>
            <div class="caixa">
                <form class="caixa" action="#" method="POST">
                    <input type="text" name="consulta" id="consulta">
                </form>
            </div>
        </main>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>