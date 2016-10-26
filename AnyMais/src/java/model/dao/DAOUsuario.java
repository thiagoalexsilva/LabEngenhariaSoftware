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
    
    private String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String SELECT_SQL = "SELECT * FROM PESSOA WHERE EMAILPESSOA=?;";
    private String SELECT_NEW_ID_SQL = "SELECT MAX(IDPESSOA) FROM PESSOA;";
    
    private String INSERT_USUARIO_SQL = "INSERT INTO USUARIO VALUES (?, ?, ?, ?, ?, ?);";
    private String SELECT_USUARIO_SQL = "SELECT * FROM USUARIO WHERE IDUSUARIO=?;";
    private String UPDATE_USUARIO_SQL = "UPDATE USUARIO SET GENEROUSUARIO=?, DATANASICMENTOUSUARIO=?, CELULARUSUARIO=?"
            + "WHERE CPFUSUARIO=?;";
    private String DELETE_USUARIO_SQL = "DELETE FROM USUARIO WHERE IDUSUARIO=?;";
    private String SELECT_NEW_ID_USUARIO_SQL = "SELECT MAX(IDUSUARIO) FROM USUARIO;";
    private String SELECT_CPF_USUARIO = "SELECT COUNT(*) "          //count????
            + "FROM USUARIO U INNER JOIN PESSOA P ON (U.IDUSUARIO = P.IDUSUARIO)"
            + "WHERE U.CPFUSUARIO=?"
            + "OR P.EMAILPESSOA=?";

    public String SELECT_MAX_ID_PESSOA_SQL = "SELECT COALESCE(MAX(IDPESSOA), 0)+1 FROM PESSOA;";
    public String SELECT_MAX_ID_USUARIO_SQL = "SELECT COALESCE(MAX(IDUSUARIO), 0)+1 FROM USUARIO;";
    
    public DAOUsuario(){
        conexao = Conexao.getConexao();
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
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    private int nextIdUsuario(){
        
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(SELECT_MAX_ID_USUARIO_SQL);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            return id;
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    
    public boolean insert(Usuario usuario){
        int pessoaid;
        PreparedStatement stmt;
        try {        
            //userid = newId(SELECT_NEW_ID_USUARIO_SQL);
            int userId = nextIdUsuario();
            pessoaid = newId(SELECT_NEW_ID_SQL);
            
            stmt = conexao.prepareStatement(INSERT_SQL);
            
            stmt.setInt(1, nextIdPessoa());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getNome());
            stmt.setString(5, usuario.getEnd());
            stmt.setString(6, usuario.getBairro());
            stmt.setString(7, usuario.getComplemento());
            stmt.setString(8, usuario.getCidade());
            stmt.setString(9, usuario.getCep());
            stmt.setString(10, usuario.getUf());
            stmt.setString(11, usuario.getTelefone());
            stmt.setInt(12, usuario.getTipo());
            stmt.setInt(13, userId);
            
            /*stmt.setInt(1, pessoaid);
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
            stmt.setInt(13, userid);*/
            
            stmt.execute();
            
            stmt = conexao.prepareStatement(INSERT_USUARIO_SQL);
            
            stmt.setInt(1, userId);
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getCelular());
            stmt.setString(6, usuario.getMensagem());
            
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
            stmt = conexao.prepareStatement(UPDATE_USUARIO_SQL);
            stmt.setString(1, usuario.getSexo());
            stmt.setString(2, usuario.getDataNascimento());
            stmt.setString(3, usuario.getCelular());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getMensagem());
            
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
            int p_id = rs.getInt(14);
            
            stmt = conexao.prepareStatement(DELETE_USUARIO_SQL);
            stmt.setInt(1, p_id);
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
            /*8int id              = rs.getInt(1);
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
            int u_id            = rs.getInt(13);*/
            
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
            int u_id            = rs.getInt(13);
            
            stmt = conexao.prepareStatement(SELECT_USUARIO_SQL);
            stmt.setInt(1, u_id);
            
            ResultSet u_rs = stmt.executeQuery();
            int new_id = u_rs.getInt(1);
            String cpf = u_rs.getString(2);
            String sexo = u_rs.getString(3);
            String dataNascimento = u_rs.getString(4);
            String celular = u_rs.getString(5);
            String mensagem = u_rs.getString(6);
            
            return new Usuario(id, cpf, sexo, dataNascimento, celular, new_id, email, senha, nome, end, bairro, complemento, cidade, cep, uf, telefone, tipo, mensagem);
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

    public boolean checkCPF_Email(String cpf, String email) {
       PreparedStatement stmt;
        
        try {
            stmt = conexao.prepareStatement(SELECT_CPF_USUARIO);
            stmt.setString(1, cpf);
            stmt.setString(2, email);
            
            ResultSet rs = stmt.executeQuery();
            int qntd = rs.getInt(1);
            
            return qntd != 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
