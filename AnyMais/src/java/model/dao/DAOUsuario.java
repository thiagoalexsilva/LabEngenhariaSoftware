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
import model.entity.Usuario;

/**
 *
 * @author ThiagoAlexandre
 */
public class DAOUsuario {
    private static Connection conexao;
    
    public String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public String SELECT_SQL = "SELECT * FROM PESSOA WHERE EMAILPESSOA=?;";
    public String UPDATE_SQL = "UPDATE PESSOA SET NOMEPESSOA=?, EMAILPESSOA=?, SENHAPESSOA=?, TIPOPESSOA=?, ENDERECOPESSOA=?,"
            + "BAIRROPESSOA=?, COMPLEMENTOPESSOA=?, CIDADEPESSOA=?, CEPPESSOA=?, UFPESSOA=?, TELEFONEPESSOA=?"
            + "WHERE PESSOA_USUARIO_PET=?;";
    public String DELETE_SQL = "DELETE FROM PESSOA WHERE EMAILPESSOA=? AND SENHAPESSOA=?;";
    public String SELECT_NEW_ID_SQL = "SELECT MAX(IDPESSOA) FROM PESSOA;";
    
    public String INSERT_USUARIO_SQL = "INSERT INTO USUARIO VALUES (?, ?, ?, ?, ?);";
    public String SELECT_USUARIO_SQL = "SELECT * FROM USUARIO WHERE IDUSUARIO=?;";
    public String UPDATE_USUARIO_SQL = "UPDATE USUARIO SET GENEROUSUARIO=?, DATANASICMENTOUSUARIO=?, CELULARUSUARIO=?"
            + "WHERE CPFUSUARIO=?;";
    public String DELETE_USUARIO_SQL = "DELETE FROM USUARIO WHERE IDUSUARIO=?;";
    public String SELECT_NEW_ID_USUARIO_SQL = "SELECT MAX(IDUSUARIO) FROM USUARIO;";

    public DAOUsuario(){
        conexao = Conexao.getConexao();
    }
    
    public boolean insert(Usuario usuario){
        int userid, pessoaid;
        PreparedStatement stmt;
        try {
            userid = newId(SELECT_NEW_ID_USUARIO_SQL);
            stmt = conexao.prepareStatement(INSERT_USUARIO_SQL);
            stmt.setInt(1, userid);
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getCelular());
            
            stmt.execute();
            
            pessoaid = newId(SELECT_NEW_ID_SQL);
            stmt = conexao.prepareStatement(INSERT_SQL);
            stmt.setInt(1, pessoaid);
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setInt(5, usuario.getTipo());
            stmt.setString(6, usuario.getEnd());
            stmt.setString(7, usuario.getBairro());
            stmt.setString(8, usuario.getComplemento());
            stmt.setString(9, usuario.getCidade());
            stmt.setString(10, usuario.getCep());
            stmt.setString(11, usuario.getUf());
            stmt.setString(12, usuario.getTelefone());
            stmt.setInt(13, userid);
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Usuario select(String email){
        
        Usuario usuario = new Usuario();
        PreparedStatement stmt;
        
        try {
            stmt = conexao.prepareStatement(SELECT_SQL);
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            usuario = getUsuario(rs);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public boolean update(Usuario usuario){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(UPDATE_SQL);
            stmt.setInt(1, usuario.getId());
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
            stmt = conexao.prepareStatement(SELECT_SQL);
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            int u_id = rs.getInt(13);
            
            stmt = conexao.prepareStatement(DELETE_USUARIO_SQL);
            stmt.setInt(1, u_id);
            stmt.execute();
            
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
    
    private Usuario getUsuario(ResultSet rs){
        PreparedStatement stmt;
            
        try {
            int id              = rs.getInt(1);
            String nome         = rs.getString(2);
            String email        = rs.getString(3);
            String senha        = rs.getString(4);
            int tipo            = rs.getInt(5);
            String end          = rs.getString(6);
            String bairro       = rs.getString(7);
            String complemento  = rs.getString(8);
            String cidade       = rs.getString(9);
            String cep          = rs.getString(10);
            String uf           = rs.getString(11);
            String telefone     = rs.getString(12);
            int u_id            = rs.getInt(13);
            
            stmt = conexao.prepareStatement(SELECT_USUARIO_SQL);
            stmt.setInt(1, u_id);
            
            ResultSet u_rs = stmt.executeQuery();
            String sexo = u_rs.getString(2);
            String dataNascimento = u_rs.getString(3);
            String celular = u_rs.getString(4);
            
            return new Usuario(null, sexo, dataNascimento, celular, u_id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, tipo);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
