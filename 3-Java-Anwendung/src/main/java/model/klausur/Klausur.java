package model.klausur;

import model.Aufgabe;
import model.Raum;
import model.person.Mitarbeiter;
import model.relationen.KlausurTeilnahme;
import model.veranstaltung.Grundvorlesung;
import model.veranstaltung.Spezialvorlesung;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "klausur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Klausur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate datum;
    private LocalTime uhrzeitVon;
    private Double gesamtpunktzahl;

    // Explicit Relation with additional data: see KlausurTeilnahme class.
    @OneToMany(mappedBy = "klausur")
    private List<KlausurTeilnahme> klausurTeilnahmen;

    // Explicit Relation with additional data: see Aufgabe class.
    @OneToMany(mappedBy = "klausur")
    private List<Aufgabe> aufgaben;

    // Look into the Raum class for the mapping instructions.
    @ManyToMany(mappedBy = "klausuren")
    private List<Raum> raum;

    // Look into the Mitarbeiter class for the mapping instructions.
    @ManyToMany(mappedBy = "klausuren")
    private List<Mitarbeiter> aufsichten;

    @ManyToOne
    @JoinColumn(
            name = "spezialvorlesung_id",
            foreignKey = @ForeignKey(name = "veranstaltung_id")
    )
    private Spezialvorlesung spezialvorlesung;

    @ManyToOne
    @JoinColumn(
            name = "grundvorlesung_id",
            foreignKey = @ForeignKey(name = "veranstaltung_id")
    )
    private Grundvorlesung grundvorlesung;

    public Klausur() {
    }

    public Klausur(LocalDate datum, LocalTime uhrzeitVon, Double gesamtpunktzahl) {
        this.datum = datum;
        this.uhrzeitVon = uhrzeitVon;
        this.gesamtpunktzahl = gesamtpunktzahl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Klausur klausur = (Klausur) o;
        return Objects.equals(this.id, klausur.id);
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getUhrzeitVon() {
        return uhrzeitVon;
    }

    public void setUhrzeitVon(LocalTime uhrzeitVon) {
        this.uhrzeitVon = uhrzeitVon;
    }

    public Double getGesamtpunktzahl() {
        return gesamtpunktzahl;
    }

    public void setGesamtpunktzahl(Double gesamtpunktzahl) {
        this.gesamtpunktzahl = gesamtpunktzahl;
    }

    public List<Aufgabe> getAufgaben() {
        return aufgaben;
    }

    public void setAufgaben(List<Aufgabe> aufgaben) {
        this.aufgaben = aufgaben;
    }

    public List<Raum> getRaum() {
        return raum;
    }

    public void setRaum(List<Raum> raeume) {
        this.raum = raeume;
    }

    public List<Mitarbeiter> getAufsichten() {
        return aufsichten;
    }

    public void setAufsichten(List<Mitarbeiter> aufsichten) {
        this.aufsichten = aufsichten;
    }

    public List<KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public void setKlausurTeilnahmen(List<KlausurTeilnahme> klausurTeilnahmen) {
        this.klausurTeilnahmen = klausurTeilnahmen;
    }

    public Spezialvorlesung getSpezialvorlesung() {
        return spezialvorlesung;
    }

    public void setSpezialvorlesung(Spezialvorlesung spezialvorlesung) {
        this.spezialvorlesung = spezialvorlesung;
    }

    public Grundvorlesung getGrundvorlesung() {
        return grundvorlesung;
    }

    public void setGrundvorlesung(Grundvorlesung grundvorlesung) {
        this.grundvorlesung = grundvorlesung;
    }
}
