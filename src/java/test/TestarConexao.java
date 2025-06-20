/*
*** NÃO UTILIZE EM PRODUÇÃO ***
Esta classe só deve ser utilizada para testar a conexão com banco de dados.
*/ 
package test;

import java.io.IOException;
import java.sql.Connection;
import model.database.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TestarConexao", urlPatterns = {"/testarConexao"})
public class TestarConexao extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try(Connection Conn = DBConnection.getConnection()){ 
            
            if (Conn != null){
                response.getWriter().write("Conexão bem-sucedida");
            }
            
        }
        catch (Exception e){
            response.getWriter().write("Erro de conexão: " + e.getMessage());
        }
       
    }
    
    public String getServletInfo() {
        return "Testar conexão com bd (remover futuramente)";
    }

}
