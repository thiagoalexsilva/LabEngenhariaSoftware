/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import model.dao.DAOPessoa;
import model.dao.DAOPetShop;
import model.entity.Pessoa;
import model.entity.PetShop;
/**
 *
 * @author Erica
 */
public class GerenciarPetShop {
    private static GerenciarPetShop instance;
    private DAOPessoa daoPessoa;
    private DAOPetShop daoPetShop;
    
    private GerenciarPetShop(){
        daoPetShop = new DAOPetShop();
        daoPessoa = new DAOPessoa();
    }    
    
    public static GerenciarPetShop getInstance(){
        if(instance == null){
            instance = new GerenciarPetShop();
        }
        return instance;
    }
    
    public Pessoa selecionarPetShop(String nome){
        return daoPetShop.select(nome);
    }
    
    public boolean adicionarPetShop(PetShop petshop){
        return daoPetShop.insert(petshop);
    }
    
    public boolean atualizarPetShop(PetShop petshop){
        return daoPetShop.update(petshop);
    }
    
    public boolean excluirPetShop(int idPetShop){
        return daoPetShop.delete(idPetShop) && daoPessoa.delete(idPetShop);
    }
    
    public boolean verificarCNPJ_Email(String cnpj, String email){
        return daoPetShop.checkCNPJ_Email(cnpj, email);
    }
}
