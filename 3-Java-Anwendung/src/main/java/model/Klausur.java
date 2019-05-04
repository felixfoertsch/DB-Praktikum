package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Entity
public class Klausur {

    @Id
    private Integer id;
    private Integer spezialvorlesungId;
    private Integer grundvorlesungId;
    private String name;
    private LocalDate datum;
    private LocalTime uhrzeitVon;
    private Double gesamtpunktzahl;
    private Double punktzahl100;
    private String vaKennung;
    private String typ;
    private String klausurNr;
    private Collection<Mitarbeiter> aufsichten;
    private Collection<Raum> orte;

    @MapKey(name = "id")
    private Map<Integer, Aufgabe> aufgaben;

    @OneToMany(mappedBy = "klausur")
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;

    public void setData(String name, String datum, String uhrzeitVon, String gesamtpunktzahl, String punktzahl100, String vaKennung, String klausurNr, Collection<Mitarbeiter> aufsichten, Collection<Raum> orte){
        this.aufgaben = new HashMap<>();
        this.klausurTeilnahmen = new HashMap<>();
        this.name = name;
        this.datum = LocalDate.parse(datum, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN));
        this.uhrzeitVon = LocalTime.parse(uhrzeitVon);
        this.gesamtpunktzahl = Double.valueOf(gesamtpunktzahl);
        this.punktzahl100 = Double.valueOf(punktzahl100);
        this.vaKennung = vaKennung;
        this.klausurNr = klausurNr;
        this.aufsichten = aufsichten;
        this.orte = orte;
    }

    public void setKlausurNr(String klausurNr) {
        this.klausurNr = klausurNr;
    }

    public void setTyp(String typ) {
        this.typ = typ.toLowerCase();
    }

    public void addAufgabe(Aufgabe aufgabe) {
        aufgaben.put(aufgabe.getAufgabenNr(), aufgabe);
    }

    public void addKlausurTeilnahme(KlausurTeilnahme kt) {
        this.klausurTeilnahmen.put(kt.generateKey(), kt);
    }

    public String generateKey() {
        return klausurNr;
    }

    public Aufgabe getAufgabeByIndex(Integer index) {
        return aufgaben.get(index);
    }

    public String getTyp() {
        return typ;
    }

    public String getKlausurNr() {
        return klausurNr;
    }
}
