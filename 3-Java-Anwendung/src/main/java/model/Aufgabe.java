package model;

import java.util.HashMap;
import java.util.Map;

public class Aufgabe {
    private Integer id;
    private Integer klausurId;
    private Integer rang;
    private String klausurNr;
    private Integer aufgabenNr;
    private Double maxPunkte;
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Aufgabe(String klausurNr, String aufgabenNr, String maxPunkte) {
        this.aufgabenBearbeitungen = new HashMap<>();
        this.klausurNr = klausurNr;
        this.aufgabenNr = Integer.valueOf(aufgabenNr);
        this.maxPunkte = Double.valueOf(maxPunkte);
    }

    public Aufgabe() {
    }

    public String generateKey() {
        return klausurNr + "_" + aufgabenNr;
    }

    public void addAufgabenBearbeitung(AufgabenBearbeitung ab) {
        this.aufgabenBearbeitungen.put(ab.generateKey(), ab);
    }

    public Integer getAufgabenNr() {
        return aufgabenNr;
    }
}
