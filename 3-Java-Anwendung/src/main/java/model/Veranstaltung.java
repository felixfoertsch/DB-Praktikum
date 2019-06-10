package model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "veranstaltung")
@Inheritance(strategy = InheritanceType.JOINED)
public class Veranstaltung {

    @Id
    private Integer id;

    //In Schema
    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;

    //Change to abhaltungMap, or change to Collection
    private Map<String, VeranstaltungAbhaltung> abhaltungMap;
    //Table betreut
    private Collection<Mitarbeiter> dozenten;

    //@Transient makes Hibernate not map the field
    @Transient
    private String kennung;

    //Vererbung
    @Transient
    private String typ;

    public Veranstaltung() {
    }

}
