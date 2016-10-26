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
import model.entity.PetShop;
import model.entity.Usuario;

/**
 *
 * @author Erica
 */
public class DAOPetShop {
    public static Connection conexao;
    
    public String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM PESSOA WHERE EMAILPESSOA=?;";
    public String SELECT_NEW_ID_SQL = "SELECT MAX(IDPESSOA) FROM PESSOA;";
    
    public String INSERT_PETSHOP_SQL = "INSERT INTO PETSHOP VALUES (?, ?);";
    public String SELECT_PETSHOP_SQL = "SELECT * FROM PETSHOP WHERE IDPET = ?;";
    public String UPDATE_PETSHOP_SQL = "UPDATE PETSHOP TELEFONE2=?" + "WHERE CNPJPET=?;";
    public String DELETE_PETSHOP_SQL = "DELETE FROM PETSHOP WHERE IDPET=?;";
    public String SELECT_NEW_ID_PETSHOP_SQL = "SELECT MAX(IDPET) FROM PETSHOP;";
    private String SELECT_CNPJ_PETSHOP = "SELECT COUNT(*) "
            + "FROM PETSHOP PS INNER JOIN PESSOA P ON (PS.IDPET = P.IDPET)"
            + "WHERE PS.CNPJPET=?"
            + "OR P.EMAILPESSOA=?";
    
    public String SELECT_MAX_ID_PESSOA_SQL = "SELECT COALESCE(MAX(IDPESSOA), 0)+1 FROM PESSOA;";
    public String SELECT_MAX_ID_PETSHOP_SQL = "SELECT COALESCE(MAX(IDPET), 0)+1 FROM PETSHOP;";
    
    public DAOPetShop(){
        conexao = Conexao.getConexao();
    }    
    
    private int nextIdPetShop(){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(SELECT_MAX_ID_PETSHOP_SQL);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            return id;
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    private int nextIdPessoa(){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(SELECT_MAX_ID_PESSOA_SQL);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            return id;
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public boolean insert(PetShop petshop){
        int pessoaId;
        PreparedStatement stmt;
        
        try{
            int petshopId = nextIdPetShop();
            //petshopId = newId(SELECT_NEW_ID_PETSHOP_SQL);
            stmt = conexao.prepareStatement(INSERT_PETSHOP_SQL);
                      
            //pessoaId = newId(SELECT_NEW_ID_SQL);
            stmt = conexao.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextIdPessoa());
            stmt.setString(2, petshop.getEmail());
            stmt.setString(3, petshop.getSenha());
            stmt.setString(4, petshop.getNome());
            stmt.setString(5, petshop.getEnd());
            stmt.setString(6, petshop.getBairro());
            stmt.setString(7, petshop.getComplemento());
            stmt.setString(8, petshop.getCidade());
            stmt.setString(9, petshop.getCep());
            stmt.setString(10, petshop.getUf());
            stmt.setString(11, petshop.getTelefone());
            stmt.setInt(12, petshop.getTipo());
            stmt.setInt(14, petshopId);
            
            stmt.execute();
            
            stmt = conexao.prepareStatement(INSERT_PETSHOP_SQL);
            
            stmt.setInt(1, petshopId);
            stmt.setString(2, petshop.getTelefone2());
            stmt.setString(3, petshop.getCnpj());

            stmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public PetShop select(String nome){
        PetShop petshop = new PetShop();
        PreparedStatement stmt;
        
        try {
            stmt = conexao.prepareStatement(SELECT_SQL);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            petshop = getPetShop(rs);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return petshop;
    }
    
    public boolean update(PetShop petshop){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(UPDATE_PETSHOP_SQL);
            stmt.setString(1, petshop.getTelefone2());
            stmt.setString(2, petshop.getCnpj());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean delete(int idPetShop){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(SELECT_SQL);
            stmt.setInt(1, idPetShop);
            
            ResultSet rs = stmt.executeQuery();
            int pet_id = rs.getInt(14);
            
            stmt = conexao.prepareStatement(DELETE_PETSHOP_SQL);
            stmt.setInt(1, idPetShop);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private PetShop getPetShop(ResultSet rs){
        PreparedStatement stmt;
            
        try {
            int id              = rs.getInt(1);
            String email        = rs.getString(2);
            String senha        = rs.getString(3);
            String nome         = rs.getString(4);
            String end          = rs.getString(5);
            String bairro       = rs.getString(6);
            String complemento  = rs.getString(7);
            String cidade       = rs.getString(8);
            String cep          = rs.getString(9);
            String uf           = rs.getString(10);
            String telefone     = rs.getString(11);
            int tipo            = rs.getInt(12);
            int p_id            = rs.getInt(14);
            
            stmt = conexao.prepareStatement(SELECT_PETSHOP_SQL);
            stmt.setInt(1, p_id);
            
            ResultSet u_rs = stmt.executeQuery();
            int new_id = u_rs.getInt(1);
            String telefone2 = u_rs.getString(2);
            String cnpj = u_rs.getString(3);
            
            return new PetShop(id, cnpj, new_id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, telefone2, tipo);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private int newId(String sql) {
        PreparedStatement stmt;
        
        try {
            int id;
            stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt(1);
            
            return id+1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean checkCNPJ_Email(String cnpj, String email) {
       PreparedStatement stmt;
        
        try {
            stmt = conexao.prepareStatement(SELECT_CNPJ_PETSHOP);
            stmt.setString(1, cnpj);
            stmt.setString(2, email);
            
            ResultSet rs = stmt.executeQuery();
            int qntd = rs.getInt(1);
            
            return qntd != 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPetShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
