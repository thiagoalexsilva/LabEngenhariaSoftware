package model.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Erica
 */

public class VacinaMedicamento {
    
    @Id
    @Column(name="idMED_VAC", nullable=false, unique=true)
    private int id;
    
    @Column(name="tipoMED_VAC", nullable=false, unique=true)
    private String tipo;
    
    @Column(name="tipoAnimalMED_VAC", nullable=false, unique=true)
    private String tipoAnimal;
    
    @Column(name="nomeMED_VAC", nullable=false, unique=true)
    private String nome;
    
    @Column(name="descMED_VAC", nullable=false, unique=true)
    private String observacao;
    
    //@Column(name="descMED_VAC", nullable=false, unique=true)
    private Integer periodicidade;
    
    public VacinaMedicamento(int id, String tipoAnimal, String nome, Integer periodicidade, String observacao){
        this.id = id;
        //this.tipo = tipo;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.observacao = observacao;
        this.periodicidade = periodicidade;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public Integer getPeriodicidade(){
        return periodicidade;
    }
    
    public void setPeriodicidade(){
        this.periodicidade = periodicidade;
    }
}
