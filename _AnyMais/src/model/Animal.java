package model;

import java.util.Date;

/**
 * @author Erica
 */

public class Animal {
    
    private String nome;
    private Raca raca;
    private Date dataNascimento;
    private float peso;
    private float tamanho;
    private String cor;
    private String sexo;
    private String descricao;
    
    public Animal(String nome, Raca raca, Date dataNascimento, float peso, float tamanho, String cor, 
                    String sexo, String descricao){
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.tamanho = tamanho;
        this.cor = cor;
        this.sexo = sexo;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
