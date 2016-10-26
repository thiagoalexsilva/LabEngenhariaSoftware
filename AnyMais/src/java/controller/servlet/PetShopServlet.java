/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarClientes;
import controller.GerenciarPetShop;
import controller.GerenciarRacas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Pessoa;
import model.entity.PetShop;
import model.entity.Usuario;
/**
 *
 * @author Erica
 */

@WebServlet(name = "PetShopServlet", urlPatterns = {"/petshop/*"}) 
public class PetShopServlet extends HttpServlet {
    
    private PetShop petshop_active = null;
    
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
        if(uri.equals("/AnyMais/petshop/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
            dispatcher.forward(request, response);
        } else if(uri.equals("/AnyMais/petshop/cadastrado")){
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String confirmacao_email = request.getParameter("confirma-email");
            String confirmacao_senha = request.getParameter("confirma-senha");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String telefone2 = request.getParameter("telefone2");
            String cnpj = request.getParameter("cpf-cnpj");
            
            //Validações de Verificações
            if(email.isEmpty() || senha.isEmpty() || confirmacao_senha.isEmpty() || nome.isEmpty() || confirmacao_email.isEmpty() || endereco.isEmpty()
                    || bairro.isEmpty() || cidade.isEmpty() || cep.isEmpty() || uf.isEmpty() || cnpj.isEmpty()
                    || !senha.equals(confirmacao_senha) || !email.equals(confirmacao_email) || cnpj.length() < 14 || senha.length() < 8
                    || confirmacao_senha.length() < 8)
                request.getSession().setAttribute("status", "falha");
            
            petshop_active = new PetShop(0, cnpj, 0, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, telefone2, 2);
            if(GerenciarPetShop.getInstance().adicionarPetShop(petshop_active))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/petshop");
        } else if(uri.equals("/AnyMais/petshop/atualizar")){
            request.getSession(true).setAttribute("petshop", petshop_active);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editar-cadastro-petshop.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("status");
        }else if(uri.equals("/AnyMais/petshop/atualizado")){
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String confirmacao_email = request.getParameter("confirma-email");
            String confirmacao_senha = request.getParameter("confirma-senha");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String telefone2 = request.getParameter("telefone2");
            String cnpj = request.getParameter("cpf-cnpj");  
            int tipo = ((Pessoa) request.getSession(true).getAttribute("tipo-usuario")).getTipo();
            
            if(email.isEmpty() || senha.isEmpty() || confirmacao_senha.isEmpty() || nome.isEmpty() || confirmacao_email.isEmpty() || endereco.isEmpty()
                    || bairro.isEmpty() || cidade.isEmpty() || cep.isEmpty() || uf.isEmpty() || cnpj.isEmpty()
                    || !senha.equals(confirmacao_senha) || !email.equals(confirmacao_email) || cnpj.length() < 14 || senha.length() < 8
                    || confirmacao_senha.length() < 8 )
                request.getSession().setAttribute("status", "falha");
            
            petshop_active = new PetShop(0, cnpj, 0, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, telefone2, tipo);
            if(GerenciarPetShop.getInstance().atualizarPetShop(petshop_active))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/petshop");
        }else if(uri.equals("/AnyMais/petshop/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                if(GerenciarPetShop.getInstance().excluirPetShop(petshop_active.getId()))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/petshop");
        }else if(uri.equals("/AnyMais/petshop/)")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-petshop.jsp");
            dispatcher.forward(request, response);

        }
    }
}
