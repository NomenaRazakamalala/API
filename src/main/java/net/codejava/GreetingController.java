/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rakot
 */
@RestController
public class GreetingController {
    /*private static final String template = "Hello , %s";
    private static AtomicLong counter = new AtomicLong();
    
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World")String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }
*/
}
