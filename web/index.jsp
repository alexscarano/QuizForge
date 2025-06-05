<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>QuizForge - Página inicial</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">
    
    <%@include file="WEB-INF/jspf/header.jspf" %>


    <main class="flex-grow-1 d-flex align-items-center justify-content-center">
        <div class="container text-center">
            <div class="mb-4">
                <h1 class="display-5 fw-bold acme">Bem-vindo ao QuizForge!</h1>
                <p class="lead mb-5 roboto">Digite o tema da prova que deseja gerar com a ajuda da IA</p>
            </div>

            <form action="generateQuiz" method="POST" class="mx-auto">
                <div class="input-group shadow-sm">
                    <input type="text" name="captureQuiz" class="form-control form-control-lg" maxlength="70" placeholder="Ex: História do Brasil, Java" required>
                    <button class="btn btn-warning btn-lg roboto" type="submit">
                        <i class="fas fa-magic me-1"></i> Gerar
                    </button>
                </div>
            </form>
        </div>
    </main>
    

    <%@include file="WEB-INF/jspf/footer.jspf" %>

    <!-- Scripts do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
