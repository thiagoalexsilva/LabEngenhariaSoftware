/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.servlet;

import controller.GerenciarUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Conta;
import model.entity.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/usuario/*"})
public class ClienteServlet extends HttpServlet {

    private boolean primeiraExecucao = true;
    
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        Usuario usuario = ((Usuario) request.getSession().getAttribute("usuario"));
        if(usuario == null){
            response.sendRedirect("/AnyMais/erro");
            return;
        }
        
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/usuario")){
            //Usuario usuario = ((Usuario) request.getSession().getAttribute("usuario"));

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-cliente.jsp");
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
            dispatcher.forward(request, response);
            
        } else if(uri.equals("/AnyMais/usuario/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
            dispatcher.forward(request, response);
            
        } else if(uri.equals("/AnyMais/usuario/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            int tipo = Integer.parseInt(request.getParameter("tipo"));
            String nome = request.getParameter("nome");
            String sexo = request.getParameter("sexo");
            String data = request.getParameter("dataNascimento");
            java.sql.Date dataNascimento;
            
            try{
                dataNascimento = new java.sql.Date(sdf.parse(data).getTime());
            }
            catch(ParseException e){
                dataNascimento = null;
            }
            
            String cpfCnpj = request.getParameter("cpfCnpj");
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone").replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
            String telefone2 = request.getParameter("telefone2").replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String imagem = request.getParameter("imagem");
            String descricao = request.getParameter("descricao");
            
            Usuario newUsuario = new Usuario(1, tipo, 
                    new Pessoa(nome, sexo, dataNascimento, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, imagem, descricao),
                    new Conta(email, senha)
            );
            
            if(GerenciarUsuarios.getInstance().adicionarUsuario(newUsuario))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais");
            
        } else if(uri.equals("/AnyMais/usuario/excluido")){
            String excluido = request.getParameter("excluido");
            
            if(excluido != null){
                int idUsuario = Integer.parseInt(excluido);
                if(GerenciarUsuarios.getInstance().excluirUsuario(idUsuario))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/usuario");
        } else if(uri.equals("/AnyMais/usuario/atualizar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atualizar-usuario.jsp");
            dispatcher.forward(request, response);
        }else if(uri.equals("/AnyMais/usuario/atualizado")){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            int idUsuario = usuario.getIdPessoa();
            int tipo = usuario.getTipo();
            String nome = request.getParameter("nome");
            String sexo = request.getParameter("sexo");
            String data = request.getParameter("dataNascimento");
            java.sql.Date dataNascimento;
            
            try{
                dataNascimento= new java.sql.Date(sdf.parse(data).getTime());
            }
            catch(ParseException e){
                dataNascimento = null;
            }
            
            String cpfCnpj = usuario.getPessoa().getCpfCnpj();
            String endereco = request.getParameter("endereco");
            String bairro = request.getParameter("bairro");
            String complemento = request.getParameter("complemento");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String telefone = request.getParameter("telefone");
            String telefone2 = request.getParameter("telefone2");
            String email = usuario.getConta().getEmail();
            String senha = usuario.getConta().getSenha();
            String imagem = request.getParameter("imagem");
            String descricao = request.getParameter("descricao");            

            Usuario usuarioAtualizada = new Usuario(idUsuario, tipo, 
                    new Pessoa(nome, sexo, dataNascimento, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, imagem, descricao),
                    new Conta(email, senha));
            if(GerenciarUsuarios.getInstance().atualizarUsuario(usuarioAtualizada))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");

            response.sendRedirect("/AnyMais/usuario");
        }
        else if(uri.equals("/AnyMais/usuario/encerrar")){
            boolean encerrado = GerenciarUsuarios.getInstance().excluirUsuario(usuario.getIdPessoa());
            
            if(encerrado){
                request.getSession().invalidate();
                response.sendRedirect("/AnyMais");
            }
            
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
