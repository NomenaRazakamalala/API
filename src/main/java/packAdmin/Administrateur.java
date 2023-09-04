/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packAdmin;

import dao.GDao;
import java.util.List;

/**
 *
 * @author rakot
 */
public class Administrateur {
    private String id;
    private String nom;
    private String mdp;
///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Administrateur");
    }
    
    public List<Administrateur>findById()throws Exception
    {
        String where="id="+this.getId();
        return GDao.find(Administrateur.class,"Administrateur",where);
    }
    
    public List<Administrateur> findAll() throws Exception{
        return GDao.find(Administrateur.class,"Administrateur");
    }
    
//

    public Administrateur() {
    }
    
    
    
    public Administrateur(String id, String nom, String mdp) {
        this.setId(id);
        this.setNom(nom);
        this.setMdp(mdp);
    }
    
    public Administrateur(String nom, String mdp) {
        this.setNom(nom);
        this.setMdp(mdp);
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
}
