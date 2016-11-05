/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import java.util.ArrayList;
import model.dao.DAOTipoServico;
import model.entity.TipoServico;

/**
 *
 * @author Gustavo
 */
public class GerenciarTiposServico {
    
    private static GerenciarTiposServico instance;
    private DAOTipoServico daoTipoServico;

    private GerenciarTiposServico(){
        daoTipoServico = new DAOTipoServico();
    }

    public static GerenciarTiposServico getInstance(){
        if(instance == null)
            instance = new GerenciarTiposServico();
        return instance;
    }
        
    public TipoServico[] selecionaTiposServico(){
        return daoTipoServico.selectAll();
    }
    
    public TipoServico[] selecionaTiposServicoComFiltro(String nome){
        TipoServico[] todasTiposServico = daoTipoServico.selectAll();
        
        ArrayList<TipoServico> filtroTiposServico = new ArrayList<TipoServico>();
        
        for(TipoServico tipoServico : todasTiposServico){
            
            if(nome.isEmpty() || tipoServico.getNome().toUpperCase().contains(nome.toUpperCase())){
                filtroTiposServico.add(tipoServico);
            }
           
        }
        
        return filtroTiposServico.toArray(new TipoServico[filtroTiposServico.size()]);
    }
    
    public boolean adicionarTipoServico(TipoServico tipoServico){
        return daoTipoServico.insert(tipoServico);
    }
    
    public boolean atualizarTipoServico(TipoServico tipoServico){
        return daoTipoServico.update(tipoServico);
    }
    
    public boolean excluirTipoServico(int idtipoServico){
        return daoTipoServico.delete(idtipoServico);
    }
}
