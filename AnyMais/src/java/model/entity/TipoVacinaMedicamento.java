package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Erica
 */

public class TipoVacinaMedicamento {
    
    private int idTipoVacinaMedicamento;
    private String nomeTipoVacinaMedicamento;
    
    public TipoVacinaMedicamento(int idTipoVacinaMedicamento, String nomeTipoVacinaMedicamento){
        this.idTipoVacinaMedicamento = idTipoVacinaMedicamento;
        this.nomeTipoVacinaMedicamento = nomeTipoVacinaMedicamento;
    }

    public int getIdTipoVacinaMedicamento() {
        return idTipoVacinaMedicamento;
    }

    public void setIdTipoVacinaMedicamento(int idTipoVacinaMedicamento) {
        this.idTipoVacinaMedicamento = idTipoVacinaMedicamento;
    }

    public String getNomeTipoVacinaMedicamento() {
        return nomeTipoVacinaMedicamento;
    }

    public void setNomeTipoVacinaMedicamento(String nomeTipoVacinaMedicamento) {
        this.nomeTipoVacinaMedicamento = nomeTipoVacinaMedicamento;
    }

}
