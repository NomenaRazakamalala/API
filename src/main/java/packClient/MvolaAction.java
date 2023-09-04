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
public class MvolaAction {
    private String id;
    private double montant;
    private double frais;
    private String type;
    private int etat;
    private Timestamp date_action;
    private String id_client;
    ///CRUD postgres
     public static MvolaAction findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<MvolaAction> clients =  GDao.find(MvolaAction.class,"Mvola_action",where);
        if(!clients.isEmpty())return clients.get(0);
        return null;
    }
    
    public static List<Client> findAll() throws Exception{
        return GDao.find(Client.class,"Mvola_action");
    }
    public void update() throws Exception{
        GDao.update(this, "Mvola_action");
    }
    public void insert() throws Exception{
        GDao.insert(this, "Mvola_action");
    }
    ///CRUD mongo
    /*
    public void insert() throws Exception{
        GDao.insert(this, "Mvola_action");
    }
    public static MvolaAction findById(String valeur) throws Exception{
        //6051f2cfe61b957bb1900d45
        return GDao.findByIdMongo(MvolaAction.class, "Mvola_action", valeur);
    }
    public static List<MvolaAction> findAll() throws Exception{
        return GDao.findAllMongo(MvolaAction.class, "Mvola_action");
    }
    
    *//*public void update() throws Exception{
        GDao.insert(this, "Mvola_action");
    }*/
    
///////////////////////////////////////////

    public MvolaAction() {
    }
    
    
    public MvolaAction(String id, double montant, double frais, String type, int etat, Timestamp date_action, String id_client) {
        this.id = id;
        this.setMontant(montant);
        this.setFrais(frais);
        this.setType(type);
        this.setEtat(etat);
        this.setDate_action(date_action);
        this.setId_client(id_client);
    }
    

    
    public MvolaAction(double montant, double frais, String type, int etat, Timestamp date_action, String id_client) {
        this.setMontant(montant);
        this.setFrais(frais);
        this.setType(type);
        this.setEtat(etat);
        this.setDate_action(date_action);
        this.setId_client(id_client);
    }
    
    public MvolaAction(String montant, String frais, String type, String etat, String date_action, String time_action, String id_client) throws Exception {
        this.setMontant(montant);
        this.setFrais(frais);
        this.setType(type);
        this.setEtat(etat);
        this.setDate_action(date_action, time_action);
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

    public void setMontant(double montant) {
        this.montant = montant;
    }
    public void setMontant(String montant){
        this.setMontant(new Double(montant));
    }
    
    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }
    public void setFrais(String frais){
        this.setFrais(new Double(frais));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEtat() {
        return etat;
    }
    public void setEtat(String etat){
        setEtat(new Integer(etat));
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Timestamp getDate_action() {
        return date_action;
    }
    public void setDate_action(String date_action, String time_action) throws Exception{
        setDate_action(Functions.toTimestamp(date_action, time_action));
    }
    public void setDate_action(Timestamp date_action) {
        this.date_action = date_action;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }
    
    
    
}
