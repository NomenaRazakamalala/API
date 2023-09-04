/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rakot
 */
@RestController
public class WebController {
    @RequestMapping("/test")
    public String monTest(){
        return "index";
    }
    
    @RequestMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("message", "monMessage");
        return "hello";
    }
}




