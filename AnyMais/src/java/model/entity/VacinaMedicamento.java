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
    private int tipo;
    
    @Column(name="tipoAnimalMED_VAC", nullable=false, unique=true)
    private int tipoAnimal;
    
    @Column(name="nomeMED_VAC", nullable=false, unique=true)
    private String nome;
    
    @Column(name="descMED_VAC", nullable=false, unique=true)
    private String observacao;
    
    //@Column(name="descMED_VAC", nullable=false, unique=true)
    private int periodicidade;
    private String tempo;

    public VacinaMedicamento(int id, int tipo, int tipoAnimal, String nome, String observacao, int periodicidade, String tempo) {
        this.id = id;
        this.tipo = tipo;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.observacao = observacao;
        this.periodicidade = periodicidade;
        this.tempo = tempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
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

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
    
}
