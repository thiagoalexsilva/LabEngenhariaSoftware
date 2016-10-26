package model.entity;

import java.util.Date;

/**
 * @author Erica
 */

public class Usuario extends Pessoa{
    
    private String cpf;
    private String sexo;
    private String dataNascimento;
    private String celular;

    public Usuario(String cpf, String sexo, String dataNascimento, String celular, int id, String email, String senha, String nome, String end, String bairro, String complemento, String cidade, String cep, String uf, String telefone, int tipo) {
        super(id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, tipo);
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
    }

    public Usuario() {
        super(0, null, null, null, null, null, null, null, null, null, null, 0);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    
}
