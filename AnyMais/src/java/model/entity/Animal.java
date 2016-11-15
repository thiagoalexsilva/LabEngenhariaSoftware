package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Animal {
    
    private int idAnimal;
    private TipoAnimal tipoAnimal;
    private String nome;
    private Raca raca;
    private Date dataNascimento;
    private double peso;
    private double tamanho;
    private String cor;
    private String sexo;
    private String descricao;
    private String imagem;
    private int idDono;
    
    public Animal(int idAnimal, TipoAnimal tipoAnimal, String nome, Raca raca, Date dataNascimento, double peso, double tamanho,
            String cor, String sexo, String descricao, String imagem, int idDono) {
        this.idAnimal = idAnimal;
        this.tipoAnimal = tipoAnimal;
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.tamanho = tamanho;
        this.cor = cor;
        this.sexo = sexo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.idDono = idDono;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
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

    public int getIdDono() {
        return idDono;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }
    
    
}
