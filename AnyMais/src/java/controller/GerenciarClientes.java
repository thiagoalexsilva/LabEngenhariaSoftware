/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import framework.DAO;
import model.dao.DAOPessoa;
import model.dao.DAOUsuario;
import model.entity.Pessoa;
import model.entity.Usuario;

/**
 *
 * @author ThiagoAlexandre
 */
public class GerenciarClientes {
   private static GerenciarClientes instance;
    private DAOUsuario daoCliente;
    private DAOPessoa daoPessoa;

    private GerenciarClientes(){
        daoCliente = new DAOUsuario();
        daoPessoa = new DAOPessoa();
    }

    public static GerenciarClientes getInstance(){
        if(instance == null)
            instance = new GerenciarClientes();
        return instance;
    }
        
    public Usuario selecionarCliente(String email){
        return daoCliente.select(email);
    }
    
    public boolean adicionarCliente(Usuario cliente){
        return daoCliente.insert(cliente);
    }
    
    public boolean atualizarCliente(Usuario cliente){
        return daoCliente.update(cliente) && daoPessoa.update(cliente);
    }
    
    public boolean excluirCliente(String email, String senha){
        return daoCliente.delete(email, senha) && daoPessoa.delete(email, senha);
    } 
    
    public boolean verificarCPF_Email(String cpf, String email){
        return daoCliente.checkCPF_Email(cpf, email);
    }
}
