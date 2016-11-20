/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author Gustavo
 */
public class TipoServico {
    
    private int idTipoServico;
    private int duracao;
    private String nome;
    private String observacao;
    private double valor;
    private int idPetshop;

    public TipoServico(int idTipoServico, String nome, int duracao, double valor, String observacao, int idPetshop) {
        this.idTipoServico = idTipoServico;
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
        this.observacao = observacao;
        this.idPetshop = idPetshop;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(int idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdPetshop() {
        return idPetshop;
    }

    public void setIdPetshop(int idPetshop) {
        this.idPetshop = idPetshop;
    }
    
}
