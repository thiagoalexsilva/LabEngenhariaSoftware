package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Pessoa{
    
    private int id;
    private String email;
    private String senha;
    private String nome;
    private String end;
    private String bairro;
    private String complemento;
    private String cidade;
    private String cep;
    private String uf;
    private String telefone;
    private String sexo;
    private int tipo;

    public Pessoa(int id, String email, String senha, String nome, String end, String bairro, String complemento, String cidade, String cep, String uf, String telefone, String sexo, int tipo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.end = end;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.cep = cep;
        this.uf = uf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.tipo = tipo;
    }

    public Pessoa() {
        this.id = 0;
        this.email = null;
        this.senha = null;
        this.nome = null;
        this.end = null;
        this.bairro = null;
        this.complemento = null;
        this.cidade = null;
        this.cep = null;
        this.uf = null;
        this.telefone = null;
        this.sexo = null;
        this.tipo = 0;
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
