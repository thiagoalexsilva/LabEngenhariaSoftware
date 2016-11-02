package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Animal {
    
    private int idAnimal;
    private int tipoAnimal;
    private String nome;
    private Raca idRaca;
    private Date dataNascimento;
    private float peso;
    private float tamanho;
    private String cor;
    private String sexo;
    private String descricao;
    private String imagem;

    public Animal(int idAnimal, int tipoAnimal, String nome, Raca idRaca, Date dataNascimento, float peso, float tamanho,
            String cor, String sexo, String descricao, String imagem) {
        this.idAnimal = idAnimal;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.idRaca = idRaca;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.tamanho = tamanho;
        this.cor = cor;
        this.sexo = sexo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Raca getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Raca idRaca) {
        this.idRaca = idRaca;
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
