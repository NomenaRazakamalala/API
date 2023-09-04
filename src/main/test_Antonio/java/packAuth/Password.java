/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packAuth;

/**
 *
 * @author rakot
 */
public class Password {
    private String id;
    private String id_client;

    
    
    public Password(String id, String id_client) {
        this.setId(id);
        this.setId_client(id_client);
    }
    public Password(String id_client) {
        this.setId_client(id_client);
    }
    
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getId_client() {
        return id_client;
    }

    private void setId_client(String id_client) {
        this.id_client = id_client;
    }
    
    
}
