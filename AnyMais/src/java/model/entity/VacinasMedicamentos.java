package model.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Erica
 */

public class VacinasMedicamentos {

    private int idVacinasMedicamentos;
    private int tipo;
    private int tipoAnimal;
    private String nome;
    private int periodicidade;
    private String tempo;
    private String observacao;

    public VacinasMedicamentos(int idVacinasMedicamentos, int tipo, int tipoAnimal, String nome, int periodicidade, 
            String tempo, String observacao) {
        this.idVacinasMedicamentos = idVacinasMedicamentos;
        this.tipo = tipo;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.periodicidade = periodicidade;
        this.tempo = tempo;
        this.observacao = observacao;
    }

    public int getIdVacinasMedicamentos() {
        return idVacinasMedicamentos;
    }

    public void setIdVacinasMedicamentos(int idVacinasMedicamentos) {
        this.idVacinasMedicamentos = idVacinasMedicamentos;
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
