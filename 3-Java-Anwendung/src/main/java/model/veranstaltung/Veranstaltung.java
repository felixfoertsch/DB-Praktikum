package model.veranstaltung;

import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;

import javax.persistence.*;
import java.time.Year;
import java.util.Collection;
import java.util.Map;

@Entity
@Table(name = "veranstaltung")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Veranstaltung {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;

    @OneToMany
    private Map<Integer, VeranstaltungAbhaltung> abhaltungMap;
    @OneToMany
    private Collection<Mitarbeiter> betreutVonMitarbeiter;

    public Veranstaltung() {
    }

}
