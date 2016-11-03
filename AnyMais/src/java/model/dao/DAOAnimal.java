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
import java.sql.Date;
import java.util.ArrayList;
import model.entity.Raca;


/**
 *
 * @author Erica
 */
public class DAOAnimal {
    private Conexao conexao;
    private Connection con;
    
    private final String INSERT_SQL = "INSERT INTO ANIMAL VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SELECT_SQL = "SELECT * FROM ANIMAL;";
    
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
            stmt.setInt(4, animal.getRaca().getIdRaca());
            stmt.setDate(5, new java.sql.Date(animal.getDataNascimento().getTime()));
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
            stmt.setInt(2, animal.getRaca().getIdRaca());
            stmt.setDate(3, new java.sql.Date(animal.getDataNascimento().getTime()));
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

    public Animal[] select() {
        con = conexao.openConexao();
        ArrayList<Animal> animais = new ArrayList<Animal>();
        Animal[] arrayAnimais;
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_SQL);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            animais.add(getAnimal(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayAnimais = new Animal[animais.size()];
        
        for(int i=0; i<arrayAnimais.length; i++){
            arrayAnimais[i] = animais.get(i);
        }
        
        conexao.closeConexao();
        return arrayAnimais;
    }
    
        private Animal getAnimal(ResultSet rs){
        try {
            DAORaca daoRaca = new DAORaca();
            Raca [] racas = null;
            Raca raca = null;
            
            int idAnimal = rs.getInt(1);
            int tipoAnimal = rs.getInt(2);
            String nome = rs.getString(3);
            
            int idRaca = rs.getInt(4);
            racas = daoRaca.selectAll(); 
            for(Raca r: racas){ //Tipo [variável] : vetor
                if(r.getIdRaca() == idRaca){
                    raca = r;
                    break;
                }
            }
                    
            Date dataNascimento = rs.getDate(5);
            float peso = rs.getFloat(6);
            float tamanho = rs.getFloat(7);
            String cor = rs.getString(8);
            String sexo = rs.getString(9);
            String descricao = rs.getString(10);
            String imagem = rs.getString(11);
            return new Animal(idAnimal, tipoAnimal, nome, raca, dataNascimento, peso, tamanho, cor, sexo, descricao, imagem);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
