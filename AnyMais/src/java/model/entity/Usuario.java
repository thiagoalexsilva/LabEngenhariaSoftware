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
public class Usuario {
    
    private int idPessoa;
    private int tipo;
    private Pessoa pessoa;
    private Conta conta;

    public Usuario() {
    }

    public Usuario(int idPessoa, int tipo, Pessoa pessoa, Conta conta) {
        this.idPessoa = idPessoa;
        this.tipo = tipo;
        this.pessoa = pessoa;
        this.conta = conta;
    }
    
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
}
