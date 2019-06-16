package dataimport.model;

public class Raum {
    Integer id;
    Integer mitarbeiterId;
    String bezeichnung;

    public Raum(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
