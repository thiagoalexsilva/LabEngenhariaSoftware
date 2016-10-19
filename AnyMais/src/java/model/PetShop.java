package model;

/**
 * @author Erica
 */

public class PetShop extends Pessoa{
    
    private String cnpj;

    public PetShop(String cnpj, int id, String email, String senha, String nome, String end, String bairro, String complemento, String cidade, String cep, String uf, String telefone, String sexo, int tipo) {
        super(id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, sexo, tipo);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
