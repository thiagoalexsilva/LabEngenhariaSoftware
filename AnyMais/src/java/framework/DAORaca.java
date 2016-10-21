/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Raca;

/**
 *
 * @author Gustavo
 */
public class DAORaca {
    
    private static Connection conexao;
    
    public String INSERT_SQL = "INSERT INTO RACA_ANIMAL VALUES (?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM RACA_ANIMAL;";
    public String UPDATE_SQL = "UPDATE RACA_ANIMAL SET NOMERACA=?, PORTERACA=?, OBSERVACAORACA=? WHERE IDRACA=?;";
    public String DELETE_SQL = "DELETE FROM RACA_ANIMAL WHERE IDRACA=?;";

    public String SELECT_WHERE_SQL = SELECT_SQL + " WHERE ";
    
    public String TIPOANIMAL_CACHORRO = "TIPOANIMALRACA = 'CACHORRO'";
    public String TIPOANIMAL_GATO = "TIPOANIMALRACA = 'GATO'";
    public String PORTE_PEQUENO = "PORTERACA = 'PEQUENO'";
    public String PORTE_MEDIO = "PORTERACA = 'MEDIO'";
    public String PORTE_GRANDE = "PORTERACA = 'GRANDE'";
        
    public DAORaca(){
        try {
            if(conexao == null){
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/anymais", "postgres", "postgres");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insert(Raca raca){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(INSERT_SQL);
            stmt.setInt(1, raca.getId());
            stmt.setString(2, raca.getNomeRaca());
            stmt.setString(3, raca.getPorte());
            stmt.setString(4, raca.getObservacao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Raca[] selectAll(){
        
        ArrayList<Raca> racas = new ArrayList<Raca>();
        Raca[] arrayRacas;
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(SELECT_SQL);
            
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
        
        return arrayRacas;
    }
    
    public Raca[] selectAllFiltered(boolean cachorro, boolean gato, boolean pequeno, boolean medio, boolean grande){
        
        ArrayList<Raca> racas = new ArrayList<Raca>();
        Raca[] arrayRacas;
        PreparedStatement stmt;
        try {
            boolean adicionado = false;
            
            if(cachorro){
                if(adicionado)
                    SELECT_WHERE_SQL += ", ";
                SELECT_WHERE_SQL += TIPOANIMAL_CACHORRO;
            }

            if(gato){
                if(adicionado)
                    SELECT_WHERE_SQL += ", ";
                SELECT_WHERE_SQL += TIPOANIMAL_GATO;
            }
            
            if(pequeno){
                if(adicionado)
                    SELECT_WHERE_SQL += ", ";
                SELECT_WHERE_SQL += PORTE_PEQUENO;
            }
            
            if(medio){
                if(adicionado)
                    SELECT_WHERE_SQL += ", ";
                SELECT_WHERE_SQL += PORTE_MEDIO;
            }
            
            if(grande){
                if(adicionado)
                    SELECT_WHERE_SQL += ", ";
                SELECT_WHERE_SQL += PORTE_GRANDE;
            }
            
            stmt = conexao.prepareStatement(SELECT_WHERE_SQL);
            
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
        
        return arrayRacas;
    }
    
    public boolean update(Raca raca){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(UPDATE_SQL);
            stmt.setString(1, raca.getNomeRaca());
            stmt.setString(2, raca.getPorte());
            stmt.setString(3, raca.getObservacao());
            stmt.setInt(4, raca.getId());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean delete(int idraca){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idraca);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private Raca getRaca(ResultSet rs){
        try {
            int id = rs.getInt(1);
            String nomeRaca = rs.getString(2);
            String porteRaca = rs.getString(3);
            String observacao = rs.getString(4);
            return new Raca(id, nomeRaca, nomeRaca, porteRaca, observacao); //primeiro nomeRaca = tipoAnimal
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}