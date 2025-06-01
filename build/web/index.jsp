<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/index.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <style>
            body {
                background-image: url('https://www.transparenttextures.com/patterns/cubes.png');
                background-color: #918f91;
          }
          
          main {
            animation: fadeIn 1s ease-in;
          }
          
          input {
              box-shadow: none;
          }
          
          btn {
              background-color: var(--cor_secundaria);
          }

          @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
          }
        </style>
        
        <main class="flex-grow-1 d-flex align-items-center justify-content-center">
            <div class="container text-center">
                <div class="mb-4">
                    <h1 class="display-5 fw-bold">Bem-vindo ao QuizForge!</h1>
                    <p class="lead">Digite o tema da prova que deseja gerar com a ajuda da IA</p>
                </div>

            <form action="gerarQuiz" method="POST" class="mx-auto">
                <div class="input-group shadow-sm">
                    <input type="text" name="consulta" id="consulta" class="form-control form-control-lg" placeholder="Ex: História do Brasil, Java básico, Álgebra linear..." required>
                    <button class="btn btn-primary btn-lg" type="submit">
                        <i class="fas fa-magic me-1"></i> Gerar
                    </button>
                </div>
            </form>
            </div>
        </main>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>