/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.dao.DAOVacinasMedicamentos;
import model.entity.VacinasMedicamentos;
import model.entity.TipoVacinaMedicamento;

/**
 *
 * @author ana
 */
public class GerenciarVacinasMedicamentos {
    
    private static GerenciarVacinasMedicamentos instance;
    private DAOVacinasMedicamentos daoVacinasMedicamentos;

    private GerenciarVacinasMedicamentos(){
        daoVacinasMedicamentos = new DAOVacinasMedicamentos();
    }

    public static GerenciarVacinasMedicamentos getInstance(){
        if(instance == null)
            instance = new GerenciarVacinasMedicamentos();
        return instance;
    }
        
    public VacinasMedicamentos[] selecionaVacinasMedicamentos(){
        return daoVacinasMedicamentos.selectAll();
    }
    
    public VacinasMedicamentos[] selecionaVacinasMedicamentosComFiltro(String nome, boolean cachorro, boolean gato, boolean vacina, boolean medicamento){
        VacinasMedicamentos[] todasVacinasMedicamentos = daoVacinasMedicamentos.selectAll();
        
        ArrayList<VacinasMedicamentos> filtroVacinasMedicamentos = new ArrayList<VacinasMedicamentos>();
        
        for(VacinasMedicamentos vacinasMedicamentos : todasVacinasMedicamentos){
            if((vacina && vacinasMedicamentos.getTipoVacinaMedicamento().getNomeTipoVacinaMedicamento().toUpperCase().equals("VACINA")) //vacina == 1
                    ||
               (medicamento && vacinasMedicamentos.getTipoVacinaMedicamento().getNomeTipoVacinaMedicamento().toUpperCase().equals("MEDICAMENTO")) //medicamento == 2
                    ||
               (!vacina && !medicamento)){ 
                
                if((cachorro && vacinasMedicamentos.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO")) //cachorro == 1
                        ||
                   (gato && vacinasMedicamentos.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO") //gato == 2
                        ||
                   (!cachorro && !gato))){ 
                    
                        if(nome.isEmpty() || nome.toUpperCase().equals(vacinasMedicamentos.getNome().toUpperCase())){
                            filtroVacinasMedicamentos.add(vacinasMedicamentos);
                        }

                    }
                }
        }
        return filtroVacinasMedicamentos.toArray(new VacinasMedicamentos[filtroVacinasMedicamentos.size()]);
    }
    
    public boolean adicionarVacinaMedicamento(VacinasMedicamentos vacinaMedicamento){
        return daoVacinasMedicamentos.insert(vacinaMedicamento);
    }
    
    public boolean atualizarVacinaMedicamento(VacinasMedicamentos vacinaMedicamento){
        return daoVacinasMedicamentos.update(vacinaMedicamento);
    }
    
    public boolean excluirVacinaMedicamento(int idVacinaMedicamento){
        return daoVacinasMedicamentos.delete(idVacinaMedicamento);
    }
    
}
