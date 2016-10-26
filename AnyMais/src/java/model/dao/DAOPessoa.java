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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Usuario;

/**
 *
 * @author ThiagoAlexandre
 */
public class DAOPessoa {
private static Connection conexao;
    
    public String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM PESSOA WHERE EMAILPESSOA=?;";
    public String UPDATE_SQL = "UPDATE PESSOA SET NOMEPESSOA=?, EMAILPESSOA=?, SENHAPESSOA=?, TIPOPESSOA=?, ENDERECOPESSOA=?,"
            + "BAIRROPESSOA=?, COMPLEMENTOPESSOA=?, CIDADEPESSOA=?, CEPPESSOA=?, UFPESSOA=?, TELEFONEPESSOA=?"
            + "WHERE PESSOA_USUARIO_PET=?;";
    public String DELETE_SQL = "DELETE FROM PESSOA WHERE EMAILPESSOA=? AND SENHAPESSOA=?;";
    public String SELECT_NEW_ID_SQL = "SELECT MAX(IDPESSOA) FROM PESSOA;";

    public DAOPessoa(){
        conexao = Conexao.getConexao();
    }
    
    public boolean update(Usuario usuario){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(UPDATE_SQL);
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getSexo());
            stmt.setInt(6, usuario.getTipo());
            stmt.setString(7, usuario.getEnd());
            stmt.setString(8, usuario.getBairro());
            stmt.setString(9, usuario.getComplemento());
            stmt.setString(10, usuario.getCidade());
            stmt.setString(11, usuario.getCep());
            stmt.setString(12, usuario.getUf());
            stmt.setString(13, usuario.getTelefone());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }    
    
    public boolean delete(int idPetShop){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(DELETE_SQL);
            stmt.setInt(1, idPetShop);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
