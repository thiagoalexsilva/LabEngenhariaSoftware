package model.entity;

/**
 * @author Erica
 */

public class VacinaMedicamento {
    
    private Enum tipo;
    private Enum tipoAnimal;
    private String nome;
    private String observacao;
    
    public VacinaMedicamento(Enum tipo, Enum tipoAnimal, String nome, String observacao){
        this.tipo = tipo;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.observacao = observacao;
    }

    public Enum getTipo() {
        return tipo;
    }

    public void setTipo(Enum tipo) {
        this.tipo = tipo;
    }

    public Enum getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(Enum tipoAnimal) {
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
}
