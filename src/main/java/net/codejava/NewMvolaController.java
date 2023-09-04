/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import dao.GDao;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packClient.MvolaAction;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */

@RestController
public class NewMvolaController {
    @GetMapping("/mvola_actions/{id}")
    public SortieWS findById(@PathVariable String id) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = MvolaAction.findAll().toArray();
        else data = MvolaAction.findById(id);
        sortie.setData(data);
        return sortie;
    }
    
    @GetMapping("/mvola_actions")
    public SortieWS findNew(HttpServletRequest request) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        data = GDao.findBy(MvolaAction.class, "mvola_action", request);
        sortie.setData(data);
        return sortie;
    }

    @PostMapping("/mvola_actions")
    public Object insertNew(HttpServletRequest request) throws Exception{  
        String montant = request.getParameter("montant");
        String frais = request.getParameter("frais");
        String type = request.getParameter("type");
        String etat = request.getParameter("etat");
        String date_action = request.getParameter("date_action");
        String time_action = request.getParameter("time_action");
        String id_client = request.getParameter("id_client");
        int status = 200;
        String error = "Pas d'erreur";
        try{
            MvolaAction mvolaAction = new MvolaAction(montant, frais, type, etat, date_action, time_action, id_client);
            mvolaAction.insert();
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
    
    @PutMapping("/mvola_actions")
    public String putNew(@RequestParam(value = "id") String id){
        String message = "mbola tsy misy";
        try{
            //MvolaAction mvola_action = MvolaAction.findById(id);
            //mvola_action.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}


