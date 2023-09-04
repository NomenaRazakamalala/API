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
public class Offre {
    private String id;
    private String nom;
    private double montant;
    private String id_operateur;

    
   ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Offre");
    }
    
    public static Offre findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<Offre> offres =  GDao.find(Offre.class,"Offre",where);
        if(!offres.isEmpty())return offres.get(0);
        return null;
    }
    
    public static List<Offre> findAll() throws Exception{
        return GDao.find(Offre.class,"Offre");
    }
    public void update() throws Exception{
        GDao.update(this, "Offre");
    }
///
    
    public Offre() {
    }
    public Offre(String nom, String montant, String id_operateur) {
        this.setNom(nom);
        this.setMontant(montant);
        this.setId_operateur(id_operateur);
    }
    public Offre(String id, String nom, double montant, String id_operateur) {
        this.setId(id);
        this.setNom(nom);
        this.setMontant(montant);
        this.setId_operateur(id_operateur);
    }

    public Offre(String nom, double montant, String id_operateur) {
        this.setNom(nom);
        this.setMontant(montant);
        this.setId_operateur(id_operateur);
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setMontant(String montant) {
        this.setMontant(new Double(montant));
    }

    
    public String getId_operateur() {
        return id_operateur;
    }
    
    
    
    public void setId_operateur(String id_operateur) {
        this.id_operateur = id_operateur;
    }
    
    
}
