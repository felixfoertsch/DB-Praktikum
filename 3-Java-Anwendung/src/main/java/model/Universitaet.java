package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Universitaet {
    private Map<String, Student> studenten;
    private Map<String, Mitarbeiter> mitarbeiter;
    private Map<String, Veranstaltung> veranstaltungen;
    private Map<String, Klausur> klausuren;

    public Universitaet() {
        this.studenten = new HashMap<>();
        this.mitarbeiter = new HashMap<>();
        this.veranstaltungen = new HashMap<>();
        this.klausuren = new HashMap<>();
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

}
