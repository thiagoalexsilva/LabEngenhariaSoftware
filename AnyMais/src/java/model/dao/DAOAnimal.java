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
import model.entity.Animal;


/**
 *
 * @author Erica
 */
public class DAOAnimal {
    private Conexao conexao;
    private Connection con;
    
    private final String INSERT_SQL = "INSERT INTO ANIMAL VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SELECT_SQL = "SELECT * FROM ANIMAL WHERE IDANIMAL=?;";
    
    private final String UPDATE_SQL = "UPDATE ANIMAL SET NOME=?, IDRACA=?, DATANASCIEMENTO=?, PESO=?, TAMANHO=?, COR=?, SEXO=?, DESCRICAO=?, IMAGEM=?"
                                    + "WHERE IDANIMAL=?;";
    
    private final String DELETE_SQL = "DELETE FROM ANIMAL WHERE IDANIMAL=?;";
    
    private final String SELECT_NEW_ID_SQL = "SELECT COALESCE(MAX(IDANIMAL), 0)+1 FROM ANIMAL;";
    

    public DAOAnimal(){
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
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return 0;
    }
    
    public boolean insert(Animal animal){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setInt(2, animal.getTipoAnimal());
            stmt.setString(3, animal.getNome());
            stmt.setInt(4, animal.getIdRaca());
            stmt.setDate(5, animal.getDataNascimento());
            stmt.setFloat(6, animal.getPeso());
            stmt.setFloat(7, animal.getTamanho());
            stmt.setString(8, animal.getCor());
            stmt.setString(9, animal.getSexo());
            stmt.setString(10, animal.getDescricao());
            stmt.setString(11, animal.getImagem());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    }
    
    public boolean update(Animal animal){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(UPDATE_SQL);
            
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getIdRaca());
            stmt.setDate(3, animal.getDataNascimento());
            stmt.setFloat(4, animal.getPeso());
            stmt.setFloat(5, animal.getTamanho());
            stmt.setString(6, animal.getCor());
            stmt.setString(7, animal.getSexo());
            stmt.setString(8, animal.getDescricao());
            stmt.setString(9, animal.getImagem());
            stmt.setInt(10, nextId());
            
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return false;
    } 

    public Animal select(String email, String nome) {
        con = conexao.openConexao();
        Animal animal = null;
        ResultSet rs;
        try {   
            PreparedStatement stmt;
            stmt = con.prepareStatement(SELECT_SQL);
            stmt.setInt(1, animal.getIdAnimal());
            rs = stmt.executeQuery();
            
            animal.setIdAnimal(rs.getInt(1));
            animal.setTipoAnimal(rs.getInt(2));
            animal.setNome(rs.getString(3));
            animal.setIdRaca(rs.getInt(4));
            animal.setDataNascimento(rs.getDate(5));
            animal.setPeso(rs.getFloat(7));
            animal.setTamanho(rs.getFloat(8));
            animal.setCor(rs.getString(9));
            animal.setSexo(rs.getString(10));
            animal.setDescricao(rs.getString(11));
            animal.setImagem(rs.getString(12));
        } catch (SQLException ex) {
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return animal;
    }
    
}
