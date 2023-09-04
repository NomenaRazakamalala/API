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
import packClient.AchatCredit;
import packOutils.Functions;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class AchatCreditController {
    @GetMapping("/achats_credit")
    public SortieWS findAll(HttpServletRequest request){
        SortieWS sortie = new SortieWS();
        Object data = null;
        int status = 200;
        String message = "success";
        try{
            data = GDao.findByMongo(AchatCredit.class, "achat_credit", request);
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
    @PostMapping("/achats_credit")
    public SortieWS insert(HttpServletRequest request){
        
        int status = 200;
        String message = "success";
        try{
            //AchatCredit achatOffre =  new AchatCredit
            //private String id;private double montant;private Timestamp date_achat;private String id_offre;private String id_client;
        String id_type_credit = request.getParameter("id_type_credit");
        String date_achat = request.getParameter("date_achat");
        String time_achat = request.getParameter("time_achat");
        String id_client = request.getParameter("id_client");
        AchatCredit achatCredit = new AchatCredit(id_type_credit, date_achat, time_achat, id_client);
        achatCredit.insert();
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
