/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packClient;

import dao.GDao;
import java.sql.Timestamp;
import java.util.List;
import packOutils.Functions;

/**
 *
 * @author rakot
 */
public class AchatOffre {
    private String id;
    private double montant;
    private Timestamp date_achat;
    private String id_offre;
    private String id_client;

    
    ///CRUD
    public void insert() throws Exception{
        GDao.insertMongo(this, "Achat_offre");
    }
    
    public static AchatOffre findById(String id)throws Exception
    {
        return GDao.findByIdMongo(AchatOffre.class, "Achat_offre", id);
    }
    public static List<AchatOffre> findAll() throws Exception{
        return GDao.findAllMongo(AchatOffre.class,"Achat_offre");
    }
    
    //////////////////////////

    public AchatOffre() {
    }
    
    
    public AchatOffre(String id, double montant, Timestamp date_achat, String id_offre, String id_client) {
        this.setId(id);
        this.setMontant(montant);
        this.setDate_achat(date_achat);
        this.setId_offre(id_offre);
        this.setId_client(id_client);
    }
    public AchatOffre(String montant, String date_achat, String time_achat, String id_offre, String id_client) throws Exception {
        this.setMontant(montant);
        this.setDate_achat(date_achat, time_achat);
        this.setId_offre(id_offre);
        this.setId_client(id_client);
    }
    
    public AchatOffre(double montant, Timestamp date_achat, String id_offre, String id_client) {
        this.setMontant(montant);
        this.setDate_achat(date_achat);
        this.setId_offre(id_offre);
        this.setId_client(id_client);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(String montant){
        this.setMontant(new Double(montant));
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }


    public Timestamp getDate_achat() {
        return date_achat;
    }
    public void setDate_achat(String date_achat, String time_achat) throws Exception{
        setDate_achat(Functions.toTimestamp(date_achat, time_achat));
    }
    public void setDate_achat(Timestamp date_achat) {
        this.date_achat = date_achat;
    }

    public String getId_offre() {
        return id_offre;
    }

    public void setId_offre(String id_offre) {
        this.id_offre = id_offre;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }
    
    
    
    
}
