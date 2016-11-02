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
 * @author Erica
 */
public class GerenciarPetShop {
    private static GerenciarPetShop instance;
    private DAOPessoa daoPetShop;
    
    private GerenciarPetShop(){
        daoPetShop = new DAOPessoa();
    }    
    
    public static GerenciarPetShop getInstance(){
        if(instance == null){
            instance = new GerenciarPetShop();
        }
        return instance;
    }
    
    public Pessoa selecionarPetShop(String email, String nome){
        return daoPetShop.select(email, nome);
    }
    
    public boolean adicionarPetShop(Pessoa petshop){
        return daoPetShop.insert(petshop);
    }
    
    public boolean atualizarPetShop(Pessoa petshop){
        return daoPetShop.update(petshop);
    }
    
    public boolean excluirPetShop(int idPetShop){
        return daoPetShop.delete(idPetShop);
    }
    
    public boolean verificarCNPJ_Email(String cnpj, String email){
        return daoPetShop.checkCNPJ_Email(cnpj, email);
    }
}
