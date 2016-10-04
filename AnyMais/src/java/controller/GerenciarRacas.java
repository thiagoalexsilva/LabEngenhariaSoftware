/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import model.Raca;

/**
 *
 * @author Gustavo
 */
public class GerenciarRacas {
    
    private static GerenciarRacas instance;
    private DAO<Raca> daoRacas;

    private GerenciarRacas(){
        daoRacas = new DAO<Raca>("raca");
    }

    public static GerenciarRacas getInstance(){
        if(instance == null)
            instance = new GerenciarRacas();
        return instance;
    }
        
    public Raca[] selecionaRacas(){
        return null;//return daoRacas.selectAll();
    }
    
    public boolean adicionarRaca(Raca raca){
        return true;//return daoRacas.insert(raca);
    }
    
    public boolean atualizarRaca(Raca raca){
        return true;//return daoRacas.update(raca);
    }
    
    public boolean excluirRaca(Raca raca){
        return true;//daoRacas.delete(raca);
    }
}
