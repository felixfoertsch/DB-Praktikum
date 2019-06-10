package importmodel;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
