/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import packClient.Appel;
import packClient.Client;
import packOperateur.Operateur;
/**
 *
 * @author rakot
 */
@RestController
public class webServiceController {
    private static final String template = "Hello , %s";
    private static AtomicLong counter = new AtomicLong();
    
    @GetMapping("/rest")
    public String takeRest(){
        return "hello my girl";
    }
    
    @PostMapping("/rest")
    public String postRest(){
        return "here in the post";
    }
    /*
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World")String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }
    */
}
