package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Animal {
    
    private int id;
    private int tipo;
    private String nome;
    private Raca raca;
    private Date dataNascimento;
    private float peso;
    private float tamanho;
    private String cor;
    private String sexo;
    private String descricao;
    private String imagem;

    public Animal(int id, int tipo, String nome, Raca raca, Date dataNascimento, float peso, float tamanho, String cor, String sexo, String descricao, String imagem) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.tamanho = tamanho;
        this.cor = cor;
        this.sexo = sexo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    
}
