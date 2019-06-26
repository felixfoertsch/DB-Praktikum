package model;

import model.klausur.Klausur;
import model.relationen.AufgabenBearbeitung;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aufgabe")
public class Aufgabe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rang;
    private Double maxPunkte;

    @ManyToOne
    private Klausur klausur;

    // Explicit Relation with additional data: see AufgabenBearbeitung class.
    @OneToMany(mappedBy = "aufgabe")
    private List<AufgabenBearbeitung> aufgabenBearbeitungen;

    public Aufgabe() {
    }

    public Aufgabe(Integer rang, Double maxPunkte, Klausur klausur) {
        this.rang = rang;
        this.maxPunkte = maxPunkte;
        this.klausur = klausur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aufgabe aufgabe = (Aufgabe) o;
        return Objects.equals(this.id, aufgabe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
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

}
