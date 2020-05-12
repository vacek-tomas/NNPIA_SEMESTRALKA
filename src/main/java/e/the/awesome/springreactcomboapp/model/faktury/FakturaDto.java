package e.the.awesome.springreactcomboapp.model.faktury;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

public class FakturaDto {


    private int id;

    private String evidencniCislo;

    private Integer variabilniSymbol;

    private LocalDate datumVystaveni;

    private LocalDate datumUzp;

    private LocalDate datumSplatnosti;

    private String odberatelFirma;

    private double cenaCelkem;

    public FakturaDto(int id, String evidencniCislo, Integer variabilniSymbol, LocalDate datumVystaveni, LocalDate datumUzp, LocalDate datumSplatnosti, String odberatelFirma, double cenaCelkem) {
        this.id = id;
        this.evidencniCislo = evidencniCislo;
        this.variabilniSymbol = variabilniSymbol;
        this.datumVystaveni = datumVystaveni;
        this.datumUzp = datumUzp;
        this.datumSplatnosti = datumSplatnosti;
        this.odberatelFirma = odberatelFirma;
        this.cenaCelkem = cenaCelkem;
    }

    public  FakturaDto(){}

    public double getCenaCelkem() {
        return cenaCelkem;
    }

    public void setCenaCelkem(double cenaCelkem) {
        this.cenaCelkem = cenaCelkem;
    }

    public LocalDate getDatumSplatnosti() {
        return datumSplatnosti;
    }

    public void setDatumSplatnosti(LocalDate datumSplatnosti) {
        this.datumSplatnosti = datumSplatnosti;
    }

    public LocalDate getDatumUzp() {
        return datumUzp;
    }

    public void setDatumUzp(LocalDate datumUzp) {
        this.datumUzp = datumUzp;
    }

    public LocalDate getDatumVystaveni() {
        return datumVystaveni;
    }

    public void setDatumVystaveni(LocalDate datumVystaveni) {
        this.datumVystaveni = datumVystaveni;
    }

    public Integer getVariabilniSymbol() {
        return variabilniSymbol;
    }

    public void setVariabilniSymbol(Integer variabilniSymbol) {
        this.variabilniSymbol = variabilniSymbol;
    }

    public String getEvidencniCislo() {
        return evidencniCislo;
    }

    public void setEvidencniCislo(String evidencniCislo) {
        this.evidencniCislo = evidencniCislo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOdberatelFirma() {
        return odberatelFirma;
    }

    public void setOdberatelFirma(String odberatelFirma) {
        this.odberatelFirma = odberatelFirma;
    }
}
