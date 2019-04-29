package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Klausur {
    private Integer id;
    private Integer spezialvorlesungId;
    private Integer grundvorlesungId;
    private String name;
    private LocalDate datum;
    private LocalTime uhrzeitVon;
    private Double gesamtpunktzahl;
    private Double punktzahl100;
    private String vaKennung;
    String typ;
    String klausurNr;
    private Collection<Mitarbeiter> aufsichten;
    private Collection<Raum> orte;
    private Map<Integer, Aufgabe> aufgaben;
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

    public void setTyp(String typ) {
        this.typ = typ.toLowerCase();
    }

    public void addAufgabe(Aufgabe aufgabe) {
        aufgaben.put(aufgabe.aufgabenNr, aufgabe);
    }

    public void addKlausurTeilnahme(KlausurTeilnahme kt) {
        this.klausurTeilnahmen.put(kt.generateKey(), kt);
    }

    public String generateKey() {
        return klausurNr;
    }
}
