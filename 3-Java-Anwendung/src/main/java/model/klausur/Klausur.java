package model.klausur;

import model.Aufgabe;
import model.Raum;
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

    // Explicit Relation with additional data: see KlausurTeilnahme class.
    @OneToMany(mappedBy = "klausur")
    private List<KlausurTeilnahme> klausurTeilnahmen;

    // Explicit Relation with additional data: see AufgabenBearbeitung class.
    @OneToMany(mappedBy = "klausur")
    private List<Aufgabe> aufgaben;

    // Look into the Raum class for the mapping instructions.
    @ManyToMany(mappedBy = "klausuren")
    private List<Raum> raum;

//    @ManyToOne
//    private Spezialvorlesung spezialvorlesung;
//    @ManyToOne
//    private Grundvorlesung grundvorlesung;
//    @OneToMany
//    private ArrayList<Mitarbeiter> aufsichten;



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
}
