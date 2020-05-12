package e.the.awesome.springreactcomboapp.model.faktury;

import javax.persistence.Column;

public class OdberatelDto {

    private int id;

    private String firma;

    private String ic;

    private String psc;

    private String mesto;

    private String ulice;

    private String cisloPopisne;

    public OdberatelDto() {
    }

    public OdberatelDto(int id, String firma, String ic, String psc, String mesto, String ulice, String cisloPopisne) {
        this.id = id;
        this.firma = firma;
        this.ic = ic;
        this.psc = psc;
        this.mesto = mesto;
        this.ulice = ulice;
        this.cisloPopisne = cisloPopisne;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getCisloPopisne() {
        return cisloPopisne;
    }

    public void setCisloPopisne(String cisloPopisne) {
        this.cisloPopisne = cisloPopisne;
    }
}
