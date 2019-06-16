package dataimport.model;

import java.util.HashMap;
import java.util.Map;

public class Universitaet {
    private Map<String, Student> studenten;
    private Map<String, Mitarbeiter> mitarbeiter;
    private Map<String, Veranstaltung> veranstaltungen;
    private Map<String, Klausur> klausuren;
    private Map<String, Raum> raeume;
    private Map<String, Studiengang> studiengaenge;

    public Universitaet() {
        this.studenten = new HashMap<>();
        this.mitarbeiter = new HashMap<>();
        this.veranstaltungen = new HashMap<>();
        this.klausuren = new HashMap<>();
        this.raeume = new HashMap<>();
        this.studiengaenge = new HashMap<>();
    }

    public Map<String, Student> getStudenten() {
        return studenten;
    }

    public Map<String, Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public Map<String, Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public Map<String, Klausur> getKlausuren() {
        return klausuren;
    }

    public Map<String, Raum> getRaeume() { return raeume; }

    public void setStudenten(Map<String, Student> studenten) {
        this.studenten = studenten;
    }

    public void setMitarbeiter(Map<String, Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public void setVeranstaltungen(Map<String, Veranstaltung> veranstaltungen) {
        this.veranstaltungen = veranstaltungen;
    }

    public void setKlausuren(Map<String, Klausur> klausuren) {
        this.klausuren = klausuren;
    }

    public void setRaeume(Map<String, Raum> raeume) {
        this.raeume = raeume;
    }

    public void setStudiengaenge(Map<String, Studiengang> studiengaenge) {
        this.studiengaenge = studiengaenge;
    }

    public Map<String, Studiengang> getStudiengaenge() {
        return studiengaenge;
    }


}
