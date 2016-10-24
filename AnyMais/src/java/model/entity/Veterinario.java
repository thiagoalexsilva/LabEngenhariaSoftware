package model.entity;

/**
 * @author Erica
 */

public class Veterinario {
    
    private String nome;
    private String crmv;
    
    public Veterinario(String nome, String crmv){
        this.nome = nome;
        this.crmv = crmv;
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
}
