package model;

import java.util.Map;

public class Mitarbeiter {
    private Integer id;

    private String vorname;
    private String nachname;
    private String email;

    private Raum raum;

    //?
    private String titel;

    private Map<String, Klausur> klausurMap;

    public Mitarbeiter(String vorname, String nachname, String email, String titel, Raum raum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.titel = titel;
        this.raum = raum;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmail() {
        return email;
    }
}
