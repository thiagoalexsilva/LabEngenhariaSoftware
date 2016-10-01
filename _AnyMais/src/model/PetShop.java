package model;

/**
 * @author Erica
 */

public class PetShop extends Pessoa{
    
    private String cnpj;
    private String telefone2;
    
    public PetShop(String email, String senha, String nome, String endereco, String bairro, String complemento, 
                    String cidade, String cep, String uf, String telefone, String cnpj, String telefone2) {
        super(email, senha, nome, endereco, bairro, complemento, cidade, cep, uf, telefone);
        this.cnpj = cnpj;
        this.telefone2 = telefone2;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
}
