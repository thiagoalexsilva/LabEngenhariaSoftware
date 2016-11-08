/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.dao.DAOPessoa;
import model.entity.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
public class GerenciarClientes {
   private static GerenciarClientes instance;
    private DAOPessoa daoUsuario;

    private GerenciarClientes(){
        daoUsuario = new DAOPessoa();
    }

    public static GerenciarClientes getInstance(){
        if(instance == null)
            instance = new GerenciarClientes();
        return instance;
    }
        
    /*public Pessoa selecionarCliente(String email){
        return daoUsuario.select(email);
    }*/
    
    public Pessoa[] selecionaClientes(){
        return daoUsuario.selectAll();
    }
    
    public Pessoa selecionaCliente(int idPessoa){
        return daoUsuario.select(idPessoa);
    }
    
    public boolean adicionarCliente(Pessoa cliente){
        return daoUsuario.insert(cliente);
    }
    
    public boolean atualizarCliente(Pessoa cliente){
        return daoUsuario.update(cliente);
    }
    
    public boolean excluirCliente(int id){
        return daoUsuario.delete(id);
    } 
    
    public boolean verificarCPF_Email(String cpf, String email){
        return daoUsuario.checkCPF_Email(cpf, email);
    }
}
