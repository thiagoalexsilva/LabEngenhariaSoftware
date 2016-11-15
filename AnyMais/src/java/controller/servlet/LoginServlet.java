/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.servlet;

import controller.GerenciarUsuarios;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Conta;
import model.entity.Usuario;

/**
 *
 * @author ThiagoAlexandre
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/logout", "/atualizarlogin", "/loginatualizado"})
public class LoginServlet extends HttpServlet{
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
        
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/login")){
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            
            Usuario usuario = GerenciarUsuarios.getInstance().selecionarUsuarioLogin(email, senha);
            if(usuario != null){
                if (usuario.getTipo() == 1){
                    request.getSession(true).setAttribute("usuario", usuario);
                    response.sendRedirect("/AnyMais/usuario");
                }
                else if (usuario.getTipo() == 2){
                    request.getSession(true).setAttribute("petshop", usuario);
                    response.sendRedirect("/AnyMais/petshop");
                }
                else if(usuario.getTipo() == 3){
                    // ADM
                }
                else{
                    request.getSession().setAttribute("status", "falha");
                    request.getSession(true).removeAttribute("cliente");
                    request.getSession(true).removeAttribute("petshop");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                }
            }
            else{
                request.getSession().setAttribute("status", "falha");
                request.getSession(true).removeAttribute("cliente");
                request.getSession(true).removeAttribute("petshop");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
            
        }
        else if(uri.equals("/AnyMais/logout")){
            request.getSession().invalidate();
            response.sendRedirect("/AnyMais");
        }
        else if(uri.equals("/AnyMais/atualizarlogin")){
            // TODO: Editar Login
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atualizar-login.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/loginatualizado")){
            String email = request.getParameter("email");
            String confirmarEmail = request.getParameter("confirmarEmail");
            String senha = request.getParameter("senha");
            String confirmarSenha = request.getParameter("confirmarSenha");
            
            boolean invalido = false;
            Usuario[] usuarios = GerenciarUsuarios.getInstance().selecionaUsuarios();
            if(email.equals(confirmarEmail) && senha.equals(confirmarSenha)){
                for(Usuario u : usuarios){
                    if(email.equals(u.getConta().getEmail())){
                        invalido = true;
                        break;
                    }
                }
            }
            else{
                invalido = true;
            }
            
            if(invalido){
                request.getSession().setAttribute("status", "falha");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atualizar-login.jsp");
                dispatcher.forward(request, response);
            }
            else{
                Usuario u = (Usuario) request.getSession().getAttribute("usuario");
                if(u == null){
                    u = (Usuario) request.getSession().getAttribute("petshop");
                    if(u == null){
                        u = (Usuario) request.getSession().getAttribute("admin");
                        if(u == null){

                        }
                    }
                }
                
                if(u != null){
                    u.setConta(new Conta(email, senha));
                    
                    boolean atualizado = GerenciarUsuarios.getInstance().atualizarUsuario(u);
                    if(atualizado){
                        request.getSession().setAttribute("status", "sucesso");
                        response.sendRedirect("/AnyMais/usuario/atualizar");
                    }
                    else{
                        request.getSession().setAttribute("status", "falha");
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atualizar-login.jsp");
                        dispatcher.forward(request, response);
                    }
                }
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
