/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import java.util.ArrayList;
import model.dao.DAORaca;
import model.dao.DAOTipoAnimal;
import model.entity.Raca;
import model.entity.TipoAnimal;

/**
 *
 * @author Gustavo
 */
public class GerenciarTiposAnimal {
    
    private static GerenciarTiposAnimal instance;
    private DAOTipoAnimal daoTipoAnimal;

    private GerenciarTiposAnimal(){
        daoTipoAnimal = new DAOTipoAnimal();
    }

    public static GerenciarTiposAnimal getInstance(){
        if(instance == null)
            instance = new GerenciarTiposAnimal();
        return instance;
    }
        
    public TipoAnimal[] selecionaTiposAnimal(){
        return daoTipoAnimal.selectAll();
    }
    
    public TipoAnimal selecionaTiposAnimalPorNome(String nomeTipoAnimal){
        TipoAnimal[] tipos = selecionaTiposAnimal();
        for(TipoAnimal t : tipos){
            if(t.getNomeTipoAnimal().toUpperCase().equals(nomeTipoAnimal.toUpperCase())){
                return t;
            }
        }
        
        return null;
    }
    
    public TipoAnimal selecionaTiposAnimalPorId(int idTipoAnimal){
        TipoAnimal[] tipos = selecionaTiposAnimal();
        for(TipoAnimal t : tipos){
            if(t.getIdTipoAnimal() == idTipoAnimal){
                return t;
            }
        }
        
        return null;
    }
    
}
