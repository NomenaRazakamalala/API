/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import packOutils.SortieWS;

/**
 *
 * @author rakot
 */
@RestController
public class StatsController {
    @GetMapping("/stats")
    public SortieWS voirStats(){
        SortieWS sortie = new SortieWS();
        
        return sortie;
    }
}
