<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sobre Nós - QuizForge</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <link rel="stylesheet" href="assets/css/style.css">

    <link rel="icon" href="favicon.ico" type="image/x-icon">
</head>
<body>
     <%@include file="WEB-INF/jspf/header.jspf" %>
     
    <main class="container my-5">
        <section class="text-center mb-5">
            <h1 class="quizforge-title mb-4" style="color: #FF6F00">QuizForge</h1>
            <div class="row align-items-center">
                <div class="col-lg-7 text-start">
                    <p class="lead quizforge-text-content">O projeto QuizForge foi criado por um grupo de alunos de Análise e Desenvolvimento de Sistemas 4° Modelo da Fatec Praia Grande, o site QuizForge. Sua função é a geração de quizes com a utilização da IA, ao se cadastrar ao site, o usuário poderá via prompt solicitar que a IA crie um quiz, baseado no conteúdo passado via prompt. O quiz gerado poderá ser transformado em PDF caso o usuário necessite imprimi-lo</p>
                </div>
                <div class="col-lg-5 text-center">
                    <img src="assets/images/logo_quizforge_trans.png" alt="Illustration of a person forging a quiz" class="img-fluid quizforge-hero-image">
                </div>
            </div>
        </section>

        <section class="row justify-content-center mb-5">
            <div class="col-md-6 col-lg-5 mb-4 mb-md-0">
                <div class="card quizforge-card h-100 p-4">
                    <h2 class="card-title quizforge-section-title text-center mb-3" style="color: #FF6F00">Nossa Visão</h2>
                    <p class="card-text quizforge-text-content">Nossa visão é ser reconhecida como uma equipe de estudantes capaz de propor soluções criativas e relevantes para desafios reais da educação, por meio da tecnologia. Acreditamos que, mesmo em fase de formação, podemos construir ferramentas com potencial de transformação social, ao mesmo tempo em que desenvolvemos nossas habilidades profissionais e trabalhamos de forma colaborativa</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-5">
                <div class="card quizforge-card h-100 p-4">
                    <h2 class="card-title quizforge-section-title text-center mb-3" style="color: #FF6F00">Nossa Missão</h2>
                    <p class="card-text quizforge-text-content">Nossa missão como equipe acadêmica é desenvolver soluções tecnológicas que contribuam com a educação, utilizando a inteligência artificial para facilitar o aprendizado e tornar o conhecimento mais acessível a todos. Buscamos aplicar na prática os conhecimentos adquiridos na graduação, criando um sistema que una inovação, funcionalidade e impacto real na vida de estudantes e educadores.</p>
                </div>
            </div>
        </section>

        <section class="text-center mb-5">
            <h2 class="quizforge-section-title mb-4" style="color: #FF6F00">Equipe</h2>
            <div class="row justify-content-center">
                <div class="col-6 col-md-3 mb-4">
                    <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/image.png" class="img-members"></div>
                    <h3 class="quizforge-member-name">Alexandre Scarano</h3>
                    <p class="quizforge-member-role" style="color: #FF6F00">Desenvolvedor <br> Back-End e Banco de Dados</p>
                </div>
                <div class="col-6 col-md-3 mb-4">
                    <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/heiitorft.jpg" alt="alt" class="img-members"/></div>
                    <h3 class="quizforge-member-name">Gabriel Altino</h3>
                    <p class="quizforge-member-role" style="color: #FF6F00">Desenvolvedor <br> Front-End</p>
                </div>
                <div class="col-6 col-md-3 mb-4">
                    <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/seyft.jpg" alt="alt" class="img-members"/></div>
                    <h3 class="quizforge-member-name">Heitor Teixeira</h3>
                    <p class="quizforge-member-role" style="color: #FF6F00">Desenvolvedor <br> Front-End</p>
                </div>
                <div class="col-6 col-md-3 mb-4">
                    <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/maxresdefault-4198145810.jpg" alt="alt" class="img-members"/></div>
                    <h3 class="quizforge-member-name">Sey Forasteira</h3>
                    <p class="quizforge-member-role" style="color: #FF6F00">Desenvolvedor <br> Back-End</p>
                </div>
            </div>
        </section>
    </main>
     <%@include file="WEB-INF/jspf/footer.jspf" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="script.js"></script>
</body>
</html>