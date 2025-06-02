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
        <title>Formulário</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <style>
            .form-template {
                max-width: 100vh;
                background-color: #ebebeb;
                padding: 0rem 2rem;
            }
            
            .form-template h2 {
                margin: 2rem 0rem;
            }
            
            .bloco {
                margin-bottom: 5rem;
            }
            
            form * {
                margin: 0.5rem 0.7rem 0.5rem 0rem;
            }
            
            .btn {
                margin: 2rem 0rem;
            }
            
        </style>
        
        <div class="form-template">
            <h2>Formulário original</h2>
            <div class="bloco">
                <p>Uma pergunta de multipla escolha, usada para testar a conversão de json para html de forma satisfatoria, isso deu certo?</p>
                <form>
                    <div>
                        <input type="radio" name="questao-1" id="a"><label>A) Primeira questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-1" id="b"><label>B) Segunda questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-1" id="c"><label>C) Terceira questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-1" id="d"><label>D) Quarta questão</label>
                    </div>
                </form>
            </div>
            <div class="bloco">
                <p>Uma pergunta de multipla escolha, usada para testar a conversão de json para html de forma satisfatoria, isso deu certo?</p>
                <form>
                    <div>
                        <input type="radio" name="questao-2" id="a"><label>A) Primeira questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-2" id="b"><label>B) Segunda questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-2" id="c"><label>C) Terceira questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-2" id="d"><label>D) Quarta questão</label>
                    </div>
                </form>
            </div>
            <div class="bloco">
                <p>Uma pergunta de multipla escolha, usada para testar a conversão de json para html de forma satisfatoria, isso deu certo?</p>
                <form>
                    <div>
                        <input type="radio" name="questao-3" id="a"><label>A) Primeira questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-3" id="b"><label>B) Segunda questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-3" id="c"><label>C) Terceira questão</label>
                    </div>
                    <div>
                        <input type="radio" name="questao-3" id="d"><label>D) Quarta questão</label>
                    </div>
                </form>
            </div>
            
            <button type="submit" class="btn btn-danger" name="action" value="deleteAccount" name="deleteAccount">
                <i class="bi bi-trash"></i> Ver Resultado
            </button>
            <button type="submit" class="btn btn-danger" name="action" value="deleteAccount" name="deleteAccount">
                <i class="bi bi-trash"></i> Salvar
            </button>
            <button type="submit" class="btn btn-danger m" name="action" value="deleteAccount" name="deleteAccount">
                <i class="bi bi-trash"></i> Baixar PDF
            </button>
        </div>
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
</html>