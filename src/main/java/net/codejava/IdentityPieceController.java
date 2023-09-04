/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.codejava;

import dao.GDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packClient.Client;
import packClient.Identitypiece1;
import packOutils.Functions;
import packOutils.SortieWS;

/**
 *
 * @author 7john
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class IdentityPieceController {

    @GetMapping("/identity/{id}")
    public String findById(@PathVariable String id) throws Exception {
//        SortieWS sortie = new SortieWS();
        Object data = null;
        if (id == null) {
            data = Client.findAll().toArray();
        } else {
            data = Client.findById(id);
        }
//        sortie.setData(data);
        return data.toString();
    }

    /*@GetMapping("/clients")
    public SortieWS findClients(HttpServletRequest request) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        int status = 200;
        String message = "success";
        try{
            data = Client.findAll();
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
     */
    @GetMapping("/identity")
    public Object findWithParams(HttpServletRequest request) throws Exception {
        SortieWS sortie = new SortieWS();
        Object data = null;
        int status = 200;
        String message = "success";
        try {
            data = GDao.findBy(Identitypiece1.class, "IDENTITYPIECE1", request);
            sortie.setData(data);
        } catch (Exception exception) {
            status = 400;
            message = exception.getMessage();
        }
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setTime(Functions.timeActuel());
        sortie.getMeta().setMessage(message);
        return sortie.getData();
    }

    @PostMapping("/identity")
    public Object insertClient(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String numpiece = request.getParameter("numpiece");
        String contryofissue = request.getParameter("countryofissue");
        String issuer = request.getParameter("issuer");
        String status = request.getParameter("status");
        String additionnalnotes = request.getParameter("additionnalnotes");
        String owner = request.getParameter("owner");
        String expirationdate = request.getParameter("expirationdate");
        int status1 = 200;
        String error = "Pas d'erreur";
//        try{
//            Client client = new Client(nom, numero, id_operateur, mdp);
//            client.insert();
//        }
//        catch(Exception exception)
//        {
//            status = 999;
//            error = exception.getMessage();
//        }
        SortieWS sortie = new SortieWS();
        sortie.getMeta().setStatus(status1);
        sortie.getMeta().setError(error);
        return sortie;
    }

    @PutMapping("/identity")
    public String putClient(@RequestParam(value = "id") String id) {

        String message = "success";
        try {
            Client typecredit = Client.findById(id);
            typecredit.update();
        } catch (Exception exception) {
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}
