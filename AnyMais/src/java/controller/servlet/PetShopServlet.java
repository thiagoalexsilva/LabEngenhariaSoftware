/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarPetShop;
import controller.GerenciarUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Conta;
import model.entity.Pessoa;
import model.entity.Usuario;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "PetShopServlet", urlPatterns = {"/petshop/*"})
public class PetShopServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        /*
        TODO: Descomentar trava de sessão sem usuário de petshop
        Usuario usuario = ((Usuario) request.getSession().getAttribute("petshop"));
        if(usuario == null){
            response.sendRedirect("/AnyMais/erro");
            return;
        }
        */
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/petshop")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-petshop.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/petshop/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/petshop/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");

            int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
            String nome = request.getParameter("nome");

            String cpfCnpj = request.getParameter("cpfCnpj");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String telefone2 = request.getParameter("telefone2");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String imagem = request.getParameter("imagem");
            String descricao = request.getParameter("descricao");

            Usuario newPetShop = new Usuario(1, tipoUsuario, 
                    new Pessoa(nome, null, null, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, imagem, descricao),
                    new Conta(email, senha));

            if(GerenciarUsuarios.getInstance().adicionarUsuario(newPetShop))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            response.sendRedirect("/AnyMais/petshop");
        }
        else if(uri.equals("/AnyMais/petshop/atualizar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/petshop/atualizado")){
            int idPetshop = Integer.parseInt(request.getParameter("idUsuario"));
            int tipoUsuario = Integer.parseInt(request.getParameter("tipo"));
            String nome = request.getParameter("nome");

            String cpfCnpj = request.getParameter("cpfCnpj");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String telefone2 = request.getParameter("telefone2");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String imagem = request.getParameter("imagem");
            String descricao = request.getParameter("descricao");

            Usuario petshopAtualizada = new Usuario(idPetshop, tipoUsuario, 
                    new Pessoa(nome, null, null, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, imagem, descricao),
                    new Conta(email, senha));

            if(GerenciarUsuarios.getInstance().atualizarUsuario(petshopAtualizada))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");

            response.sendRedirect("/AnyMais/petshop");
        }
        else if(uri.equals("/AnyMais/petshop/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idUsuario = Integer.parseInt(excluido);
                if(GerenciarUsuarios.getInstance().excluirUsuario(idUsuario))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
                }   
            response.sendRedirect("/AnyMais/petshop");
        }
        
        /*if(uri.equals("/AnyMais/petshop/")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-petshop.jsp");
            dispatcher.forward(request, response);
        }*/
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
