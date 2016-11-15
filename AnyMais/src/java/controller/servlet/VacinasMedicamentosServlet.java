/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarTiposAnimal;
import controller.GerenciarTiposVacinasMedicamentos;
import controller.GerenciarVacinasMedicamentos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.TipoAnimal;
import model.entity.TipoVacinaMedicamento;
import model.entity.Usuario;
import model.entity.VacinasMedicamentos;

/**
 *
 * @author ana
 */
@WebServlet(name = "VacinasMedicamentosServlet", urlPatterns = {"/vacinasMedicamentos/*"})
public class VacinasMedicamentosServlet extends HttpServlet {
    
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
       
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        /*
        TODO: Descomentar trava de sessão sem usuário admin.
        Usuario usuario = ((Usuario) request.getSession().getAttribute("admin"));
        if(usuario == null){
            response.sendRedirect("/AnyMais/erro");
            return;
        }
        */
        
        String uri = request.getRequestURI();
        if(uri.equals("/AnyMais/vacinasMedicamentos")){
            
            String nomeVacinaMedicamento = request.getParameter("nome") != null ? request.getParameter("nome") : "";
            boolean tipoPetC = request.getParameter("tipo-pet-c") != null;
            boolean tipoPetG = request.getParameter("tipo-pet-g") != null;
            boolean tipoVacina = request.getParameter("tipo-vacina") != null;
            boolean tipoMedicamento = request.getParameter("tipo-medicamento") != null;
           
            
            if(primeiraExecucao){
                tipoPetC = true;
                tipoPetG = true;
                tipoVacina = true;
                tipoMedicamento = true;
                primeiraExecucao = false;
                
            }
            
            request.getSession(true).removeAttribute("nome");
            request.getSession(true).removeAttribute("tipo-pet-c");
            request.getSession(true).removeAttribute("tipo-pet-g");
            request.getSession(true).removeAttribute("tipo-vacina");
            request.getSession(true).removeAttribute("tipo-medicamento");
            
            
            if(nomeVacinaMedicamento != null)
                request.getSession(true).setAttribute("nome", nomeVacinaMedicamento);
            if(tipoPetC)
                request.getSession(true).setAttribute("tipo-pet-c", tipoPetC);
            if(tipoPetG)
                request.getSession(true).setAttribute("tipo-pet-g", tipoPetG);
            if(tipoVacina)
                request.getSession(true).setAttribute("tipo-vacina", tipoVacina);
            if(tipoMedicamento)
                request.getSession(true).setAttribute("tipo-medicamento", tipoMedicamento);
            
          
            
            VacinasMedicamentos[] vacinasMedicamentos = GerenciarVacinasMedicamentos.getInstance().selecionaVacinasMedicamentosComFiltro(nomeVacinaMedicamento, tipoPetC, tipoPetG, tipoVacina, tipoMedicamento);
            request.getSession(true).setAttribute("medicamento", vacinasMedicamentos); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-vacinas-medicamentos.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("medicamento");
            request.getSession().removeAttribute("status");
        }
        else if(uri.equals("/AnyMais/vacinasMedicamentos/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-vacinas-medicamentos.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/vacinasMedicamentos/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");
            
            String nomeTipoVacinaMedicamento = request.getParameter("tipoVacinaMedicamento");
            TipoVacinaMedicamento tipoVacinaMedicamento = GerenciarTiposVacinasMedicamentos.getInstance().selecionaTiposAnimalPorNome(nomeTipoVacinaMedicamento);
            
            String nomeTipoAnimal = request.getParameter("tipoAnimal");
            TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorNome(nomeTipoAnimal);
            
            String nomeVacinaMedicamento = request.getParameter("nome");
            int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));  
            String tempo = request.getParameter("tempo");
            String observacao = request.getParameter("observacao");
            
            
            VacinasMedicamentos novaVacinaMedicamento = new VacinasMedicamentos(1, tipoVacinaMedicamento, tipoAnimal, nomeVacinaMedicamento, periodicidade, tempo, observacao); //id mock
            if(GerenciarVacinasMedicamentos.getInstance().adicionarVacinaMedicamento(novaVacinaMedicamento))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/vacinasMedicamentos");
            
        }
        else if(uri.equals("/AnyMais/vacinasMedicamentos/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idVacinaMedicamento = Integer.parseInt(excluido);
                if(GerenciarVacinasMedicamentos.getInstance().excluirVacinaMedicamento(idVacinaMedicamento))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/vacinasMedicamentos");
            
        } else if(uri.equals("/AnyMais/vacinasMedicamentos/atualizar")){
            String atualizado = request.getParameter("atualizado");
            if(atualizado != null){
                int idVacinaMedicamento = Integer.parseInt(atualizado);
                VacinasMedicamentos[] vacinasMedicamentos = GerenciarVacinasMedicamentos.getInstance().selecionaVacinasMedicamentos();
                VacinasMedicamentos vacinaMedicamentoAtualizacao = null;
                for(VacinasMedicamentos vacinaMedicamento : vacinasMedicamentos){
                    if(vacinaMedicamento.getIdVacinasMedicamentos() == idVacinaMedicamento){
                        vacinaMedicamentoAtualizacao = vacinaMedicamento;
                        break;
                    }
                }
                request.getSession(true).setAttribute("medicamento", vacinaMedicamentoAtualizacao);
            }
            else{
                request.getSession(true).removeAttribute("medicamento");
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-vacinas-medicamentos.jsp");
            dispatcher.forward(request, response);
            
        } else if(uri.equals("/AnyMais/vacinasMedicamentos/atualizado")){
            
            int idVacinaMedicamento = ((VacinasMedicamentos) request.getSession(true).getAttribute("medicamento")).getIdVacinasMedicamentos();
            String nomeTipoVacinaMedicamento = request.getParameter("tipoVacinaMedicamento");
            TipoVacinaMedicamento tipoVacinaMedicamento = GerenciarTiposVacinasMedicamentos.getInstance().selecionaTiposAnimalPorNome(nomeTipoVacinaMedicamento);
            
            String nomeTipoAnimal = request.getParameter("tipoAnimal");
            TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorNome(nomeTipoAnimal);
            
            String nomeVacinaMedicamento = request.getParameter("nome");
            int periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
            String tempo = request.getParameter("tempo");
            String observacao = request.getParameter("obeservacao");
            
            VacinasMedicamentos vacinaMedicamentoAtualizada = new VacinasMedicamentos(idVacinaMedicamento, tipoVacinaMedicamento, tipoAnimal, nomeVacinaMedicamento, periodicidade, tempo, observacao);
            if(GerenciarVacinasMedicamentos.getInstance().atualizarVacinaMedicamento(vacinaMedicamentoAtualizada))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");

            response.sendRedirect("/AnyMais/vacinasMedicamentos");
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
