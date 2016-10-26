package model.entity;

/**
 * @author Erica
 */

public class PetShop extends Pessoa{
    
    private String cnpj;
    private String telefone2;

    public PetShop(String cnpj, int id, String email, String senha, String nome, String end, String bairro, String complemento, String cidade, String cep, String uf, String telefone, String telefone2, int tipo) {
        super(id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, tipo);
        this.cnpj = cnpj;
        this.telefone2 = telefone2;
    }

    public PetShop() {
        super(0, null, null, null, null, null, null, null, null, null, null, 0);
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
