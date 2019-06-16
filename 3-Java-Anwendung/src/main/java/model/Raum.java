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
    @GeneratedValue
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
}
