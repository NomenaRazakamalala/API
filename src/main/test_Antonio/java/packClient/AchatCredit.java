/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packClient;

import dao.GDao;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author rakot
 */
public class AchatCredit {
    private String id;
    private String id_type_credit;
    private Timestamp date_achat;
    private String id_client;
    
        ///CRUD
    ///CRUD

    public void insert() throws Exception{
        GDao.insertMongo(this, "Achat_credit");
    }
    public static AchatCredit findById(String valeur) throws Exception{
        //6051f2cfe61b957bb1900d45
        return GDao.findByIdMongo(AchatCredit.class, "Achat_credit", valeur);
    }
    public static List<AchatCredit> findAll() throws Exception{
        return GDao.findAllMongo(AchatCredit.class, "Achat_credit");
    }
    
    
//////////////////////////////////////////////
    
    
    public AchatCredit() {
    }

    public AchatCredit(String id, String id_type_credit, Timestamp date_achat, String id_client) {
        this.setId(id);
        this.setId_type_credit(id_type_credit);
        this.setDate_achat(date_achat);
        this.setId_client(id_client);
    }
    
    public AchatCredit(String id_type_credit, Timestamp date_achat, String id_client) {
        this.setId_type_credit(id_type_credit);
        this.setDate_achat(date_achat);
        this.setId_client(id_client);
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_type_credit() {
        return id_type_credit;
    }

    public void setId_type_credit(String id_type_credit) {
        this.id_type_credit = id_type_credit;
    }

    public Timestamp getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Timestamp date_achat) {
        this.date_achat = date_achat;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }
    
    
}
