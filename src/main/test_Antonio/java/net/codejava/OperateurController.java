/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import java.net.http.HttpRequest;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import packOperateur.Operateur;
import packOutils.Post;
import packOutils.SortieWS;
/**
 *
 * @author rakot
 */
@RestController
public class OperateurController {
    
    @GetMapping("/operateurs/{id}")
    public SortieWS findById(@PathVariable String id) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = new Operateur().findAll().toArray();
        else data = Operateur.findById(new Integer(id));
        sortie.setData(data);
        return sortie;
    }
    @GetMapping("/operateurs")
    public SortieWS findOperateur() throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        data = new Operateur().findAll();
        sortie.setData(data);
        return sortie;
    }
    /*@GetMapping("/operateur/{id}")
    public String getId(@PathVariable String id){
        return id;
    }
    */
    @PostMapping("/operateurs")
    public Object insertOperateur(HttpServletRequest request) throws Exception{  
        String idDevise = request.getParameter("id_devise");
        String nom = request.getParameter("nom");
        String initial = request.getParameter("initial");
        int status = 200;
        String error = "Pas d'erreur";
        try{
        Operateur operateur = new Operateur(nom, initial, idDevise);
        operateur.insert();
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
    
    @PutMapping("/operateurs")
    public String putOperateur(@RequestParam(value = "id") String id){
        String message = "success";
        try{
            Operateur operateur = Operateur.findById(10);
            operateur.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
    
}
