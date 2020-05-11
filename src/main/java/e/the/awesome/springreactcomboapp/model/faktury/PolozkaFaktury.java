package e.the.awesome.springreactcomboapp.model.faktury;

import e.the.awesome.springreactcomboapp.model.faktury.Faktura;

import javax.persistence.*;

@Entity
@Table(name = "polozka_faktury")
public class PolozkaFaktury {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(length = 10, nullable = false)
    private String popis;

    @Column
    private double cenaZaJednotku;

    @Column(length = 10)
    private String jednotka;

    @Column
    private double mnozstvi;

    @Column(nullable = false)
    private double cenaCelkem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="faktura_id", nullable=false)
    private Faktura faktura;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public double getCenaZaJednotku() {
        return cenaZaJednotku;
    }

    public void setCenaZaJednotku(double cenaZaJednotku) {
        this.cenaZaJednotku = cenaZaJednotku;
    }

    public String getJednotka() {
        return jednotka;
    }

    public void setJednotka(String jednotka) {
        this.jednotka = jednotka;
    }

    public double getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(double mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public double getCenaCelkem() {
        return cenaCelkem;
    }

    public void setCenaCelkem(double cenaCelkem) {
        this.cenaCelkem = cenaCelkem;
    }

    public Faktura getFaktura() {
        return faktura;
    }

    public void setFaktura(Faktura faktura) {
        this.faktura = faktura;
    }
}
