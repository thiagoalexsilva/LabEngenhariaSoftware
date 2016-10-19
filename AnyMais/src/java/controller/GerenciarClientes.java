/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import framework.DAO;
import framework.DAOPessoa;
import model.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
public class GerenciarClientes {
   private static GerenciarClientes instance;
    private DAOPessoa daoCliente;

    private GerenciarClientes(){
        daoCliente = new DAOPessoa();
    }

    public static GerenciarClientes getInstance(){
        if(instance == null)
            instance = new GerenciarClientes();
        return instance;
    }
        
    public Pessoa selecionarCliente(String email){
        return daoCliente.select(email);
    }
    
    public boolean adicionarCliente(Pessoa cliente){
        return daoCliente.insert(cliente);
    }
    
    public boolean atualizarCliente(Pessoa cliente){
        return daoCliente.update(cliente);
    }
    
    public boolean excluirCliente(String email, String senha){
        return daoCliente.delete(email, senha);
    } 
}
