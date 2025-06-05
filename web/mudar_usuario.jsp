<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>QuizForge - Gerenciar Conta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="icon" href="assets/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="assets/css/mudar_usuario.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Acme&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>

<!-- Sidebar do usuário -->
<nav id="sidebarMenu" class="collapse d-lg-block sidebar tema">
    <div class="position-sticky tema">
        <div class="list-group list-group-flush mx-3 mt-4 mt-3 tema">
            <a href="/QuizForge/usuario.jsp" class="list-group-item list-group-item-action py-2 active bg-white roboto-side" aria-current="true">
                <i class="bi bi-person-gear fa-fw me-3 tema-white"></i><span class="tema-white">Gerenciar conta</span>
            </a>

            <a href="/QuizForge/listQuizzes" class="list-group-item list-group-item-action tema py-2 roboto-side">
                <i class="fa-solid fa-wand-magic-sparkles fa-fw me-3 mt-3"></i><span>Meus quizzes</span>
            </a>

            <a href="<%= request.getContextPath() %>/logoutServlet" class="tema list-group-item list-group-item-action roboto-side py-2 ripple">
                <i class="fa-solid fa-right-from-bracket fa-fw me-3 mt-3"></i><span>Log out</span>
            </a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <h2 class="mb-4 text-center acme">Gerenciar Conta</h2>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
                <div class="alert alert-danger text-center" role="alert">
                    <%= errorMessage %>
                </div>
            <%
                }
            %>

            <!-- Alterar login -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title acme"><i class="bi bi-pencil-square"></i> Alterar Nome de Usuário</h5>
                    <form action="profileUpdate" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" name="newLogin" placeholder="Novo login" required>
                            <button type="submit" class="btn btn-warning roboto" name="action" value="updateLogin">Atualizar</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Alterar email -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title acme"><i class="bi bi-envelope-fill"></i> Alterar E-mail</h5>
                    <form action="profileUpdate" method="post">
                        <div class="input-group">
                            <input type="email" class="form-control" name="newEmail" placeholder="Novo e-mail" required>
                            <button type="submit" class="btn btn-warning roboto" name="action" value="updateEmail">Atualizar</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Alterar senha -->
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title acme"><i class="bi bi-key-fill"></i> Alterar Senha</h5>
                    <form action="profileUpdate" method="post">
                        <div class="mb-3">
                            <input type="password" class="form-control" name="currentPassword" placeholder="Senha atual" required>
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" name="newPassword" placeholder="Nova senha" required>
                        </div>
                        <button type="submit" class="btn btn-warning roboto" name="action" value="updatePassword">
                            <i class="bi bi-lock-fill"></i> Atualizar Senha
                        </button>
                    </form>
                </div>
            </div>

            <!-- Excluir conta -->
            <div class="card border-danger shadow-sm mb-5">
                <div class="card-body">
                    <h5 class="card-title text-danger acme"><i class="bi bi-trash3-fill"></i> Excluir Conta</h5>
                    <p class="text-danger">Esta ação é <strong>irreversível</strong>. Todos os seus dados serão apagados permanentemente.</p>
                    <form action="profileUpdate" method="post" onsubmit="return confirmDelete();">
                        <div class="mb-3">
                            <label for="deletePassword" class="form-label roboto">Confirme sua senha:</label>
                            <input type="password" class="form-control" id="deletePassword" name="deletePassword" placeholder="Digite sua senha atual" required>
                        </div>
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="confirmDeleteCheckbox" required>
                            <label class="form-check-label text-danger roboto" for="confirmDeleteCheckbox">
                                Confirmo que desejo excluir minha conta permanentemente.
                            </label>
                        </div>
                        <button type="submit" class="btn btn-danger w-100 roboto" name="action" value="deleteAccount" name="deleteAccount">
                            <i class="bi bi-trash"></i> Excluir Conta
                        </button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="WEB-INF/jspf/footer.jspf" %>

<script>
    function confirmDelete() {
        return confirm("Tem certeza que deseja excluir sua conta? Esta ação é permanente.");
    }
</script>
</body>
</html>
