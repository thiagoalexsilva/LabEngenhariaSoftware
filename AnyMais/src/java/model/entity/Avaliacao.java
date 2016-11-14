package model.entity;

/**
 * @author Erica
 */

public class Avaliacao {
    
    private Pessoa usuario;
    private Pessoa petshop;
    private double nota;
    private String mensagem;

    public Avaliacao(Pessoa usuario, Pessoa petshop, double nota, String mensagem){
        this.usuario = usuario;
        this.petshop = petshop;
        this.nota = nota;
        this.mensagem = mensagem;
    }

    public Pessoa getPessoa() {
        return usuario;
    }

    public void setPessoa(Pessoa usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPetshop() {
        return petshop;
    }

    public void setPetshop(Pessoa petshop) {
        this.petshop = petshop;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
