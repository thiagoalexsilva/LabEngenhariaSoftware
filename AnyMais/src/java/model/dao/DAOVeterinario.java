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
import model.entity.Veterinario;

/**
 *
 * @author Erica
 */
public class DAOVeterinario {
    
    private Conexao conexao;
    private Connection con;
    public String INSERT_SQL = "INSERT INTO VETERINARIO VALUES (?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM VETERINARIO;";
    public String UPDATE_SQL = "UPDATE VETERINARIO SET NOME=?, OBSERVACAO=? WHERE IDVETERINARIO=?;";
    public String DELETE_SQL = "DELETE FROM VETERINARIO WHERE IDVETERINARIO=?;";

    public String SELECT_MAX_ID_SQL = "SELECT COALESCE(MAX(IDVETERINARIO), 0)+1 FROM VETERINARIO;";
    
    public DAOVeterinario(){
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
            Logger.getLogger(DAOVeterinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return 0;
    }
    
    public boolean insert(Veterinario veterinario){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setString(2, veterinario.getNome());
            stmt.setString(3, veterinario.getCrmv());
            stmt.setString(4, veterinario.getObservacao());
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOVeterinario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    public Veterinario[] selectAll(){
        
        ArrayList<Veterinario> veterinarios = new ArrayList<Veterinario>();
        Veterinario[] arrayVeterinarios;
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                veterinarios.add(getVeterinario(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOVeterinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayVeterinarios = new Veterinario[veterinarios.size()];
        
        for(int i=0; i<arrayVeterinarios.length; i++){
            arrayVeterinarios[i] = veterinarios.get(i);
        }
        
        conexao.closeConexao();
        return arrayVeterinarios;
    }
    
    public boolean update(Veterinario veterinario){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(UPDATE_SQL);
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getObservacao());
            stmt.setInt(3, veterinario.getIdVeterinario());
            
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOVeterinario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    public boolean delete(int idVeterinario){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idVeterinario);
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOVeterinario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    private Veterinario getVeterinario(ResultSet rs){
        try {
            int idVeterinario = rs.getInt(1);           
            String nome = rs.getString(3);
            String crmv = rs.getString(4);
            String observacao = rs.getString(5);
            return new Veterinario(idVeterinario, nome, crmv, observacao);
        } catch (SQLException ex) {
            Logger.getLogger(DAOVeterinario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
