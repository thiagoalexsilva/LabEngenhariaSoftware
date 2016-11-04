package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Erica
 */

public class TipoAnimal {
    
    private int idTipoAnimal;
    private String nomeTipoAnimal;
    
    public TipoAnimal(int idTipoAnimal, String nomeTipoAnimal){
        this.idTipoAnimal = idTipoAnimal;
        this.nomeTipoAnimal = nomeTipoAnimal;
    }

    public int getIdTipoAnimal() {
        return idTipoAnimal;
    }

    public void setIdTipoAnimal(int idTipoAnimal) {
        this.idTipoAnimal = idTipoAnimal;
    }

    public String getNomeTipoAnimal() {
        return nomeTipoAnimal;
    }

    public void setNomeTipoAnimal(String nomeTipoAnimal) {
        this.nomeTipoAnimal = nomeTipoAnimal;
    }

}
