/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packOperateur.TypeCredit;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class CreditController {
    @GetMapping("/typecredits/{id}")
    public SortieWS findById(@PathVariable String id) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = TypeCredit.findAll().toArray();
        else if(id.compareToIgnoreCase("id_operateur")==0)
        {
            data=TypeCredit.findbyop("id_operateur");
        }
        else data = TypeCredit.findById(id);
        sortie.setData(data);
        return sortie;
    }
    @GetMapping("/typecredits")
    public SortieWS findTypeCredit() throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        data = TypeCredit.findAll();
        sortie.setData(data);
        return sortie;
    }

    @PostMapping("/typecredits")
    public Object insertTypeCredit(HttpServletRequest request) throws Exception{  
        String montant = request.getParameter("montant");
        String id_operateur = request.getParameter("id_operateur");
        int status = 200;
        String error = "Pas d'erreur";
        try{
            TypeCredit type = new TypeCredit(montant, id_operateur);
            type.insert();
        }
        catch(Exception exception)
        {
            status = 999;
            error = exception.getMessage();
        }
        SortieWS sortie = new SortieWS();
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setError(error);
        return sortie;
    }
    
    @PutMapping("/typecredits")
    public String putTypeCredit(@RequestParam(value = "id") String id){
        String message = "success";
        try{
            TypeCredit typecredit = TypeCredit.findById(id);
            typecredit.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}
