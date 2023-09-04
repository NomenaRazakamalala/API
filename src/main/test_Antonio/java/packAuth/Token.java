/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packAuth;

import dao.GDao;
import java.util.List;

/**
 *
 * @author rakot
 */
public class Token {
    private String id;
    private String token;
    private String id_client;
    
    ///CRUD
    public void insert() throws Exception{
        GDao.insert(this, "Token");
    }
    
    public List<Token>findById()throws Exception
    {
        String where="id="+this.getId();
        return GDao.find(Token.class,"Token",where);
    }
    
    public List<Token> findAll() throws Exception{
        return GDao.find(Token.class,"Token");
    }
    
///

    public Token() {
    }
    
    
    
    public Token(String id, String token, String id_client) {
        this.setId(id);
        this.setToken(token);
        this.setId_client(id_client);
    }
    public Token(String token, String id_client) {
        this.setToken(token);
        this.setId_client(id_client);
    }
    
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    private void setToken(String token) {
        this.token = token;
    }

    public String getId_client() {
        return id_client;
    }

    private void setId_client(String id_client) {
        this.id_client = id_client;
    }
    
}
