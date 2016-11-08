/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import controller.GerenciarPetShop;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.Pessoa;
/**
 *
 * @author Erica
 */

@WebServlet(name = "PetShopServlet", urlPatterns = {"/apetshop/*"}) 
public class PetShopServlet extends HttpServlet {
    
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
            throws ServletException, IOException, ParseException {
        
        String uri = request.getRequestURI();
        switch (uri) {
            case "/AnyMais/petshop":
                {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-petshop.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
            case "/AnyMais/petshop/cadastrar":
                {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
            case "/AnyMais/petshop/cadastrado":
                {
                    String cadastrar = request.getParameter("cadastrar");
            
                    int tipoPessoa = Integer.parseInt(request.getParameter("tipoPessoa"));
                    String nome = request.getParameter("nome");
            
                    String cpfCnpj = request.getParameter("cpfCnpj");
                    String endereco = request.getParameter("endereco");
                    String bairro = request.getParameter("bairro");
                    String complemento = request.getParameter("complemento");
                    String cep = request.getParameter("cep");
                    String cidade = request.getParameter("cidade");
                    String uf = request.getParameter("uf");
                    String telefone = request.getParameter("telefone");
                    String telefone2 = request.getParameter("telefone2");
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");
                    String imagem = request.getParameter("imagem");
                    String descricao = request.getParameter("descricao");
            
                    Pessoa newPetShop = new Pessoa(1, tipoPessoa, nome, null, null, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, email, senha, imagem, descricao);
            
                    if(GerenciarPetShop.getInstance().adicionarPetShop(newPetShop))
                        request.getSession().setAttribute("status", "sucesso");
                    else
                        request.getSession().setAttribute("status", "falha");
                    response.sendRedirect("/AnyMais/petshop");
                    break;
                }
            case "/AnyMais/petshop/atualizar":
                {
                    String atualizado = request.getParameter("atualizado");
                    if(atualizado != null){
                        int idpetshop = Integer.parseInt(atualizado);
                        Pessoa[] petshops = GerenciarPetShop.getInstance().selecionarPetShop();
                        Pessoa petshopAtualizacao = null;
                        for(Pessoa petshop : petshops){
                            if(petshop.getIdPessoa() == idpetshop){
                                petshopAtualizacao = petshop;
                                break;
                            }
                        }
                        request.getSession(true).setAttribute("petshop", petshopAtualizacao);
                    }
                    else{
                        request.getSession(true).removeAttribute("petshop");
                    }
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrar-usuario.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
            case "/AnyMais/petshop/atualizado":
                {
                    int idPetshop = Integer.parseInt(request.getParameter("idPessoa"));
                    int tipoPessoa = Integer.parseInt(request.getParameter("tipo"));
                    String nome = request.getParameter("nome");
            
                    String cpfCnpj = request.getParameter("cpfCnpj");
                    String endereco = request.getParameter("endereco");
                    String bairro = request.getParameter("bairro");
                    String complemento = request.getParameter("complemento");
                    String cep = request.getParameter("cep");
                    String cidade = request.getParameter("cidade");
                    String uf = request.getParameter("uf");
                    String telefone = request.getParameter("telefone");
                    String telefone2 = request.getParameter("telefone2");
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");
                    String imagem = request.getParameter("imagem");
                    String descricao = request.getParameter("descricao");
            
                    Pessoa petshopAtualizada = new Pessoa(idPetshop, tipoPessoa, nome, null, null, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, email, senha, imagem, descricao);
                    
                    if(GerenciarPetShop.getInstance().atualizarPetShop(petshopAtualizada))
                        request.getSession().setAttribute("status", "sucesso");
                    else
                        request.getSession().setAttribute("status", "falha");

                    response.sendRedirect("/AnyMais/petshop");
                    break;
                }
            case "/AnyMais/petshop/excluido":
                String excluido = request.getParameter("excluido");
                if(excluido != null){
                    int idPessoa = Integer.parseInt(excluido);
                    if(GerenciarPetShop.getInstance().excluirPetShop(idPessoa))
                        request.getSession().setAttribute("status", "sucesso");
                    else
                        request.getSession().setAttribute("status", "falha");
                    }   
                response.sendRedirect("/AnyMais/petshop");
                break;
            case "/AnyMais/petshop/)":
                {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home-petshop.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
        }
    }
}
