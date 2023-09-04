/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packOperateur.Offre;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class OffreController {
    @GetMapping("/offres/{id}")
    public SortieWS findById(@PathVariable String id) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = Offre.findAll().toArray();
        else data = Offre.findById(id);
        sortie.setData(data);
        return sortie;
    }
    @GetMapping("/offres")
    public SortieWS findOffre() throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        data = Offre.findAll();
        sortie.setData(data);
        return sortie;
    }
    /*@GetMapping("/offre/{id}")
    public String getId(@PathVariable String id){
        return id;
    }
    */
    @PostMapping("/offres")
    public Object insertOffre(HttpServletRequest request) throws Exception{ 
        String nom = request.getParameter("nom");
        String id_operateur = request.getParameter("id_operateur");
        String montant = request.getParameter("montant");
        int status = 200;
        String error = "Pas d'erreur";
        try{
            Offre type = new Offre(nom, montant, id_operateur);
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
    
    @PutMapping("/offres")
    public String putOffre(@RequestParam(value = "id") String id){
        String message = "success";
        try{
            Offre offre = Offre.findById(id);
            offre.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}
