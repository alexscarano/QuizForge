package filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// URLs que este filtro protegerá
@WebFilter(urlPatterns = {
    "/index.jsp", "/pagina_usuario.jsp", "/formulario.jsp", "/mudar_usuario.jsp", 
    "/resultado.jsp", "/listQuizzes.jsp", "/quizResult.jsp", "/viewQuiz.jsp"}) 
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("userLogged") == null) {
            // redireciona para a página de login se não tiver a sessão
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return; 
        }

        // Continuar com a requisição caso exista a sessão userLogged
        chain.doFilter(request, response);
    }

}
