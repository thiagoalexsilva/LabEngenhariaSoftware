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
import model.entity.Usuario;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "PesquisaPetShopServlet", urlPatterns = {"/pesquisapetshops/*"})
public class PesquisaPetShopServlet extends HttpServlet {

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
        
        /*Usuario usuario = ((Usuario) request.getSession().getAttribute("usuario"));
        if(usuario == null){
            response.sendRedirect("/AnyMais/erro");
            return;
        }*/
        
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/pesquisapetshops")){
            String nome = request.getParameter("nome-usuario") != null ? request.getParameter("nome-usuario") : "";
            String email = request.getParameter("email-usuario") != null ? request.getParameter("email-usuario") : "";
            String bairro = request.getParameter("bairro-usuario") != null ? request.getParameter("bairro-usuario") : "";
            
            Usuario[] petshops = GerenciarPetShop.getInstance().selecionaPetShopComFiltro(nome, email, bairro);

            request.getSession(true).setAttribute("petshops", petshops);
            request.getSession(true).setAttribute("nome-usuario", nome);
            request.getSession(true).setAttribute("email-usuario", email);
            request.getSession(true).setAttribute("bairro-usuario", bairro);
                        
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-petshops.jsp");
            dispatcher.forward(request, response);
        }
        else{
            String[] parts = uri.split("/");
            int idPetshop = Integer.parseInt(parts[parts.length-1]);
            Usuario petshop = GerenciarUsuarios.getInstance().selecionaUsuario(idPetshop);
            if(petshop != null){
                request.getSession(true).setAttribute("petshop", petshop);
                //TODO: Página da petshop
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-petshop.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.getSession().setAttribute("status", "falha");                
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
