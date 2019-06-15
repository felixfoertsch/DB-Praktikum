package model;

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
    private Map<String, Mitarbeiter> mitarbeiterMap;
    @ManyToMany
    private Map<String, Klausur> klausurMap;
    @OneToMany
    private Map<String, VeranstaltungAbhaltung> abhaltungMap;

    public Raum() {
    }
}
