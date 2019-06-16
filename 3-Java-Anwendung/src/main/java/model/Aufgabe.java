package model;

import model.klausur.Klausur;
import model.relationen.AufgabenBearbeitung;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "aufgabe")
public class Aufgabe {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer rang;
    private Double maxPunkte;
    @OneToOne
    private Klausur klausur;
    @Transient
    private Collection<AufgabenBearbeitung> aufgabenBearbeitungen;

    public Aufgabe() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Double getMaxPunkte() {
        return maxPunkte;
    }

    public void setMaxPunkte(Double maxPunkte) {
        this.maxPunkte = maxPunkte;
    }

    public Klausur getKlausur() {
        return klausur;
    }

    public void setKlausur(Klausur klausur) {
        this.klausur = klausur;
    }

    public Collection<AufgabenBearbeitung> getAufgabenBearbeitungen() {
        return aufgabenBearbeitungen;
    }

    public void setAufgabenBearbeitungen(Collection<AufgabenBearbeitung> aufgabenBearbeitungen) {
        this.aufgabenBearbeitungen = aufgabenBearbeitungen;
    }
}
