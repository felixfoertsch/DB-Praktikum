package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Entity
@Table(name = "klausur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Klausur {

    @Id
    private Integer id;

    // Properties
    private LocalDate datum;
    private LocalTime uhrzeitVon;
    private Double gesamtpunktzahl;

    // Foreign key, Vererbung
    private String typ;

    // Nicht im Schema
    @Transient
    private String name;
    @Transient
    private Double punktzahl100;
    @Transient
    private String vaKennung;
    @Transient
    private String klausurNr;

    private Spezialvorlesung spezialvorlesung;
    private Grundvorlesung grundvorlesung;
    private Collection<Mitarbeiter> aufsichten;
    private Collection<Raum> orte;

    @MapKey(name = "id")
    private Map<Integer, Aufgabe> aufgaben;

    @OneToMany(mappedBy = "klausur")
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;

    public Klausur() {
    }


}
