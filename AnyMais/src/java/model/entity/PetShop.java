package model.entity;

/**
 * @author Erica
 */

public class PetShop extends Pessoa{
    
    private int p_id;
    private String cnpj;
    private String telefone2;

    public PetShop(int id, String cnpj, int p_id, String email, String senha, String nome, String end, String bairro, String complemento, String cidade, String cep, String uf, String telefone, String telefone2, int tipo) {
        super(id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, tipo);
        this.cnpj = cnpj;
        this.telefone2 = telefone2;
        this.p_id = p_id;
    }

    public PetShop() {
        super(0, null, null, null, null, null, null, null, null, null, null, 0);
    }

    public int getP_Id() {
        return p_id;
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
