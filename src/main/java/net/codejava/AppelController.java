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
import packClient.Appel;
import packOutils.Functions;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class AppelController {
    @GetMapping("/appels")
    public SortieWS findAll(HttpServletRequest request){
        SortieWS sortie = new SortieWS();
        Object data = null;
        int status = 200;
        String message = "success";
        try{
            data = GDao.findByMongo(Appel.class, "appel", request);
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
    @PostMapping("/appels")
    public SortieWS insert(HttpServletRequest request){
        String duree = request.getParameter("duree");
        String date_appel = request.getParameter("date_appel");
        String time_appel = request.getParameter("time_appel");
        String id_destinataire = request.getParameter("id_destinataire");
        String id_client = request.getParameter("id_client");
        int status = 200;
        String message = "success";
        try{
            //Appel appel =  new Appel
            Appel appel = new Appel(duree, date_appel, time_appel, id_destinataire, id_client);
            appel.insert();
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
