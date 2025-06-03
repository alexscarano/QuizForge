<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/sobre_nos.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>Sobre nós</title>
    </head>
    <body>
        <style>
            body{
                background-color: #f4f4f4;
            }
            
            .acme {
                font-family: "Acme", sans-serif;
                font-weight: 400;
                font-style: normal;
            }

            .member-photo-circle {
                width: 100px;
                height: 100px;
                background-color: #ff7c00; /* Orange color from the image */
                border-radius: 50%;
                margin: 0 auto 15px;
            }
            
            .roboto {
                font-family: "Roboto", sans-serif;
                font-weight: 500;
                font-style: normal;
                font-size: 1.2rem;
                text-align: center;
            }
            
            .quizforge-member-role {
                text-align: center;
            }
            
            .linha {
                height: 5vh;
                width: 100%;
            }
            
            .vis {
                border-top: 0.1rem solid black;
            }
            
            input::placeholder{
                font-family: "Roboto",sans-serif
            }
        </style>
        
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <main class="container my-5">
            <section class="mb-5">
                <h1 class="text-center mb-4 acme" style="color: #FF6F00">QuizForge</h1>
                <div class="row align-items-center">
                    <div class="col-lg-7 text-start">
                        <p class="text-justify roboto">O projeto QuizForge foi criado por um grupo de alunos de Análise e Desenvolvimento de Sistemas 4° ciclo da Fatec Praia Grande, o site QuizForge. Sua função é a geração de quizes com a utilização da IA, ao se cadastrar ao site, o usuário poderá via prompt solicitar que a IA crie um quiz, baseado no conteúdo passado via prompt. O quiz gerado poderá ser transformado em PDF caso o usuário necessite imprimi-lo</p>
                    </div>
                    <div class="col-lg-5 text-center">
                        <img src="assets/images/logo_quizforge_trans.png" alt="Illustration of a person forging a quiz" class="img-fluid quizforge-hero-image">
                    </div>
                </div>
            </section>
            
            <div class="linha"></div>

            <section class="row justify-content-between m-5">
                <div class="col-md-6 col-lg-5 mb-4 mb-md-0">
                    <div class="card quizforge-card h-100 p-4">
                        <h2 class="card-title quizforge-section-title text-center mb-3 acme" style="color: #FF6F00">Nossa Visão</h2>
                        <p class="text-justify roboto">Nossa visão é ser reconhecida como uma equipe de estudantes capaz de propor soluções criativas e relevantes para desafios reais da educação, por meio da tecnologia. Acreditamos que, mesmo em fase de formação, podemos construir ferramentas com potencial de transformação social, ao mesmo tempo em que desenvolvemos nossas habilidades profissionais e trabalhamos de forma colaborativa</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-5">
                    <div class="card quizforge-card h-100 p-4">
                        <h2 class="card-title quizforge-section-title text-center mb-3 acme" style="color: #FF6F00">Nossa Missão</h2>
                        <p class="card-text roboto">Nossa missão como equipe acadêmica é desenvolver soluções tecnológicas que contribuam com a educação, utilizando a inteligência artificial para facilitar o aprendizado e tornar o conhecimento mais acessível a todos. Buscamos aplicar na prática os conhecimentos adquiridos na graduação, criando um sistema que una inovação, funcionalidade e impacto real na vida de estudantes e educadores.</p>
                    </div>
                </div>
            </section>
            
            <div class="linha"></div>
            <div class="linha"></div>
            <div class="linha vis"></div>

            <section class="text-center mb-5">
                <h2 class="quizforge-section-title mb-4 acme" style="color: #FF6F00">Equipe</h2>
                <div class="row justify-content-center">
                    <div class="col-6 col-md-3 mb-4">
                        <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/image.png" class="img-members member-photo-circle"></div>
                        <h3 class="quizforge-member-name">Alexandre Scarano</h3>
                        <p class="quizforge-member-role roboto" style="color: #FF6F00">Desenvolvedor <br> Back-End e Banco de Dados</p>
                    </div>
                    <div class="col-6 col-md-3 mb-4">
                        <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/heiitorft.jpg" alt="alt" class="img-members member-photo-circle"/></div>
                        <h3 class="quizforge-member-name">Gabriel Altino</h3>
                        <p class="quizforge-member-role roboto" style="color: #FF6F00">Desenvolvedor <br> Front-End</p>
                    </div>
                    <div class="col-6 col-md-3 mb-4">
                        <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/seyft.jpg" alt="alt" class="img-members member-photo-circle"/></div>
                        <h3 class="quizforge-member-name">Heitor Teixeira</h3>
                        <p class="quizforge-member-role roboto" style="color: #FF6F00">Desenvolvedor <br> Front-End</p>
                    </div>
                    <div class="col-6 col-md-3 mb-4">
                        <div class="member-photo-circle mx-auto mb-3"><img src="assets/images/john-marston-is-so-much-more-bad-ass-in-rdr1-v0-k70ginzzs14b1-3404878409.jpg" alt="alt" class="img-members member-photo-circle"/></div>
                        <h3 class="quizforge-member-name">Sey Forasteira</h3>
                        <p class="quizforge-member-role roboto" style="color: #FF6F00">Desenvolvedor <br> Back-End</p>
                    </div>
                </div>
            </section>
        </main>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    </body>
</html>
</html>