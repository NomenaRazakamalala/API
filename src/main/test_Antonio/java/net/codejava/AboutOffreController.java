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
import packOperateur.AboutOffre;
import packOutils.Functions;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class AboutOffreController {
    @GetMapping("/about_offre/{id}")
    public SortieWS findById(@PathVariable String id) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = AboutOffre.findAll().toArray();
        else data = AboutOffre.findById(id);
        sortie.setData(data);
        return sortie;
    }
    @GetMapping("/about_offres")
    public SortieWS findAboutOffre() throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        data = AboutOffre.findAll();
        sortie.setData(data);
        return sortie;
    }

    @PostMapping("/about_offres")
    public Object insertAboutOffre(HttpServletRequest request) throws Exception{ 
        String duree_offre = request.getParameter("duree_offre");
        String type_duree = request.getParameter("type_duree");
        String type = request.getParameter("type");
        String valeur = request.getParameter("valeur");
        String id_offre = request.getParameter("id_offre");
        int status = 200;
        String error = "Pas d'erreur";
        try{
            AboutOffre aboutOffre = new AboutOffre(duree_offre, type_duree, type, valeur, id_offre);
            aboutOffre.insert();
        }
        catch(Exception exception)
        {
            status = 999;
            error = exception.getMessage();
        }
        SortieWS sortie = new SortieWS();
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setError(error);
        sortie.getMeta().setTime(Functions.timeActuel());
        return sortie;
    }
    
    @PutMapping("/about_offre")
    public String putAboutOffre(@RequestParam(value = "id") String id){
        String message = "success";
        try{
            AboutOffre aboutoffre = AboutOffre.findById(id);
            aboutoffre.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}
