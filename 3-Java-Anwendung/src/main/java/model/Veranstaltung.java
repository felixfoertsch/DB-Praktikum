package model;

import javax.persistence.*;
import java.time.Year;
import java.util.Collection;
import java.util.Map;

@MappedSuperclass
@Table(name = "veranstaltung")
abstract public class Veranstaltung {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;

    @Transient
    private Map<String, VeranstaltungAbhaltung> abhaltungMap;
    @Transient
    private Collection<Mitarbeiter> betreutVonMitarbeiter;

    public Veranstaltung() {
    }

}
