package model;

import java.util.Map;

public class Raum {
    private Integer id;
    private String bezeichnung;

    private Map<String, Mitarbeiter> mitarbeiterMap;

    private Map<String, Klausur> klausurMap;

    private Map<String, VeranstaltungAbhaltung> abhaltungMap;

    public Raum(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Raum() {
    }
}
