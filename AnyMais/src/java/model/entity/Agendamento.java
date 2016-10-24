package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Agendamento {
    
    private Animal pet;
    private Usuario dono;
    private Servico tipo;
    private Date data;
    private String hora;
    private String observacao;
    
    public Agendamento(Animal pet, Usuario dono, Servico tipo, Date data, String hora, String observacao){
        this.pet = pet;
        this.dono = dono;
        this.tipo = tipo;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
    }

    public Animal getPet() {
        return pet;
    }

    public void setPet(Animal pet) {
        this.pet = pet;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Servico getTipo() {
        return tipo;
    }

    public void setTipo(Servico tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
