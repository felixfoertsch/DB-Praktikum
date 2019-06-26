package model.person;

import model.Raum;
import model.klausur.Klausur;
import model.veranstaltung.Veranstaltung;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String vorname;
    private String nachname;
    private String email;

    @ManyToOne
    private Raum raum;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "aufsicht", joinColumns = {
            @JoinColumn(name = "mitarbeiter_id")}, inverseJoinColumns = {
            @JoinColumn(name = "klausur_id")})
    private List<Klausur> klausuren;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "betreut", joinColumns = {
            @JoinColumn(name = "mitarbeiter_id")}, inverseJoinColumns = {
            @JoinColumn(name = "veranstaltung_id")})
    private List<Veranstaltung> veranstaltungen;

    public Mitarbeiter() {
    }

    public Mitarbeiter(String vorname, String nachname, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mitarbeiter mitarbeiter = (Mitarbeiter) o;
        return Objects.equals(this.id, mitarbeiter.id);
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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Raum getRaum() {
        return raum;
    }

    public void setRaum(Raum raum) {
        this.raum = raum;
    }

    public List<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(List<Klausur> klausuren) {
        this.klausuren = klausuren;
    }

    public List<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public void setVeranstaltungen(List<Veranstaltung> veranstaltungen) {
        this.veranstaltungen = veranstaltungen;
    }
}
