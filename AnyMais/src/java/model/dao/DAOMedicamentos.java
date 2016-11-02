/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.VacinaMedicamento;

/**
 *
 * @author ana
 */
public class DAOMedicamentos {
    
    private Conexao conexao;
    private Connection con;
    private String INSERT_SQL = "INSERT INTO VACINASMEDICAMENTOS VALUES (?, ?, ?, ?, ?, ?, ?);";
    private String SELECT_SQL = "SELECT * FROM VACINASMEDICAMENTOS";
    private String UPDATE_SQL = "UPDATE VACINASMEDICAMENTOS SET TIPO=?, TIPOANIMAL=?, NOME=?, PERIODICIDADE=?, TEMPO=?, OBSERVACAO=? WHERE IDVACINASMEDICAMENTOS=?;";
    private String DELETE_SQL = "DELETE FROM VACINASMEDICAMENTOS WHERE IDVACINASMEDICAMENTOS=?;";
    private String SELECT_NEW_ID_SQL = "SELECT COALESCE(MAX(IDRACA), 0)+1 FROM VACINASMEDICAMENTOS;";    
        
    public DAOMedicamentos(){
        conexao = new Conexao();
    }
    
    private int nextId(){
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_NEW_ID_SQL);
            
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
    
    public boolean insert(VacinaMedicamento medicamento){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setInt(2, medicamento.getTipo());
            stmt.setInt(3, medicamento.getTipoAnimal());
            stmt.setString(4, medicamento.getNome());
            stmt.setInt(5, medicamento.getPeriodicidade());
            stmt.setString(6, medicamento.getTempo());
            stmt.setString(7, medicamento.getObservacao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public VacinaMedicamento[] selectAll(){
        con = conexao.openConexao();
        ArrayList<VacinaMedicamento> medicamentos = new ArrayList<VacinaMedicamento>();
        VacinaMedicamento[] arrayMedicamentos;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                medicamentos.add(getMedicamento(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayMedicamentos = new VacinaMedicamento[medicamentos.size()];
        
        for(int i=0; i<arrayMedicamentos.length; i++){
            arrayMedicamentos[i] = medicamentos.get(i);
        }
        
        conexao.closeConexao();
        return arrayMedicamentos;
    }
   
    public boolean update(VacinaMedicamento medicamento){
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(UPDATE_SQL);
            stmt.setInt(1, medicamento.getTipo());
            stmt.setInt(2, medicamento.getTipoAnimal());
            stmt.setString(3, medicamento.getNome());
            stmt.setInt(4, medicamento.getPeriodicidade());
            stmt.setString(5, medicamento.getTempo());
            stmt.setString(6, medicamento.getObservacao());
            stmt.setInt(7, medicamento.getId());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public boolean delete(int idmed_vac){
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idmed_vac);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    private VacinaMedicamento getMedicamento(ResultSet rs){
        try {
            int id = rs.getInt(1);
            int tipo = rs.getInt(2);
            int tipoAnimal = rs.getInt(3);
            String nomeMedicamento = rs.getString(4);
            int periodicidade = rs.getInt(5);
            String tempo = rs.getString(6);
            String observacao = rs.getString(7);
            return new VacinaMedicamento(id, tipo, tipoAnimal, nomeMedicamento, observacao, periodicidade, tempo); 
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
