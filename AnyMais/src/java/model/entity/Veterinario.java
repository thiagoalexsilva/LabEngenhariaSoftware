package model.entity;

/**
 * @author Erica
 */

public class Veterinario {
    
    private int idVeterinario;
    private String nome;
    private String crmv;
    private String observacao;
    
    public Veterinario(int idVeterinario, String nome, String crmv, String observacao){
        this.idVeterinario = idVeterinario;
        this.nome = nome;
        this.crmv = crmv;
        this.observacao = observacao;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }
    
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
