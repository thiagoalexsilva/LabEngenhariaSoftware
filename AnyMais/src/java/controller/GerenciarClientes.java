/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
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
    
    public Pessoa selecionarClienteLogin(String email, String senha){
        Pessoa[] todasPessoas = daoUsuario.selectAll();
        
        for(Pessoa pessoa : todasPessoas){
            if(pessoa.getSenha().equals(senha) && pessoa.getEmail().equals(email)){
                return pessoa;
            }
        } 
        
        return null;
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
