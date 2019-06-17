package model;

import model.klausur.Klausur;
import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "raum")
public class Raum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    private String bezeichnung;

    @OneToMany
    private Collection<Mitarbeiter> mitarbeiter;
    @ManyToMany
    private Collection<Klausur> klausuren;
    @OneToMany
    private Collection<VeranstaltungAbhaltung> abhaltungen;

    public Raum() {
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

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Collection<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Collection<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public Collection<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(Collection<Klausur> klausuren) {
        this.klausuren = klausuren;
    }

    public Collection<VeranstaltungAbhaltung> getAbhaltungen() {
        return abhaltungen;
    }

    public void setAbhaltungen(Collection<VeranstaltungAbhaltung> abhaltungen) {
        this.abhaltungen = abhaltungen;
    }
}
