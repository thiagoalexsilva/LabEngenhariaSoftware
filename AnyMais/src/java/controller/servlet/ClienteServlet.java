/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.servlet;

import controller.GerenciarClientes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Pessoa;
import model.entity.Usuario;

/**
 *
 * @author ThiagoAlexandre
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/usuario/*"})
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
    private Usuario user_active = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/usuario/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/usuario/cadastrado")){
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String email2 = request.getParameter("confirma-email");
            String senha2 = request.getParameter("confirma-senha");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String celular = request.getParameter("celular");
            String sexo = request.getParameter("sexo");  
            String cpf = request.getParameter("cpf-cnpj");  
            String dataNascimento = request.getParameter("nascimento");
            int tipo = ((Pessoa) request.getSession(true).getAttribute("tipo-usuario")).getTipo();
            String mensagem = request.getParameter("mensagem");
            
            // ------------ validacoes de verificacoes --------//
            if(sexo.equals("Masculino")) sexo = "M";
            else if(sexo.equals("Feminino")) sexo = "F";
            else sexo = "O";
            
            if(email.isEmpty() || senha.isEmpty() || senha2.isEmpty() || nome.isEmpty() || email2.isEmpty() || endereco.isEmpty()
                    || bairro.isEmpty() || cidade.isEmpty() || cep.isEmpty() || uf.isEmpty() || sexo.isEmpty() || cpf.isEmpty()
                    || dataNascimento.isEmpty() || !senha.equals(senha2) || !email.equals(email2) || cpf.length() < 11 || senha.length() < 8
                    || senha2.length() < 8 || (!telefone.isEmpty() && telefone.length() < 9) || (!celular.isEmpty() && celular.length() < 9))
                request.getSession().setAttribute("status", "falha");
            
            if(GerenciarClientes.getInstance().verificarCPF_Email(cpf, email))
                request.getSession().setAttribute("status", "Ja existe usuario com email ou cpf informados.");
            
            // ------------ validacoes de verificacoes --------//
                
            Usuario user_active = new Usuario(1, cpf, sexo, dataNascimento, celular, 1, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, tipo, mensagem);
            if(GerenciarClientes.getInstance().adicionarCliente(user_active))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/usuario");
        }
        else if(uri.equals("/AnyMais/usuario/atualizar")){
            request.getSession(true).setAttribute("usuario", user_active);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editar-cadastro_cliente.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("status");
        }
        else if(uri.equals("/AnyMais/usuario/atualizado")){
            
            /*String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String email2 = request.getParameter("confirma-email");
            String senha2 = request.getParameter("confirma-senha");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String cep = request.getParameter("cep");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String celular = request.getParameter("telefone2");
            String sexo = request.getParameter("sexo");  
            String cpf = request.getParameter("cpf-cnpj");  
            String dataNascimento = request.getParameter("nascimento");
            int tipo = ((Pessoa) request.getSession(true).getAttribute("tipo-usuario")).getTipo();
            String mensagem = request.getParameter("mensagem");
            
            // ------------ validacoes de verificacoes --------//
                        
            if(sexo.equals("Masculino")) sexo = "M";
            else if(sexo.equals("Feminino")) sexo = "F";
            else sexo = "O";
            
            if(telefone.isEmpty()) telefone = celular;
                
            if(email.isEmpty() || senha.isEmpty() || senha2.isEmpty() || nome.isEmpty() || email2.isEmpty() || endereco.isEmpty()
                    || bairro.isEmpty() || cidade.isEmpty() || cep.isEmpty() || uf.isEmpty() || sexo.isEmpty() || cpf.isEmpty()
                    || dataNascimento.isEmpty() || !senha.equals(senha2) || !email.equals(email2) || cpf.length() < 11 || senha.length() < 8
                    || senha2.length() < 8 || (!telefone.isEmpty() && telefone.length() < 9) || (!celular.isEmpty() && celular.length() < 9))
                request.getSession().setAttribute("status", "falha");
            
            
            // ------------ validacoes de verificacoes --------//
                
            user_active = new Usuario( cpf, sexo, dataNascimento, celular, 0, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, 1);
            if(GerenciarClientes.getInstance().atualizarCliente(user_active))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");*/
            
            String cadastrar = request.getParameter("cadastrar");
            if(cadastrar != null && cadastrar.equals("Atualizar")){
                int u_id = ((Usuario) request.getSession(true).getAttribute("usuario")).getId();
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String email2 = request.getParameter("confirma-email");
                String senha2 = request.getParameter("confirma-senha");
                String nome = request.getParameter("nome");
                String endereco = request.getParameter("endereco");
                String bairro = request.getParameter("bairro");
                String complemento = request.getParameter("complemento");
                String cidade = request.getParameter("cidade");
                String cep = request.getParameter("cep");
                String uf = request.getParameter("uf");
                String telefone = request.getParameter("telefone");
                String celular = request.getParameter("telefone2");
                String sexo = request.getParameter("sexo");  
                String cpf = request.getParameter("cpf-cnpj");  
                String dataNascimento = request.getParameter("nascimento");
                int tipo = ((Pessoa) request.getSession(true).getAttribute("tipo-usuario")).getTipo();
                String mensagem = request.getParameter("mensagem");            

                Usuario usuarioAtualizado = new Usuario(0, cpf, sexo, dataNascimento, celular, u_id, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, tipo, mensagem);
                if(GerenciarClientes.getInstance().atualizarCliente(usuarioAtualizado))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
                
                
            }
            
            response.sendRedirect("/AnyMais/usuario");
        }
        else if(uri.equals("/AnyMais/usuario/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                if(GerenciarClientes.getInstance().excluirCliente(user_active.getEmail(), user_active.getSenha()))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/usuario");
        }else if(uri.equals("/AnyMais/usuario/)")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-cliente.jsp");
            dispatcher.forward(request, response);
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
