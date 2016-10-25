/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo
 */
public class Conexao {
    
    private static Connection conexao;
    
    private Conexao(){
        getConexao();
    }
    
    public static Connection getConexao(){
        try {
            if(conexao == null){
                Class.forName("org.postgresql.Driver");
                DriverManager.setLoginTimeout(20);
                conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5433/anymais", "postgres", "postgres");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexao;
    }
    
    
}
