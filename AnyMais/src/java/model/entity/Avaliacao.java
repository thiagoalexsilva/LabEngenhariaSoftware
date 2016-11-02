package model.entity;

/**
 * @author Erica
 */

public class Avaliacao {
    
    private Pessoa usuario;
    private Pessoa petshop;
    private int nota;
    private String mensagem;

    public Avaliacao(Pessoa usuario, Pessoa petshop, int nota, String mensagem){
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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
