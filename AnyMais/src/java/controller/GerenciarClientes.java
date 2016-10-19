/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import framework.DAO;
import model.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
public class GerenciarClientes {
   private static GerenciarClientes instance;
    private DAO<Pessoa> daoClientes;

    private GerenciarClientes(){
        daoClientes = new DAO<Pessoa>("cliente");
    }

    public static GerenciarClientes getInstance(){
        if(instance == null)
            instance = new GerenciarClientes();
        return instance;
    }
        
    public Pessoa selecionarCliente(String email){
        Pessoa[] clientes = daoClientes.selectAll();
        
        for (Pessoa cliente : clientes)
            if(cliente.getEmail().equals(email))
                return cliente;

        return null;
    }
    
    public boolean adicionarCliente(Pessoa cliente){
        return daoClientes.insert(cliente);
    }
    
    public boolean atualizarCliente(Pessoa cliente){
        return daoClientes.update(cliente);
    }
    
    public boolean excluirCliente(Pessoa cliente){
        return daoClientes.delete(cliente);
    } 
}
