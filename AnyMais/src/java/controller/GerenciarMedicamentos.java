/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import java.util.ArrayList;
import model.dao.DAOMedicamentos;
import model.entity.VacinaMedicamento;

/**
 *
 * @author ana
 */
public class GerenciarMedicamentos {
    
    private static GerenciarMedicamentos instance;
    private DAOMedicamentos daoMedicamento;

    private GerenciarMedicamentos(){
        daoMedicamento = new DAOMedicamentos();
    }

    public static GerenciarMedicamentos getInstance(){
        if(instance == null)
            instance = new GerenciarMedicamentos();
        return instance;
    }
        
    public VacinaMedicamento[] selecionaMedicamentos(){
        return daoMedicamento.selectAll();
    }
    
    public VacinaMedicamento[] selecionaMedicamentosComFiltro(String nome, boolean cachorro, boolean gato){
        VacinaMedicamento[] todosMedicamentos = daoMedicamento.selectAll();
        
        ArrayList<VacinaMedicamento> filtroMedicamentos = new ArrayList<VacinaMedicamento>();
        
        for(VacinaMedicamento medicamentos : todosMedicamentos){
            /*if((medicamento && medicamentos.getTipo().toUpperCase().equals("MEDICAMENTO"))
                    ||
               (vacina && medicamentos.getTipoAnimal().toUpperCase().equals("VACINA"))){*/
                
                if((cachorro && medicamentos.getTipoAnimal().toUpperCase().equals("CACHORRO"))
                        ||
                   (gato && medicamentos.getTipoAnimal().toUpperCase().equals("GATO"))){
                    /*if((hora && medicamentos.getPeriodicidade() >= 0)
                            ||
                       (dia && medicamentos.getPeriodicidade() >= 0)
                            ||
                       (semana && medicamentos.getPeriodicidade() >= 0)
                             ||
                       (mes && medicamentos.getPeriodicidade() >= 0)
                             ||
                       (ano && medicamentos.getPeriodicidade() >= 0)){      */
                   
                        if(nome.isEmpty() || nome.toUpperCase().equals(medicamentos.getNome().toUpperCase())){
                            filtroMedicamentos.add(medicamentos);
                        }
                    
                    
                    }
                }
          //  }
            //Descomentar linhas acima
            //Eliminar linhas abaixo
            //trocar ordens acima rs
            /*
            if(cachorro && raca.getId()%2 == 0 || gato && raca.getId()%2 == 1){
                if(nome.isEmpty() || nome.toUpperCase().equals(raca.getNomeRaca().toUpperCase())){
                    filtroRacas.add(raca);
                }
            }*/
            
        //}
        
        return filtroMedicamentos.toArray(new VacinaMedicamento[filtroMedicamentos.size()]);
    }
    
    public boolean adicionarMedicamento(VacinaMedicamento medicamento){
        return daoMedicamento.insert(medicamento);
    }
    
    public boolean atualizarMedicamento(VacinaMedicamento medicamento){
        return daoMedicamento.update(medicamento);
    }
    
    public boolean excluirMedicamento(int idmed_vac){
        return daoMedicamento.delete(idmed_vac);
    }
    
}
