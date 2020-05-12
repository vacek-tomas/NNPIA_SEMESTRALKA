package e.the.awesome.springreactcomboapp.model.faktury;

public class OdberatelVM {
    private int id;

    private String firma;

    public OdberatelVM() {
    }

    public OdberatelVM(int id, String firma) {
        this.id = id;
        this.firma = firma;
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
}
