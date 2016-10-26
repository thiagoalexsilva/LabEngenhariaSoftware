package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Erica
 */

@Entity(name = "raca_animal")
public class Raca {
    
    @Id
    @Column(name="idraca", nullable=false, unique=true)
    private int id;
    
    @Column(name="tipoanimalraca", nullable=false, unique=true)
    private String tipoAnimal;
    
    @Column(name="nomeraca", nullable=false, unique=true)
    private String nomeRaca;
    
    @Column(name="porteraca", nullable=false, unique=true)
    private String porte;
    
    @Column(name="observacaoraca", nullable=false, unique=true)
    private String observacao;
    
    public Raca(int id, String tipoAnimal, String nomeRaca, String porte, String observacao){
        this.id = id;
        this.tipoAnimal = tipoAnimal;
        this.nomeRaca = nomeRaca;
        this.porte = porte;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
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
