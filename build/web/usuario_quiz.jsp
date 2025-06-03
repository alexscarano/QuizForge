<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
        <link rel="stylesheet" href="assets/css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <title>Usuário</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <style>
            .acme {
                font-family: "Acme", sans-serif;
                font-weight: 400;
                font-style: normal;
                font-size: 1.1rem;
            }
            .roboto {
                font-family: "Roboto", sans-serif;
                font-weight: 500;
                font-style: normal;
                font-size: 1.2rem;
                text-align: center;
            }
            
            .roboto-side {
                font-family: "Roboto", sans-serif;
                font-weight: 500;
                font-style: normal;
                font-size: 1.1rem;
            }
            
            .sidebar {
              position: fixed;
              top: 11vh;
              bottom: 0;
              left: 0;
              padding: 20vh 0 0; /* Height of navbar */
              box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
              width: 280px;
              height: 80vh;
              z-index: 600;
              border-radius: 0rem 2rem 2rem 0rem;
            }
            
            .tema {
                background-color: var(--cor_primaria);
                color: white;
            }

            .sidebar .active {
              border-radius: 5px;
              box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
              position: relative;
              top: 0;
              height: calc(100vh - 48px);
              padding-top: 0.5rem;
              overflow-x: hidden;
              overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }
            
            .tema-white {
                color: black;
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
        </style>
        
        <nav id="sidebarMenu" class="collapse d-lg-block sidebar tema">
            <div class="position-sticky tema">
                <div class="list-group list-group-flush mx-3 mt-4 mt-3 tema">
                    <a href="/QuizForge/usuario.jsp" class="list-group-item roboto-side list-group-item-action py-2 tema">
                        <i class="bi bi-person-gear fa-fw me-3"></i><span>Gerenciar conta</span>
                    </a>

                    <a href="/QuizForge/usuario_quiz.jsp" class="list-group-item roboto-side list-group-item-action acme tema py-2 active bg-white">
                        <i class="fa-solid fa-wand-magic-sparkles fa-fw me-3 mt-3 tema-white"></i ><span class="tema-white">Meus quizzes</span>
                    </a>

                    <a href="<%= request.getContextPath() %>/logoutServlet" class="tema roboto-side list-group-item list-group-item-action py-2 ripple">
                        <i class="fa-solid fa-right-from-bracket fa-fw me-3 mt-3"></i><span>Log out</span>
                    </a>
                </div>
            </div>
        </nav>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>