package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Mensagem {
    
    private int idMensagem;
    private Usuario destinatario;
    private Usuario remetente;
    private String assunto;
    private String mensagem;
    private Date data;
    
    public Mensagem(int idMensagem, Usuario destinatario, Usuario remetente, String assunto, String mensagem, Date data){
        this.idMensagem = idMensagem;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.data = data;
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }
    
    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
