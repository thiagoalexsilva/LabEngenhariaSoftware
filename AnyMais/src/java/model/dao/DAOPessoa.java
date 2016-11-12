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
import java.util.ArrayList;
import java.util.Date;
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
    
    private final String INSERT_SQL = "INSERT INTO PESSOA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private final String SELECT_SQL = "SELECT * FROM PESSOA;";
    
    private final String SELECT_SQL_ID = "SELECT * FROM PESSOA WHERE IDPESSOA=?;";
    
    private final String SELECT_SQL_EMAIL = "SELECT * FROM PESSOA WHERE EMAIL=?;";
    
    private final String UPDATE_SQL = "UPDATE PESSOA SET NOME=?, SEXO=?, DATANASCIMENTO=?, ENDERECO=?,"
                                    + "BAIRRO=?, COMPLEMENTO=?, CEP=?, CIDADE=?, UF=?, TELEFONE=?, TELEFONE2=?, EMAIL=?, SENHA=?, IMAGEM=?, DESCRICAO=?"
                                    + "WHERE IDPESSOA=?;";
    
    private final String DELETE_SQL = "DELETE FROM PESSOA WHERE IDPESSOA=?;";
    
    private final String SELECT_NEW_ID_SQL = "SELECT COALESCE(MAX(IDPESSOA), 0)+1 FROM PESSOA;";
    
    private final String SELECT_CNPJ_PETSHOP = "SELECT COUNT(*) "
                                            + "FROM PESSOA P"
                                            + "WHERE P.CPFCNPJ=?"
                                            + "OR P.EMAIL=?";
    
    private final String SELECT_CPF_USUARIO = "SELECT COUNT(*) "         
                                            + "FROM PESSOA P"
                                            + "WHERE P.CPFCNPJ=?"
                                            + "OR P.PESSOA=?";

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
            if(pessoa.getDataNascimento() != null){
                stmt.setDate(5, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            } else{
                stmt.setDate(5, null);
            }
            stmt.setString(6, pessoa.getCpfCnpj());
            stmt.setString(7, pessoa.getEndereco());
            stmt.setString(8, pessoa.getBairro());
            stmt.setString(9, pessoa.getComplemento());
            stmt.setString(10, pessoa.getCep());
            stmt.setString(11, pessoa.getCidade());
            stmt.setString(12, pessoa.getUf());
            stmt.setString(13, pessoa.getTelefone());
            stmt.setString(14, pessoa.getTelefone2());
            stmt.setString(15, pessoa.getEmail());
            stmt.setString(16, pessoa.getSenha());
            stmt.setString(17, pessoa.getImagem());
            stmt.setString(18, pessoa.getDescricao());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
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
            if(pessoa.getDataNascimento() != null){
                stmt.setDate(3, new java.sql.Date(pessoa.getDataNascimento().getTime()));
            } else{
                stmt.setDate(3, null);
            }
            
            stmt.setString(4, pessoa.getEndereco());
            stmt.setString(5, pessoa.getBairro());
            stmt.setString(6, pessoa.getComplemento());
            stmt.setString(7, pessoa.getCep());
            stmt.setString(8, pessoa.getCidade());
            stmt.setString(9, pessoa.getUf());
            stmt.setString(10, pessoa.getTelefone());
            stmt.setString(11, pessoa.getTelefone2());
            stmt.setString(12, pessoa.getEmail());
            stmt.setString(13, pessoa.getSenha());
            stmt.setString(14, pessoa.getImagem());
            stmt.setString(15, pessoa.getDescricao());
            stmt.setInt(16, nextId());
            
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

    public Pessoa select(int idPessoa) {
        con = conexao.openConexao();
        Pessoa pessoa = null;
        PreparedStatement stmt;
        ResultSet rs;
        
        try{   
            stmt = con.prepareStatement(SELECT_SQL_ID);
                
            stmt.setInt(1, pessoa.getIdPessoa());
            rs = stmt.executeQuery();
            
            
            pessoa = new Pessoa();
            pessoa.setIdPessoa(rs.getInt(1));
            pessoa.setTipo(rs.getInt(2));
            pessoa.setNome(rs.getString(3));
            pessoa.setSexo(rs.getString(4));
            
            if(pessoa.getDataNascimento() != null){
                pessoa.setDataNascimento(new java.sql.Date(rs.getDate(5).getTime()));
            } else{
                pessoa.setDataNascimento(null);
            }
            
            pessoa.setCpfCnpj(rs.getString(6));
            pessoa.setEndereco(rs.getString(7));
            pessoa.setBairro(rs.getString(8));
            pessoa.setComplemento(rs.getString(9));
            pessoa.setCep(rs.getString(10));
            pessoa.setCidade(rs.getString(11));
            pessoa.setUf(rs.getString(12));
            pessoa.setTelefone(rs.getString(13));
            pessoa.setTelefone2(rs.getString(14));
            pessoa.setEmail(rs.getString(15));
            pessoa.setSenha(rs.getString(16));
            pessoa.setImagem(rs.getString(17));
            pessoa.setDescricao(rs.getString(18));
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return pessoa;
    }
    
    public Pessoa[] selectAll(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        Pessoa[] arrayPessoas;
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(SELECT_SQL);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                pessoas.add(getPessoa(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayPessoas = new Pessoa[pessoas.size()];
        
        for(int i=0; i<arrayPessoas.length; i++){
            arrayPessoas[i] = pessoas.get(i);
        }
        
        conexao.closeConexao();
        return arrayPessoas;
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
    
    private Pessoa getPessoa(ResultSet rs){
        try {
            int idPessoa = rs.getInt(1);
            int tipoPessoa = rs.getInt(2);
            String nome = rs.getString(3);
            String sexo = rs.getString(4);
            java.sql.Date dataNascimento = new java.sql.Date(rs.getDate(5).getTime());
            String cpfCnpj = rs.getString(6);
            String endereco = rs.getString(7);
            String bairro = rs.getString(8);
            String complemento = rs.getString(9);
            String cep = rs.getString(10);
            String cidade = rs.getString(11);
            String uf = rs.getString(12);
            String telefone = rs.getString(13);
            String telefone2 = rs.getString(14);
            String email = rs.getString(15);
            String senha = rs.getString(16);
            String imagem = rs.getString(17);
            String descricao = rs.getString(18);
            return new Pessoa(idPessoa, tipoPessoa, nome, sexo, dataNascimento, cpfCnpj, endereco, bairro, complemento, cep, cidade, uf, telefone, telefone2, email, senha, imagem, descricao); 
        } catch (SQLException ex) {
            Logger.getLogger(DAOPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
