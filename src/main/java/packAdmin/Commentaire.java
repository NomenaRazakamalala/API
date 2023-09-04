/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packAdmin;

import dao.GDao;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author rakot
 */
public class Commentaire {
    private String id;
    private String type_mere;
    private String id_mere;
    private String id_user;
    private String contenu;
    private Timestamp date_commentaire;   
    public void insert() throws Exception{
            GDao.insert(this, "commentaire_o");
    }
    
    public static Commentaire findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<Commentaire> abouts =  GDao.find(Commentaire.class,"commentaire_o",where);
        if(!abouts.isEmpty())return abouts.get(0);
        return null;
    }
    
    public static List<Commentaire> findAll() throws Exception{
        return GDao.find(Commentaire.class,"commentaire_o");
    }
    public void update() throws Exception{
        GDao.update(this, "commentaire_o");
    }
    
    public Commentaire(){}

    public Commentaire(String type_mere, String id_mere, String id_user, String contenu) {
        this.type_mere = type_mere;
        this.id_mere = id_mere;
        this.id_user = id_user;
        this.contenu = contenu;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType_mere() {
        return type_mere;
    }

    public void setType_mere(String type_mere) {
        this.type_mere = type_mere;
    }

    public String getId_mere() {
        return id_mere;
    }

    public void setId_mere(String id_mere) {
        this.id_mere = id_mere;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(Timestamp date_commentaire) {
        this.date_commentaire = date_commentaire;
    }
    
}
