package model.klausur;

import model.Aufgabe;
import model.Raum;
import model.person.Mitarbeiter;
import model.relationen.KlausurTeilnahme;
import model.veranstaltung.Grundvorlesung;
import model.veranstaltung.Spezialvorlesung;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Table(name = "klausur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Klausur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate datum;
    private LocalTime uhrzeitVon;
    private Double gesamtpunktzahl;

    @ManyToOne
    private Spezialvorlesung spezialvorlesung;
    @ManyToOne
    private Grundvorlesung grundvorlesung;
    @OneToMany
    private Collection<Mitarbeiter> aufsichten;
    @ManyToMany
    private Collection<Raum> orte;
    @OneToMany
    private Collection<Aufgabe> aufgaben;
    @OneToMany
    private Collection<KlausurTeilnahme> klausurTeilnahmen;

    public Klausur() {
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

    public Collection<Mitarbeiter> getAufsichten() {
        return aufsichten;
    }

    public void setAufsichten(Collection<Mitarbeiter> aufsichten) {
        this.aufsichten = aufsichten;
    }

    public Collection<Raum> getOrte() {
        return orte;
    }

    public void setOrte(Collection<Raum> orte) {
        this.orte = orte;
    }

    public Collection<Aufgabe> getAufgaben() {
        return aufgaben;
    }

    public void setAufgaben(Collection<Aufgabe> aufgaben) {
        this.aufgaben = aufgaben;
    }

    public Collection<KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public void setKlausurTeilnahmen(Collection<KlausurTeilnahme> klausurTeilnahmen) {
        this.klausurTeilnahmen = klausurTeilnahmen;
    }
}
