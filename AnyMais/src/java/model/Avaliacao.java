package model;

/**
 * @author Erica
 */

public class Avaliacao {
    
    private Usuario usuario;
    private PetShop petshop;
    private int nota;
    private String mensagem;

    public Avaliacao(Usuario usuario, PetShop petshop, int nota, String mensagem){
        this.usuario = usuario;
        this.petshop = petshop;
        this.nota = nota;
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PetShop getPetshop() {
        return petshop;
    }

    public void setPetshop(PetShop petshop) {
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
