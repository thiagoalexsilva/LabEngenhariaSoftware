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
    
    private static Connection conexao;
    
    public String INSERT_SQL = "INSERT INTO MEDICAMENTOS_VACINAS VALUES (?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM MEDICAMENTOS_VACINAS";
    public String UPDATE_SQL = "UPDATE MEDICAMENTOS_VACINAS SET NOMEVACINA=?, DESCVACINA=?, NOMEMEDICAMENTO=?, DESCMEDICAMENTO=?, TIPO=?, TIPOANIMAL=?, PERIODICIDADE=? WHERE IDMED_VAC=?;";
    public String DELETE_SQL = "DELETE FROM VACINA WHERE IDMED_VAC=?;";
   
    
        
    public DAOMedicamentos(){
        conexao = Conexao.getConexao();
    }
    
    public boolean insert(VacinaMedicamento medicamento){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(INSERT_SQL);
            stmt.setString(1, medicamento.getTipo());
            stmt.setString(2, medicamento.getTipoAnimal());
            stmt.setString(3, medicamento.getNome());
            stmt.setInt(4, medicamento.getPeriodicidade());
            stmt.setString(5, medicamento.getObservacao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public VacinaMedicamento[] selectAll(){
        
        ArrayList<VacinaMedicamento> medicamentos = new ArrayList<VacinaMedicamento>();
        VacinaMedicamento[] arrayMedicamentos;
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(SELECT_SQL);
            
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
        
        return arrayMedicamentos;
    }
   
    
    public boolean update(VacinaMedicamento medicamento){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(UPDATE_SQL);
            stmt.setString(1, medicamento.getTipo());
            stmt.setString(2, medicamento.getTipoAnimal());
            stmt.setString(3, medicamento.getNome());
            stmt.setInt(4, medicamento.getPeriodicidade());
            stmt.setString(5, medicamento.getObservacao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean delete(int idmed_vac){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idmed_vac);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private VacinaMedicamento getMedicamento(ResultSet rs){
        try {
            String tipo = rs.getString(1);
            String tipoAnimal = rs.getString(2);
            String nomeMedicamento = rs.getString(3);
            int periodicidade = rs.getInt(4);
            String descMedicamento = rs.getString(5);
            return new VacinaMedicamento(tipo, tipoAnimal, nomeMedicamento, periodicidade, descMedicamento); 
        } catch (SQLException ex) {
            Logger.getLogger(DAOMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
