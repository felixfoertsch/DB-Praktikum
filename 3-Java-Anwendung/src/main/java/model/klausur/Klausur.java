package model.klausur;

import model.*;
import model.person.Mitarbeiter;
import model.relationen.KlausurTeilnahme;
import model.veranstaltung.Grundvorlesung;
import model.veranstaltung.Spezialvorlesung;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;

@Entity
@Table(name = "klausur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Klausur {

    @Id
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
    private Map<Integer, Aufgabe> aufgaben;
    @OneToMany
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;

    public Klausur() {
    }


}
