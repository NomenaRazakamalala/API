/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOutils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rakot
 */
public class Functions {
    public static String timeActuel(){
        return new Timestamp(Calendar.getInstance().getTimeInMillis()).toString();
    }
    
    public static List<String> getParameterNames(HttpServletRequest request){
        List<String> parametres = new ArrayList<String>();
        Enumeration<String> parameterNames = request.getParameterNames();
        String parameters = "";
        while (parameterNames.hasMoreElements()) {
                parametres.add(parameterNames.nextElement());
        }
        return parametres;
    }
    
    public static Timestamp toTimestamp(String dateString, String timeString) throws Exception{
        Date date =  stringToSqlDate(dateString);
        int[] times = stringToHour(timeString);
        Timestamp timestamp = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), times[0], times[1], 0, 0);
        return timestamp;
    }
    public static Date stringToSqlDate(String dateS)throws Exception{
        try{
        String[] dates = dateS.split("-");
        return new Date(new Integer(dates[0])-1900,new Integer(dates[1])-1 , new Integer(dates[2]));
        }
        catch(Exception exception){
            throw new Exception("La date entree n'est pas valide");
        }
    }
    public static int[] stringToHour(String heure) throws Exception{
        String[] listes = heure.split(":");
        if(listes.length<2){
            throw new Exception("Heure invalide");
        }
        int[] heures = new int[2];
        heures[0] = new Integer(listes[0]);
        heures[1] = new Integer(listes[1]);
        return heures;
    }
}
