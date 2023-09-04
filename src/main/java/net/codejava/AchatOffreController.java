/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import dao.GDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import packClient.AchatOffre;
import packOutils.Functions;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class AchatOffreController {
    @GetMapping("/achats_offre")
    public SortieWS findAll(HttpServletRequest request){
        SortieWS sortie = new SortieWS();
        Object data = null;
        int status = 200;
        String message = "success";
        try{
            data = GDao.findByMongo(AchatOffre.class, "achat_offre", request);
            sortie.setData(data);
        }
        catch(Exception exception){
            status = 400;
            message = exception.getMessage();
        }
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setTime(Functions.timeActuel());
        sortie.getMeta().setMessage(message);
        return sortie;
    }
    @PostMapping("/achats_offre")
    public SortieWS insert(HttpServletRequest request){
        
        int status = 200;
        String message = "success";
        String montant = request.getParameter("montant");
        String date_achat = request.getParameter("date_achat");
        String time_achat = request.getParameter("time_achat");
        String id_offre = request.getParameter("id_offre");
        String id_client = request.getParameter("id_client");
        try{
            //AchatOffre achatOffre =  new AchatOffre
            //private String id;private double montant;private Timestamp date_achat;private String id_offre;private String id_client;
            AchatOffre achatOffre = new AchatOffre(montant, date_achat, time_achat, id_offre, id_client);
            achatOffre.insert();
        }
        catch(Exception exception){
            status = 400;
            message = "Insertion echouee " + exception.getMessage();
        }
        SortieWS sortie = new SortieWS();
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setTime(Functions.timeActuel());
        sortie.getMeta().setMessage(message);
        return sortie;
    }
}
