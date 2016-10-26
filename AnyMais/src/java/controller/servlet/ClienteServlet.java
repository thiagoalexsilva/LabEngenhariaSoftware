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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        System.out.println("Chegou: " + uri);
        if(uri.equals("/AnyMais/clientes")){
        
        //    boolean tipoPetC = request.getParameter("tipo-pet-c") != null;
        //    boolean tipoPetG = request.getParameter("tipo-pet-g") != null;
        //    boolean portePetP = request.getParameter("porte-pet-p") != null;
        //    boolean portePetM = request.getParameter("porte-pet-m") != null;
        //    boolean portePetG = request.getParameter("porte-pet-g") != null;
            
        //    Raca[] racas = GerenciarRacas.getInstance().selecionaRacasComFiltro(tipoPetC, tipoPetG, portePetP, portePetM, portePetG);
        //    request.getSession(true).setAttribute("racas", racas); 
            
        //    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-racas.jsp");
        //    dispatcher.forward(request, response);
            
        //    request.getSession().removeAttribute("status");
        }
        else if(uri.equals("/AnyMais/usuario/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("cadastrar-usuario.jsp");
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
            String celular = request.getParameter("telefone2");
            String sexo = request.getParameter("sexo");  
            String cpf = request.getParameter("cpf-cnpj");  
            String dataNascimento = request.getParameter("nascimento");
            
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
                
            Usuario novo_cliente = new Usuario( cpf, sexo, dataNascimento, celular, 0, email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone, 1);
            if(GerenciarClientes.getInstance().adicionarCliente(novo_cliente))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/clientes");
        }
        else if(uri.equals("/AnyMais/clientes/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idraca = Integer.parseInt(excluido);
                if(GerenciarClientes.getInstance().excluirCliente(uri, uri))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/racas");
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
