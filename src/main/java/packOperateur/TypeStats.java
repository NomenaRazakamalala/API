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
public class TypeStats {
    private String id;
    private String type;
    private String nom;
    private double montant;
    private String id_operateur;
    
    public void insert() throws Exception{
        GDao.insert(this, "alltypestats");
    }
    
    public static TypeStats findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<TypeStats> typeStats = GDao.find(TypeStats.class,"alltypestats",where);
        if(!typeStats.isEmpty())return typeStats.get(0);
        return null;
    }
    public static List<TypeStats> findAll(String idOperateur)throws Exception
    {
        String where= "idOperateur='"+idOperateur+"'";
        List<TypeStats> typeStats = GDao.find(TypeStats.class,"alltypestats",where);
        return typeStats;
    }
    public static List<TypeStats> findAll() throws Exception{
        return GDao.find(TypeStats.class,"alltypestats");
    }
    public void update() throws Exception{
        GDao.update(this, "type_credit");
    }
    
    public TypeStats() {
    }

    public TypeStats(String id, String type, String nom, double montant, String id_operateur) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.montant = montant;
        this.id_operateur = id_operateur;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getId_operateur() {
        return id_operateur;
    }

    public void setId_operateur(String id_operateur) {
        this.id_operateur = id_operateur;
    }
    
}
