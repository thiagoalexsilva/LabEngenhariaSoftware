package model.entity;

/**
 * @author Erica
 */

public class VacinaMedicamento {
    
    private String tipo;
    private String tipoAnimal;
    private String nome;
    private String observacao;
    private Integer periodicidade;
    
    public VacinaMedicamento(String tipo, String tipoAnimal, String nome, Integer periodicidade, String observacao){
        this.tipo = tipo;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.observacao = observacao;
        this.periodicidade = periodicidade;
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
