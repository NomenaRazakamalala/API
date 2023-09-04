/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/test2")
    public String retour(HttpServletRequest request){
        Enumeration<String> parameterNames = request.getParameterNames();
        String parameters = "";
        while (parameterNames.hasMoreElements()) {
                parameters+=parameterNames.nextElement();
        }
        return parameters;
    }
    @RequestMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("message", "monMessage");
        return "hello";
    }
}




