package model;

/**
 * @author Erica
 */

public class Servico {
    
    private String nome;
    private int duracao;
    private Veterinario veterinario;
    private String dias;
    private String horario;
    private String observacao;
    
    public Servico(String nome, int duracao, Veterinario veterinario, String dias, String horario, String observacao){
        this.nome = nome;
        this.duracao = duracao;
        this.veterinario = veterinario;
        this.dias = dias;
        this.horario = horario;
        this.observacao = observacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
