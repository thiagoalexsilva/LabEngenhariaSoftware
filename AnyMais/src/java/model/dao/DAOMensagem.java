/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import controller.GerenciarTiposAnimal;
import controller.GerenciarUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Mensagem;
import model.entity.TipoAnimal;
import model.entity.Usuario;

/**
 *
 * @author Gustavo
 */
public class DAOMensagem {
    
    private Conexao conexao;
    private Connection con;
    public String INSERT_SQL = "INSERT INTO MENSAGEM VALUES (?, ?, ?, ?, ?, ?, false, false);";
    public String SELECT_RECEBIDAS_SQL = "SELECT * FROM MENSAGEM WHERE IDDESTINATARIO = ? AND EXCLUIDODESTINATARIO = FALSE;";
    public String SELECT_ENVIADAS_SQL = "SELECT * FROM MENSAGEM WHERE IDREMETENTE = ? AND EXCLUIDOREMETENTE = FALSE;";
    public String UPDATE_REMETENTE_SQL = "UPDATE MENSAGEM SET EXCLUIDOREMETENTE=FALSE WHERE IDMENSAGEM=?;";
    public String UPDATE_DESTINATARIO_SQL = "UPDATE MENSAGEM SET EXCLUIDODESTINATARIO=FALSE WHERE IDMENSAGEM=?;";

    public String SELECT_MAX_ID_SQL = "SELECT COALESCE(MAX(IDMENSAGEM), 0)+1 FROM MENSAGEM;";
       
    public DAOMensagem(){
        conexao = new Conexao();
    }
    
    private int nextId(){
        
        con = conexao.openConexao();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(SELECT_MAX_ID_SQL);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            return id;
                        
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexao.closeConexao();
        return 0;
    }
    
    public boolean insert(Mensagem mensagem){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(INSERT_SQL);
            stmt.setInt(1, nextId());
            stmt.setString(2, mensagem.getAssunto());
            stmt.setString(3, mensagem.getMensagem());
            stmt.setInt(4, mensagem.getRemetente().getIdPessoa());
            stmt.setInt(5, mensagem.getDestinatario().getIdPessoa());
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    public Mensagem[] selectAllReceived(int idDestinatario){
        
        ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
        Mensagem[] arrayMensagens;
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(SELECT_RECEBIDAS_SQL);
            stmt.setInt(1, idDestinatario);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                mensagens.add(getMensagem(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayMensagens = new Mensagem[mensagens.size()];
        
        for(int i=0; i<arrayMensagens.length; i++){
            arrayMensagens[i] = mensagens.get(i);
        }
        
        conexao.closeConexao();
        return arrayMensagens;
    }

    public Mensagem[] selectAllSent(int idRemetente){
        
        ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
        Mensagem[] arrayMensagens;
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(SELECT_ENVIADAS_SQL);
            stmt.setInt(1, idRemetente);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                mensagens.add(getMensagem(rs));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        arrayMensagens = new Mensagem[mensagens.size()];
        
        for(int i=0; i<arrayMensagens.length; i++){
            arrayMensagens[i] = mensagens.get(i);
        }
        
        conexao.closeConexao();
        return arrayMensagens;
    }

    
    public boolean updateTo(int idMensagem){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(UPDATE_DESTINATARIO_SQL);
            stmt.setInt(1, idMensagem);
            
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    public boolean updateFrom(int idMensagem){
        
        PreparedStatement stmt;
        try {
            con = conexao.openConexao();
            stmt = con.prepareStatement(UPDATE_REMETENTE_SQL);
            stmt.setInt(1, idMensagem);
            
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        conexao.closeConexao();
        return true;
    }
    
    private Mensagem getMensagem(ResultSet rs){
        try {
            int id = rs.getInt(1);
            
            String assunto = rs.getString(2);
            String mensagem = rs.getString(3);
            
            int idRemetente = rs.getInt(4);
            Usuario remetente = GerenciarUsuarios.getInstance().selecionaUsuario(idRemetente);

            int idDestinatario = rs.getInt(5);
            Usuario destinatario = GerenciarUsuarios.getInstance().selecionaUsuario(idDestinatario);
            
            Date data = null;
            java.sql.Date date = rs.getDate(6);
            if(date != null)
                data = new Date(date.getTime());
            
            return new Mensagem(id, remetente, destinatario, assunto, mensagem, data);
        } catch (SQLException ex) {
            Logger.getLogger(DAOMensagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}
