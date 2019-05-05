package model;

import java.util.HashMap;
import java.util.Map;

public class Grundvorlesung extends Veranstaltung {
    private Integer veranstaltungId;
    private Map<String, Uebung> uebungen;
    private Map<String, Klausur> klausurMap;

    public Grundvorlesung() {
        this.uebungen = new HashMap<>();
    }
}
