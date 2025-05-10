/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import dao.database.DBConnection;
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

}
