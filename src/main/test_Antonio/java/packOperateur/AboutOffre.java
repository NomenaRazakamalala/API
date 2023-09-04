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
public class AboutOffre {
    private String id;
    private int duree_offre;
    private String type_duree;
    private String type;
    private double valeur;
    private String id_offre;
    
    ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "About_offre");
    }
    
    public static AboutOffre findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<AboutOffre> abouts =  GDao.find(AboutOffre.class,"About_offre",where);
        if(!abouts.isEmpty())return abouts.get(0);
        return null;
    }
    
    public static List<AboutOffre> findAll() throws Exception{
        return GDao.find(AboutOffre.class,"About_offre");
    }
    public void update() throws Exception{
        GDao.update(this, "About_offre");
    }
///////////////////////////////////////////
/////////////////////////////////
    public AboutOffre(String id, int duree_offre, String type_duree, String type, double valeur, String id_offre) {
        this.setId(id);
        this.setDuree_offre(duree_offre);
        this.setType_duree(type_duree);
        this.setType(type);
        this.setValeur(valeur);
        this.setId_offre(id_offre);
    }

    public AboutOffre(int duree_offre, String type_duree, String type, double valeur, String id_offre) {
        this.setDuree_offre(duree_offre);
        this.setType_duree(type_duree);
        this.setType(type);
        this.setValeur(valeur);
        this.setId_offre(id_offre);
    }

    public AboutOffre(String duree_offre, String type_duree, String type, String valeur, String id_offre) throws Exception {
        this.setDuree_offre(duree_offre);
        this.setType_duree(type_duree);
        this.setType(type);
        this.setValeur(valeur);
        this.setId_offre(id_offre);
    }

    
    public AboutOffre() {
    }
    
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDuree_offre() {
        return duree_offre;
    }
    
    
    public void setDuree_offre(String duree_offre) throws Exception {
        try{
            setDuree_offre(new Integer(duree_offre));
        }
        catch(NumberFormatException numberE){
            throw new Exception("Duree offre, chiffre non valide");
        }
    }

    
    public void setDuree_offre(int duree_offre) {
        this.duree_offre = duree_offre;
    }

    public String getType_duree() {
        return type_duree;
    }

    public void setType_duree(String type_duree) {
        this.type_duree = type_duree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public double getValeur() {
        return valeur;
    }
    
public void setValeur(String valeur) throws Exception{
    try{
        setValeur(new Double(valeur));
    }
    catch(NumberFormatException numberE){
        throw new Exception("Nombre non valide");
    }
}
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String getId_offre() {
        return id_offre;
    }

    public void setId_offre(String id_offre) {
        this.id_offre = id_offre;
    }
    
}
