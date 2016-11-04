/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import java.util.ArrayList;
import model.dao.DAORaca;
import model.entity.Raca;

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
    
    public Raca[] selecionaRacasComFiltro(String nome, boolean cachorro, boolean gato, boolean pequeno, boolean medio, boolean grande){
        Raca[] todasRacas = daoRaca.selectAll();
        
        ArrayList<Raca> filtroRacas = new ArrayList<Raca>();
        
        for(Raca raca : todasRacas){
            if((cachorro && raca.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO"))
                    ||
               (gato && raca.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO"))
                    ||
               (!cachorro && !gato)){
                
                if((pequeno && raca.getPorte().toUpperCase().equals("PEQUENO"))
                        ||
                   (medio && raca.getPorte().toUpperCase().equals("MEDIO"))
                        ||
                   (grande && raca.getPorte().toUpperCase().equals("GRANDE"))
                        ||
                   (!grande && !medio && !pequeno)){
                    if(nome.isEmpty() || nome.toUpperCase().equals(raca.getNomeRaca().toUpperCase())){
                        filtroRacas.add(raca);
                    }
                }
            } 
            
            //Descomentar linhas acima
            //Eliminar linhas abaixo
            /*
            if(cachorro && raca.getId()%2 == 0 || gato && raca.getId()%2 == 1){
                if(nome.isEmpty() || nome.toUpperCase().equals(raca.getNomeRaca().toUpperCase())){
                    filtroRacas.add(raca);
                }
            }*/
            
        }
        
        return filtroRacas.toArray(new Raca[filtroRacas.size()]);
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
