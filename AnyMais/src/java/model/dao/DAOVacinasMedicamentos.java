/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import controller.GerenciarTiposAnimal;
import controller.GerenciarTiposVacinasMedicamentos;
import controller.GerenciarVacinasMedicamentos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.TipoAnimal;
import model.entity.TipoVacinaMedicamento;
import model.entity.VacinasMedicamentos;

/**
 *
 * @author ana
 */
public class DAOVacinasMedicamentos {
    
    private Conexao conexao;
    private Connection con;
    
    private String INSERT_SQL = "INSERT INTO VACINASMEDICAMENTOS VALUES (?, ?, ?, ?, ?, ?, ?);";
    private String SELECT_SQL = "SELECT * FROM VACINASMEDICAMENTOS";
    private String UPDATE_SQL = "UPDATE VACINASMEDICAMENTOS SET TIPO=?, TIPOANIMAL=?, NOME=?, PERIODICIDADE=?, TEMPO=?, OBSERVACAO=? WHERE IDVACINASMEDICAMENTOS=?;";
    private String DELETE_SQL = "DELETE FROM VACINASMEDICAMENTOS WHERE IDVACINASMEDICAMENTOS=?;";
    
    private String SELECT_NEW_ID_SQL = "SELECT COALESCE(MAX(IDVACINASMEDICAMENTOS), 0)+1 FROM VACINASMEDICAMENTOS;";    
        
    public DAOVacinasMedicamentos(){
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
            Logger.getLogger(DAOVacinasMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao.closeConexao();
        return 0;
    }
    
    public boolean insert(VacinasMedicamentos vacinasMedicamentos){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setInt(2, vacinasMedicamentos.getTipoVacinaMedicamento().getIdTipoVacinaMedicamento());
            stmt.setInt(3, vacinasMedicamentos.getTipoAnimal().getIdTipoAnimal());
            stmt.setString(4, vacinasMedicamentos.getNome());
            stmt.setInt(5, vacinasMedicamentos.getPeriodicidade());
            stmt.setString(6, vacinasMedicamentos.getTempo());
            stmt.setString(7, vacinasMedicamentos.getObservacao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOVacinasMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public VacinasMedicamentos[] selectAll(){
        con = conexao.openConexao();
        ArrayList<VacinasMedicamentos> vacinasMedicamentos = new ArrayList<VacinasMedicamentos>();
        VacinasMedicamentos[] arrayVacinasMedicamentos;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                vacinasMedicamentos.add(getMedicamento(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOVacinasMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayVacinasMedicamentos = new VacinasMedicamentos[vacinasMedicamentos.size()];
        
        for(int i=0; i<arrayVacinasMedicamentos.length; i++){
            arrayVacinasMedicamentos[i] = vacinasMedicamentos.get(i);
        }
        
        conexao.closeConexao();
        return arrayVacinasMedicamentos;
    }
   
    public boolean update(VacinasMedicamentos medicamento){
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(UPDATE_SQL);
            stmt.setInt(1, medicamento.getTipoVacinaMedicamento().getIdTipoVacinaMedicamento());
            stmt.setInt(2, medicamento.getTipoAnimal().getIdTipoAnimal());
            stmt.setString(3, medicamento.getNome());
            stmt.setInt(4, medicamento.getPeriodicidade());
            stmt.setString(5, medicamento.getTempo());
            stmt.setString(6, medicamento.getObservacao());
            stmt.setInt(7, medicamento.getIdVacinasMedicamentos());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOVacinasMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public boolean delete(int idVacinasMedicamentos){
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idVacinasMedicamentos);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOVacinasMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    private VacinasMedicamentos getMedicamento(ResultSet rs){
        try {
            int idVacinasMedicamentos = rs.getInt(1);
            int tipo = rs.getInt(2);
            TipoVacinaMedicamento tipoVacinaMedicamento = GerenciarTiposVacinasMedicamentos.getInstance().selecionaTiposAnimalPorId(tipo);
            
            int idTipoAnimal = rs.getInt(3);
            TipoAnimal tipoAnimal = GerenciarTiposAnimal.getInstance().selecionaTiposAnimalPorId(idTipoAnimal);
            
            String nome = rs.getString(4);
            int periodicidade = rs.getInt(5);
            String tempo = rs.getString(6);
            String observacao = rs.getString(7);
            return new VacinasMedicamentos(idVacinasMedicamentos, tipoVacinaMedicamento, tipoAnimal, nome, periodicidade, tempo, observacao); 
        } catch (SQLException ex) {
            Logger.getLogger(DAOVacinasMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
