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
        this.dozenten = new ArrayList<>();
    }

    public void setData(
            String name,
            String jahr,
            String semester,
            Raum raum,
            String maxTeilnehmer,
            String zeit,
            String tag,
            String kennung) {
        this.name = name;
        this.jahr = Year.parse(jahr);
        this.semester = semester;
        this.abhaltungMap = new HashMap<>();
        this.abhaltungMap.put("", new VeranstaltungAbhaltung(parseTime(zeit), convertGerWeekDay(tag), raum));
        this.maxTeilnehmer = Integer.valueOf(maxTeilnehmer);
        this.kennung = kennung;
    }

    public void addDozent(Mitarbeiter mitarbeiter) {
        this.dozenten.add(mitarbeiter);
    }

    public String getKennung() {
        return kennung;
    }

    public String generateKey() {
        return kennung;
    }

    public String getName() {
        return name;
    }

    private LocalTime parseTime(String zeit) {
        if (zeit.equals("NULL")) {
            return null;
        }
        return LocalTime.parse(zeit);
    }
    private DayOfWeek convertGerWeekDay(String tag) {
        DayOfWeek day;
        switch (tag) {
            case "Montag": day = DayOfWeek.MONDAY; break;
            case "Dienstag": day = DayOfWeek.TUESDAY; break;
            case "Mittwoch": day = DayOfWeek.WEDNESDAY; break;
            case "Donnerstag": day = DayOfWeek.THURSDAY; break;
            case "Freitag": day = DayOfWeek.FRIDAY; break;
            case "Samstag": day = DayOfWeek.SATURDAY; break;
            case "Sonntag": day = DayOfWeek.SUNDAY; break;
            default: day = null;
        }
        return day;
    }

}
