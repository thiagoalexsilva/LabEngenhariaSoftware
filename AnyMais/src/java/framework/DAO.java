/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gustavo
 */
public final class DAO<T> {
    // T deve ser uma Entity do sistema
    
    private String table;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;
    
    public String INSERT_SQL = "INSERT INTO ? VALUES (?);";
    public String SELECT_SQL = "SELECT * FROM ?;";
    public String UPDATE_SQL = "UPDATE ? SET ?=?;";
    public String DELETE_SQL = "DELETE FROM ? WHERE ?=?;";
        
    public DAO(String table){
        setTable(table);
    }
    
    public void setTable(String table){
        this.table = table;
        entityManagerFactory = Persistence.createEntityManagerFactory(table);
        em = entityManagerFactory.createEntityManager();
    }
    
    public boolean insert(T t){
        
        try{
            em.persist(t);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    public boolean update(T t){
        
        try{
            em.refresh(t);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    public T[] selectAll(){

        try{
            return (T[]) em.createQuery(SELECT_SQL).getResultList().toArray();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }
    
    public boolean delete(T t){
        
        try{
            em.remove(t);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    
}
