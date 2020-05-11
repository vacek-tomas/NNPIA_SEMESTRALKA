package e.the.awesome.springreactcomboapp.model.faktury;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "faktura")
public class Faktura {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    private String evidencniCislo;

    @Column
    private int variabilniSymbol;

    @Column(nullable = false)
    private LocalDate datumVystaveni;

    @Column
    private LocalDate datumUzp;

    @Column(nullable = false)
    private LocalDate datumSplatnosti;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Odberatel odberatel;

    @OneToMany(mappedBy="faktura", cascade = CascadeType.REMOVE)
    private Set<PolozkaFaktury> polozkyFaktury;

    @Column(nullable = false)
    private double cenaCelkem;

    public double getCenaCelkem() {
        return cenaCelkem;
    }

    public void setCenaCelkem(double cenaCelkem) {
        this.cenaCelkem = cenaCelkem;
    }

    public Set<PolozkaFaktury> getPolozkyFaktury() {
        return polozkyFaktury;
    }

    public void setPolozkyFaktury(Set<PolozkaFaktury> polozkyFaktury) {
        this.polozkyFaktury = polozkyFaktury;
    }

    public Odberatel getOdberatel() {
        return odberatel;
    }

    public void setOdberatel(Odberatel odberatel) {
        this.odberatel = odberatel;
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

    public int getVariabilniSymbol() {
        return variabilniSymbol;
    }

    public void setVariabilniSymbol(int variabilniSymbol) {
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
}
