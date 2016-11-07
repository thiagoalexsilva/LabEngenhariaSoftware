/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarRacas;
import controller.GerenciarTiposAnimal;
import controller.GerenciarVeterinario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Veterinario;

/**
 *
 * @author Erica
 */
@WebServlet(name = "VeterinarioServlet", urlPatterns = {"/veterinario/*"})
public class VeterinarioServlet extends HttpServlet{
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/veterinario")){
            
            String nome = request.getParameter("nome") != null ? request.getParameter("nome") : "";
            
            request.getSession(true).removeAttribute("nome");
            
            if(nome != null)
                request.getSession(true).setAttribute("nome", nome);
            
            Veterinario[] veterinarios = GerenciarVeterinario.getInstance().selecionaVeterinariosComFiltro(nome);
            request.getSession(true).setAttribute("veterinario", veterinarios); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-veterinarios.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("veterinario");
            request.getSession().removeAttribute("status");
            
        } else if(uri.equals("/AnyMais/veterinario/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-veterinario.jsp");
            dispatcher.forward(request, response);
            
        } else if(uri.equals("/AnyMais/veterinario/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");
            
            String nome = request.getParameter("nome");
            String crmv = request.getParameter("crmv");
            String observacao = request.getParameter("observacao");
            
            Veterinario newVeterinario = new Veterinario(1, nome, crmv, observacao);
            
            if(GerenciarVeterinario.getInstance().adicionarVeterinario(newVeterinario))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/veterinario");
            
        } else if(uri.equals("/AnyMais/veterinario/excluido")){
            String excluido = request.getParameter("excluido");
            
            if(excluido != null){
                int idVeterinario = Integer.parseInt(excluido);
                if(GerenciarVeterinario.getInstance().excluirVeterinario(idVeterinario))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/veterinario");
            
        }else if(uri.equals("/AnyMais/veterinario/atualizar")){
            String atualizado = request.getParameter("atualizado");
            
            if(atualizado != null){
                int idVeterinario = Integer.parseInt(atualizado);
                Veterinario[] veterinarios = GerenciarVeterinario.getInstance().selecionaVeterinario();
                Veterinario veterinarioAtualizacao = null;
                for(Veterinario v : veterinarios){
                    if(v.getIdVeterinario() == idVeterinario){
                        veterinarioAtualizacao = v;
                        break;
                    }
                }
                request.getSession(true).setAttribute("veterinario", veterinarioAtualizacao);
            } else{
                    request.getSession(true).removeAttribute("veterinario");
                }
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-veterinario.jsp");
                dispatcher.forward(request, response);
                
    } else if(uri.equals("/AnyMais/veterinario/atualizado")){
            int idVeterinario = Integer.parseInt(request.getParameter("idVeterinario"));
            String nome = request.getParameter("nome");
            String crmv = request.getParameter("crmv");
            String observacao = request.getParameter("observacao");            

            Veterinario veterinarioAtualizado = new Veterinario(idVeterinario, nome, crmv, observacao);
            if(GerenciarVeterinario.getInstance().atualizarVeterinario(veterinarioAtualizado))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");

            response.sendRedirect("/AnyMais/veterinario");
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
