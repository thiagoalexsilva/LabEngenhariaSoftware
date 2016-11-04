/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.dao.DAOVacinasMedicamentos;
import model.entity.VacinasMedicamentos;

/**
 *
 * @author ana
 */
public class GerenciarVacinasMedicamentos {
    
    private static GerenciarVacinasMedicamentos instance;
    private DAOVacinasMedicamentos daoMedicamento;

    private GerenciarVacinasMedicamentos(){
        daoMedicamento = new DAOVacinasMedicamentos();
    }

    public static GerenciarVacinasMedicamentos getInstance(){
        if(instance == null)
            instance = new GerenciarVacinasMedicamentos();
        return instance;
    }
        
    public VacinasMedicamentos[] selecionaMedicamentos(){
        return daoMedicamento.selectAll();
    }
    
    public VacinasMedicamentos[] selecionaMedicamentosComFiltro(String nome, boolean cachorro, boolean gato){
        VacinasMedicamentos[] todosMedicamentos = daoMedicamento.selectAll();
        
        ArrayList<VacinasMedicamentos> filtroMedicamentos = new ArrayList<>();
        
        for(VacinasMedicamentos medicamentos : todosMedicamentos){
            /*if((medicamento && medicamentos.getTipo().toUpperCase().equals("MEDICAMENTO"))
                    ||
               (vacina && medicamentos.getTipoAnimal().toUpperCase().equals("VACINA"))){*/
                
                if((cachorro && medicamentos.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("CACHORRO")) //cachorro == 1
                        ||
                   (gato && medicamentos.getTipoAnimal().getNomeTipoAnimal().toUpperCase().equals("GATO"))){ //gato == 2
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
        
        return filtroMedicamentos.toArray(new VacinasMedicamentos[filtroMedicamentos.size()]);
    }
    
    public boolean adicionarMedicamento(VacinasMedicamentos medicamento){
        return daoMedicamento.insert(medicamento);
    }
    
    public boolean atualizarMedicamento(VacinasMedicamentos medicamento){
        return daoMedicamento.update(medicamento);
    }
    
    public boolean excluirMedicamento(int idmed_vac){
        return daoMedicamento.delete(idmed_vac);
    }
    
}
