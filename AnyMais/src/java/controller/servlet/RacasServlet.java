/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarRacas;
import controller.GerenciarTiposAnimal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Raca;
import model.entity.TipoAnimal;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "RacasServlet", urlPatterns = {"/racas/*"})
public class RacasServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = request.getRequestURI();
        //System.out.println("Chegou: " + uri);
        if(uri.equals("/AnyMais/racas")){
        
            String nomeRaca = request.getParameter("nome-raca") != null ? request.getParameter("nome-raca") : "";
            boolean tipoPetC = request.getParameter("tipo-pet-c") != null;
            boolean tipoPetG = request.getParameter("tipo-pet-g") != null;
            boolean portePetP = request.getParameter("porte-pet-p") != null;
            boolean portePetM = request.getParameter("porte-pet-m") != null;
            boolean portePetG = request.getParameter("porte-pet-g") != null;
            
            if(primeiraExecucao){
                tipoPetC = true;
                tipoPetG = true;
                portePetP = true;
                portePetM = true;
                portePetG = true;
                primeiraExecucao = false;
                
            }
            
            request.getSession(true).removeAttribute("nome-raca");
            request.getSession(true).removeAttribute("tipo-pet-c");
            request.getSession(true).removeAttribute("tipo-pet-g");
            request.getSession(true).removeAttribute("porte-pet-p");
            request.getSession(true).removeAttribute("porte-pet-m");
            request.getSession(true).removeAttribute("porte-pet-g");
            
            if(nomeRaca != null)
                request.getSession(true).setAttribute("nome-raca", nomeRaca);
            if(tipoPetC)
                request.getSession(true).setAttribute("tipo-pet-c", tipoPetC);
            if(tipoPetG)
                request.getSession(true).setAttribute("tipo-pet-g", tipoPetG);
            if(portePetP)
                request.getSession(true).setAttribute("porte-pet-p", portePetP);
            if(portePetM)
                request.getSession(true).setAttribute("porte-pet-m", portePetM);
            if(portePetG)
                request.getSession(true).setAttribute("porte-pet-g", portePetG);
            
            Raca[] racas = GerenciarRacas.getInstance().selecionaRacasComFiltro(nomeRaca, tipoPetC, tipoPetG, portePetP, portePetM, portePetG);
            request.getSession(true).setAttribute("racas", racas); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-racas.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("raca");
            request.getSession().removeAttribute("status");
        }
        else if(uri.equals("/AnyMais/racas/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-racas.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/racas/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");
            if(cadastrar != null && cadastrar.equals("Cadastrar")){
                String nomeTipoAnimal = request.getParameter("tipo");
                TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorNome(nomeTipoAnimal);
                
                String nomeRaca = request.getParameter("nome-raca");
                String porte = request.getParameter("porte");
                String observacao = request.getParameter("observacao");            

                Raca novaRaca = new Raca(1, tipoAnimal, nomeRaca, porte, observacao); //id mock
                if(GerenciarRacas.getInstance().adicionarRaca(novaRaca))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/racas");
        }
        else if(uri.equals("/AnyMais/racas/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idraca = Integer.parseInt(excluido);
                if(GerenciarRacas.getInstance().excluirRaca(idraca))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/racas");
        }
        else if(uri.equals("/AnyMais/racas/atualizar")){
            String atualizado = request.getParameter("atualizado");
            if(atualizado != null){
                int idraca = Integer.parseInt(atualizado);
                Raca[] racas = GerenciarRacas.getInstance().selecionaRacas();
                Raca racaAtualizacao = null;
                for(Raca raca : racas){
                    if(raca.getIdRaca() == idraca){
                        racaAtualizacao = raca;
                        break;
                    }
                }
                request.getSession(true).setAttribute("raca", racaAtualizacao);
            }
            else{
                request.getSession(true).removeAttribute("raca");
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-racas.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/racas/atualizado")){
            String cadastrar = request.getParameter("cadastrar");
            if(cadastrar != null && cadastrar.equals("Atualizar")){
                int idRaca = ((Raca) request.getSession(true).getAttribute("raca")).getIdRaca();
                String nomeTipoAnimal = request.getParameter("tipo");
                TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorNome(nomeTipoAnimal);
                                
                String nomeRaca = request.getParameter("nome-raca");
                String porte = request.getParameter("porte");
                String observacao = request.getParameter("observacao");            

                Raca racaAtualizada = new Raca(idRaca, tipoAnimal, nomeRaca, porte, observacao);
                if(GerenciarRacas.getInstance().atualizarRaca(racaAtualizada))
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
