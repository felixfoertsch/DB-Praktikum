package model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "aufgabe")
public class Aufgabe {

    @Id
    private Integer id;
    private Integer klausurId;
    private Integer rang;

    //Remove if not needed
    private String klausurNr;

    private Integer aufgabenNr;
    private Double maxPunkte;

    @ManyToOne
    @JoinColumn(name = "klausurId")
    private Klausur klausur;

    @OneToMany(mappedBy = "aufgabenBearbeitung")
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

    public void setKlausurNr(String klausurNr) {
        this.klausurNr = klausurNr;
    }

    public Integer getAufgabenNr() {
        return aufgabenNr;
    }
}
