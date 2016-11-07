/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import framework.DAO;
import java.util.ArrayList;
import model.dao.DAOVeterinario;
import model.entity.Veterinario;

/**
 *
 * @author Erica
 */
public class GerenciarVeterinario {
    
    private static GerenciarVeterinario instance;
    private DAOVeterinario daoVeterinario;
    
    private GerenciarVeterinario(){
        daoVeterinario = new DAOVeterinario();
    }
    
    public static GerenciarVeterinario getInstance(){
        if(instance == null)
            instance = new GerenciarVeterinario();
        return instance;
    }
    
    public Veterinario[] selecionaVeterinario(){
        return daoVeterinario.selectAll();
    }
    
    public boolean adicionarVeterinario(Veterinario veterinario){
        return daoVeterinario.insert(veterinario);
    }
    
    public boolean atualizarVeterinario(Veterinario veterinario){
        return daoVeterinario.update(veterinario);
    }
    
    public boolean excluirVeterinario(int idVeterinario){
        return daoVeterinario.delete(idVeterinario);
    }
}
