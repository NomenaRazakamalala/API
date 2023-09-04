/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packClient;

import java.sql.Date;

/**
 *
 * @author 7john
 */
public class Users1 {

    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    private String usertypeid;
    private String pieceid;

    public Users1() {
    }
    

    public Users1(String id, String firstName, String lastName, String password, Date birthDate, String phoneNumber, String email, String usertypeid, String pieceid) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.usertypeid = usertypeid;
        this.pieceid = pieceid;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(String usertypeid) {
        this.usertypeid = usertypeid;
    }

    public String getPieceid() {
        return pieceid;
    }

    public void setPieceid(String pieceid) {
        this.pieceid = pieceid;
    }

    
}
