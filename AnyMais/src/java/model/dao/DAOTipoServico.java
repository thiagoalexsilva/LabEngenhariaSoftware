/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import controller.GerenciarTiposAnimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.TipoServico;
import model.entity.TipoAnimal;

/**
 *
 * @author Gustavo
 */
public class DAOTipoServico {
    
    private Conexao conexao;
    private Connection con;
    public String INSERT_SQL = "INSERT INTO TIPOSERVICO VALUES (?, ?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM TIPOSERVICO WHERE IDPETSHOP = ?;";
    public String UPDATE_SQL = "UPDATE TIPOSERVICO SET NOMETIPOSERVICO=?, DURACAO=?, VALOR=?, OBSERVACAO=? WHERE IDTIPOSERVICO=?;";
    public String DELETE_SQL = "DELETE FROM TIPOSERVICO WHERE IDTIPOSERVICO=?;";

    public String SELECT_MAX_ID_SQL = "SELECT COALESCE(MAX(IDTIPOSERVICO), 0)+1 FROM TIPOSERVICO;";
       
    public DAOTipoServico(){
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
            Logger.getLogger(DAOTipoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return 0;
    }
    
    public boolean insert(TipoServico tipoServico){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setString(2, tipoServico.getNome());
            stmt.setInt(3, tipoServico.getDuracao());
            stmt.setDouble(4, tipoServico.getValor());
            stmt.setString(5, tipoServico.getObservacao());
            stmt.setInt(6, tipoServico.getIdPetshop());
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoServico.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    public TipoServico[] selectAll(int idPetshop){
        
        ArrayList<TipoServico> tipoServicos = new ArrayList<TipoServico>();
        TipoServico[] arrayTipoServicos;
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(SELECT_SQL);
            stmt.setInt(1, idPetshop);
                        
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                tipoServicos.add(getTipoServico(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayTipoServicos = new TipoServico[tipoServicos.size()];
        
        for(int i=0; i<arrayTipoServicos.length; i++){
            arrayTipoServicos[i] = tipoServicos.get(i);
        }
        
        conexao.closeConexao();
        return arrayTipoServicos;
    }
    
    public boolean update(TipoServico tipoServico){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(UPDATE_SQL);
            stmt.setString(1, tipoServico.getNome());
            stmt.setInt(2, tipoServico.getDuracao());
            stmt.setDouble(3, tipoServico.getValor());
            stmt.setString(4, tipoServico.getObservacao());
            stmt.setInt(5, tipoServico.getIdTipoServico());            
            
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoServico.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    public boolean delete(int idTipoServico){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idTipoServico);
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoServico.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    private TipoServico getTipoServico(ResultSet rs){
        try {
            int id = rs.getInt(1);
            String nome = rs.getString(2);
            int duracao = rs.getInt(3);
            double valor = rs.getDouble(4);
            String observacao = rs.getString(5);
            int idPetshop = rs.getInt(6);
            return new TipoServico(id, nome, duracao, valor, observacao, idPetshop);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoServico.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
