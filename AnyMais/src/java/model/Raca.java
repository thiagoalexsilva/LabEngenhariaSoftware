package model;

/**
 * @author Erica
 */

public class Raca {
    
    private String tipoAnimal;
    private String nomeRaca;
    private String porte;
    private String observacao;
    
    public Raca(String tipoAnimal, String nomeRaca, String porte, String observacao){
        this.tipoAnimal = tipoAnimal;
        this.nomeRaca = nomeRaca;
        this.porte = porte;
        this.observacao = observacao;
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
