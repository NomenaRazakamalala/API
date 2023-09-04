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
public class CoutAppelOperateur {
    private String id;
    private double unite_appel;
    private double cout_interieur;
    private double cout_exterieur;
    private String id_operateur;
///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Cout_appel_operateur");
    }
    
    public List<CoutAppelOperateur>findById()throws Exception
    {
        String where="id="+this.getId();
        return GDao.find(CoutAppelOperateur.class,"Cout_appel_operateur",where);
    }
    
    public List<CoutAppelOperateur> findAll() throws Exception{
        return GDao.find(CoutAppelOperateur.class,"Cout_appel_operateur");
    }
    
///////////////////////////////////////////
/////////////////////////////////////////////

    public CoutAppelOperateur() {
    }

    public CoutAppelOperateur(String id, double unite_appel, double cout_interieur, double cout_exterieur, String id_operateur) {
        this.setId(id);
        this.setUnite_appel(unite_appel);
        this.setCout_interieur(cout_interieur);
        this.setCout_exterieur(cout_exterieur);
        this.setId_operateur(id_operateur);
    }
    
    
    public CoutAppelOperateur(double unite_appel, double cout_interieur, double cout_exterieur, String id_operateur) {
        this.setUnite_appel(unite_appel);
        this.setCout_interieur(cout_interieur);
        this.setCout_exterieur(cout_exterieur);
        this.setId_operateur(id_operateur);
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public double getUnite_appel() {
        return unite_appel;
    }

    public void setUnite_appel(String unite_appel) throws Exception {
        try{
            setUnite_appel(new Double(unite_appel));
        }
        catch(NumberFormatException numberE){
            throw new Exception("unite appel, chiffre non valide");
        }
    }
    
    public void setUnite_appel(double unite_appel) {
        this.unite_appel = unite_appel;
    }

    public double getCout_interieur() {
        return cout_interieur;
    }
    public void setCout_interieur(String unite_appel) throws Exception {
        try{
            setCout_interieur(new Double(unite_appel));
        }
        catch(NumberFormatException numberE){
            throw new Exception("cout meme operateur, chiffre non valide");
        }
    }

    public void setCout_interieur(double cout_interieur) {
        this.cout_interieur = cout_interieur;
    }

    public double getCout_exterieur() {
        return cout_exterieur;
    }

    public void setCut_exterieur(String unite_appel) throws Exception {
        try{
            setCout_exterieur(new Double(unite_appel));
        }
        catch(NumberFormatException numberE){
            throw new Exception("cout operateurs differents, chiffre non valide");
        }
    }
    
    public void setCout_exterieur(double cout_exterieur) {
        this.cout_exterieur = cout_exterieur;
    }

    public String getId_operateur() {
        return id_operateur;
    }

    public void setId_operateur(String id_operateur) {
        this.id_operateur = id_operateur;
    }
    
    
}
