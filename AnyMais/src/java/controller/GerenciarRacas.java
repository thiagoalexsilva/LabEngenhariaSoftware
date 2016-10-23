/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import framework.DAORaca;
import model.Raca;

/**
 *
 * @author Gustavo
 */
public class GerenciarRacas {
    
    private static GerenciarRacas instance;
    private DAORaca daoRaca;

    private GerenciarRacas(){
        daoRaca = new DAORaca();
    }

    public static GerenciarRacas getInstance(){
        if(instance == null)
            instance = new GerenciarRacas();
        return instance;
    }
        
    public Raca[] selecionaRacas(){
        return daoRaca.selectAll();
    }
    
    public Raca[] selecionaRacasComFiltro(boolean cachorro, boolean gato, boolean pequeno, boolean medio, boolean grande){
        return daoRaca.selectAllFiltered(cachorro, gato, pequeno, medio, grande);
    }
    
    public boolean adicionarRaca(Raca raca){
        return daoRaca.insert(raca);
    }
    
    public boolean atualizarRaca(Raca raca){
        return daoRaca.update(raca);
    }
    
    public boolean excluirRaca(int idraca){
        return daoRaca.delete(idraca);
    }
}
