/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOperateur;
/**
 *
 * @author rakot
 */
public class Stats {
    private String id;
    private String nom;
    private int nb_achats;
    private int nb_clients;
    private double total_gain;
    
    
    public Stats(){}
    public Stats(String id, String nom, int nb_achats, int nb_clients, double total_gain) {
        this.setId(id);
        this.setNom(nom);
        this.setNb_achats(nb_achats);
        this.setNb_clients(nb_clients);
        this.setTotal_gain(total_gain);
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

    public int getNb_achats() {
        return nb_achats;
    }

    public void setNb_achats(int nb_achats) {
        this.nb_achats = nb_achats;
    }

    public int getNb_clients() {
        return nb_clients;
    }

    public void setNb_clients(int nb_clients) {
        this.nb_clients = nb_clients;
    }

    public double getTotal_gain() {
        return total_gain;
    }

    public void setTotal_gain(double total_gain) {
        this.total_gain = total_gain;
    }
    
    
}
