/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarMedicamentos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.VacinaMedicamento;

/**
 *
 * @author ana
 */
@WebServlet(name = "MedicamentoServlet", urlPatterns = {"/MedicamentoServlet"})
public class MedicamentoServlet extends HttpServlet {
    
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
        if(uri.equals("/AnyMais/VacinaseMedicamentos")){
            
            boolean tipoPetC = request.getParameter("tipo-pet-c") != null;
            boolean tipoPetG = request.getParameter("tipo-pet-g") != null;
            String nome= request.getParameter("nome-medicamento") != null ? request.getParameter("nome-medicamento") : "";
            boolean tipoMedM = request.getParameter("tipo-med-m") != null;
            boolean tipoMedV= request.getParameter("tipo-med-v") != null;
            boolean periodMedH = request.getParameter("period-med-h") != null;
            boolean periodMedD = request.getParameter("period-med-d") != null;
            boolean periodMedS = request.getParameter("period-med-s") != null;
            boolean periodMedM = request.getParameter("period-med-m") != null;
            boolean periodMedA = request.getParameter("period-med-a") != null;
           
            
            if(primeiraExecucao){
                tipoPetC = true;
                tipoPetG = true;
                tipoMedM = true;
                tipoMedV = true;
                periodMedH = true;
                periodMedD = true;
                periodMedS = true;
                periodMedM = true;
                periodMedA= true;
                primeiraExecucao = false;
                
            }
            
            request.getSession(true).removeAttribute("nome-medicamento");
            request.getSession(true).removeAttribute("tipo-pet-c");
            request.getSession(true).removeAttribute("tipo-pet-g");
            request.getSession(true).removeAttribute("tipo-med-m");
            request.getSession(true).removeAttribute("tipo-med-v");
            request.getSession(true).removeAttribute("period-med-h");
            request.getSession(true).removeAttribute("period-med-d");
            request.getSession(true).removeAttribute("period-med-s");
            request.getSession(true).removeAttribute("period-med-m");
            request.getSession(true).removeAttribute("period-med-a");
            
          
            
            if(nome != null)
                request.getSession(true).setAttribute("nome-medicamento", nome);
            if(tipoPetC)
                request.getSession(true).setAttribute("tipo-pet-c", tipoPetC);
            if(tipoPetG)
                request.getSession(true).setAttribute("tipo-pet-g", tipoPetG);
            if(tipoMedM)
                request.getSession(true).setAttribute("tipo-med-m", tipoMedM);
            if(tipoMedV)
                request.getSession(true).setAttribute("porte-pet-m", tipoMedV);
            if(periodMedH)
                request.getSession(true).setAttribute("period-med-h", periodMedH);
            if(periodMedD)
                request.getSession(true).setAttribute("period-med-d", periodMedD);
            if(periodMedS)
                request.getSession(true).setAttribute("period-med-s", periodMedS);
            if(periodMedM)
                request.getSession(true).setAttribute("period-med-m", periodMedM);
            if(periodMedA)
                request.getSession(true).setAttribute("period-med-a", periodMedA);
          
            
            VacinaMedicamento[] medicamentos = GerenciarMedicamentos.getInstance().selecionaMedicamentosComFiltro(nome, tipoMedM, tipoMedV, tipoPetC, tipoPetG, periodMedH, periodMedD, periodMedS, periodMedM, periodMedA);
            request.getSession(true).setAttribute("medicamentos", medicamentos); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-medicamentos.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("status");
        }
        else if(uri.equals("/AnyMais/VacinaseMedicamentos/cadastrar")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-medicamentos.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/VacinaseMedicamentos/cadastrado")){
            String cadastrar = request.getParameter("cadastrar");
            if(cadastrar != null && cadastrar.equals("Cadastrar")){
                String tipo = request.getParameter("tipo-med");
                String tipoAnimal = request.getParameter("tipo-pet");
                String nome = request.getParameter("nome");
                Integer periodicidade = Integer.parseInt(request.getParameter("periodicidade"));
                String observacao = request.getParameter("observacao");            

                VacinaMedicamento novoMed = new VacinaMedicamento(tipo, tipoAnimal, nome, periodicidade, observacao);
                if(GerenciarMedicamentos.getInstance().adicionarMedicamento(novoMed))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/VacinaseMedicamentos");
        }
        else if(uri.equals("/AnyMais/VacinaseMedicamentos/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idmed_vac = Integer.parseInt(excluido);
                if(GerenciarMedicamentos.getInstance().excluirMedicamento(idmed_vac))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/VacinaseMedicamentos");
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
