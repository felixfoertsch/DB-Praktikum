package model.person;

import model.Raum;
import model.klausur.Klausur;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter {

    @Id
    @GeneratedValue
    private Integer id;
    private String vorname;
    private String nachname;
    private String email;

    @OneToOne
    private Raum raum;
    @OneToMany
    private Collection<Klausur> klausuren;

    public Mitarbeiter() {
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

    public Collection<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(Collection<Klausur> klausuren) {
        this.klausuren = klausuren;
    }
}
