/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.dao.DAOAnimal;
import model.entity.Animal;

/**
 *
 * @author Erica
 */
public class GerenciarAnimais {

    private static GerenciarAnimais instance;
    private DAOAnimal daoAnimais;

    private GerenciarAnimais(){
        daoAnimais = new DAOAnimal();
    }

    public static GerenciarAnimais getInstance(){
        if(instance == null)
            instance = new GerenciarAnimais();
        return instance;
    }
        
    public Animal[] selecionaAnimais(){
        return daoAnimais.selectAll();
    }
    
    public boolean adicionarAnimais(Animal animal){
        return daoAnimais.insert(animal);
    }
    
    public boolean atualizarAnimais(Animal animal){
        return daoAnimais.update(animal);
    }
    
    public boolean excluirAnimais(int idanimal){
        return daoAnimais.delete(idanimal);
    }

    public Animal[] selecionaAnimais(int idPessoa){
        ArrayList<Animal> animaisPessoa = new ArrayList<Animal>();
        
        Animal[] animais = selecionaAnimais();
        
        for(Animal a : animais){
            if(a.getIdDono() == idPessoa){
                animaisPessoa.add(a);
            }
        }
        
        return animaisPessoa.toArray(new Animal[animaisPessoa.size()]);
    }
}
