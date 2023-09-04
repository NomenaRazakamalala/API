/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import packClient.Appel;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class AppelController {
    @PostMapping("/appel")
    public SortieWS insert(HttpServletRequest request){
        String duree = request.getParameter("duree");
        String date_appel = request.getParameter("date_appel");
        String id_destinataire = request.getParameter("id_destinataire");
        String id_client = request.getParameter("id_client");
        try{
            //Appel appel =  new Appel
            
        }
        catch(Exception exception){
            
        }
        SortieWS sortie = new SortieWS();
        return sortie;
    }
}
