package model;

import model.klausur.Klausur;
import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "raum")
public class Raum implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    private String bezeichnung;

    // The ManyToMany mapping in the table ort is defined here, since there
    // is no associated data to this relation: this is the owner of the relation.
    // The target mapping in Klausur references this construct via "mappedBy",
    // that's why both columns have to be identified here.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ort", joinColumns = {
            @JoinColumn(name = "raum_id")}, inverseJoinColumns = {
            @JoinColumn(name = "klausur_id")})
    private List<Klausur> klausuren = new ArrayList<>();

    @OneToMany(mappedBy = "raum")
    private List<Mitarbeiter> mitarbeiter;

    @OneToMany(mappedBy = "raum")
    private List<VeranstaltungAbhaltung> abhaltungen;

    protected Raum() {
    }

    public Raum(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Raum raum = (Raum) o;
        return Objects.equals(this.id, raum.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    /***********************************************************************************************
     *
     * Helpers
     *
     */

    public void addKlausur(Klausur klausur) {
        klausuren.add(klausur);
        klausur.getRaum().add(this);
    }

    public void removeKlausur(Klausur klausur) {
        klausuren.remove(this);
        klausur.getRaum().remove(this);
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

    public List<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(List<Klausur> klausuren) {
        this.klausuren = klausuren;
    }

    public List<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(List<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
}
