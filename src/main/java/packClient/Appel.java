/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packClient;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import dao.DbConnect;
import dao.GDao;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import packOutils.Functions;

/**
 *
 * @author rakot
 */
public class Appel {//MongoDB
    private String id;
    private double duree;
    private Timestamp date_appel;
    private String id_destinataire;
    private String id_client;
///CRUD

    public void insert() throws Exception{
        GDao.insertMongo(this, "appel");
    }
    public static Appel findById(String valeur) throws Exception{
        //6051f2cfe61b957bb1900d45
        return GDao.findByIdMongo(Appel.class, "appel", valeur);
    }
    public static List<Appel> findAll() throws Exception{
        return GDao.findAllMongo(Appel.class, "appel");
    }
    
///////////////////////////////////////////////////////////////////

    public Appel() {
    }
    
    public Appel(String id, double duree, Timestamp date_appel, String id_destinataire, String id_client) {
        this.setId(id);
        this.setDuree(duree);
        this.setDate_appel(date_appel);
        this.setId_destinataire(id_destinataire);
        this.setId_client(id_client);
    }
    public Appel(String duree, String date_appel, String time_appel, String id_destinataire, String id_client) throws Exception{
        setDuree(duree);
        setDate_appel(Functions.toTimestamp(date_appel, time_appel));
        setId_destinataire(id_destinataire);
        setId_client(id_client);
    }
    public Appel(double duree, Timestamp date_appel, String id_destinataire, String id_client) {
        this.setDuree(duree);
        this.setDate_appel(date_appel);
        this.setId_destinataire(id_destinataire);
        this.setId_client(id_client);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
    public void setDuree(String duree) {
        this.setDuree(new Double(duree));
    }
    public Timestamp getDate_appel() {
        return date_appel;
    }

    public void setDate_appel(Timestamp date_appel) {
        this.date_appel = date_appel;
    }

    public String getId_destinataire() {
        return id_destinataire;
    }

    public void setId_destinataire(String id_destinataire) {
        this.id_destinataire = id_destinataire;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }
    
    
    
}
