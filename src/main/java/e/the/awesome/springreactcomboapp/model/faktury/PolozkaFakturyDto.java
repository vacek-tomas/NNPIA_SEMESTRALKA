package e.the.awesome.springreactcomboapp.model.faktury;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PolozkaFakturyDto {
    private int id;

    private String popis;

    private Double cenaZaJednotku;

    private String jednotka;

    private Double mnozstvi;

    private double cenaCelkem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PolozkaFakturyDto(int id, String popis, Double cenaZaJednotku, String jednotka, Double mnozstvi, double cenaCelkem) {
        this.id = id;
        this.popis = popis;
        this.cenaZaJednotku = cenaZaJednotku;
        this.jednotka = jednotka;
        this.mnozstvi = mnozstvi;
        this.cenaCelkem = cenaCelkem;
    }

    public PolozkaFakturyDto() {

    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Double getCenaZaJednotku() {
        return cenaZaJednotku;
    }

    public void setCenaZaJednotku(Double cenaZaJednotku) {
        this.cenaZaJednotku = cenaZaJednotku;
    }

    public String getJednotka() {
        return jednotka;
    }

    public void setJednotka(String jednotka) {
        this.jednotka = jednotka;
    }

    public Double getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(Double mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public double getCenaCelkem() {
        return cenaCelkem;
    }

    public void setCenaCelkem(double cenaCelkem) {
        this.cenaCelkem = cenaCelkem;
    }
}
