/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarRacas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Raca;

/**
 *
 * @author Gustavo
 */
public class RacasServlet extends HttpServlet {

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
        if(uri.equals("racas")){
        
            Raca[] racas = GerenciarRacas.getInstance().selecionaRacas();
            request.getSession(true).setAttribute("racas", racas); 
            response.sendRedirect("ver-racas.jsp");
        }
        else if(uri.equals("racas/cadastrar")){
            response.sendRedirect("cadastrar-racas.jsp");
        }
        else if(uri.equals("racas/cadastrado")){
            String tipoAnimal = request.getParameter("tipo-pet");
            String nomeRaca = request.getParameter("nome-raca");
            String porte = request.getParameter("porte");
            String observacao = request.getParameter("observacao");            
            
            Raca novaRaca = new Raca(tipoAnimal, nomeRaca, porte, observacao);
            if(GerenciarRacas.getInstance().adicionarRaca(novaRaca))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            Raca[] racas = GerenciarRacas.getInstance().selecionaRacas();
            request.getSession(true).setAttribute("racas", racas); 
            response.sendRedirect("ver-racas.jsp");
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
