<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/index.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <title>QuizForge</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <style>
            
          .acme {
                font-family: "Acme", sans-serif;
                font-weight: 400;
                font-style: normal;
            }
            .roboto {
                font-family: "Roboto", sans-serif;
                font-weight: 500;
                font-style: normal;
                font-size: 1.2rem;
                text-align: center;
            }
            
          main {
            animation: fadeIn 1s ease-in;
          }
          
          input {
              box-shadow: none;
          }
          
          .btn-warning {
                background-color: var(--cor_secundaria);
          }
          
          .btn-warning:hover, .btn-warning:active, .btn-warning:visited {
                background-color: #ee5e00;
                box-shadow: none;
          }
          
          input::placeholder{
                font-family: "Roboto",sans-serif
            }

          @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
          }
        </style>
        
        <main class="flex-grow-1 d-flex align-items-center justify-content-center">
            <div class="container text-center">
                <div class="mb-4">
                    <h1 class="display-5 fw-bold acme">Bem-vindo ao QuizForge!</h1>
                    <p class="lead mb-5 roboto">Digite o tema da prova que deseja gerar com a ajuda da IA</p>
                </div>

            <form action="gerarQuiz" method="POST" class="mx-auto">
                <div class="input-group shadow-sm">
                    <input type="text" name="consulta" id="consulta" class="form-control form-control-lg" placeholder="Ex: História do Brasil, Álgebra linear" required>
                    <button class="btn btn-warning btn-lg roboto" type="submit">
                        <i class="fas fa-magic me-1"></i>Gerar
                    </button>
                </div>
            </form>
            </div>
        </main>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>