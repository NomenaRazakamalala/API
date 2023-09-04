/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOutils;

import dao.GDao;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author rakot
 */
public class ValeurDevise {
    private String id;
    private double valeur;
    private Date date;
    private String id_devise;
    
    
    ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Valeur_devise");
    }
    
    public List<ValeurDevise>findById()throws Exception
    {
        String where="id="+this.getId();
        return GDao.find(ValeurDevise.class,"Valeur_devise",where);
    }
    
    public List<ValeurDevise> findAll() throws Exception{
        return GDao.find(ValeurDevise.class,"Valeur_devise");
    }
    
///
/////////////////////////////////////////
    public ValeurDevise(){}
    public ValeurDevise(String id, double valeur, Date date, String id_devise) {
        this.setId(id);
        this.setValeur(valeur);
        this.setDate(date);
        this.setId_devise(id_devise);
    }

    public ValeurDevise( double valeur, Date date, String id_devise) {
        this.setValeur(valeur);
        this.setDate(date);
        this.setId_devise(id_devise);
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId_devise() {
        return id_devise;
    }

    public void setId_devise(String id_devise) {
        this.id_devise = id_devise;
    }
    
    
    
}
