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
import model.entity.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
public class DAOPessoa {
    private static Connection conexao;
    
    public String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM PESSOA WHERE EMAILPESSOA=?;";
    public String UPDATE_SQL = "UPDATE PESSOA SET IDPESSOA=?, NOMEPESSOA=?, EMAILPESSOA=?, SENHAPESSOA=?, GENEROPESSOA=?, TIPOPESSOA=?"
            + "ENDERECOPESSOA=?, BAIRROPESSOA=?, COMPLEMENTOPESSOA=?, CIDADEPESSOA=?, CEPPESSOA=?, UFPESSOA=?, TELEFONEPESSOA=?"
            + "WHERE IDPESSOA=?;";
    public String DELETE_SQL = "DELETE FROM PESSOA WHERE EMAILPESSOA=? AND SENHAPESSOA=?;";
    public String SELECT_NEW_ID_SQL = "SELECT MAX(IDPESSOA) FROM PESSOA;";

    public DAOPessoa(){
        conexao = Conexao.getConexao();
    }
    
    public boolean insert(Pessoa pessoa){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(INSERT_SQL);
            stmt.setInt(1, newId());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getSenha());
            stmt.setString(5, pessoa.getSexo());
            stmt.setInt(6, pessoa.getTipo());
            stmt.setString(7, pessoa.getEnd());
            stmt.setString(8, pessoa.getBairro());
            stmt.setString(9, pessoa.getComplemento());
            stmt.setString(10, pessoa.getCidade());
            stmt.setString(11, pessoa.getCep());
            stmt.setString(12, pessoa.getUf());
            stmt.setString(13, pessoa.getTelefone());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Pessoa select(String email){
        
        Pessoa pessoa = new Pessoa();
        PreparedStatement stmt;
        
        try {
            stmt = conexao.prepareStatement(SELECT_SQL);
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            pessoa = getPessoa(rs);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pessoa;
    }
    
    public boolean update(Pessoa pessoa){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(UPDATE_SQL);
            stmt.setInt(1, pessoa.getId());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getSenha());
            stmt.setString(5, pessoa.getSexo());
            stmt.setInt(6, pessoa.getTipo());
            stmt.setString(7, pessoa.getEnd());
            stmt.setString(8, pessoa.getBairro());
            stmt.setString(9, pessoa.getComplemento());
            stmt.setString(10, pessoa.getCidade());
            stmt.setString(11, pessoa.getCep());
            stmt.setString(12, pessoa.getUf());
            stmt.setString(13, pessoa.getTelefone());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean delete(String email, String senha){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(DELETE_SQL);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private Pessoa getPessoa(ResultSet rs){
        try {
            int id = rs.getInt(1);
            String nome = rs.getString(2);
            String email = rs.getString(3);
            String senha = rs.getString(4);
            String sexo = rs.getString(5);
            int tipo = rs.getInt(6);
            String end = rs.getString(7);
            String bairro = rs.getString(8);
            String complemento = rs.getString(9);
            String cidade = rs.getString(10);
            String cep = rs.getString(11);
            String uf = rs.getString(12);
            String telefone = rs.getString(13);
            
            return new Pessoa(id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, sexo, tipo); //primeiro nomeRaca = tipoAnimal
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private int newId() {
        PreparedStatement stmt;
        
        try {
            int id;
            stmt = conexao.prepareStatement(SELECT_NEW_ID_SQL);
            
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt(1);
            
            return id+1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
