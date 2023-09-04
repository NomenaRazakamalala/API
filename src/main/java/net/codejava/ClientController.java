/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;
import dao.GDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packClient.Client;
import packClient.Users1;
import packOutils.Functions;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class ClientController {
    @GetMapping("/clients/{id}")
    public String findById(@PathVariable String id) throws Exception{
//        SortieWS sortie = new SortieWS();
        Object data = null;
        if(id==null)data = Client.findAll().toArray();
        else data = Client.findById(id);
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
    @GetMapping("/clients")
    public Object findWithParams(HttpServletRequest request) throws Exception{
        SortieWS sortie = new SortieWS();
        Object data = null;
        int status = 200;
        String message = "success";
        try{
            data = GDao.findBy(Users1.class, "users1", request);
            sortie.setData(data);
        }
        catch(Exception exception){
            status = 400;
            message = exception.getMessage();
        }
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setTime(Functions.timeActuel());
        sortie.getMeta().setMessage(message);
        return sortie.getData();
    }
    
   
    @PostMapping("/clients")
    public Object insertClient(HttpServletRequest request) throws Exception{  
        String nom = request.getParameter("firstName");
        String mdp = request.getParameter("password");
        int status = 200;
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
        sortie.getMeta().setStatus(status);
        sortie.getMeta().setError(error);
        return sortie;
    }
    @PutMapping("/clients")
    public String putClient(@RequestParam(value = "id") String id){
        
        String message = "success";
        try{
            Client typecredit = Client.findById(id);
            typecredit.update();
        }
        catch(Exception exception){
            message = "failed" + exception.getMessage();
        }
        return message;
    }
}
