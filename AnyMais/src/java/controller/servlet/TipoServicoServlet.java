/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarTiposServico;
import controller.GerenciarTiposAnimal;
import controller.GerenciarTiposServico;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.TipoServico;
import model.entity.TipoAnimal;
import model.entity.TipoServico;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "TiposServicoServlet", urlPatterns = {"/petshop/servicos/*"})
public class TipoServicoServlet extends HttpServlet {

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
        //System.out.println("Chegou: " + uri);
        if(uri.equals("/AnyMais/petshop/servicos")){
        
            String nomeTipoServico = request.getParameter("nomeServico") != null ? request.getParameter("nomeServico") : "";
            
            request.getSession(true).removeAttribute("nomeServico");
            
            if(nomeTipoServico != null)
                request.getSession(true).setAttribute("nomeServico", nomeTipoServico);
            
            TipoServico[] tiposServico = GerenciarTiposServico.getInstance().selecionaTiposServicoComFiltro(nomeTipoServico);
            request.getSession(true).setAttribute("servicos", tiposServico); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-tipos-servico.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("servico");
            request.getSession().removeAttribute("status");
        }
        else if(uri.equals("/AnyMais/petshop/servicos/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-tipos-servico.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/petshop/servicos/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");
            
            String nome = request.getParameter("nomeServico");
            String duracaoStr = request.getParameter("duracao");
            int duracao = Integer.parseInt(duracaoStr);
            String valorStr = request.getParameter("valor");
            double valor = Double.parseDouble(valorStr);
            String observacao = request.getParameter("observacao");            

            TipoServico novoTipoServico = new TipoServico(1, nome, duracao, valor, observacao); //id mock
            if(GerenciarTiposServico.getInstance().adicionarTipoServico(novoTipoServico))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/petshop/servicos");
        }
        else if(uri.equals("/AnyMais/petshop/servicos/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idTipoServico = Integer.parseInt(excluido);
                if(GerenciarTiposServico.getInstance().excluirTipoServico(idTipoServico))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/petshop/servicos");
        }
        else if(uri.equals("/AnyMais/petshop/servicos/atualizar")){
            String atualizado = request.getParameter("atualizado");
            if(atualizado != null){
                int idTipoServico = Integer.parseInt(atualizado);
                TipoServico[] tiposServico = GerenciarTiposServico.getInstance().selecionaTiposServico();
                TipoServico tipoServicoAtualizacao = null;
                for(TipoServico tipoServico : tiposServico){
                    if(tipoServico.getIdTipoServico() == idTipoServico){
                        tipoServicoAtualizacao = tipoServico;
                        break;
                    }
                }
                request.getSession(true).setAttribute("servico", tipoServicoAtualizacao);
            }
            else{
                request.getSession(true).removeAttribute("servico");
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-tipos-servico.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/petshop/servicos/atualizado")){
            int idTipoServico = ((TipoServico) request.getSession(true).getAttribute("servico")).getIdTipoServico();
            String nome = request.getParameter("nomeServico");
            String duracaoStr = request.getParameter("duracao");
            int duracao = Integer.parseInt(duracaoStr);
            String valorStr = request.getParameter("valor");
            double valor = Double.parseDouble(valorStr);
            String observacao = request.getParameter("observacao");            

            TipoServico tipoServicoAtualizado = new TipoServico(idTipoServico, nome, duracao, valor, observacao);
            if(GerenciarTiposServico.getInstance().atualizarTipoServico(tipoServicoAtualizado))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");

            response.sendRedirect("/AnyMais/petshop/servicos");
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
