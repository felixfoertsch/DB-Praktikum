package model;

import javax.persistence.*;
import java.time.Year;
import java.util.Collection;
import java.util.Map;

@Entity
@Table(name = "veranstaltung")
@Inheritance(strategy = InheritanceType.JOINED)
public class Veranstaltung {

    @Id
    private Integer id;

    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;

    private Map<String, VeranstaltungAbhaltung> abhaltungMap;
    private Collection<Mitarbeiter> betreutVonMitarbeiter;

    public Veranstaltung() {
    }

}
