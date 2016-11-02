package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Erica
 */

public class Raca {
    
    private int idRaca;
    private int tipoAnimal;
    private String nomeRaca;
    private String porte;
    private String observacao;
    
    public Raca(int idRaca, int tipoAnimal, String nomeRaca, String porte, String observacao){
        this.idRaca = idRaca;
        this.tipoAnimal = tipoAnimal;
        this.nomeRaca = nomeRaca;
        this.porte = porte;
        this.observacao = observacao;
    }

    public int getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
    }
    
    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
