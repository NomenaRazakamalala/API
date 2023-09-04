/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOperateur;

import dao.GDao;
import java.util.List;

/**
 *
 * @author rakot
 */
public class Operateur {
    private String id;
    private String nom;
    private String initial;
    private String id_devise;
    
       ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Operateur");
    }

    public static Operateur findById(int id)throws Exception
    {
        String where="id='"+id+"'";
        List<Operateur> operateurs =  GDao.find(Operateur.class,"Operateur",where);
        if(!operateurs.isEmpty()){
            return operateurs.get(0);
        }
        return null;
    }
    
    public List<Operateur> findAll() throws Exception{
        return GDao.find(Operateur.class,"Operateur");
    }
    public void update() throws Exception{
        GDao.update(this, "operateur");
    }
///
    
///////////////////////////////////////////////////////////

    public Operateur() {
    }

    public Operateur(String id, String nom, String initial, String id_devise) {
        this.setId(id);
        this.setNom(nom);
        this.setInitial(initial);
        this.setId_devise(id_devise);
    }
    
    public Operateur(String nom, String initial, String id_devise) {
        this.setNom(nom);
        this.setInitial(initial);
        this.setId_devise(id_devise);
    }
    
    
    
    public void setInitial(String initial) {
        this.initial = initial;
    }
    
    public String getInitial() {
        return initial;
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

    public String getId_devise() {
        return id_devise;
    }

    public void setId_devise(String id_devise) {
        this.id_devise = id_devise;
    }
    
}
