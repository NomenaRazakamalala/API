/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packClient;

import dao.GDao;
import java.util.List;

/**
 *
 * @author rakot
 */
public class Client {
    private String id;
    private String nom;
    private String numero;
    private String mdp;
    private String id_operateur;
    
    ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Client");
    }
    
    public static Client findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<Client> clients =  GDao.find(Client.class,"Client",where);
        if(!clients.isEmpty())return clients.get(0);
        return null;
    }
    
    public static List<Client> findAll() throws Exception{
        return GDao.find(Client.class,"Client");
    }
    public void update() throws Exception{
        GDao.update(this, "Client");
    }
///////////////////////////////////////////////////////////////
    
    public Client() {
    }

    public Client(String id, String nom, String numero, String mdp, String id_operateur) {
        this.setId(id);
        this.setNom(nom);
        this.setNumero(numero);
        this.setMdp(mdp);
        this.setId_operateur(id_operateur);
    }

    public Client(String nom, String numero, String mdp, String id_operateur) {
        this.setNom(nom);
        this.setNumero(numero);
        this.setMdp(mdp);
        this.setId_operateur(id_operateur);
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    public String getId_operateur() {
        return id_operateur;
    }

    public void setId_operateur(String id_operateur) {
        this.id_operateur = id_operateur;
    }
    
    
    
}
