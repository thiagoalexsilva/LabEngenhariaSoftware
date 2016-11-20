/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.dao.DAOMensagem;
import model.dao.DAORaca;
import model.entity.Mensagem;

/**
 *
 * @author Gustavo
 */
public class GerenciarMensagens {

    private static GerenciarMensagens instance;
    private DAOMensagem daoMensagem;

    private GerenciarMensagens() {
        daoMensagem = new DAOMensagem();
    }

    public static GerenciarMensagens getInstance() {
        if (instance == null) {
            instance = new GerenciarMensagens();
        }
        return instance;
    }

    public Mensagem[] selecionaMensagensRecebidas(int idRemetente) {
        return daoMensagem.selectAllReceived(idRemetente);
    }

    public Mensagem[] selecionaMensagensEnviadas(int idDestinatario) {
        return daoMensagem.selectAllSent(idDestinatario);
    }

    public Mensagem[] selecionaMensagensRecebidasComFiltro(int idRemetente, String texto) {
        Mensagem[] mensagens = selecionaMensagensRecebidas(idRemetente);
        
        ArrayList<Mensagem> mensagensFiltro = new ArrayList<Mensagem>();
        
        for(Mensagem m : mensagens){
            if(m.getAssunto().contains(texto) || m.getMensagem().contains(texto)
                || m.getRemetente().getPessoa().getNome().contains(texto)
                || m.getRemetente().getConta().getEmail().contains(texto)
                || texto.isEmpty()
                ){
                mensagensFiltro.add(m);
            }
        }
        
        return mensagensFiltro.toArray(new Mensagem[mensagensFiltro.size()]);
    }

    public Mensagem[] selecionaMensagensEnviadasComFiltro(int idDestinatario, String texto) {
        Mensagem[] mensagens = selecionaMensagensRecebidas(idDestinatario);
        
        ArrayList<Mensagem> mensagensFiltro = new ArrayList<Mensagem>();
        
        for(Mensagem m : mensagens){
            if(m.getAssunto().contains(texto) || m.getMensagem().contains(texto)
                || m.getRemetente().getPessoa().getNome().contains(texto)
                || m.getRemetente().getConta().getEmail().contains(texto)
                || texto.isEmpty()
                ){
                mensagensFiltro.add(m);
            }
        }
        
        return mensagensFiltro.toArray(new Mensagem[mensagensFiltro.size()]);
    }
    
    public boolean adicionarMensagem(Mensagem mensagem) {
        return daoMensagem.insert(mensagem);
    }

    public boolean excluirMensagemRemetente(int idMensagem) {
        return daoMensagem.updateFrom(idMensagem);
    }
    
    public boolean excluirMensagemDestinatario(int idMensagem) {
        return daoMensagem.updateTo(idMensagem);
    }


}
