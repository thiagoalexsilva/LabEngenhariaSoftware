/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarAnimais;
import controller.GerenciarRacas;
import controller.GerenciarTiposAnimal;
import controller.GerenciarUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Animal;
import model.entity.Raca;
import model.entity.TipoAnimal;
import model.entity.Usuario;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "AnimaisServlet", urlPatterns = {"/usuario/animais/*"})
public class AnimaisServlet extends HttpServlet {

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
        
        String uri = request.getRequestURI();
        //System.out.println("Chegou: " + uri);
/*        if(uri.equals("/AnyMais/animais")){
        
            String nomeAnimal = request.getParameter("nome-animal") != null ? request.getParameter("nome-animal") : "";
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
            
            request.getSession(true).removeAttribute("nome-animal");
            request.getSession(true).removeAttribute("tipo-pet-c");
            request.getSession(true).removeAttribute("tipo-pet-g");
            request.getSession(true).removeAttribute("porte-pet-p");
            request.getSession(true).removeAttribute("porte-pet-m");
            request.getSession(true).removeAttribute("porte-pet-g");
            
            if(nomeAnimal != null)
                request.getSession(true).setAttribute("nomeAnimal", nomeAnimal);
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
            
            Animal[] animais = GerenciarAnimais.getInstance().selecionaAnimaisComFiltro(nomeAnimal, tipoPetC, tipoPetG, portePetP, portePetM, portePetG);
            request.getSession(true).setAttribute("animais", animais); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ver-animais.jsp");
            dispatcher.forward(request, response);
            
            request.getSession().removeAttribute("animal");
            request.getSession().removeAttribute("status");
        }
        else */if(uri.equals("/AnyMais/usuario/animais/cadastrar")){
            request.getSession().removeAttribute("atualizar");
            
            // MOCK
            request.getSession().setAttribute("usuario", GerenciarUsuarios.getInstance().selecionaUsuario(1));
            
            request.getSession().setAttribute("racas", GerenciarRacas.getInstance().selecionaRacas());
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-animais.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/usuario/animais/cadastrar/carregaracas")){
            request.setAttribute("carregaracas", true);
            
            // MOCK
            request.getSession().setAttribute("racas", GerenciarRacas.getInstance().selecionaRacas());
            
            
            request.getSession().setAttribute("tipoAnimal", request.getParameter("tipoAnimal"));
            request.getSession().setAttribute("nomeAnimal", request.getParameter("nomeAnimal"));
            request.getSession().setAttribute("racaAnimal", request.getParameter("racaAnimal"));
            request.getSession().setAttribute("dataNascimentoAnimal", request.getParameter("dataNascimentoAnimal"));
            request.getSession().setAttribute("pesoAnimal", request.getParameter("pesoAnimal"));
            request.getSession().setAttribute("tamanhoAnimal", request.getParameter("tamanhoAnimal"));
            request.getSession().setAttribute("corAnimal", request.getParameter("corAnimal"));
            request.getSession().setAttribute("sexoAnimal", request.getParameter("sexoAnimal"));            
            request.getSession().setAttribute("descricaoAnimal", request.getParameter("descricaoAnimal"));            
            
            String imagem = null;
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-animais.jsp");
            dispatcher.forward(request, response);
            
            
            request.getSession().removeAttribute("tipoAnimal");
            request.getSession().removeAttribute("nomeAnimal");
            request.getSession().removeAttribute("racaAnimal");
            request.getSession().removeAttribute("dataNascimentoAnimal");
            request.getSession().removeAttribute("pesoAnimal");
            request.getSession().removeAttribute("tamanhoAnimal");
            request.getSession().removeAttribute("corAnimal");
            request.getSession().removeAttribute("sexoAnimal");            
            request.getSession().removeAttribute("descricaoAnimal");            
            
            
            request.removeAttribute("carregaracas");
            
        }
        else if(uri.equals("/AnyMais/usuario/animais/cadastrado")){
            
            String cadastrar = request.getParameter("cadastrar");
            
            String nomeTipoAnimal = request.getParameter("tipoAnimal");
            TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorNome(nomeTipoAnimal);

            String nomeAnimal = request.getParameter("nomeAnimal");
            
            int idracaAnimal = Integer.parseInt(request.getParameter("racaAnimal"));
            Raca racaAnimal = GerenciarRacas.getInstance().selecionaRaca(idracaAnimal);
            
            SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
            String data = request.getParameter("dataNascimentoAnimal");
            java.sql.Date dataNascimentoAnimal;
            try{
                dataNascimentoAnimal = new java.sql.Date(sdf.parse(data).getTime());
            }
            catch(ParseException e){
                dataNascimentoAnimal = null;
            }
            
            double peso = Double.parseDouble(request.getParameter("pesoAnimal"));
            double tamanho = Double.parseDouble(request.getParameter("tamanhoAnimal"));
            String cor = request.getParameter("corAnimal");
            String sexo = request.getParameter("sexoAnimal");            
            String descricao = request.getParameter("descricaoAnimal");            
            String imagem = null;
            
            Animal novoAnimal = new Animal(1, tipoAnimal, nomeAnimal, racaAnimal, dataNascimentoAnimal, peso, tamanho, cor, sexo, descricao, imagem); //id mock
            if(GerenciarAnimais.getInstance().adicionarAnimais(novoAnimal))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");
            
            response.sendRedirect("/AnyMais/usuario/animais");
        }
        /*else if(uri.equals("/AnyMais/animais/excluido")){
            String excluido = request.getParameter("excluido");
            if(excluido != null){
                int idanimal = Integer.parseInt(excluido);
                if(GerenciarAnimais.getInstance().excluirAnimal(idanimal))
                    request.getSession().setAttribute("status", "sucesso");
                else
                    request.getSession().setAttribute("status", "falha");
            }
            
            response.sendRedirect("/AnyMais/animais");
        }*/
        else if(uri.equals("/AnyMais/usuario/animais/atualizar")){
            request.getSession().setAttribute("atualizar", true);
            
            String atualizado = request.getParameter("atualizado");
            if(atualizado != null){
                int idanimal = Integer.parseInt(atualizado);
                Animal[] animais = GerenciarAnimais.getInstance().selecionaAnimais();
                Animal animalAtualizacao = null;
                for(Animal animal : animais){
                    if(animal.getIdAnimal() == idanimal){
                        animalAtualizacao = animal;
                        break;
                    }
                }
                request.getSession(true).setAttribute("animal", animalAtualizacao);
            }
            else{
                request.getSession(true).removeAttribute("animal");
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-animais.jsp");
            dispatcher.forward(request, response);
        }
        else if(uri.equals("/AnyMais/usuario/animais/atualizado")){
            request.getSession().removeAttribute("carregaracas");
            
            int idAnimal = ((Animal) request.getSession(true).getAttribute("animal")).getIdAnimal();
            String nomeTipoAnimal = request.getParameter("tipoAnimal");
            TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorNome(nomeTipoAnimal);

            String nomeAnimal = request.getParameter("nomeAnimal");
            
            int idracaAnimal = Integer.parseInt(request.getParameter("racaAnimal"));
            Raca racaAnimal = GerenciarRacas.getInstance().selecionaRaca(idracaAnimal);
            
            SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
            String data = request.getParameter("dataNascimentoAnimal");
            java.sql.Date dataNascimentoAnimal;
            try{
                dataNascimentoAnimal = new java.sql.Date(sdf.parse(data).getTime());
            }
            catch(ParseException e){
                dataNascimentoAnimal = null;
            }
            
            double peso = Double.parseDouble(request.getParameter("pesoAnimal"));
            double tamanho = Double.parseDouble(request.getParameter("tamanhoAnimal"));
            String cor = request.getParameter("corAnimal");
            String sexo = request.getParameter("sexoAnimal");            
            String descricao = request.getParameter("descricaoAnimal");            
            String imagem = null;
            
            Animal animalAtualizado = new Animal(idAnimal, tipoAnimal, nomeAnimal, racaAnimal, dataNascimentoAnimal, peso, tamanho, cor, sexo, descricao, imagem); //id mock
            if(GerenciarAnimais.getInstance().atualizarAnimais(animalAtualizado))
                request.getSession().setAttribute("status", "sucesso");
            else
                request.getSession().setAttribute("status", "falha");

            response.sendRedirect("/AnyMais/usuario/animais");
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
