package e.the.awesome.springreactcomboapp.model.faktury;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class FakturaIM {

    private int id;

    private String evidencniCislo;

    private Integer variabilniSymbol;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datumVystaveni;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datumUzp;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate datumSplatnosti;

    private int odberatelId;

    private List<PolozkaFakturyDto> polozkyFaktury;

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

    public int getOdberatelId() {
        return odberatelId;
    }

    public void setOdberatelId(int odberatelId) {
        this.odberatelId = odberatelId;
    }

    public List<PolozkaFakturyDto> getPolozkyFaktury() {
        return polozkyFaktury;
    }

    public void setPolozkyFaktury(List<PolozkaFakturyDto> polozkyFaktury) {
        this.polozkyFaktury = polozkyFaktury;
    }
}
