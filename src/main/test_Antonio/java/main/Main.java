/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.List;
import org.bson.types.ObjectId;
import packClient.Appel;

/**
 *
 * @author rakot
 */
public class Main {
    public static void main(String[] ags) throws Exception{
        //DB db = DbConnect.getDatabaseMongo();
        String id = new ObjectId("60576c10ebb79c3e9f55736f").toString();
        Appel appel = Appel.findById(id);
        //List<Appel> appels = Appel.findAll();
        System.out.print("");
    }
}
/*





AboutOffre about = new AboutOffre(1, "jours", "appel", 30, "10");
        AboutOffre about2 = new AboutOffre(7, "jours", "appel", 60*3, "11");
        about.insert();
        about2.insert();


Operateur operateur = new Operateur("airtel", "033", "10");
        operateur.insert();

Offre offre1 = new Offre("yellow100", 100, "10");
        Offre offre2 = new Offre("facebobaka", 500, "10");
        offre1.insert();
        offre2.insert();


*/
