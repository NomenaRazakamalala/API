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
public class TypeCredit {
    private String id;
    private double montant;
    private String id_operateur;
   ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Type_credit");
    }
    
    public static List<TypeCredit> findbyop(String idop) throws Exception{
        String where="id_operateur='"+idop+"'";
        List<TypeCredit> typeCredits = GDao.find(TypeCredit.class,"Type_credit",where);
        return typeCredits;
    }
    
    public static TypeCredit findById(String id)throws Exception
    {
        String where="id='"+id+"'";
        List<TypeCredit> typeCredits = GDao.find(TypeCredit.class,"Type_credit",where);
        if(!typeCredits.isEmpty())return typeCredits.get(0);
        return null;
    }
    
    public static List<TypeCredit> findAll() throws Exception{
        return GDao.find(TypeCredit.class,"Type_credit");
    }
    public void update() throws Exception{
        GDao.update(this, "type_credit");
    }
//////////////////////////////////////////
    
    public TypeCredit() {
    }
    
    public TypeCredit(String id, double montant, String id_operateur) {
        this.setId(id);
        this.setMontant(montant);
        this.setId_operateur(id_operateur);
    }

    public TypeCredit(double montant, String id_operateur) {
        this.setMontant(montant);
        this.setId_operateur(id_operateur);
    }
    
    public TypeCredit(String montant, String id_operateur) throws Exception {
        this.setMontant(montant);
        this.setId_operateur(id_operateur);
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
    public void setMontant(String montant) throws Exception{
        try{
            this.setMontant(new Double(montant));
        }
        catch(NumberFormatException numberE){
            throw new Exception("Nombre non valide ==> " + numberE);
        }
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getId_operateur() {
        return id_operateur;
    }

    public void setId_operateur(String id_operateur) {
        this.id_operateur = id_operateur;
    }
    
}
