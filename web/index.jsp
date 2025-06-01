<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>QuizForge - Gerar Prova</title>
    <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">

    <!-- Bootstrap e FontAwesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Estilos customizados -->
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <%@include file="WEB-INF/jspf/header.jspf" %>

    <main class="flex-grow-1 d-flex align-items-center justify-content-center">
        <div class="container text-center">
            <div class="mb-4">
                <h1 class="display-5 fw-bold">Bem-vindo ao QuizForge!</h1>
                <p class="lead">Digite o tema da prova que deseja gerar com a ajuda da IA:</p>
            </div>

            <form action="gerarQuiz" method="POST" class="mx-auto" style="max-width: 600px;">
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

    <!-- Scripts do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
