/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.servlet;

import controller.GerenciarClientes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/clientes/*"})
public class ClienteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        System.out.println("Chegou: " + uri);
        if(uri.equals("/AnyMais/clientes/cadastrar")){
            response.sendRedirect("cadastrar-clientes.jsp");
        }
        else if(uri.equals("/AnyMais/clientes/cadastrado")){
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");   
            String sexo = request.getParameter("sexo");          
            
            Pessoa novo_cliente = new Pessoa(0, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, sexo, 1);
            if(GerenciarClientes.getInstance().adicionarCliente(novo_cliente))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            Pessoa cliente = GerenciarClientes.getInstance().selecionarCliente(email);
            request.getSession(true).setAttribute("clientes", cliente); 
            response.sendRedirect("ver-cliente.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
