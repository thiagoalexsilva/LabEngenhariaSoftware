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
import model.entity.Raca;

/**
 *
 * @author Gustavo
 */
public class DAORaca {
    
    private Conexao conexao;
    private Connection con;
    public String INSERT_SQL = "INSERT INTO RACA VALUES (?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM RACA;";
    public String UPDATE_SQL = "UPDATE RACA SET TIPOANIMAL=?, NOMERACA=?, PORTE=?, OBSERVACAO=? WHERE IDRACA=?;";
    public String DELETE_SQL = "DELETE FROM RACA WHERE IDRACA=?;";

    public String SELECT_MAX_ID_SQL = "SELECT COALESCE(MAX(IDRACA), 0)+1 FROM RACA;";
       
    public DAORaca(){
        conexao = new Conexao();
    }
    
    private int nextId(){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_MAX_ID_SQL);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            return id;
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return 0;
    }
    
    public boolean insert(Raca raca){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setInt(2, raca.getTipoAnimal());
            stmt.setString(3, raca.getNomeRaca());
            stmt.setString(4, raca.getPorte());
            stmt.setString(5, raca.getObservacao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public Raca[] selectAll(){
        
        con = conexao.openConexao();
        ArrayList<Raca> racas = new ArrayList<Raca>();
        Raca[] arrayRacas;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                racas.add(getRaca(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayRacas = new Raca[racas.size()];
        
        for(int i=0; i<arrayRacas.length; i++){
            arrayRacas[i] = racas.get(i);
        }
        
        conexao.closeConexao();
        return arrayRacas;
    }
    
    public boolean update(Raca raca){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(UPDATE_SQL);
            stmt.setInt(1, raca.getTipoAnimal());
            stmt.setString(2, raca.getNomeRaca());
            stmt.setString(3, raca.getPorte());
            stmt.setString(4, raca.getObservacao());
            stmt.setInt(5, raca.getIdRaca());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public boolean delete(int idRaca){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idRaca);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    private Raca getRaca(ResultSet rs){
        try {
            int id = rs.getInt(1);
            int tipoAnimal = rs.getInt(2);
            String nomeRaca = rs.getString(3);
            String porte = rs.getString(4);
            String observacao = rs.getString(5);
            return new Raca(id, tipoAnimal, nomeRaca, porte, observacao);
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
