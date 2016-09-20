package model;

import java.util.Date;

/**
 * @author Erica
 */

public class Usuario extends Pessoa{
    
    private String cpf;
    private String sexo;
    private Date dataNascimento;
    private String celular;
    
    public Usuario(String email, String senha, String nome, String endereco, String bairro, String complemento, 
                    String cidade, String cep, String uf, String telefone, String cpf, String sexo, Date dataNascimento, 
                    String celular) {
        super(email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone);
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
