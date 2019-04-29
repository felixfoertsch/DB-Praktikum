package model;

public class Aufgabe {
    Integer id;
    Integer klausurId;
    Integer rang;
    Integer aufgabenNr;
    Double maxPunkte;

    public Aufgabe(String aufgabenNr, String maxPunkte) {
        this.aufgabenNr = Integer.valueOf(aufgabenNr);
        this.maxPunkte = Double.valueOf(maxPunkte);
    }
}
