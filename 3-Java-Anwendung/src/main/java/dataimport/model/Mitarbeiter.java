package dataimport.model;

public class Mitarbeiter {
    private Integer id;
    private String vorname;
    private String nachname;
    private String email;
    private String titel;
    private Raum raum;

    public Mitarbeiter(String vorname, String nachname, String email, String titel, Raum raum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.titel = titel;
        this.raum = raum;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Raum getRaum() {
        return raum;
    }
}
