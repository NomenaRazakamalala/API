/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import dao.GDao;
import java.sql.Timestamp;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packAdmin.Commentaire;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class CommentaireController {
    @GetMapping("/commentaires/{id}")
    public SortieWS findById(@PathVariable String id) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = Commentaire.findAll().toArray();
        else data = Commentaire.findById(id);
        sortie.setData(data);
        return sortie;
    }
    @GetMapping("/commentaires")
    public SortieWS findCommentaire(HttpServletRequest request) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        data = GDao.findBy(Commentaire.class, "commentaire_o", request);
        sortie.setData(data);
        return sortie;
    }
    /*@GetMapping("/offre/{id}")
    public String getId(@PathVariable String id){
        return id;
    }
    */
    @PostMapping("/commentaires")
    public Object insertCommentaire(HttpServletRequest request) throws Exception{ 
        String type_mere = request.getParameter("type_mere");
        String id_mere = request.getParameter("id_mere");
        String id_user = request.getParameter("id_user");
        String contenu = request.getParameter("contenu");
        int status = 200;
        String error = "Pas d'erreur";
        try{
            Commentaire type = new Commentaire(type_mere, id_mere, id_user, contenu);
            Instant currentTimestamp = Instant.now();
            type.setDate_commentaire(new Timestamp(currentTimestamp.toEpochMilli()));
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
    
    @PutMapping("/commentaires")
    public String putCommentaire(@RequestParam(value = "id") String id){
        String message = "success";
        try{
            Commentaire offre = Commentaire.findById(id);
            offre.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}
