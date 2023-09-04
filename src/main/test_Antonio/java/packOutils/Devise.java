/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOutils;

import dao.GDao;
import java.util.List;

/**
 *
 * @author rakot
 */
public class Devise {
    private String id;
    private String nom;


///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Devise");
    }
    
    public List<Devise>findById()throws Exception
    {
        String where="id="+this.getId();
        return GDao.find(Devise.class,"Devise",where);
    }
    
    public List<Devise> findAll() throws Exception{
        return GDao.find(Devise.class,"Devise");
    }
    
///

////////////////////////////////////////////////////////////////////////////////////
    public Devise(){}
    public Devise(String id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }
    
    public Devise(String nom) {
        this.setNom(nom);
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
