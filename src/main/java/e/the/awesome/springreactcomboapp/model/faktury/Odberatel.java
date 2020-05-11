package e.the.awesome.springreactcomboapp.model.faktury;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "odberatel")
public class Odberatel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(length = 200, nullable = false)
    private String firma;

    @Column(length = 8)
    private String ic;

    @Column(length = 12)
    private String dic;

    @Column(length = 10, nullable = false)
    private String psc;

    @Column(length = 150, nullable = false)
    private String mesto;

    @Column(length = 100, nullable = false)
    private String ulice;

    @Column(length = 25, nullable = false)
    private String cisloPopisne;


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

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
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
