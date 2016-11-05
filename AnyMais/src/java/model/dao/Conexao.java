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
    
    private Connection conexao;
    
    Conexao(){}
    
    public Connection openConexao(){
        try {
            if(conexao == null){
                Class.forName("com.mysql.jdbc.Driver");
                DriverManager.setLoginTimeout(20);
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/anymais","root","root");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORaca.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexao;
    }
    
    public void closeConexao(){
        try {
            conexao.close();
            conexao = null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            conexao = null;
        }
    }
}
