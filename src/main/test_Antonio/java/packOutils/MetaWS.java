/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOutils;

import java.sql.Timestamp;

/**
 *
 * @author rakot
 */
public class MetaWS {
    private int status;
    private String time;
    private String error;
    private String message = "success";
    public MetaWS() {
        setTime(Functions.timeActuel());
    }
    
    public MetaWS(int status, String time, String error) {
        this.status = status;
        this.time = time;
        this.error = error;
        
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
