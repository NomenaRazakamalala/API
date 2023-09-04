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
public class Identitypiece1 {
    private String ID, TITLE, TYPE, NUMPIECE, COUNTRYOFISSUE,ISSUER, STATUS, ADDITIONNALNOTES;
    private int OWNER;
    private Date EXPIRATIONDATE;

    public Identitypiece1() {
    }

    public Identitypiece1(String ID, String TITLE, String TYPE, String NUMPIECE, String COUNTRYOFISSUE, String ISSUER, String STATUS, String ADDITIONNALNOTES, int OWNER, Date EXPIRATIONDATE) {
        this.ID = ID;
        this.TITLE = TITLE;
        this.TYPE = TYPE;
        this.NUMPIECE = NUMPIECE;
        this.COUNTRYOFISSUE = COUNTRYOFISSUE;
        this.ISSUER = ISSUER;
        this.STATUS = STATUS;
        this.ADDITIONNALNOTES = ADDITIONNALNOTES;
        this.OWNER = OWNER;
        this.EXPIRATIONDATE = EXPIRATIONDATE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getNUMPIECE() {
        return NUMPIECE;
    }

    public void setNUMPIECE(String NUMPIECE) {
        this.NUMPIECE = NUMPIECE;
    }

    public String getCOUNTRYOFISSUE() {
        return COUNTRYOFISSUE;
    }

    public void setCOUNTRYOFISSUE(String COUNTRYOFISSUE) {
        this.COUNTRYOFISSUE = COUNTRYOFISSUE;
    }

    public String getISSUER() {
        return ISSUER;
    }

    public void setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getADDITIONNALNOTES() {
        return ADDITIONNALNOTES;
    }

    public void setADDITIONNALNOTES(String ADDITIONNALNOTES) {
        this.ADDITIONNALNOTES = ADDITIONNALNOTES;
    }

    public int getOWNER() {
        return OWNER;
    }

    public void setOWNER(int OWNER) {
        this.OWNER = OWNER;
    }

    public Date getEXPIRATIONDATE() {
        return EXPIRATIONDATE;
    }

    public void setEXPIRATIONDATE(Date EXPIRATIONDATE) {
        this.EXPIRATIONDATE = EXPIRATIONDATE;
    }

}
