/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.TipoVacinaMedicamento;
import model.entity.TipoVacinaMedicamento;

/**
 *
 * @author Gustavo
 */
public class DAOTipoVacinaMedicamento {
    
    private Conexao conexao;
    private Connection con;
    
    // Possui apenas SELECT porque a aplicação não modifica a tabela
    
    public String SELECT_SQL = "SELECT * FROM TIPOVACINAMEDICAMENTO;";
    
    public DAOTipoVacinaMedicamento(){
        conexao = new Conexao();
    }
    
    public TipoVacinaMedicamento[] selectAll(){
        
        con = conexao.openConexao();
        ArrayList<TipoVacinaMedicamento> tipos = new ArrayList<TipoVacinaMedicamento>();
        TipoVacinaMedicamento[] arrayTipos;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tipos.add(getTipoVacinaMedicamento(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoVacinaMedicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayTipos = new TipoVacinaMedicamento[tipos.size()];
        
        for(int i=0; i<arrayTipos.length; i++){
            arrayTipos[i] = tipos.get(i);
        }
        
        conexao.closeConexao();
        return arrayTipos;
    }
    
    private TipoVacinaMedicamento getTipoVacinaMedicamento(ResultSet rs){
        try {
            int idTipoVacinaMedicamento = rs.getInt(1);
            String nomeTipoVacinaMedicamento = rs.getString(2);
            return new TipoVacinaMedicamento(idTipoVacinaMedicamento, nomeTipoVacinaMedicamento);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoVacinaMedicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
