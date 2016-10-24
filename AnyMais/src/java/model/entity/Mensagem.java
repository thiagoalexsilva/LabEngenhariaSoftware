package model.entity;

/**
 * @author Erica
 */

public class Mensagem {
    
    private Pessoa destinatario;
    private Pessoa remetente;
    private String assunto;
    private String mensagem;
    
    public Mensagem(Pessoa destinatario, Pessoa remetente, String assunto, String mensagem){
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public Pessoa getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Pessoa destinatario) {
        this.destinatario = destinatario;
    }

    public Pessoa getRemetente() {
        return remetente;
    }

    public void setRemetente(Pessoa remetente) {
        this.remetente = remetente;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
