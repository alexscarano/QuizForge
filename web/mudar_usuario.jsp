<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/index.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <title>QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
            <form>
            <input type="text" id="nome" placeholder="novo nome">
            <button class="" type="submit">Mudar</button><br>
            
            <input type="email" id="email" placeholder="novo email">
            <button class="" type="submit">Mudar</button><br><br>
            
            <input type="text" placeholder="senha atual">
            
            <input type="text" placeholder="nova senha">
            <button class="" type="submit">Mudar</button>
        </form>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>