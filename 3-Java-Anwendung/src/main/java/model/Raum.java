package model;

import model.klausur.Klausur;
import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "raum")
public class Raum {

    @Id
    @GeneratedValue
    private Integer id;
    @NaturalId
    private String bezeichnung;

    @OneToMany
    private Map<Integer, Mitarbeiter> mitarbeiterMap;
    @ManyToMany
    private Map<Integer, Klausur> klausurMap;
    @OneToMany(mappedBy = "raumId")
    private Map<Integer, VeranstaltungAbhaltung> abhaltungMap;

    public Raum() {
    }
}
