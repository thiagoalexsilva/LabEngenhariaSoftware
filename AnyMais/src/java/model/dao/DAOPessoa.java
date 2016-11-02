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
import model.entity.Pessoa;

/**
 *
 * @author ThiagoAlexandre
 */
public class DAOPessoa {
    private Conexao conexao;
    private Connection con;
    private final String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SELECT_SQL_NOME = "SELECT * FROM PESSOA WHERE IDPESSOA=?;";
    private final String SELECT_SQL_EMAIL = "SELECT * FROM PESSOA WHERE EMAIL=?;";
    private final String UPDATE_SQL = "UPDATE PESSOA SET NOME=?, SEXO=?, DATANASCIEMENTO=?, ENDERECO=?,"
            + "BAIRRO=?, COMPLEMENTO=?, CIDADE=?, CEP=?, UF=?, TELEFONE=?, TELEFONE2=?, SENHA=?, EMAIL=?"
            + "WHERE IDPESSOA=?;";
    private final String DELETE_SQL = "DELETE FROM PESSOA WHERE IDPESSOA=?;";
    private final String SELECT_NEW_ID_SQL = "SELECT COALESCE(MAX(IDRACA), 0)+1 FROM PESSOA;";
    private final String SELECT_CNPJ_PETSHOP = "SELECT COUNT(*) "
            + "FROM PESSOA P"
            + "WHERE P.CPFCNPJ=?"
            + "OR P.EMAILPESSOA=?";
    private final String SELECT_CPF_USUARIO = "SELECT COUNT(*) "         
            + "FROM PESSOA P"
            + "WHERE P.CPFCNPJ=?"
            + "OR P.EMAILPESSOA=?";

    public DAOPessoa(){
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
    
    public boolean insert(Pessoa pessoa){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setInt(2, pessoa.getTipo());
            stmt.setString(3, pessoa.getNome());
            stmt.setString(4, pessoa.getSexo());
            stmt.setDate(5, pessoa.getDataNascimento());
            stmt.setString(6, pessoa.getCpf_cnpj());
            stmt.setString(7, pessoa.getEnd());
            stmt.setString(8, pessoa.getBairro());
            stmt.setString(9, pessoa.getCep());
            stmt.setString(10, pessoa.getComplemento());
            stmt.setString(11, pessoa.getCidade());
            stmt.setString(12, pessoa.getUf());
            stmt.setString(13, pessoa.getTelefone());
            stmt.setString(14, pessoa.getTelefone2());
            stmt.setString(15, pessoa.getEmail());
            stmt.setString(16, pessoa.getSenha());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public boolean update(Pessoa pessoa){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(UPDATE_SQL);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setDate(3, pessoa.getDataNascimento());
            stmt.setString(4, pessoa.getEnd());
            stmt.setString(5, pessoa.getBairro());
            stmt.setString(6, pessoa.getComplemento());
            stmt.setString(7, pessoa.getCidade());
            stmt.setString(8, pessoa.getCep());
            stmt.setString(9, pessoa.getUf());
            stmt.setString(10, pessoa.getTelefone());
            stmt.setString(11, pessoa.getTelefone2());
            stmt.setString(12, pessoa.getSenha());
            stmt.setString(13, pessoa.getEmail());
            stmt.setString(14, pessoa.getSenha());
            stmt.setInt(15, nextId());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public boolean delete(int id){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(DELETE_SQL);
            stmt.setInt(1, id);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    } 

    public Pessoa select(String email, String nome) {
        con = conexao.openConexao();
        Pessoa pessoa = null;
        ResultSet rs;
        try {   
            if(email.isEmpty()){
                PreparedStatement stmt;
                stmt = con.prepareStatement(SELECT_SQL_NOME);
                rs = stmt.executeQuery();
            }
            else{
                PreparedStatement stmt;
                stmt = con.prepareStatement(SELECT_SQL_EMAIL);
                rs = stmt.executeQuery();
            }
            
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt(1));
            pessoa.setTipo(rs.getInt(2));
            pessoa.setNome(rs.getString(3));
            pessoa.setSexo(rs.getString(4));
            pessoa.setDataNascimento(rs.getDate(5));
            pessoa.setCpf_cnpj(rs.getString(6));
            pessoa.setEnd(rs.getString(7));
            pessoa.setBairro(rs.getString(8));
            pessoa.setComplemento(rs.getString(9));
            pessoa.setCep(rs.getString(10));
            pessoa.setCidade(rs.getString(11));
            pessoa.setUf(rs.getString(12));
            pessoa.setTelefone(rs.getString(13));
            pessoa.setTelefone2(rs.getString(14));
            pessoa.setEmail(rs.getString(15));
            pessoa.setSenha(rs.getString(16));
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return pessoa;
    }
    
    public boolean checkCNPJ_Email(String cnpj, String email) {
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        
        try {
            stmt = con.prepareStatement(SELECT_CNPJ_PETSHOP);
            stmt.setString(1, cnpj);
            stmt.setString(2, email);
            
            ResultSet rs = stmt.executeQuery();
            int qntd = rs.getInt(1);
            
            return qntd != 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao.closeConexao();
        return false;
    }
    
    public boolean checkCPF_Email(String cpf, String email) {
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        
        try {
            stmt = con.prepareStatement(SELECT_CPF_USUARIO);
            stmt.setString(1, cpf);
            stmt.setString(2, email);
            
            ResultSet rs = stmt.executeQuery();
            int qntd = rs.getInt(1);
            
            return qntd != 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexao.closeConexao();
        return false;
    }
}
