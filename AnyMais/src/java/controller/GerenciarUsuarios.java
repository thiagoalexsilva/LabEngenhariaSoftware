/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import model.dao.DAOUsuario;
import model.entity.Animal;
import model.entity.Conta;
import model.entity.Pessoa;
import model.entity.Usuario;

/**
 *
 * @author ThiagoAlexandre
 */
public class GerenciarUsuarios {
   private static GerenciarUsuarios instance;
    private DAOUsuario daoUsuario;
    
    private GerenciarUsuarios(){
        daoUsuario = new DAOUsuario();
    }

    public static GerenciarUsuarios getInstance(){
        if(instance == null)
            instance = new GerenciarUsuarios();
        return instance;
    }
        
    /*public Pessoa selecionarCliente(String email){
        return daoUsuario.select(email);
    }*/
    
    public Usuario[] selecionaUsuarios(){
        return daoUsuario.selectAll();
    }
    
    public Usuario[] selecionaClientes(){
        Usuario[] usuarios = daoUsuario.selectAll();
        ArrayList<Usuario> clientes = new ArrayList<Usuario>();
        
        for(Usuario usuario : usuarios){
            if(usuario.getTipo() == 1)
                clientes.add(usuario);
        }
        
        return clientes.toArray(new Usuario[clientes.size()]);
    }

    public Usuario[] selecionaPetShops(){
        Usuario[] usuarios = daoUsuario.selectAll();
        ArrayList<Usuario> clientes = new ArrayList<Usuario>();
        
        for(Usuario usuario : usuarios){
            if(usuario.getTipo() == 2)
                clientes.add(usuario);
        }
        
        return clientes.toArray(new Usuario[clientes.size()]);
    }

    
    public Usuario selecionaUsuario(int idPessoa){
        return daoUsuario.select(idPessoa);
    }
    
    public Usuario selecionarUsuarioLogin(String email, String senha){
        Usuario[] todosUsuarios = daoUsuario.selectAll();
        
        for(Usuario usuario : todosUsuarios){
            if(usuario.getConta().equals(new Conta(email, senha))){
                return usuario;
            }
        } 
        
        return null;
    }
    
    public boolean adicionarUsuario(Usuario usuario){
        return daoUsuario.insert(usuario);
    }
    
    public boolean atualizarUsuario(Usuario usuario){
        return daoUsuario.update(usuario);
    }
    
    public boolean excluirUsuario(int id){
        return daoUsuario.delete(id);
    }
    
    public boolean encerrarCliente(int id){
        boolean sucesso = true;
        Animal[] animais = GerenciarAnimais.getInstance().selecionaAnimais(id);
        for(Animal a : animais){
            sucesso = GerenciarAnimais.getInstance().excluirAnimais(a.getIdAnimal()) && sucesso;
        }
        
        sucesso = excluirUsuario(id) && sucesso;
        
        return sucesso;
    }
    
}
