/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import java.util.ArrayList;
import model.dao.DAORaca;
import model.dao.DAOTipoVacinaMedicamento;
import model.dao.DAOVacinasMedicamentos;
import model.entity.Raca;
import model.entity.TipoVacinaMedicamento;
import model.entity.VacinasMedicamentos;

/**
 *
 * @author Gustavo
 */
public class GerenciarTiposVacinasMedicamentos {
    
    private static GerenciarTiposVacinasMedicamentos instance;
    private DAOTipoVacinaMedicamento daoTipoVacinaMedicamento;

    private GerenciarTiposVacinasMedicamentos(){
        daoTipoVacinaMedicamento = new DAOTipoVacinaMedicamento();
    }

    public static GerenciarTiposVacinasMedicamentos getInstance(){
        if(instance == null)
            instance = new GerenciarTiposVacinasMedicamentos();
        return instance;
    }
        
    public TipoVacinaMedicamento[] selecionaTiposVacinasMedicamentos(){
        return daoTipoVacinaMedicamento.selectAll();
    }
    
    public TipoVacinaMedicamento selecionaTiposAnimalPorNome(String nomeVacinasMedicamentos){
        TipoVacinaMedicamento[] tipos = selecionaTiposVacinasMedicamentos();
        for(TipoVacinaMedicamento t : tipos){
            if(t.getNomeTipoVacinaMedicamento().equals(nomeVacinasMedicamentos)){
                return t;
            }
        }
        
        return null;
    }
    
    public TipoVacinaMedicamento selecionaTiposAnimalPorId(int idVacinasMedicamentos){
        TipoVacinaMedicamento[] tipos = selecionaTiposVacinasMedicamentos();
        for(TipoVacinaMedicamento t : tipos){
            if(t.getIdTipoVacinaMedicamento() == idVacinasMedicamentos){
                return t;
            }
        }
        
        return null;
    }
    
}
