/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.dao.DAOUsuario;
import model.entity.Pessoa;
import model.entity.Pessoa;
import model.entity.Usuario;
/**
 *
 * @author Erica
 */
public class GerenciarPetShop {
    private static GerenciarPetShop instance;
    private DAOUsuario daoPetShop;
    
    private GerenciarPetShop(){
        daoPetShop = new DAOUsuario();
    }    
    
    public static GerenciarPetShop getInstance(){
        if(instance == null){
            instance = new GerenciarPetShop();
        }
        return instance;
    }
    
    public Usuario[] selecionarPetShops(){
        return GerenciarUsuarios.getInstance().selecionaPetShops();
    }
       
    public Usuario[] selecionaPetShopComFiltro(String nome, String email, String bairro){
        Usuario[] todasPetShops = GerenciarUsuarios.getInstance().selecionaPetShops();
        
        ArrayList<Usuario> filtroPetshop = new ArrayList<Usuario>();
        
        for(Usuario petshop : todasPetShops){
            if (bairro.isEmpty() || petshop.getPessoa().getBairro().contains(bairro))
                if (nome.isEmpty() || petshop.getPessoa().getNome().contains(nome))
                    if (email.isEmpty() || petshop.getConta().getEmail().contains(email))
                        filtroPetshop.add(petshop);
        }
        
        return filtroPetshop.toArray(new Usuario[filtroPetshop.size()]);
    }
    
}
