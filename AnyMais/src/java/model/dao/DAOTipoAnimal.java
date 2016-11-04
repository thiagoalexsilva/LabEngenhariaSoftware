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
import model.entity.TipoAnimal;
import model.entity.TipoAnimal;

/**
 *
 * @author Gustavo
 */
public class DAOTipoAnimal {
    
    private Conexao conexao;
    private Connection con;
    
    // Possui apenas SELECT porque a aplicação não modifica a tabela
    
    public String SELECT_SQL = "SELECT * FROM TIPOANIMAL;";
           
    public DAOTipoAnimal(){
        conexao = new Conexao();
    }
    
    public TipoAnimal[] selectAll(){
        
        con = conexao.openConexao();
        ArrayList<TipoAnimal> tipos = new ArrayList<TipoAnimal>();
        TipoAnimal[] arrayTipos;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tipos.add(getTipoAnimal(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayTipos = new TipoAnimal[tipos.size()];
        
        for(int i=0; i<arrayTipos.length; i++){
            arrayTipos[i] = tipos.get(i);
        }
        
        conexao.closeConexao();
        return arrayTipos;
    }
    
    private TipoAnimal getTipoAnimal(ResultSet rs){
        try {
            int idTipoAnimal = rs.getInt(1);
            String nomeTipoAnimal = rs.getString(2);
            return new TipoAnimal(idTipoAnimal, nomeTipoAnimal);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
