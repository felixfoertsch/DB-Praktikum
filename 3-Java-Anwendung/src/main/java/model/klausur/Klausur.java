package model.klausur;

import model.relationen.KlausurTeilnahme;

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

    @OneToMany(mappedBy = "klausur")
    private List<KlausurTeilnahme> klausurTeilnahmen;

//    @ManyToOne
//    private Spezialvorlesung spezialvorlesung;
//    @ManyToOne
//    private Grundvorlesung grundvorlesung;
//    @OneToMany
//    private ArrayList<Mitarbeiter> aufsichten;
//    @ManyToMany
//    private Collection<Raum> orte;
//    @OneToMany
//    private Collection<Aufgabe> aufgaben;


    public Klausur() {
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

}
